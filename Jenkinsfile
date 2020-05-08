def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/support/4.1.6",
                               "Mule-runtime/mule-http-service/1.3.0",
                               "Mule-runtime/mule-oauth-service/support/1.1.x",
                               "Mule-runtime/mule-scheduler-service/1.1.11",
                               "Mule-runtime/mule-soap-service/support/1.1.x",
                               "Mule-runtime/mule-embedded-api/support/1.1.4",
                               "Mule-runtime/mule-maven-client/support/1.2.3" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
