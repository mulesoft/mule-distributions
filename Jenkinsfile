def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/support/4.2.0",
                               "Mule-runtime/mule-http-service/1.4.14",
                               "Mule-runtime/mule-oauth-service/1.2.0",
                               "Mule-runtime/mule-scheduler-service/1.2.3",
                               "Mule-runtime/mule-soap-service/1.2.0",
                               "Mule-runtime/mule-embedded-api/1.2.0-20200518",
                               "Mule-runtime/mule-maven-client/1.4.0-20200518" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),

                       // Comment public setting to get org.mule.runtime:api-annotations:jar:1.1.0-20200709 from private
                       // repo until it is released in a public repo
                       // "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
