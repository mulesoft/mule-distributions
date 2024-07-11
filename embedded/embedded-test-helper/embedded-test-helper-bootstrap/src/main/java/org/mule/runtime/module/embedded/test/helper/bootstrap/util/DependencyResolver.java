/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.test.helper.bootstrap.util;

import static java.io.File.separator;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.nio.file.Files.walk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DependencyResolver {

  public static Path getLibs(String fileLocation, Path outputDirectory) throws IOException {
    fileLocation =
        "/Users/rbourbon/.m2/repository/org/mule/distributions/mule-module-embedded-test-helper/4.8.0-SNAPSHOT/mule-module-embedded-test-helper-4.8.0-SNAPSHOT-dependencies.zip";
    // String fileLocation = getProperty("embedded.client.dependencies.location");
    unzipFile(fileLocation, outputDirectory);
    try (Stream<Path> paths = walk(outputDirectory)) {
      return paths
          .filter(Files::isDirectory)
          .filter(path -> path.getFileName().toString().equals("lib"))
          .findFirst()
          .orElseThrow(() -> new RuntimeException(format("No 'lib' directory found under %s", outputDirectory)));
    }
  }

  private static void unzipFile(String fileLocation, Path outputDirectory) {
    try {
      byte[] buffer = new byte[1024];
      ZipInputStream zis = new ZipInputStream(new FileInputStream(fileLocation));
      ZipEntry zipEntry = zis.getNextEntry();
      while (zipEntry != null) {
        File newFile = newFile(outputDirectory.toFile(), zipEntry);
        if (zipEntry.isDirectory()) {
          if (!newFile.isDirectory() && !newFile.mkdirs()) {
            throw new IOException("Failed to create directory " + newFile);
          }
        } else {
          // fix for Windows-created archives
          File parent = newFile.getParentFile();
          if (!parent.isDirectory() && !parent.mkdirs()) {
            throw new IOException("Failed to create directory " + parent);
          }

          // write file content
          FileOutputStream fos = new FileOutputStream(newFile);
          int len;
          while ((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
          }
          fos.close();
        }
        zipEntry = zis.getNextEntry();
      }

      zis.closeEntry();
      zis.close();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
    File destFile = new File(destinationDir, zipEntry.getName());

    String destDirPath = destinationDir.getCanonicalPath();
    String destFilePath = destFile.getCanonicalPath();

    if (!destFilePath.startsWith(destDirPath + separator)) {
      throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
    }

    return destFile;
  }

}

