def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/4.2.2-APRIL-2021",
                               "Mule-runtime/mule-http-service/1.4.22",
                               "Mule-runtime/mule-oauth-service/1.3.3",
                               "Mule-runtime/mule-scheduler-service/1.2.4",
                               "Mule-runtime/mule-soap-service/1.2.1",
                               "Mule-runtime/mule-embedded-api/1.2.1-APRIL-2021",
                               "Mule-runtime/mule-maven-client/1.4.2-APRIL-2021" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
