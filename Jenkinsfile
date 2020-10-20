def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/support/4.2.1",
                               "Mule-runtime/mule-http-service/1.4.14",
                               "Mule-runtime/mule-oauth-service/1.3.1",
                               "Mule-runtime/mule-scheduler-service/1.2.3",
                               "Mule-runtime/mule-soap-service/1.2.0",
                               "Mule-runtime/mule-embedded-api/1.2.0-OCTOBER",
                               "Mule-runtime/mule-maven-client/support/1.4.0" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
