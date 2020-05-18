def UPSTREAM_PROJECTS_LIST = [ "Mule-runtime/mule-integration-tests/4.2.0-MAY-DRY-RUN",
                               "Mule-runtime/mule-http-service/1.4.14-DRY-RUN",
                               "Mule-runtime/mule-oauth-service/1.2.0-DRY-RUN",
                               "Mule-runtime/mule-scheduler-service/1.2.3-DRY-RUN",
                               "Mule-runtime/mule-soap-service/1.2.0-DRY-RUN",
                               "Mule-runtime/mule-embedded-api/1.2.0-MAY-DRY-RUN",
                               "Mule-runtime/mule-maven-client/1.4.0-MAY-DRY-RUN" ]

Map pipelineParams = [ "upstreamProjects" : UPSTREAM_PROJECTS_LIST.join(','),
                       "mavenSettingsXmlId" : "mule-runtime-maven-settings-MuleSettings",
                       "projectType" : "Runtime" ]

runtimeBuild(pipelineParams)
