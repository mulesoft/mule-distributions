/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.params;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Some utils for manipulating configuration files.
 */
class ConfigFileUtils {

  private ConfigFileUtils() {
    // Private constructor to prevent instantiation.
  }

  /**
   * @param someFile A {@link File} handler to a given file (existing or not).
   * @param fileName A name representing a file (existing or not) in the same directory as {@code someFile}.
   * @return A {@link File} handler to another file in the same directory as {@code someFile} but named {@code fileName}.
   */
  static File getFileInSameDir(File someFile, String fileName) {
    return someFile.toPath().getParent().resolve(fileName).toFile();
  }

  /**
   * @param configFile A {@link File} handler to a configuration file.
   * @return The {@link SequenceInfo} conveying the highest found sequences in the given {@code configFile}.
   * @throws IOException If the file doesn't exist or if there is an error closing it.
   */
  static SequenceInfo lookupLastSequencesFromConfig(File configFile) throws IOException {
    SequenceInfo sequenceInfo = new SequenceInfo();

    try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
      reader.lines().forEach(sequenceInfo::feedLine);
    }
    return sequenceInfo;
  }
}
