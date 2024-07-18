/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.params;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.System.getProperty;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Process additional JVM parameters in wrapper.conf file and write required information to wrapper-additional.conf
 */
public class AdditionalJvmParameters {

  private static final String JAVA_8_VERSION = "1.8";
  private static final String JAVA_11_VERSION = "11";
  private static final String JAVA_RUNNING_VERSION = "java.specification.version";
  private static final Pattern BOOTSTRAP_LEGACY_MODE_PATTERN = compile("^(-M)?-Dbootstrap\\.legacy\\.mode(?<value>=.*)?$");

  protected static String jpdaOpts = "";
  protected static int paramIndex = -1;
  protected static int classpathIndex = 0;
  static final String wrapperPrefix = "wrapper.java.additional.";
  static final String classpathPrefix = "wrapper.java.classpath.";
  static final int DEFAULT_NUMBER_OF_ADDITIONAL_JAVA_ARGUMENTS = 0;

  public static void main(String[] args) throws IOException {
    File wrapperConfigFile = new File(args[0]);
    jpdaOpts = args[1];

    // extracting wrapper conf directory
    String wrapperConfDir = extractWrapperConfDir(wrapperConfigFile);

    // create wrapper-additional.conf
    File wrapperAdditionalConfFile = new File(wrapperConfDir + "wrapper-additional.conf");
    FileWriter writer = new FileWriter(wrapperAdditionalConfFile);
    writeHeader(writer);

    List<String> arguments = asList(args);
    boolean debugEnabled = isDebugEnabled(arguments);
    boolean adHocOptionsAvailable = isOptionAvailable(arguments, "-M");
    boolean wrapperOptionsAvailable = isOptionAvailable(arguments, "-W");

    BufferedReader reader = new BufferedReader(new FileReader(wrapperConfigFile));
    // looking for maximum number of wrapper.java.additional property
    String line = reader.readLine();
    while (line != null) {
      paramIndex = findWrapperAdditionalProperties(line);
      classpathIndex = findWrapperClassPathEntries(line);
      line = reader.readLine();
    }
    reader.close();

    if (debugEnabled || adHocOptionsAvailable) {
      paramIndex += getNumberOfAdditionalJavaProperties(args);
      if (debugEnabled) {
        writeJpdaOpts(writer);
      }
      if (adHocOptionsAvailable) {
        writeAdHocProps(args, writer);
      }
      if (wrapperOptionsAvailable) {
        writeWrapperProps(args, writer);
      }
    }

    File wrapperLicenseConfFile;
    Properties bootstrapProps = new Properties();
    // Do not use commons-lang3 to avoid having to add that jar to lib/boot
    if (isJava8() || (isJava11() && useBootstrapLegacyMode(wrapperConfigFile, adHocOptionsAvailable, args))) {
      bootstrapProps.load(new FileInputStream(wrapperConfDir + "java8/wrapper.jvmDependant.conf"));
      wrapperLicenseConfFile = new File(wrapperConfDir + "java8/wrapper-license.conf");
    } else {
      bootstrapProps.load(new FileInputStream(wrapperConfDir + "java11-plus/wrapper.jvmDependant.conf"));
      wrapperLicenseConfFile = new File(wrapperConfDir + "java11-plus/wrapper-license.conf");
    }

    processBootstrapProperties(bootstrapProps, writer);

    if (wrapperLicenseConfFile.exists()) {
      Files.copy(wrapperLicenseConfFile.toPath(), new File(wrapperConfDir + "wrapper-license.conf").toPath(), REPLACE_EXISTING);
    }

    writer.close();
  }

  private static boolean useBootstrapLegacyMode(File wrapperConfigFile, boolean adHocOptionsAvailable, String[] args)
      throws IOException {
    Properties wrapperConfProps = new Properties();
    wrapperConfProps.load(new FileInputStream(wrapperConfigFile));

    boolean isUseBootstrapLegacyModeConf = isUseBootstrapLegacyMode(wrapperConfProps.values().stream()
        .filter(v -> v instanceof String).map(v -> (String) v).collect(toList()));

    if (!isUseBootstrapLegacyModeConf && adHocOptionsAvailable) {
      return isUseBootstrapLegacyMode(getAdHocArgs(args));
    }

    return isUseBootstrapLegacyModeConf;
  }

