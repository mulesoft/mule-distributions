def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/mule-4.1.x",
                               "Mule-runtime/mule-http-service/1.1.x",
                               "Mule-runtime/mule-oauth-service/1.1.x",
                               "Mule-runtime/mule-scheduler-service/1.1.x",
                               "Mule-runtime/mule-soap-service/1.1.x",
                               "Mule-runtime/mule-embedded-api/1.1.x",
                               "Mule-runtime/mule-maven-client/1.2.x" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
