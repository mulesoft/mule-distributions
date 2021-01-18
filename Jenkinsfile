def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/4.1.5-JANUARY",
                               "Mule-runtime/mule-http-service/1.2.0",
                               "Mule-runtime/mule-oauth-service/1.1.7",
                               "Mule-runtime/mule-scheduler-service/1.1.10",
                               "Mule-runtime/mule-soap-service/1.1.8",
                               "Mule-runtime/mule-embedded-api/1.1.3-JANUARY",
                               "Mule-runtime/mule-maven-client/1.2.1-JANUARY" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
