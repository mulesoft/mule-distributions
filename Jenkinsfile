def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/4.3.0-NOVEMBER-2023",
                               "Mule-runtime/mule-http-service/1.5.21",
                               "Mule-runtime/mule-oauth-service/support/2.0.x",
                               "Mule-runtime/mule-scheduler-service/support/1.3.x",
                               "Mule-runtime/mule-soap-service/support/1.3.x",
                               "Mule-runtime/mule-embedded-api/1.3.0-NOVEMBER-2023",
                               "Mule-runtime/mule-maven-client/1.5.0-NOVEMBER-2023" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "archiveArtifacts" : '**/logs/**,**/conf/wrapper*.conf',
                      // MULE-18045: Comment public setting to get raml-parser 2 from private repo until it is released in a public repo
                      // "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
