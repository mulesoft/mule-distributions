def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/4.2.1-NOVEMBER-2022",
                               "Mule-runtime/mule-http-service/1.4.16",
                               "Mule-runtime/mule-oauth-service/1.3.1",
                               "Mule-runtime/mule-scheduler-service/1.2.6",
                               "Mule-runtime/mule-soap-service/1.2.4",
                               "Mule-runtime/mule-embedded-api/1.2.0-NOVEMBER-2022",
                               "Mule-runtime/mule-maven-client/1.4.0-NOVEMBER-2022" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
