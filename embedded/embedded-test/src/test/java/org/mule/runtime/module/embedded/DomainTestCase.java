/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.runtime.module.embedded;

import static java.util.Optional.empty;
import static org.mule.tck.MuleTestUtils.testWithSystemProperty;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DEPLOYMENT_TYPE;
import static org.mule.test.allure.AllureConstants.DeploymentTypeFeature.DeploymentTypeStory.EMBEDDED;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EMBEDDED_API;
import static org.mule.test.allure.AllureConstants.EmbeddedApiFeature.EmbeddedApiStory.CONFIGURATION;
import static org.mule.test.infrastructure.maven.MavenTestUtils.getApplicationBundleDescriptor;
import static org.mule.test.infrastructure.maven.MavenTestUtils.getDomainBundleDescriptor;
import static org.mule.test.infrastructure.maven.MavenTestUtils.installMavenArtifact;
import org.mule.runtime.module.artifact.api.descriptor.BundleDescriptor;
import org.mule.runtime.module.embedded.api.ArtifactConfiguration;
import org.mule.tck.junit4.rule.DynamicPort;

import java.io.File;
import java.nio.file.Paths;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

@Features({@Feature(EMBEDDED_API), @Feature(DEPLOYMENT_TYPE)})
@Stories({@Story(CONFIGURATION), @Story(EMBEDDED)})
public class DomainTestCase extends AbstractEmbeddedTestCase {

  @Rule
  public DynamicPort dynamicPort = new DynamicPort("httpPort");

  @Description("Embedded deploys a domain and an application associated to that domain")
  @Test
  @Ignore("MULE-19061")
  public void domainWithHttpConnector() throws Exception {
    BundleDescriptor domainBundleDescriptor = getDomainBundleDescriptor("simple-domain");
    doWithinDomain(domainBundleDescriptor, getDomainFolder("simple-domain"), port -> {
      BundleDescriptor appBundleDescriptor = getApplicationBundleDescriptor("http-echo-domain-app", empty());
      File applicationFile = installMavenArtifact(getAppFolder("http-echo-domain-app"), appBundleDescriptor);
      embeddedTestHelper.getContainer().getDeploymentService()
          .deployApplication(ArtifactConfiguration.builder().artifactLocation(applicationFile).build());
      executeWithRetry(() -> ApplicationTestCase.assertTestMessage(port));
    });

  }

  @Description("Embedded deploys and undeploys a domain")
  @Test
  @Ignore("MULE-19061")
  public void deployUndeployDomain() throws Exception {
    runWithContainer(container -> {
      try {
        testWithSystemProperty("httpPort", dynamicPort.getValue(), () -> {
          BundleDescriptor domainBundleDescriptor = getDomainBundleDescriptor("simple-domain");
          File domainFile = installMavenArtifact(getDomainFolder("simple-domain"), domainBundleDescriptor);
          container.getDeploymentService().deployDomain(ArtifactConfiguration.builder().artifactLocation(domainFile).build());
          executeWithRetry(() -> validateDomainIsDeployed(container, domainFile));
          container.getDeploymentService().undeployDomain(domainFile.getName().replace(".jar", ""));
          executeWithRetry(() -> validateDomainIsUndeployed(container, domainFile));
        });
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  private String getDomainFolder(String domainName) {
    return Paths.get("domains", domainName).toString();
  }
}
