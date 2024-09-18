/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.params;

import static org.mule.runtime.params.ConfigFileUtils.getFileInSameDir;
import static org.mule.runtime.params.ConfigFileUtils.lookupLastSequencesFromConfig;

import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Files.move;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Updates the sequence numbers in wrapper-additional.conf so that they do not overlap (and don't leave gaps) with the ones in
 * wrapper.conf.
 */
public class WrapperAdditionalRefresher {

  public static void main(String[] args) throws IOException {
    File wrapperConfigFile = new File(args[0]);
    File wrapperAdditionalConfFile = getFileInSameDir(wrapperConfigFile, "wrapper-additional.conf");
    File updatedWrapperAdditionalConfFile = getFileInSameDir(wrapperConfigFile, "wrapper-additional-tmp.conf");

    SequenceInfo sequenceInfo = lookupLastSequencesFromConfig(wrapperConfigFile);
    try {
      copyUpdatingSequences(wrapperAdditionalConfFile, updatedWrapperAdditionalConfFile, sequenceInfo);
      move(updatedWrapperAdditionalConfFile.toPath(), wrapperAdditionalConfFile.toPath(), REPLACE_EXISTING);
    } finally {
      // Makes sure the temporary file does not exist anymore
      deleteIfExists(updatedWrapperAdditionalConfFile.toPath());
    }
  }

  private static void copyUpdatingSequences(File srcConfFile, File dstConfFile, SequenceInfo sequenceInfo) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(srcConfFile))) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(dstConfFile))) {
        // Not using forEach because Writer#write throws a checked exception.
        Iterable<String> lines = reader.lines()::iterator;
        for (String line : lines) {
          writer.write(sequenceInfo.reSequenceLine(line));
          writer.newLine();
        }
      }
    }
  }
}
