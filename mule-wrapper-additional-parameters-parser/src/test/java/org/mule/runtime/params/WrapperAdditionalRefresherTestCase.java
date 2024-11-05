/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.params;

import static java.nio.charset.StandardCharsets.UTF_8;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.io.FileUtils.readLines;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.io.FileMatchers.anExistingFile;
import static org.hamcrest.io.FileMatchers.anExistingFileOrDirectory;
import static org.junit.Assert.assertThrows;
import static org.junit.internal.matchers.ThrowableMessageMatcher.hasMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
public class WrapperAdditionalRefresherTestCase {

  private static final String WRAPPER_CONF = "wrapper.conf";
  private static final String WRAPPER_ADDITIONAL_CONF = "wrapper-additional.conf";
  private static final String WRAPPER_ADDITIONAL_CONF_TEMP = "wrapper-additional-tmp.conf";

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  private File wrapperConfFile;

  @Before
  public void before() throws IOException {
    copyWrapper();
    this.wrapperConfFile = temporaryFolder.getRoot().toPath().resolve(WRAPPER_CONF).toFile();
  }

  @Test
  public void withGaps() throws IOException {
    runUsingAdditionalAndAssertExpected("conf/wrapper-additional-with-gap.conf",
                                        "conf/wrapper-additional-expected.conf");
  }

  @Test
  public void withOverlap() throws IOException {
    runUsingAdditionalAndAssertExpected("conf/wrapper-additional-with-overlap.conf",
                                        "conf/wrapper-additional-expected.conf");
  }

  @Test
  public void noChanges() throws IOException {
    runUsingAdditionalAndAssertExpected("conf/wrapper-additional-expected.conf",
                                        "conf/wrapper-additional-expected.conf");
  }

  @Test
  public void noWrapperAdditional() {
    FileNotFoundException exception = assertThrows(FileNotFoundException.class,
                                                   () -> WrapperAdditionalRefresher
                                                       .main(new String[] {wrapperConfFile.getPath()}));
    assertThat(exception, hasMessage(containsString(WRAPPER_ADDITIONAL_CONF)));
    // Checks the temporary file is not left behind
    assertThat(fileFromTemp(WRAPPER_ADDITIONAL_CONF_TEMP), is(not(anExistingFileOrDirectory())));
  }

  @Test
  public void noWrapperConf() {
    temporaryFolder.delete();
    FileNotFoundException exception = assertThrows(FileNotFoundException.class,
                                                   () -> WrapperAdditionalRefresher
                                                       .main(new String[] {wrapperConfFile.getPath()}));
    assertThat(exception, hasMessage(containsString(WRAPPER_CONF)));
    // Checks the temporary file is not left behind
    assertThat(fileFromTemp(WRAPPER_ADDITIONAL_CONF_TEMP), is(not(anExistingFileOrDirectory())));
  }

  private void runUsingAdditionalAndAssertExpected(String wrapperAdditionalResource, String expectedResource) throws IOException {
    useWrapperAdditional(wrapperAdditionalResource);
    WrapperAdditionalRefresher.main(new String[] {wrapperConfFile.getPath()});
    File actual = fileFromTemp(WRAPPER_ADDITIONAL_CONF);
    assertThat(actual, is(anExistingFile()));
    assertSameContents(actual, fileFromResource(expectedResource));

    // Checks the temporary file is not left behind
    assertThat(fileFromTemp(WRAPPER_ADDITIONAL_CONF_TEMP), is(not(anExistingFileOrDirectory())));
  }

  private void copyWrapper() throws IOException {
    copyResourceToTemp("conf/" + WRAPPER_CONF, WRAPPER_CONF);
  }

  private void useWrapperAdditional(String wrapperAdditionalName) throws IOException {
    copyResourceToTemp(wrapperAdditionalName, WRAPPER_ADDITIONAL_CONF);
  }

  private void copyResourceToTemp(String resourceName, String targetName) throws IOException {
    copyFile(fileFromResource(resourceName), fileFromTemp(targetName));
  }

  private File fileFromResource(String resourceName) {
    return new File(this.getClass().getClassLoader().getResource(resourceName).getFile());
  }

  private File fileFromTemp(String tempFileName) {
    return temporaryFolder.getRoot().toPath().resolve(tempFileName).toFile();
  }

  private void assertSameContents(File actualFile, File expectedFile) throws IOException {
    List<String> actualLines = readLines(actualFile, UTF_8);
    String[] expectedLines = readLines(expectedFile, UTF_8).toArray(new String[0]);
    assertThat(actualLines, contains(expectedLines));
  }
}
