/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.params;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.util.Optional.empty;
import static java.util.regex.Pattern.compile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the information of the highest sequences found in a configuration file.
 * <p>
 * There are independent sequences for additional parameters and classpath entries.
 */
class SequenceInfo {

  private static final Pattern RE_ADDITIONAL = compile("^\\s*wrapper\\.java\\.additional\\.(\\d+).+");
  private static final Pattern RE_CLASSPATH = compile("^\\s*wrapper\\.java\\.classpath\\.(\\d+).+");

  private final HighestSequenceTracker additionalParamSeqTracker = new HighestSequenceTracker(RE_ADDITIONAL);
  private final HighestSequenceTracker classpathSeqTracker = new HighestSequenceTracker(RE_CLASSPATH);

  /**
   * Updates the highest sequence information after observing the given {@code line}.
   * <p>
   * Note that this may have no effect, either if the sequence is lower or if there is no sequence in the given line.
   *
   * @param line A line from a configuration file.
   */
  public void feedLine(String line) {
    additionalParamSeqTracker.feedLine(line);
    classpathSeqTracker.feedLine(line);
  }

  /**
   * Given a new configuration line, re-sequences it so that it uses the next available sequence number considering the highest
   * sequences previously computed.
   * <p>
   * Note that this will also update the highest sequence information accordingly.
   * <p>
   * Guarantees that two lines with the same sequence will also share the same sequence number after re-sequencing.
   *
   * @param line A line from a configuration file.
   * @return The same line but with a new sequence number assigned, to avoid gaps and collisions.
   */
  public String reSequenceLine(String line) {
    return additionalParamSeqTracker.reSequenceLine(line)
        .orElseGet(() -> classpathSeqTracker.reSequenceLine(line)
            .orElse(line));
  }

  private static class HighestSequenceTracker {

    private int highestSequence = 0;
    private final Map<String, Integer> reSequenced = new HashMap<>();
    private final Pattern lookupPattern;

    private HighestSequenceTracker(Pattern lookupPattern) {
      this.lookupPattern = lookupPattern;
    }

    public void feedLine(String line) {
      Matcher matcher = lookupPattern.matcher(line);
      if (matcher.find()) {
        highestSequence = max(highestSequence, parseInt(matcher.group(1)));
      }
    }

    public Optional<String> reSequenceLine(String line) {
      Matcher matcher = lookupPattern.matcher(line);
      StringBuilder sb = new StringBuilder();
      if (matcher.find()) {
        String foundSequence = matcher.group(1);

        // Checks if this sequence number has already been re-sequenced, otherwise just occupies the next available number
        Integer assignedSequence = reSequenced.computeIfAbsent(foundSequence, (k) -> ++highestSequence);

        // Performs the replacement
        sb.append(line, 0, matcher.start(1));
        sb.append(assignedSequence);
        sb.append(line, matcher.end(1), line.length());

        return Optional.of(sb.toString());
      }
      return empty();
    }
  }
}
