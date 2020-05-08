def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/mule-4.x",
                               "Mule-runtime/mule-http-service/master",
                               "Mule-runtime/mule-oauth-service/master",
                               "Mule-runtime/mule-scheduler-service/master",
                               "Mule-runtime/mule-soap-service/master",
                               "Mule-runtime/mule-embedded-api/support/1.2.0",
                               "Mule-runtime/mule-maven-client/master" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
