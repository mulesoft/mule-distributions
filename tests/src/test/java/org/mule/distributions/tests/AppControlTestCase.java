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

import java.io.IOException;
import java.nio.file.Paths;

import io.qameta.allure.Issue;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("MULE-13040")
@Issue("MULE-13040")
public class AppControlTestCase extends AbstractAppControl {

  private static final String SINGLE_APP_COMMAND = "-app";
  private static final String DEFAULT = "default";

  private static final String EMPTY_APP = "empty";

  @Before
  public void setup() throws IOException {
    mule.deploy(getResourceAsString("apps", EMPTY_APP));
  }

  @After
  public void tearDown() {
    mule.stop();
    assertMuleStops();
  }

  @Test
  public void muleStarts() throws IOException {
    mule.start();
    assertMuleStarts();
    assertAppIsDeployed(EMPTY_APP);
  }

  @Test
  public void stopShouldStopMule() {
    mule.start();
    assertMuleStarts();
    mule.stop();
    assertMuleStops();
  }

  @Test
  public void restartSpawnsANewProcess() {
    mule.start();
    int id = mule.getProcessId();
    mule.restart();
    assertAppIsDeployed(EMPTY_APP);
    assertMuleStarts();
    assertThat(mule.getProcessId(), not(is(id)));
  }

  @Test
  public void restartStartStoppedServer() {
    mule.restart();
    assertAppIsDeployed(EMPTY_APP);
    assertMuleStarts();
  }

  @Test
  public void deploySingleApplication() {
    mule.deploy(getResourceAsString("apps", EMPTY_APP));
    mule.start(SINGLE_APP_COMMAND, EMPTY_APP);
    assertAppIsDeployed(EMPTY_APP);
    assertAppNotDeployed(DEFAULT);
    assertMuleStarts();
  }

  private static String getResourceAsString(String directory, String name) {
    return getResource(Paths.get(directory, name).toString(), AppControlTestCase.class).getPath();

  }

}
