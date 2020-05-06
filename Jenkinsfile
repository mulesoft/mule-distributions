def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/support/4.1.x",
                               "Mule-runtime/mule-http-service/support/1.1.x",
                               "Mule-runtime/mule-oauth-service/support/1.1.x",
                               "Mule-runtime/mule-scheduler-service/support/1.1.x",
                               "Mule-runtime/mule-soap-service/support/1.1.x",
                               "Mule-runtime/mule-embedded-api/support/1.1.x",
                               "Mule-runtime/mule-maven-client/support/1.2.x" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       // Uncomment it after parent artifacts are copied
                       //"mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
