def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/mule-4.2.1-SPX",
                               "Mule-runtime/mule-http-service/1.4.2",
                               "Mule-runtime/mule-oauth-service/1.2.0",
                               "Mule-runtime/mule-scheduler-service/1.2.2",
                               "Mule-runtime/mule-soap-service/1.2.0",
                               "Mule-runtime/mule-embedded-api/1.2.0",
                               "Mule-runtime/mule-maven-client/1.4.0" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       // Comment the mule public settings as there are some transitive deps from the services that are only present
                       // in private repos like ci-releases and releases-ee.
                       //"mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings"
                     ]

runtimeProjectsBuild(pipelineParams)
