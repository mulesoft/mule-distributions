def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/4.1.6-AUGUST",
                               "Mule-runtime/mule-http-service/1.3.0",
                               "Mule-runtime/mule-oauth-service/1.1.7",
                               "Mule-runtime/mule-scheduler-service/1.1.11",
                               "Mule-runtime/mule-soap-service/1.1.9",
                               "Mule-runtime/mule-embedded-api/1.1.4-AUGUST",
                               "Mule-runtime/mule-maven-client/1.2.3-AUGUST" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
