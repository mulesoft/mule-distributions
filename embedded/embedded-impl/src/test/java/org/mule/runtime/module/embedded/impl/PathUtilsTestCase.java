/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.embedded.impl;

import static org.mule.runtime.module.embedded.impl.PathUtils.getPath;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DeploymentTypeStory.EMBEDDED;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EMBEDDED_API;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.mule.tck.junit4.AbstractMuleTestCase;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import io.qameta.allure.Story;
import org.junit.Test;

@Feature(EMBEDDED_API)
@Story(EMBEDDED)
@Issues({@Issue("W-11193698"), @Issue("MULE-14428")})
public class PathUtilsTestCase extends AbstractMuleTestCase {

  private static final URL URL_WITHOUT_SPACES = getUrlWithoutSpaces();
  private static final URL URL_WITH_SPACES = getUrlWithSpaces();
  private static final String EXPECTED_PATH_WITH_SPACES = getExpectedPathWithSpaces();
  private static final String EXPECTED_PATH_WITHOUT_SPACES = getExpectedPathWithoutSpaces();

  @Test
  public void pathFromURLWithSpaces() throws URISyntaxException {
    assertThat(getPath(URL_WITH_SPACES), is(EXPECTED_PATH_WITH_SPACES));
  }

  @Test
  public void pathFromURLWithoutSpaces() throws URISyntaxException {
    assertThat(getPath(URL_WITHOUT_SPACES), is(EXPECTED_PATH_WITHOUT_SPACES));
  }

  private static String getExpectedPathWithSpaces() {
    if (IS_OS_WINDOWS) {
      return "C:\\Hello World";
    } else {
      return "/Hello World";
    }
  }

  private static String getExpectedPathWithoutSpaces() {
    if (IS_OS_WINDOWS) {
      return "C:\\Hello\\World";
    } else {
      return "/Hello/World";
    }
  }

  private static URL getUrlWithoutSpaces() {
    try {
      if (IS_OS_WINDOWS) {
        return new URL("file:/C:/Hello/World");
      } else {
        return new URL("file:/Hello/World");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  private static URL getUrlWithSpaces() {
    try {
      if (IS_OS_WINDOWS) {
        return new URL("file:///C:/Hello%20World");
      } else {
        return new URL("file:///Hello%20World");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
