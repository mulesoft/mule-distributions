def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/mule-4.x",
                               "Mule-runtime/mule-http-service/master",
                               "Mule-runtime/mule-oauth-service/master",
                               "Mule-runtime/mule-scheduler-service/master",
                               "Mule-runtime/mule-soap-service/master",
                               "Mule-runtime/mule-embedded-api/master",
                               "Mule-runtime/mule-maven-client/master" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "archiveArtifacts" : '**/logs/**,**/conf/wrapper*.conf',
                      // MULE-18045: Comment public setting to get raml-parser 2 from private repo until it is released in a public repo
                      // "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