  private static boolean isUseBootstrapLegacyMode(Collection<String> args) {
    for (String arg : args) {
      Matcher matcher = BOOTSTRAP_LEGACY_MODE_PATTERN.matcher(arg);
      if (matcher.matches()) {
        String value = matcher.group("value");
        return value == null || value.equals("=true");
      }
    }

    return false;
  }

  private static List<String> getAdHocArgs(String[] args) {
    return getArgsWithPrefix(args, "-M");
  }

  private static List<String> getWrapperProps(String[] args) {
    return getArgsWithPrefix(args, "-W");
  }

  private static List<String> getArgsWithPrefix(String[] args, String prefix) {
    return stream(args).filter(arg -> arg.startsWith(prefix)).collect(toList());
  }

  private static boolean isJava8() {
    return getJavaVersion().startsWith(JAVA_8_VERSION);
  }

  private static boolean isJava11() {
    return getJavaVersion().startsWith(JAVA_11_VERSION);
  }

  private static String getJavaVersion() {
    return getProperty(JAVA_RUNNING_VERSION);
  }

  /**
   * Find the directory where wrapper.conf file is stored
   * 
   * @param wrapperConfigFile
   * @return directory
   */
  protected static String extractWrapperConfDir(File wrapperConfigFile) {
    Pattern pattern = compile("^.*[\\\\/]");
    String path = wrapperConfigFile.getPath();
    Matcher matcher = pattern.matcher(path);
    matcher.find();
    return matcher.group(0);
  }

  /**
   * Set default header unconditionally
   * 
   * @param writer
   * @throws IOException
   */
  protected static void writeHeader(FileWriter writer) throws IOException {
    writer.write("#encoding=UTF-8\n");
    writer.write("# Do not edit this file!\n");
    writer.write("# This is a generated file to add additional parameters to JVM and Wrapper\n");
    writer.flush();
  }

  /**
   * Check if there is an element related to debug in the arguments
   * 
   * @param arguments
   * @return
   */
  protected static boolean isDebugEnabled(List<String> arguments) {
    return arguments.stream().anyMatch("-debug"::equalsIgnoreCase);
  }

  /**
   * Check if there is an option related to the prefix provided in the arguments
   * 
   * @param arguments
   * @param s         prefix provided, '-M' for adhoc, '-W' for wrapper options
   * @return
   */
  protected static boolean isOptionAvailable(List<String> arguments, String s) {
    return arguments.stream().anyMatch(arg -> arg.startsWith(s));
  }

  /**
   * Find additional wrapper properties that start with 'wrapper.java.additional.-'
   * 
   * @param line a single line from the wrapper.conf file
   * @return paramIndex that takes the number of additional properties into account
   */
  protected static int findWrapperAdditionalProperties(String line) {
    Pattern prefixPattern = compile("^\\s*wrapper\\.java\\.additional\\..+");
    Matcher prefixMatcher = prefixPattern.matcher(line);

    if (prefixMatcher.find()) {
      Pattern prefixWithNumPattern = compile("^\\s*wrapper\\.java\\.additional\\.(\\d+).+");
      Matcher prefixWithNumMatcher = prefixWithNumPattern.matcher(line);
      prefixWithNumMatcher.find();
      return max(parseInt(prefixWithNumMatcher.group(1)), paramIndex);
    }
    return paramIndex;
  }

  /**
   * Find additional wrapper classpath entries that start with 'wrapper.java.classpath.-'
   * 
   * @param line a single line from the wrapper.conf file
   * @return paramIndex that takes the number of additional classpath entries into account
   */
  protected static int findWrapperClassPathEntries(String line) {
    Pattern prefixPattern = compile("^\\s*wrapper\\.java\\.classpath\\..+");
    Matcher prefixMatcher = prefixPattern.matcher(line);

    if (prefixMatcher.find()) {
      Pattern prefixWithNumPattern = compile("^\\s*wrapper\\.java\\.classpath\\.(\\d+).+");
      Matcher prefixWithNumMatcher = prefixWithNumPattern.matcher(line);
      prefixWithNumMatcher.find();
      return max(parseInt(prefixWithNumMatcher.group(1)), classpathIndex);
    }
    return classpathIndex;
  }

