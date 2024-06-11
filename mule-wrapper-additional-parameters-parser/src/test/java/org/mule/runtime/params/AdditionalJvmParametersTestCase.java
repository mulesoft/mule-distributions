/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.params;

import static java.lang.System.getProperty;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Files.newTemporaryFile;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runners.MethodSorters;

/**
 * Tests AdditionalJvmParameter class to see if it can replace Additional. groovy successfully
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdditionalJvmParametersTestCase {

  // TODO W-12266734: refactor for making additional properties parser more testable.
  private final String[] args = new String[6];
  private List<String> arguments = new ArrayList<>();
  private String jpdaOpts = "";
  private File file;
  private FileWriter fileWriter;
  AdditionalJvmParameters additionalJvmParameters = new AdditionalJvmParameters();

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Before
  public void setUp() throws Exception {
    args[0] = "conf/wrapper.conf";
    args[1] = "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005";
    args[2] = "-M-Dmule.blabla=true";
    args[3] = "-W-Dmule.anotherone=false";
    args[4] = "-additionalJavaProperties=40";
    args[5] = "-debug";

    arguments = asList(args);
    jpdaOpts = args[1];

    file = temporaryFolder.newFolder("folder").toPath().resolve("file.txt").toFile();
    fileWriter = new FileWriter(file);
  }

  @Test
  public void testExtractWrapperConfDir() {
    File wrapperConfigFile = newTemporaryFile();
    String wrapperConfDir = additionalJvmParameters.extractWrapperConfDir(wrapperConfigFile);
    assertThat(wrapperConfDir, is(wrapperConfigFile.getParentFile().getAbsolutePath() + getProperty("file.separator")));
  }

  @Test
  public void testWriteHeader() throws IOException {
    additionalJvmParameters.writeHeader(fileWriter);
    StringBuilder sb = new StringBuilder();
    sb.append("#encoding=UTF-8\n");
    sb.append("# Do not edit this file!\n");
    sb.append("# This is a generated file to add additional parameters to JVM and Wrapper\n");
    assertThat(file).hasContent(sb.toString());
  }

  @Test
  public void testIsDebugEnabled() {
    boolean debugEnabled = additionalJvmParameters.isDebugEnabled(arguments);
    assertThat(debugEnabled, is(true));
  }

  @Test
  public void testIsOptionAvailable() {
    boolean adHocOptionsAvailable = additionalJvmParameters.isOptionAvailable(arguments, "-M");
    assertThat(adHocOptionsAvailable, is(true));
  }


  @Test
  public void testFindWrapperAdditionalPropertiesWithoutProperty() {
    int oldParamIndex = additionalJvmParameters.paramIndex;
    int indexWithoutAdditionalProperty = additionalJvmParameters.findWrapperAdditionalProperties("xx");
    assertThat(indexWithoutAdditionalProperty, is(oldParamIndex));
  }

  @Test
  public void testFindWrapperAdditionalPropertiesWithProperty() {
    int indexWithAdditionalProperty =
        additionalJvmParameters.findWrapperAdditionalProperties("wrapper.java.additional.100=-Dmule.home=\"%MULE_HOME%\"");
    assertThat(indexWithAdditionalProperty, is(100));
  }

  @Test
  public void testGetNumberOfAdditionalJavaProperties() {
    int indexWithAdditionalProperties = additionalJvmParameters.getNumberOfAdditionalJavaProperties(args);
    assertThat(indexWithAdditionalProperties, is(41));
    args[4] = "";
    int indexWithoutAdditionalProperties = additionalJvmParameters.getNumberOfAdditionalJavaProperties(args);
    assertThat(indexWithoutAdditionalProperties, is(21));
    args[4] = "-additionalJavaProperties=40";
  }

  @Test
  public void test1WriteJpdaOptions() throws IOException {
    additionalJvmParameters.jpdaOpts = args[1];
    additionalJvmParameters.writeJpdaOpts(fileWriter);
    StringBuilder sb = new StringBuilder();
    sb.append("wrapper.java.additional.0=-Xdebug\n");
    sb.append("wrapper.java.additional.1=-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005");
    assertThat(file).hasContent(sb.toString());
  }

  @Test
  public void test2WriteAdHocProperties() throws IOException {
    additionalJvmParameters.writeAdHocProps(args, fileWriter);
    StringBuilder sb = new StringBuilder();
    sb.append("wrapper.java.additional.2=\"-Dmule.blabla=true\"\n");
    sb.append("wrapper.java.additional.2.stripquotes=TRUE");
    assertThat(file).hasContent(sb.toString());
  }

  @Test
  public void test3WriteWrapperProperties() throws IOException {
    additionalJvmParameters.writeWrapperProps(args, fileWriter);
    assertThat(file).hasContent("-Dmule.anotherone=false");
  }

  /**
   * Write additional property after 3 tests(test1WriteJpdaOptions(), test2WriteAdHocProperties(), test3WriteWrapperProperties())
   * and check if the index given is correct.
   * 
   * @throws IOException
   */
  @Test
  public void test4WriteAnotherProperty() throws IOException {
    String[] newArgs = new String[1];
    newArgs[0] = "-M-Dmule.newBlabla=true";
    additionalJvmParameters.writeAdHocProps(newArgs, fileWriter);

    StringBuilder sb = new StringBuilder();
    sb.append("wrapper.java.additional.3=\"-Dmule.newBlabla=true\"\n");
    sb.append("wrapper.java.additional.3.stripquotes=TRUE");
    assertThat(file).hasContent(sb.toString());
  }

  /**
   * Tests AdditionalJvmParameters class. New wrapper-additional.conf file will be created with starting index 59. (Index in
   * wrapper.conf ends with 18 and -additionalJavaProperties=40 will add 40. 40+18=58.)
   * 
   * @throws IOException
   */
  @Test
  public void testAdditionalJvmParameters() throws IOException {
    // extracting wrapper conf directory
    File wrapperConfigFile = new File(getClass().getClassLoader().getResource(args[0]).getFile());
    additionalJvmParameters.jpdaOpts = args[1];
    String wrapperConfDir = additionalJvmParameters.extractWrapperConfDir(wrapperConfigFile);

    // create wrapper-additional.conf
    File wrapperAdditionalConfFile = newTemporaryFile();
    FileWriter writer = new FileWriter(wrapperAdditionalConfFile);
    additionalJvmParameters.writeHeader(writer);

    List<String> arguments = asList(args);
    boolean debugEnabled = additionalJvmParameters.isDebugEnabled(arguments);
    boolean adHocOptionsAvailable = additionalJvmParameters.isOptionAvailable(arguments, "-M");
    boolean wrapperOptionsAvailable = additionalJvmParameters.isOptionAvailable(arguments, "-W");

    BufferedReader reader = new BufferedReader(new FileReader(wrapperConfigFile));
    if (debugEnabled || adHocOptionsAvailable) {
      // looking for maximum number of wrapper.java.additional property
      String line = reader.readLine();
      while (line != null) {
        additionalJvmParameters.paramIndex = additionalJvmParameters.findWrapperAdditionalProperties(line);
        line = reader.readLine();
      }
      reader.close();

      if (debugEnabled) {
        additionalJvmParameters.writeJpdaOpts(writer);
      }
      if (adHocOptionsAvailable) {
        additionalJvmParameters.writeAdHocProps(args, writer);
      }
      if (wrapperOptionsAvailable) {
        additionalJvmParameters.writeWrapperProps(args, writer);
      }
    }
    writer.close();

    StringBuilder sb = new StringBuilder();
    sb.append("#encoding=UTF-8\n");
    sb.append("# Do not edit this file!\n");
    sb.append("# This is a generated file to add additional parameters to JVM and Wrapper\n");
    sb.append("wrapper.java.additional.19=-Xdebug\n");
    sb.append("wrapper.java.additional.20=-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005\n");
    sb.append("wrapper.java.additional.21=\"-Dmule.blabla=true\"\n");
    sb.append("wrapper.java.additional.21.stripquotes=TRUE\n");
    sb.append("-Dmule.anotherone=false");
    assertThat(wrapperAdditionalConfFile).hasContent(sb.toString());
  }

  @Test
  public void testProps() throws IOException {
    String wrapperPrefix = "wrapper.java.additional.";
    StringWriter writer = new StringWriter();
    Properties properties = new Properties();
    properties.setProperty("wrapper.java.additional.<n1>", "value1");
    properties.setProperty("wrapper.java.additional.<n1>.suffix", "value1");
    properties.setProperty("wrapper.java.additional.<n2>", "value2");
    properties.setProperty("wrapper.java.classpath.<n1>", "classpathValue1");
    properties.setProperty("other.property", "otherValue");

    AdditionalJvmParameters.processBootstrapProperties(properties, writer);

    List<String> actualList = Arrays.asList(writer.toString().split("\n"));
    List<String> list = actualList.stream().filter(s -> s.matches("wrapper\\.java\\.additional.*value1")).collect(toList());
    assertThat(list.size(), is(2));

    String prop1 = list.get(0);
    int prop1EndIdx = prop1.indexOf("=", wrapperPrefix.length() + 1);
    String prop2 = list.get(1);
    int prop2EndIdx = prop2.indexOf(".", wrapperPrefix.length() + 1);
    assertThat(prop1.substring(0, prop1EndIdx), is(prop2.substring(0, prop2EndIdx)));

    Arrays.asList("wrapper.java.additional.\\d+=value2",
                  "wrapper.java.classpath.\\d+=classpathValue1",
                  "other.property=otherValue")
        .forEach(pattern -> assertThat(actualList.stream().anyMatch(p -> p.matches(pattern)), is(true)));
  }

}
