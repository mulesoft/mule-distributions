/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.distributions.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mule.runtime.core.api.util.ClassUtils.getResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class AppControlTestCase extends AbstractAppControl {

  private static final String SINGLE_APP_COMMAND = "-app";
  private static final String DEFAULT = "default";

  private static final String EMPTY_APP = "empty";

  @Before
  public void setup() throws IOException {
    getMule().deploy(getResourceAsString("apps", EMPTY_APP));
  }

  @After
  public void tearDown() {
    getMule().stop();
    assertMuleStops();
  }

  @Test
  public void muleStarts() throws IOException {
    getMule().start();
    assertMuleStarts();
    assertAppIsDeployed(EMPTY_APP);
  }

  @Test
  public void stopShouldStopMule() {
    getMule().start();
    assertMuleStarts();
    getMule().stop();
    assertMuleStops();
  }

  @Test
  public void restartSpawnsANewProcess() {
    getMule().start();
    int id = getMule().getProcessId();
    getMule().restart();
    assertAppIsDeployed(EMPTY_APP);
    assertMuleStarts();
    assertThat(getMule().getProcessId(), not(is(id)));
  }

  @Test
  public void restartStartStoppedServer() {
    getMule().restart();
    assertAppIsDeployed(EMPTY_APP);
    assertMuleStarts();
  }

  @Test
  public void deploySingleApplication() {
    getMule().deploy(getResourceAsString("apps", EMPTY_APP));
    getMule().start(SINGLE_APP_COMMAND, EMPTY_APP);
    assertAppIsDeployed(EMPTY_APP);
    assertAppNotDeployed(DEFAULT);
    assertMuleStarts();
  }

  private static String getResourceAsString(String directory, String name) {
    return getResource(directory + "/" + name, AppControlTestCase.class).getPath();
  }

}
