def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/4.2.2-TESTCYCLE-2020-DRY-RUN",
                               "Mule-runtime/mule-http-service/1.4.22-DRY-RUN",
                               "Mule-runtime/mule-oauth-service/1.3.3-DRY-RUN",
                               "Mule-runtime/mule-scheduler-service/1.2.6-DRY-RUN",
                               "Mule-runtime/mule-soap-service/1.2.4-DRY-RUN",
                               "Mule-runtime/mule-embedded-api/1.2.1-TESTCYCLE-2020-DRY-RUN",
                               "Mule-runtime/mule-maven-client/1.4.2-TESTCYCLE-2020-DRY-RUN" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