  /**
   * Check if the number of additional java properties is specified, using '-additionalJavaProperties='
   * 
   * @param args
   * @return paramIndex that takes the number of additional properties into account
   */
  protected static int getNumberOfAdditionalJavaProperties(String[] args) {
    boolean propertyExists = false;
    String propertyPrefix = "-additionalJavaProperties=(\\d+)+";
    // Finds the item in the list
    String additionalJavaArgument = "";
    for (String arg : args) {
      if (arg.matches(propertyPrefix)) {
        propertyExists = true;
        additionalJavaArgument = arg;
      }
    }
    if (propertyExists) {
      Pattern argPattern = compile(propertyPrefix);
      Matcher additionalJavaArgMatcher = argPattern.matcher(additionalJavaArgument);
      additionalJavaArgMatcher.find();

      return parseInt(additionalJavaArgMatcher.group(1));
    }
    return DEFAULT_NUMBER_OF_ADDITIONAL_JAVA_ARGUMENTS;
  }

  /**
   * Write Jpda option related lines to wrapper-additional.conf
   * 
   * @param writer
   * @throws IOException
   */
  protected static void writeJpdaOpts(FileWriter writer) throws IOException {
    List<String> jvmArgs = asList(jpdaOpts.split("\\s-"));
    for (String arg : jvmArgs) {
      paramIndex++;
      writer.write(wrapperPrefix + paramIndex + "=-" + arg.replaceFirst("^-", "") + "\n");
      writer.flush();
    }
  }

  /**
   * Write adhoc option related lines to wrapper-additional.conf
   * 
   * @param args   arguments provided
   * @param writer
   * @throws IOException
   */
  protected static void writeAdHocProps(String[] args, FileWriter writer) throws IOException {
    List<String> adHocArgs = getAdHocArgs(args);
    for (String arg : adHocArgs) {
      paramIndex++;
      writer.write(wrapperPrefix + paramIndex + "=\"" + arg.replaceFirst("^-M", "") + "\"\n");
      writer.write(wrapperPrefix + paramIndex + ".stripquotes=TRUE\n");
      writer.flush();
    }

  }

  /**
   * Write wrapper option related lines to wrapper-addition.conf
   * 
   * @param args   arguments provided
   * @param writer
   * @throws IOException
   */
  protected static void writeWrapperProps(String[] args, FileWriter writer) throws IOException {
    List<String> wrapperArgs = getWrapperProps(args);
    for (String arg : wrapperArgs) {
      writer.write(arg.replaceFirst("^-W", "") + "\n");
      writer.flush();
    }
  }

  protected static void processBootstrapProperties(Properties bootstrapProperties, Writer writer) throws IOException {
    Pattern additionalPattern = compile("wrapper\\.java\\.additional\\.(<n\\d>)(\\.\\w+)?");
    // Correspond <n> component number with the index in order to re-use the same index for the same component number
    Map<String, Integer> nComponentIndexMap = new HashMap<>();

    for (Entry entry : bootstrapProperties.entrySet()) {
      Matcher additionalMatcher = additionalPattern.matcher(entry.getKey().toString());
      if (additionalMatcher.matches()) {
        String wrapperSuffix = additionalMatcher.group(2) != null ? additionalMatcher.group(2) : "";
        int index = nComponentIndexMap.computeIfAbsent(additionalMatcher.group(1), k -> ++paramIndex);

        writer.write(wrapperPrefix + index + wrapperSuffix + "=" + entry.getValue().toString() + "\n");
      } else if (entry.getKey().toString().matches("wrapper\\.java\\.classpath\\.<n\\d>")) {
        writer.write(classpathPrefix + ++classpathIndex + "=" + entry.getValue().toString() + "\n");
      } else {
        writer.write(entry.getKey() + "=" + entry.getValue().toString() + "\n");
      }
    }

    writer.flush();
  }
}
