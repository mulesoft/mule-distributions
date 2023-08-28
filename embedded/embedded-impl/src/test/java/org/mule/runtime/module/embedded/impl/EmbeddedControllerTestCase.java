/*
 * Copyright 2023 Salesforce, Inc. All rights reserved.
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl;

import static org.mule.runtime.container.api.MuleFoldersUtil.getMuleHomeFolder;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DeploymentTypeStory.EMBEDDED;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EMBEDDED_API;

import static java.lang.System.getProperty;
import static java.util.Collections.emptyList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.mule.runtime.module.embedded.api.ContainerInfo;
import org.mule.runtime.module.embedded.internal.controller.EmbeddedController;
import org.mule.tck.junit4.AbstractMuleTestCase;

import java.io.IOException;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.junit.Before;
import org.junit.Test;

@Feature(EMBEDDED_API)
@Story(EMBEDDED)
@Issue("W-11193698")
public class EmbeddedControllerTestCase extends AbstractMuleTestCase {

  private EmbeddedController embeddedController;

  @Before
  public void setUp() throws IOException {
    ContainerInfo containerInfo = new ContainerInfo("4.1.1", getMuleHomeFolder().toURI().toURL(), emptyList(), emptyList());
    embeddedController = new DefaultEmbeddedController(containerInfo);
  }

  @Test
  public void muleHomeIsCorrectlySetWhenStartingTheController() throws Exception {
    embeddedController.start();
    assertThat(getProperty("mule.home"), is(getMuleHomeFolder().getAbsolutePath()));
  }
}
