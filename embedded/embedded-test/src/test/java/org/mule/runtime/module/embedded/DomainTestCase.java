/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.runtime.module.embedded;

import static com.mashape.unirest.http.Unirest.post;
import static java.lang.String.format;
import static java.util.Optional.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DEPLOYMENT_TYPE;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DeploymentTypeStory.EMBEDDED;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EMBEDDED_API;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EmbeddedApiStory.CONFIGURATION;
import static org.mule.test.infrastructure.maven.MavenTestUtils.getApplicationBundleDescriptor;
import static org.mule.test.infrastructure.maven.MavenTestUtils.getDomainBundleDescriptor;
import static org.mule.test.infrastructure.maven.MavenTestUtils.installMavenArtifact;
import org.mule.runtime.module.artifact.api.descriptor.BundleDescriptor;
import org.mule.runtime.module.embedded.api.ArtifactConfiguration;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;
import java.nio.file.Paths;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.junit.AfterClass;
import org.junit.Test;

@Features({@Feature(EMBEDDED_API), @Feature(DEPLOYMENT_TYPE)})
@Stories({@Story(CONFIGURATION), @Story(EMBEDDED)})
public class DomainTestCase extends AbstractEmbeddedTestCase {

  @AfterClass
  public static void dispose() {
    embeddedTestHelper.dispose();
  }

  @Description("Embedded runs an application depending on a connector")
  @Test
  public void domainWithHttpConnector() throws Exception {
    BundleDescriptor domainBundleDescriptor = getDomainBundleDescriptor("simple-domain");
    doWithinDomain(domainBundleDescriptor, getDomainFolder("simple-domain"), port -> {
      BundleDescriptor appBundleDescriptor = getApplicationBundleDescriptor("http-echo-domain-app", empty());
      File applicationFile = installMavenArtifact(getAppFolder("http-echo-domain-app"), appBundleDescriptor);
      embeddedTestHelper.getContainer().getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(applicationFile).build());
      try {
        String httpBody = "test-message";
        HttpResponse<String> response = post(format("http://localhost:%s/", port)).body(httpBody).asString();
        assertThat(response.getBody(), is(httpBody));
      } catch (UnirestException e) {
        throw new RuntimeException(e);
      }
    });

  }

  private String getDomainFolder(String domainName) {
    return Paths.get("domains", domainName).toString();
  }

}
