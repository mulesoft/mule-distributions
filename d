[INFO] Scanning for projects...
[WARNING] 
[WARNING] Some problems were encountered while building the effective model for org.mule.distributions:mule-distributions:pom:4.1.4-HF-SNAPSHOT
[WARNING] 'parent.relativePath' of POM org.mule.distributions:mule-distributions:4.1.4-HF-SNAPSHOT (/Users/szaffarano/Projects/cherry-picks/mule4-uber/mule-distributions/pom.xml) points at org.mule:mule-runtime-project instead of org.mule.runtime:mule, please verify your project structure @ line 4, column 13
[WARNING] 'build.pluginManagement.plugins.plugin.(groupId:artifactId)' must be unique but found duplicate declaration of plugin org.apache.maven.plugins:maven-source-plugin @ org.mule.runtime:mule:4.1.4, /Users/szaffarano/.m2/repository/org/mule/runtime/mule/4.1.4/mule-4.1.4.pom, line 1787, column 25
[WARNING] 
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING] 
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING] 
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] Mule Distributions                                                 [pom]
[INFO] All Mule Services                                                  [pom]
[INFO] Mule Runtime BOM                                                   [pom]
[INFO] Mule Runtime APIs BOM                                              [pom]
[INFO] Mule Runtime Implementation Libraries BOM                          [jar]
[INFO] Mule Embedded                                                      [pom]
[INFO] Mule Embedded Implementation                                       [jar]
[INFO] Mule Module Embedded Implementation Distribution BOM               [pom]
[INFO] Mule Embedded Implementation Tests                                 [jar]
[INFO] Embedded (Single jar file)                                         [pom]
[INFO] Full Distribution                                                  [pom]
[INFO] Mule Distribution Tests                                            [jar]
[INFO] 
[INFO] -------------< org.mule.distributions:mule-distributions >--------------
[INFO] Building Mule Distributions 4.1.4-HF-SNAPSHOT                     [1/12]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-distributions ---
[INFO] org.mule.distributions:mule-distributions:pom:4.1.4-HF-SNAPSHOT
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- com.google.guava:guava:jar:25.1-jre:test
[INFO]    |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:test
[INFO]    |  |  +- org.checkerframework:checker-qual:jar:2.0.0:test
[INFO]    |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:test
[INFO]    |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:test
[INFO]    |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:test
[INFO]    |  |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:test
[INFO]    |  |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:test
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.apache.commons:commons-lang3:jar:3.6:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  +- org.jooq:joor:jar:0.9.6:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:1.7.25:test
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] --------------< org.mule.distributions:mule-services-all >--------------
[INFO] Building All Mule Services 4.1.4-HF-SNAPSHOT                      [2/12]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-services-all ---
[INFO] org.mule.distributions:mule-services-all:pom:4.1.4-HF-SNAPSHOT
[INFO] +- org.mule.services:mule-service-scheduler:jar:mule-service:1.1.7:compile
[INFO] |  +- com.google.guava:guava:jar:25.1-jre:compile
[INFO] |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:2.0.0:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:compile
[INFO] |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:compile
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:compile
[INFO] |  \- org.apache.commons:commons-lang3:jar:3.6:compile
[INFO] +- org.mule.services:mule-service-oauth:jar:mule-service:1.1.5:compile
[INFO] |  \- commons-codec:commons-codec:jar:1.9:compile
[INFO] +- org.mule.services:mule-service-http:jar:mule-service:1.1.4:compile
[INFO] |  \- commons-io:commons-io:jar:2.4:compile
[INFO] +- org.mule.services:mule-service-soap:jar:mule-service:1.1.8:compile
[INFO] |  +- org.springframework:spring-context:jar:4.3.19.RELEASE:compile
[INFO] |  |  +- org.springframework:spring-aop:jar:4.3.19.RELEASE:compile
[INFO] |  |  +- org.springframework:spring-core:jar:4.3.19.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-expression:jar:4.3.19.RELEASE:compile
[INFO] |  +- org.springframework:spring-beans:jar:4.3.19.RELEASE:compile
[INFO] |  \- dom4j:dom4j:jar:1.6.1:compile
[INFO] +- org.mule.services:mule-service-weave:jar:mule-service:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  +- org.scala-lang:scala-library:jar:2.12.4:compile
[INFO] |  +- org.mule.weave:runtime:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  +- org.mule.weave:wlang:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  +- org.mule.weave:core:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |  \- org.typelevel:spire_2.12:jar:0.14.1:compile
[INFO] |  |  |     +- org.typelevel:spire-macros_2.12:jar:0.14.1:compile
[INFO] |  |  |     +- org.typelevel:machinist_2.12:jar:0.6.1:compile
[INFO] |  |  |     |  \- org.scala-lang:scala-reflect:jar:2.12.0:compile
[INFO] |  |  |     \- org.typelevel:algebra_2.12:jar:0.7.0:compile
[INFO] |  |  |        \- org.typelevel:cats-kernel_2.12:jar:0.9.0:compile
[INFO] |  |  +- org.mule.weave:core-modules:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |  +- com.fasterxml.woodstox:woodstox-core:jar:5.0.2:compile
[INFO] |  |  |  |  \- org.codehaus.woodstox:stax2-api:jar:3.1.4:compile
[INFO] |  |  |  +- com.fasterxml:aalto-xml:jar:1.0.0:compile
[INFO] |  |  |  \- javax.mail:mail:jar:1.4.7:compile
[INFO] |  |  |     \- javax.activation:activation:jar:1.1:compile
[INFO] |  |  \- org.mule.weave:debugger:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  +- org.mule.weave:yaml-module:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  \- org.mule.syaml:syaml_2.12:jar:0.1.11:compile
[INFO] |  |     \- org.mule.common:scala-common_2.12:jar:0.1.3:compile
[INFO] |  |        \- org.scalactic:scalactic_2.12:jar:3.0.1:compile
[INFO] |  \- org.mule.weave:mule-tooling-weave:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     \- org.mule.weave:parser:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |        \- org.parboiled:parboiled_2.12:jar:2.1.4:compile
[INFO] |           \- com.chuusai:shapeless_2.12:jar:2.3.2:compile
[INFO] |              \- org.typelevel:macro-compat_2.12:jar:1.1.1:compile
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:test
[INFO]    |  |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:test
[INFO]    |  |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:test
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  +- org.jooq:joor:jar:0.9.6:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:1.7.25:test
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] ------------------< org.mule.distributions:mule-bom >-------------------
[INFO] Building Mule Runtime BOM 4.1.4-HF-SNAPSHOT                       [3/12]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-bom ---
[INFO] org.mule.distributions:mule-bom:pom:4.1.4-HF-SNAPSHOT
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- com.google.guava:guava:jar:25.1-jre:test
[INFO]    |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:test
[INFO]    |  |  +- org.checkerframework:checker-qual:jar:2.0.0:test
[INFO]    |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:test
[INFO]    |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:test
[INFO]    |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:test
[INFO]    |  |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:test
[INFO]    |  |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:test
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.apache.commons:commons-lang3:jar:3.6:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  +- org.jooq:joor:jar:0.9.6:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:1.7.25:test
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] ------------< org.mule.distributions:mule-runtime-api-bom >-------------
[INFO] Building Mule Runtime APIs BOM 4.1.4-HF-SNAPSHOT                  [4/12]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-runtime-api-bom ---
[INFO] org.mule.distributions:mule-runtime-api-bom:pom:4.1.4-HF-SNAPSHOT
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- com.google.guava:guava:jar:25.1-jre:test
[INFO]    |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:test
[INFO]    |  |  +- org.checkerframework:checker-qual:jar:2.0.0:test
[INFO]    |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:test
[INFO]    |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:test
[INFO]    |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:test
[INFO]    |  |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:test
[INFO]    |  |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:test
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.apache.commons:commons-lang3:jar:3.6:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  +- org.jooq:joor:jar:0.9.6:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:1.7.25:test
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] ------------< org.mule.distributions:mule-runtime-impl-bom >------------
[INFO] Building Mule Runtime Implementation Libraries BOM 4.1.4-HF-SNAPSHOT [5/12]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-runtime-impl-bom ---
[INFO] org.mule.distributions:mule-runtime-impl-bom:jar:4.1.4-HF-SNAPSHOT
[INFO] +- org.mule.patches:SE-10300-4.1.4:jar:1.0:compile
[INFO] +- org.mule.patches:SE-10165-4.1.4:jar:1.0:compile
[INFO] +- org.mule.patches:SE-10038-4.1.4:jar:1.0:compile
[INFO] +- org.mule.patches:SE-9843-4.1.4:jar:1.0:compile
[INFO] +- org.mule.patches:SE-10007-4.1.4:jar:1.0:compile
[INFO] +- org.mule.runtime:mule-core:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-api:jar:1.1.5:compile
[INFO] |  |  +- org.slf4j:slf4j-api:jar:1.7.25:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.8.1:compile
[INFO] |  |  +- org.mule.runtime:mule-artifact-declaration:jar:1.1.1:compile
[INFO] |  |  \- org.mule.runtime:api-annotations:jar:1.0.1:compile
[INFO] |  +- org.mule.runtime:mule-module-dsl-api:jar:1.1.4:compile
[INFO] |  +- org.mule.runtime:mule-extensions-api:jar:1.1.4:compile
[INFO] |  |  \- org.reactivestreams:reactive-streams:jar:1.0.2:compile
[INFO] |  +- org.mule.runtime:mule-module-http-policy-api:jar:1.1.2:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-xml:jar:1.1.5:compile
[INFO] |  |  +- org.apache.xmlbeans:xmlbeans:jar:2.6.0:compile
[INFO] |  |  \- org.mule.apache:xerces2-xsd11:jar:2.11.3:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-java:jar:1.1.5:compile
[INFO] |  +- org.mule.runtime:mule-message-metadata-model:jar:1.1.5:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-json:jar:1.1.5:compile
[INFO] |  |  \- org.everit.json:org.everit.json.schema:jar:1.5.0:compile
[INFO] |  |     +- org.json:json:jar:20160810:compile
[INFO] |  |     \- commons-validator:commons-validator:jar:1.5.1:compile
[INFO] |  |        +- commons-digester:commons-digester:jar:1.8.1:compile
[INFO] |  |        \- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  +- commons-beanutils:commons-beanutils:jar:1.9.3:compile
[INFO] |  +- com.github.stephenc.eaio-uuid:uuid:jar:3.4.0:compile
[INFO] |  |  \- com.github.stephenc.eaio-grabbag:grabbag:jar:1.8.1:compile
[INFO] |  +- commons-cli:commons-cli:jar:1.2:compile
[INFO] |  +- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  +- commons-io:commons-io:jar:2.4:compile
[INFO] |  +- org.apache.commons:commons-lang3:jar:3.6:compile
[INFO] |  +- commons-pool:commons-pool:jar:1.6:compile
[INFO] |  +- javax.transaction:javax.transaction-api:jar:1.2:compile
[INFO] |  +- org.apache.geronimo.specs:geronimo-j2ee-connector_1.5_spec:jar:2.0.0:compile
[INFO] |  +- javax.inject:javax.inject:jar:1:compile
[INFO] |  +- org.mule.mvel:mule-mvel2:jar:2.1.9-MULE-017:compile
[INFO] |  +- org.jgrapht:jgrapht-jdk1.5:jar:0.7.3:compile
[INFO] |  +- com.google.guava:guava:jar:25.1-jre:compile
[INFO] |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:2.0.0:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:compile
[INFO] |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:compile
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:compile
[INFO] |  +- org.reflections:reflections:jar:0.9.10:compile
[INFO] |  +- org.mule.projectreactor:reactor-core:jar:3.2.0.M1-MULE-002:compile
[INFO] |  +- io.projectreactor.addons:reactor-extra:jar:3.1.2.RELEASE:compile
[INFO] |  +- org.yaml:snakeyaml:jar:1.18:compile
[INFO] |  +- cglib:cglib-nodep:jar:3.2.6:compile
[INFO] |  +- org.apache.commons:commons-pool2:jar:2.5.0:compile
[INFO] |  +- it.unimi.dsi:fastutil:jar:8.1.1:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:2.6.2:compile
[INFO] |  \- javax.jms:javax.jms-api:jar:2.0.1:compile
[INFO] +- org.mule.runtime:mule-modules-all:pom:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-launcher:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-deployment:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-deployment-model-impl:jar:4.1.4:compile
[INFO] |  |  |  |  \- org.mule.tools.maven:mule-classloader-model:jar:3.1.7:compile
[INFO] |  |  |  +- org.mule:mule-maven-client-impl:jar:1.1.4:runtime
[INFO] |  |  |  |  +- org.apache.maven:maven-settings-builder:jar:3.3.9:runtime
[INFO] |  |  |  |  |  +- org.apache.maven:maven-builder-support:jar:3.3.9:compile
[INFO] |  |  |  |  |  +- org.codehaus.plexus:plexus-interpolation:jar:1.21:compile
[INFO] |  |  |  |  |  +- org.apache.maven:maven-settings:jar:3.3.9:runtime
[INFO] |  |  |  |  |  \- org.sonatype.plexus:plexus-sec-dispatcher:jar:1.3:runtime
[INFO] |  |  |  |  |     \- org.sonatype.plexus:plexus-cipher:jar:1.4:runtime
[INFO] |  |  |  |  +- com.beust:jcommander:jar:1.69:runtime
[INFO] |  |  |  |  \- org.eclipse.sisu:org.eclipse.sisu.plexus:jar:0.3.2:runtime
[INFO] |  |  |  \- org.mule.runtime:mule-module-service:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-repository:jar:4.1.4:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-api:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-spi:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-impl:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-connector-basic:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-transport-file:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-transport-http:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.apache.httpcomponents:httpclient:jar:4.5.3:compile
[INFO] |  |  |  |  \- org.apache.httpcomponents:httpcore:jar:4.4.6:compile
[INFO] |  |  |  +- org.apache.maven:maven-aether-provider:jar:3.3.9:compile
[INFO] |  |  |  |  +- org.apache.maven:maven-model-builder:jar:3.3.9:compile
[INFO] |  |  |  |  |  \- org.apache.maven:maven-artifact:jar:3.3.9:compile
[INFO] |  |  |  |  +- org.apache.maven:maven-repository-metadata:jar:3.3.9:compile
[INFO] |  |  |  |  \- org.codehaus.plexus:plexus-component-annotations:jar:1.6:compile
[INFO] |  |  |  \- org.codehaus.plexus:plexus-utils:jar:3.0.24:compile
[INFO] |  |  +- org.mule.runtime:mule-module-reboot:jar:4.1.4:compile
[INFO] |  |  \- tanukisoft:wrapper:jar:3.2.3:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-support:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-extensions-api-dsql:jar:1.1.4:compile
[INFO] |  |  |  \- org.antlr:antlr-runtime:jar:3.5:compile
[INFO] |  |  |     \- org.antlr:stringtemplate:jar:3.2.1:compile
[INFO] |  |  +- org.mule.runtime:mule-extensions-api-persistence:jar:1.1.4:compile
[INFO] |  |  |  \- org.mule.runtime:mule-metadata-model-persistence:jar:1.1.5:compile
[INFO] |  |  \- org.mule.runtime:mule-module-deployment-model:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-soap-support:jar:4.1.4:compile
[INFO] |  |  \- org.mule.runtime:mule-extensions-soap-api:jar:1.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-spring-support:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-tls:jar:4.1.4:compile
[INFO] |  |  +- jboss:javassist:jar:3.7.ga:compile
[INFO] |  |  +- joda-time:joda-time:jar:2.9.1:compile
[INFO] |  |  +- dom4j:dom4j:jar:1.6.1:compile
[INFO] |  |  \- jaxen:jaxen:jar:1.1.1:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-xml-support:jar:4.1.4:compile
[INFO] |  |  \- org.mule.runtime:mule-metadata-model-catalog:jar:1.1.5:compile
[INFO] |  |     \- org.mule.runtime:mule-metadata-model-raml:jar:1.1.5:compile
[INFO] |  |        \- org.raml:raml-parser-2:jar:1.0.27:compile
[INFO] |  |           +- org.raml:yagi:jar:1.0.27:compile
[INFO] |  |           |  +- commons-lang:commons-lang:jar:2.6:compile
[INFO] |  |           |  +- com.google.code.findbugs:annotations:jar:3.0.0:compile
[INFO] |  |           |  \- com.fasterxml.jackson.module:jackson-module-jsonSchema:jar:2.9.6:compile
[INFO] |  |           |     \- javax.validation:validation-api:jar:1.1.0.Final:compile
[INFO] |  |           +- com.github.java-json-tools:json-schema-validator:jar:2.2.8:compile
[INFO] |  |           |  +- com.github.java-json-tools:json-schema-core:jar:1.2.8:compile
[INFO] |  |           |  |  +- org.mozilla:rhino:jar:1.7R4:compile
[INFO] |  |           |  |  +- com.github.fge:jackson-coreutils:jar:1.8:compile
[INFO] |  |           |  |  |  \- com.github.fge:msg-simple:jar:1.1:compile
[INFO] |  |           |  |  |     \- com.github.fge:btf:jar:1.2:compile
[INFO] |  |           |  |  \- com.github.fge:uri-template:jar:0.9:compile
[INFO] |  |           |  +- javax.mail:mailapi:jar:1.4.3:compile
[INFO] |  |           |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.0.0:compile
[INFO] |  |           |  \- net.sf.jopt-simple:jopt-simple:jar:5.0.3:compile
[INFO] |  |           +- com.googlecode.juniversalchardet:juniversalchardet:jar:1.0.3:compile
[INFO] |  |           +- org.apache.ws.xmlschema:xmlschema-core:jar:2.2.1:compile
[INFO] |  |           +- javax.json:javax.json-api:jar:1.0:compile
[INFO] |  |           \- org.glassfish:javax.json:jar:1.0.4:compile
[INFO] |  +- org.mule.runtime:mule-module-policy-api:jar:1.1.2:compile
[INFO] |  +- org.mule.runtime:mule-module-spring-config:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-artifact:jar:4.1.4:compile
[INFO] |  |  |  +- com.vdurmont:semver4j:jar:2.0.3:compile
[INFO] |  |  |  \- org.eclipse.aether:aether-util:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.springframework:spring-context:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  +- org.springframework:spring-core:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  \- org.springframework:spring-expression:jar:4.3.19.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-aop:jar:4.3.19.RELEASE:compile
[INFO] |  +- org.mule.runtime:mule-module-tooling-support:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-global-config:jar:4.1.4:compile
[INFO] |  |  +- com.typesafe:config:jar:1.3.1:compile
[INFO] |  |  +- org.mule:mule-maven-client-api:jar:1.1.4:compile
[INFO] |  |  |  \- org.apache.maven:maven-model:jar:3.3.9:compile
[INFO] |  |  \- org.mule.runtime:mule-module-container:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-logging:jar:4.1.4:compile
[INFO] |  |  +- com.lmax:disruptor:jar:3.3.7:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-api:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-core:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-slf4j-impl:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-1.2-api:jar:2.11.0:compile
[INFO] |  |  +- org.slf4j:jcl-over-slf4j:jar:1.7.25:compile
[INFO] |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.25:compile
[INFO] |  \- org.mule.runtime:mule-module-license-api:jar:4.1.4:compile
[INFO] +- org.mule.distributions:mule-services-all:pom:4.1.4-HF-SNAPSHOT:compile
[INFO] |  +- org.mule.services:mule-service-scheduler:jar:mule-service:1.1.7:compile
[INFO] |  +- org.mule.services:mule-service-oauth:jar:mule-service:1.1.5:compile
[INFO] |  |  \- commons-codec:commons-codec:jar:1.9:compile
[INFO] |  +- org.mule.services:mule-service-http:jar:mule-service:1.1.4:compile
[INFO] |  +- org.mule.services:mule-service-soap:jar:mule-service:1.1.8:compile
[INFO] |  |  \- org.springframework:spring-beans:jar:4.3.19.RELEASE:compile
[INFO] |  \- org.mule.services:mule-service-weave:jar:mule-service:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     +- org.scala-lang:scala-library:jar:2.12.4:compile
[INFO] |     +- org.mule.weave:runtime:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  +- org.mule.weave:wlang:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  +- org.mule.weave:core:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  |  \- org.typelevel:spire_2.12:jar:0.14.1:compile
[INFO] |     |  |     +- org.typelevel:spire-macros_2.12:jar:0.14.1:compile
[INFO] |     |  |     +- org.typelevel:machinist_2.12:jar:0.6.1:compile
[INFO] |     |  |     |  \- org.scala-lang:scala-reflect:jar:2.12.0:compile
[INFO] |     |  |     \- org.typelevel:algebra_2.12:jar:0.7.0:compile
[INFO] |     |  |        \- org.typelevel:cats-kernel_2.12:jar:0.9.0:compile
[INFO] |     |  +- org.mule.weave:core-modules:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  |  +- com.fasterxml.woodstox:woodstox-core:jar:5.0.2:compile
[INFO] |     |  |  |  \- org.codehaus.woodstox:stax2-api:jar:3.1.4:compile
[INFO] |     |  |  +- com.fasterxml:aalto-xml:jar:1.0.0:compile
[INFO] |     |  |  \- javax.mail:mail:jar:1.4.7:compile
[INFO] |     |  |     \- javax.activation:activation:jar:1.1:compile
[INFO] |     |  \- org.mule.weave:debugger:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     +- org.mule.weave:yaml-module:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  \- org.mule.syaml:syaml_2.12:jar:0.1.11:compile
[INFO] |     |     \- org.mule.common:scala-common_2.12:jar:0.1.3:compile
[INFO] |     |        \- org.scalactic:scalactic_2.12:jar:3.0.1:compile
[INFO] |     \- org.mule.weave:mule-tooling-weave:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |        \- org.mule.weave:parser:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |           \- org.parboiled:parboiled_2.12:jar:2.1.4:compile
[INFO] |              \- com.chuusai:shapeless_2.12:jar:2.3.2:compile
[INFO] |                 \- org.typelevel:macro-compat_2.12:jar:1.1.1:compile
[INFO] +- org.mule.runtime:mule-service-soap-api:jar:4.1.4:compile
[INFO] +- org.mule.runtime:mule-service-http-api:jar:4.1.4:compile
[INFO] +- org.mule.runtime:mule-service-oauth-api:jar:4.1.4:compile
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:compile
[INFO]    |  |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:compile
[INFO]    |  |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:compile
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  \- org.jooq:joor:jar:0.9.6:test
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] ----------------< org.mule.distributions:mule-embedded >----------------
[INFO] Building Mule Embedded 4.1.4-HF-SNAPSHOT                          [6/12]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-embedded ---
[INFO] org.mule.distributions:mule-embedded:pom:4.1.4-HF-SNAPSHOT
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- com.google.guava:guava:jar:25.1-jre:test
[INFO]    |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:test
[INFO]    |  |  +- org.checkerframework:checker-qual:jar:2.0.0:test
[INFO]    |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:test
[INFO]    |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:test
[INFO]    |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:test
[INFO]    |  |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:test
[INFO]    |  |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:test
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.apache.commons:commons-lang3:jar:3.6:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  +- org.jooq:joor:jar:0.9.6:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:1.7.25:test
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] ----------< org.mule.distributions:mule-module-embedded-impl >----------
[INFO] Building Mule Embedded Implementation 4.1.4-HF-SNAPSHOT           [7/12]
[INFO] --------------------------------[ jar ]---------------------------------
Downloading from spring: https://repo.spring.io/libs-release/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from mulesoft-releases-ee: https://repository-master.mulesoft.org/nexus/service/local/repositories/releases-ee/content/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from mule: https://repository.mulesoft.org/nexus/content/repositories/public/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from spring-snapshot: http://repo.spring.io/snapshot/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Progress (1): 805 B                   Downloaded from mule: https://repository.mulesoft.org/nexus/content/repositories/public/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml (805 B at 445 B/s)
Progress (1): 805 B                   Downloaded from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml (805 B at 305 B/s)
[WARNING] Could not transfer metadata org.mule.distributions:mule-runtime-impl-bom:4.1.4-HF-SNAPSHOT/maven-metadata.xml from/to spring-snapshot (http://repo.spring.io/snapshot/): Authorization failed for http://repo.spring.io/snapshot/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml 403 Forbidden
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-module-embedded-impl ---
[WARNING] Failure to transfer org.mule.distributions:mule-runtime-impl-bom:4.1.4-HF-SNAPSHOT/maven-metadata.xml from http://repo.spring.io/snapshot/ was cached in the local repository, resolution will not be reattempted until the update interval of spring-snapshot has elapsed or updates are forced. Original error: Could not transfer metadata org.mule.distributions:mule-runtime-impl-bom:4.1.4-HF-SNAPSHOT/maven-metadata.xml from/to spring-snapshot (http://repo.spring.io/snapshot/): Authorization failed for http://repo.spring.io/snapshot/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml 403 Forbidden
Downloading from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/mule-runtime-impl-bom-4.1.4-HF-20201029.023802-96.jar
Progress (1): 2.3 kB                    Downloaded from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/mule-runtime-impl-bom-4.1.4-HF-20201029.023802-96.jar (2.3 kB at 926 B/s)
[INFO] org.mule.distributions:mule-module-embedded-impl:jar:4.1.4-HF-SNAPSHOT
[INFO] +- org.mule.runtime:mule-embedded-api:jar:1.1.3:compile
[INFO] |  +- org.mule:mule-maven-client-api:jar:1.1.4:compile
[INFO] |  |  +- org.apache.maven:maven-model:jar:3.3.9:compile
[INFO] |  |  +- org.codehaus.plexus:plexus-utils:jar:3.0.24:compile
[INFO] |  |  +- org.slf4j:slf4j-api:jar:1.7.25:compile
[INFO] |  |  \- org.mule.runtime:api-annotations:jar:1.0.1:compile
[INFO] |  +- org.mule:mule-maven-client-impl:jar:1.1.4:runtime
[INFO] |  |  +- com.vdurmont:semver4j:jar:2.0.3:compile
[INFO] |  |  +- org.eclipse.aether:aether-api:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.eclipse.aether:aether-util:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.eclipse.aether:aether-connector-basic:jar:1.0.2.v20150114:compile
[INFO] |  |  |  \- org.eclipse.aether:aether-spi:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.eclipse.aether:aether-transport-file:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.eclipse.aether:aether-transport-http:jar:1.0.2.v20150114:compile
[INFO] |  |  |  \- org.slf4j:jcl-over-slf4j:jar:1.7.25:compile
[INFO] |  |  +- org.apache.httpcomponents:httpclient:jar:4.5.4:compile
[INFO] |  |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.7:compile
[INFO] |  |  |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |  |  \- commons-codec:commons-codec:jar:1.9:compile
[INFO] |  |  +- org.apache.maven:maven-settings-builder:jar:3.3.9:runtime
[INFO] |  |  |  +- org.apache.maven:maven-builder-support:jar:3.3.9:compile
[INFO] |  |  |  +- org.codehaus.plexus:plexus-interpolation:jar:1.21:compile
[INFO] |  |  |  +- org.codehaus.plexus:plexus-component-annotations:jar:1.6:compile
[INFO] |  |  |  +- org.apache.maven:maven-settings:jar:3.3.9:runtime
[INFO] |  |  |  \- org.sonatype.plexus:plexus-sec-dispatcher:jar:1.3:runtime
[INFO] |  |  |     \- org.sonatype.plexus:plexus-cipher:jar:1.4:runtime
[INFO] |  |  +- org.apache.maven:maven-aether-provider:jar:3.3.9:compile
[INFO] |  |  |  +- org.apache.maven:maven-model-builder:jar:3.3.9:compile
[INFO] |  |  |  |  \- org.apache.maven:maven-artifact:jar:3.3.9:compile
[INFO] |  |  |  +- org.apache.maven:maven-repository-metadata:jar:3.3.9:compile
[INFO] |  |  |  \- org.eclipse.aether:aether-impl:jar:1.0.2.v20150114:compile
[INFO] |  |  +- com.beust:jcommander:jar:1.69:runtime
[INFO] |  |  \- org.eclipse.sisu:org.eclipse.sisu.plexus:jar:0.3.2:runtime
[INFO] |  +- com.google.code.gson:gson:jar:2.5:compile
[INFO] |  +- com.google.guava:guava:jar:25.1-jre:compile
[INFO] |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:2.0.0:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:compile
[INFO] |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:compile
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:compile
[INFO] |  \- commons-io:commons-io:jar:2.4:compile
[INFO] +- org.mule.distributions:mule-runtime-impl-bom:jar:4.1.4-HF-SNAPSHOT:compile
[INFO] |  +- org.mule.patches:SE-10300-4.1.4:jar:1.0:compile
[INFO] |  +- org.mule.patches:SE-10165-4.1.4:jar:1.0:compile
[INFO] |  +- org.mule.patches:SE-10038-4.1.4:jar:1.0:compile
[INFO] |  +- org.mule.patches:SE-9843-4.1.4:jar:1.0:compile
[INFO] |  +- org.mule.patches:SE-10007-4.1.4:jar:1.0:compile
[INFO] |  +- org.mule.runtime:mule-modules-all:pom:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-launcher:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-deployment:jar:4.1.4:compile
[INFO] |  |  |  |  +- org.mule.runtime:mule-module-deployment-model-impl:jar:4.1.4:compile
[INFO] |  |  |  |  |  \- org.mule.tools.maven:mule-classloader-model:jar:3.1.7:compile
[INFO] |  |  |  |  \- org.mule.runtime:mule-module-service:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-repository:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-reboot:jar:4.1.4:compile
[INFO] |  |  |  \- tanukisoft:wrapper:jar:3.2.3:compile
[INFO] |  |  +- org.mule.runtime:mule-module-extensions-support:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-extensions-api-dsql:jar:1.1.4:compile
[INFO] |  |  |  |  \- org.antlr:antlr-runtime:jar:3.5:compile
[INFO] |  |  |  |     \- org.antlr:stringtemplate:jar:3.2.1:compile
[INFO] |  |  |  +- org.mule.runtime:mule-extensions-api-persistence:jar:1.1.4:compile
[INFO] |  |  |  |  \- org.mule.runtime:mule-metadata-model-persistence:jar:1.1.5:compile
[INFO] |  |  |  \- org.mule.runtime:mule-module-deployment-model:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-extensions-soap-support:jar:4.1.4:compile
[INFO] |  |  |  \- org.mule.runtime:mule-extensions-soap-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-extensions-spring-support:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-tls:jar:4.1.4:compile
[INFO] |  |  |  +- jboss:javassist:jar:3.7.ga:compile
[INFO] |  |  |  +- joda-time:joda-time:jar:2.9.1:compile
[INFO] |  |  |  +- dom4j:dom4j:jar:1.6.1:compile
[INFO] |  |  |  \- jaxen:jaxen:jar:1.1.1:compile
[INFO] |  |  +- org.mule.runtime:mule-module-extensions-xml-support:jar:4.1.4:compile
[INFO] |  |  |  \- org.mule.runtime:mule-metadata-model-catalog:jar:1.1.5:compile
[INFO] |  |  |     \- org.mule.runtime:mule-metadata-model-raml:jar:1.1.5:compile
[INFO] |  |  |        \- org.raml:raml-parser-2:jar:1.0.27:compile
[INFO] |  |  |           +- org.raml:yagi:jar:1.0.27:compile
[INFO] |  |  |           |  +- commons-lang:commons-lang:jar:2.6:compile
[INFO] |  |  |           |  +- com.google.code.findbugs:annotations:jar:3.0.0:compile
[INFO] |  |  |           |  \- com.fasterxml.jackson.module:jackson-module-jsonSchema:jar:2.9.6:compile
[INFO] |  |  |           |     \- javax.validation:validation-api:jar:1.1.0.Final:compile
[INFO] |  |  |           +- com.github.java-json-tools:json-schema-validator:jar:2.2.8:compile
[INFO] |  |  |           |  +- com.github.java-json-tools:json-schema-core:jar:1.2.8:compile
[INFO] |  |  |           |  |  +- org.mozilla:rhino:jar:1.7R4:compile
[INFO] |  |  |           |  |  +- com.github.fge:jackson-coreutils:jar:1.8:compile
[INFO] |  |  |           |  |  |  \- com.github.fge:msg-simple:jar:1.1:compile
[INFO] |  |  |           |  |  |     \- com.github.fge:btf:jar:1.2:compile
[INFO] |  |  |           |  |  \- com.github.fge:uri-template:jar:0.9:compile
[INFO] |  |  |           |  +- javax.mail:mailapi:jar:1.4.3:compile
[INFO] |  |  |           |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.0.0:compile
[INFO] |  |  |           |  \- net.sf.jopt-simple:jopt-simple:jar:5.0.3:compile
[INFO] |  |  |           +- com.googlecode.juniversalchardet:juniversalchardet:jar:1.0.3:compile
[INFO] |  |  |           +- org.apache.ws.xmlschema:xmlschema-core:jar:2.2.1:compile
[INFO] |  |  |           +- javax.json:javax.json-api:jar:1.0:compile
[INFO] |  |  |           \- org.glassfish:javax.json:jar:1.0.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-policy-api:jar:1.1.2:compile
[INFO] |  |  +- org.mule.runtime:mule-module-spring-config:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-artifact:jar:4.1.4:compile
[INFO] |  |  |  +- org.springframework:spring-context:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  |  +- org.springframework:spring-core:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  |  \- org.springframework:spring-expression:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  \- org.springframework:spring-aop:jar:4.3.19.RELEASE:compile
[INFO] |  |  +- org.mule.runtime:mule-module-tooling-support:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-global-config:jar:4.1.4:compile
[INFO] |  |  |  +- com.typesafe:config:jar:1.3.1:compile
[INFO] |  |  |  \- org.mule.runtime:mule-module-container:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-logging:jar:4.1.4:compile
[INFO] |  |  |  +- com.lmax:disruptor:jar:3.3.7:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-api:jar:2.11.0:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-core:jar:2.11.0:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-slf4j-impl:jar:2.11.0:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-1.2-api:jar:2.11.0:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.25:compile
[INFO] |  |  \- org.mule.runtime:mule-module-license-api:jar:4.1.4:compile
[INFO] |  +- org.mule.distributions:mule-services-all:pom:4.1.4-HF-SNAPSHOT:compile
[INFO] |  |  +- org.mule.services:mule-service-scheduler:jar:mule-service:1.1.7:compile
[INFO] |  |  +- org.mule.services:mule-service-oauth:jar:mule-service:1.1.5:compile
[INFO] |  |  +- org.mule.services:mule-service-http:jar:mule-service:1.1.4:compile
[INFO] |  |  +- org.mule.services:mule-service-soap:jar:mule-service:1.1.8:compile
[INFO] |  |  |  \- org.springframework:spring-beans:jar:4.3.19.RELEASE:compile
[INFO] |  |  \- org.mule.services:mule-service-weave:jar:mule-service:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |     +- org.scala-lang:scala-library:jar:2.12.4:compile
[INFO] |  |     +- org.mule.weave:runtime:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |     |  +- org.mule.weave:wlang:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |     |  +- org.mule.weave:core:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |     |  |  \- org.typelevel:spire_2.12:jar:0.14.1:compile
[INFO] |  |     |  |     +- org.typelevel:spire-macros_2.12:jar:0.14.1:compile
[INFO] |  |     |  |     +- org.typelevel:machinist_2.12:jar:0.6.1:compile
[INFO] |  |     |  |     |  \- org.scala-lang:scala-reflect:jar:2.12.0:compile
[INFO] |  |     |  |     \- org.typelevel:algebra_2.12:jar:0.7.0:compile
[INFO] |  |     |  |        \- org.typelevel:cats-kernel_2.12:jar:0.9.0:compile
[INFO] |  |     |  +- org.mule.weave:core-modules:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |     |  |  +- com.fasterxml.woodstox:woodstox-core:jar:5.0.2:compile
[INFO] |  |     |  |  |  \- org.codehaus.woodstox:stax2-api:jar:3.1.4:compile
[INFO] |  |     |  |  +- com.fasterxml:aalto-xml:jar:1.0.0:compile
[INFO] |  |     |  |  \- javax.mail:mail:jar:1.4.7:compile
[INFO] |  |     |  |     \- javax.activation:activation:jar:1.1:compile
[INFO] |  |     |  \- org.mule.weave:debugger:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |     +- org.mule.weave:yaml-module:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |     |  \- org.mule.syaml:syaml_2.12:jar:0.1.11:compile
[INFO] |  |     |     \- org.mule.common:scala-common_2.12:jar:0.1.3:compile
[INFO] |  |     |        \- org.scalactic:scalactic_2.12:jar:3.0.1:compile
[INFO] |  |     \- org.mule.weave:mule-tooling-weave:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |        \- org.mule.weave:parser:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |           \- org.parboiled:parboiled_2.12:jar:2.1.4:compile
[INFO] |  |              \- com.chuusai:shapeless_2.12:jar:2.3.2:compile
[INFO] |  |                 \- org.typelevel:macro-compat_2.12:jar:1.1.1:compile
[INFO] |  +- org.mule.runtime:mule-service-soap-api:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-service-http-api:jar:4.1.4:compile
[INFO] |  \- org.mule.runtime:mule-service-oauth-api:jar:4.1.4:compile
[INFO] +- org.mule.runtime:mule-core:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-api:jar:1.1.5:compile
[INFO] |  |  \- org.mule.runtime:mule-artifact-declaration:jar:1.1.1:compile
[INFO] |  +- org.mule.runtime:mule-module-dsl-api:jar:1.1.4:compile
[INFO] |  +- org.mule.runtime:mule-extensions-api:jar:1.1.4:compile
[INFO] |  |  \- org.reactivestreams:reactive-streams:jar:1.0.2:compile
[INFO] |  +- org.mule.runtime:mule-module-http-policy-api:jar:1.1.2:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-xml:jar:1.1.5:compile
[INFO] |  |  +- org.apache.xmlbeans:xmlbeans:jar:2.6.0:compile
[INFO] |  |  \- org.mule.apache:xerces2-xsd11:jar:2.11.3:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-java:jar:1.1.5:compile
[INFO] |  +- org.mule.runtime:mule-message-metadata-model:jar:1.1.5:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-json:jar:1.1.5:compile
[INFO] |  |  \- org.everit.json:org.everit.json.schema:jar:1.5.0:compile
[INFO] |  |     +- org.json:json:jar:20160810:compile
[INFO] |  |     \- commons-validator:commons-validator:jar:1.5.1:compile
[INFO] |  |        \- commons-digester:commons-digester:jar:1.8.1:compile
[INFO] |  +- commons-beanutils:commons-beanutils:jar:1.9.3:compile
[INFO] |  +- com.github.stephenc.eaio-uuid:uuid:jar:3.4.0:compile
[INFO] |  |  \- com.github.stephenc.eaio-grabbag:grabbag:jar:1.8.1:compile
[INFO] |  +- commons-cli:commons-cli:jar:1.2:compile
[INFO] |  +- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  +- org.apache.commons:commons-lang3:jar:3.6:compile
[INFO] |  +- commons-pool:commons-pool:jar:1.6:compile
[INFO] |  +- javax.transaction:javax.transaction-api:jar:1.2:compile
[INFO] |  +- org.apache.geronimo.specs:geronimo-j2ee-connector_1.5_spec:jar:2.0.0:compile
[INFO] |  +- javax.inject:javax.inject:jar:1:compile
[INFO] |  +- org.mule.mvel:mule-mvel2:jar:2.1.9-MULE-017:compile
[INFO] |  +- org.jgrapht:jgrapht-jdk1.5:jar:0.7.3:compile
[INFO] |  +- org.reflections:reflections:jar:0.9.10:compile
[INFO] |  +- org.mule.projectreactor:reactor-core:jar:3.2.0.M1-MULE-002:compile
[INFO] |  +- io.projectreactor.addons:reactor-extra:jar:3.1.2.RELEASE:compile
[INFO] |  +- org.yaml:snakeyaml:jar:1.18:compile
[INFO] |  +- cglib:cglib-nodep:jar:3.2.6:compile
[INFO] |  +- org.apache.commons:commons-pool2:jar:2.5.0:compile
[INFO] |  +- it.unimi.dsi:fastutil:jar:8.1.1:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:2.6.2:compile
[INFO] |  \- javax.jms:javax.jms-api:jar:2.0.1:compile
[INFO] +- net.lingala.zip4j:zip4j:jar:1.3.2:compile
[INFO] +- log4j:log4j:jar:1.2.17:compile
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:compile
[INFO]    |  |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:compile
[INFO]    |  |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:compile
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  \- org.jooq:joor:jar:0.9.6:test
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] --------< org.mule.distributions:mule-module-embedded-impl-bom >--------
[INFO] Building Mule Module Embedded Implementation Distribution BOM 4.1.4-HF-SNAPSHOT [8/12]
[INFO] --------------------------------[ pom ]---------------------------------
Downloading from mulesoft-releases-ee: https://repository-master.mulesoft.org/nexus/service/local/repositories/releases-ee/content/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from spring: https://repo.spring.io/libs-release/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from mule: https://repository.mulesoft.org/nexus/content/repositories/public/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from spring-snapshot: http://repo.spring.io/snapshot/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Progress (1): 1.2 kB                    Downloaded from mule: https://repository.mulesoft.org/nexus/content/repositories/public/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml (1.2 kB at 992 B/s)
Progress (1): 1.2 kB                    Downloaded from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml (1.2 kB at 590 B/s)
[WARNING] Could not transfer metadata org.mule.distributions:mule-module-embedded-impl:4.1.4-HF-SNAPSHOT/maven-metadata.xml from/to spring-snapshot (http://repo.spring.io/snapshot/): Authorization failed for http://repo.spring.io/snapshot/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml 403 Forbidden
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-module-embedded-impl-bom ---
[WARNING] Failure to transfer org.mule.distributions:mule-module-embedded-impl:4.1.4-HF-SNAPSHOT/maven-metadata.xml from http://repo.spring.io/snapshot/ was cached in the local repository, resolution will not be reattempted until the update interval of spring-snapshot has elapsed or updates are forced. Original error: Could not transfer metadata org.mule.distributions:mule-module-embedded-impl:4.1.4-HF-SNAPSHOT/maven-metadata.xml from/to spring-snapshot (http://repo.spring.io/snapshot/): Authorization failed for http://repo.spring.io/snapshot/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml 403 Forbidden
Downloading from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/mule-module-embedded-impl-4.1.4-HF-20201029.023802-96.jar
Progress (1): 3.7/11 kBProgress (1): 7.8/11 kBProgress (1): 11 kB                       Downloaded from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/mule-module-embedded-impl-4.1.4-HF-20201029.023802-96.jar (11 kB at 5.0 kB/s)
[INFO] org.mule.distributions:mule-module-embedded-impl-bom:pom:4.1.4-HF-SNAPSHOT
[INFO] +- org.mule.distributions:mule-module-embedded-impl:jar:4.1.4-HF-SNAPSHOT:compile
[INFO] |  +- org.mule.runtime:mule-embedded-api:jar:1.1.3:compile
[INFO] |  |  +- org.mule:mule-maven-client-api:jar:1.1.4:compile
[INFO] |  |  |  +- org.apache.maven:maven-model:jar:3.3.9:compile
[INFO] |  |  |  +- org.codehaus.plexus:plexus-utils:jar:3.0.24:compile
[INFO] |  |  |  \- org.mule.runtime:api-annotations:jar:1.0.1:compile
[INFO] |  |  +- org.mule:mule-maven-client-impl:jar:1.1.4:runtime
[INFO] |  |  |  +- com.vdurmont:semver4j:jar:2.0.3:runtime
[INFO] |  |  |  +- org.eclipse.aether:aether-api:jar:1.0.2.v20150114:runtime
[INFO] |  |  |  +- org.eclipse.aether:aether-util:jar:1.0.2.v20150114:runtime
[INFO] |  |  |  +- org.eclipse.aether:aether-connector-basic:jar:1.0.2.v20150114:runtime
[INFO] |  |  |  |  \- org.eclipse.aether:aether-spi:jar:1.0.2.v20150114:runtime
[INFO] |  |  |  +- org.eclipse.aether:aether-transport-file:jar:1.0.2.v20150114:runtime
[INFO] |  |  |  +- org.eclipse.aether:aether-transport-http:jar:1.0.2.v20150114:runtime
[INFO] |  |  |  |  \- org.slf4j:jcl-over-slf4j:jar:1.7.25:runtime
[INFO] |  |  |  +- org.apache.httpcomponents:httpclient:jar:4.5.4:runtime
[INFO] |  |  |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.7:runtime
[INFO] |  |  |  |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |  |  |  \- commons-codec:commons-codec:jar:1.9:runtime
[INFO] |  |  |  +- org.apache.maven:maven-settings-builder:jar:3.3.9:runtime
[INFO] |  |  |  |  +- org.apache.maven:maven-builder-support:jar:3.3.9:runtime
[INFO] |  |  |  |  +- org.codehaus.plexus:plexus-interpolation:jar:1.21:runtime
[INFO] |  |  |  |  +- org.codehaus.plexus:plexus-component-annotations:jar:1.6:runtime
[INFO] |  |  |  |  +- org.apache.maven:maven-settings:jar:3.3.9:runtime
[INFO] |  |  |  |  \- org.sonatype.plexus:plexus-sec-dispatcher:jar:1.3:runtime
[INFO] |  |  |  |     \- org.sonatype.plexus:plexus-cipher:jar:1.4:runtime
[INFO] |  |  |  +- org.apache.maven:maven-aether-provider:jar:3.3.9:runtime
[INFO] |  |  |  |  +- org.apache.maven:maven-model-builder:jar:3.3.9:runtime
[INFO] |  |  |  |  |  \- org.apache.maven:maven-artifact:jar:3.3.9:runtime
[INFO] |  |  |  |  +- org.apache.maven:maven-repository-metadata:jar:3.3.9:runtime
[INFO] |  |  |  |  \- org.eclipse.aether:aether-impl:jar:1.0.2.v20150114:runtime
[INFO] |  |  |  +- com.beust:jcommander:jar:1.69:runtime
[INFO] |  |  |  \- org.eclipse.sisu:org.eclipse.sisu.plexus:jar:0.3.2:runtime
[INFO] |  |  +- com.google.code.gson:gson:jar:2.5:compile
[INFO] |  |  +- com.google.guava:guava:jar:25.1-jre:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:2.0.0:compile
[INFO] |  |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:compile
[INFO] |  |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:compile
[INFO] |  |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:compile
[INFO] |  |  \- commons-io:commons-io:jar:2.4:compile
[INFO] |  +- org.mule.runtime:mule-core:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-api:jar:1.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-metadata-model-api:jar:1.1.5:compile
[INFO] |  |  |  \- org.mule.runtime:mule-artifact-declaration:jar:1.1.1:compile
[INFO] |  |  +- org.mule.runtime:mule-module-dsl-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-extensions-api:jar:1.1.4:compile
[INFO] |  |  |  \- org.reactivestreams:reactive-streams:jar:1.0.2:compile
[INFO] |  |  +- org.mule.runtime:mule-module-http-policy-api:jar:1.1.2:compile
[INFO] |  |  |  \- org.mule.runtime:mule-module-policy-api:jar:1.1.2:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-xml:jar:1.1.5:compile
[INFO] |  |  |  +- org.apache.xmlbeans:xmlbeans:jar:2.6.0:compile
[INFO] |  |  |  \- org.mule.apache:xerces2-xsd11:jar:2.11.3:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-java:jar:1.1.5:compile
[INFO] |  |  +- org.mule.runtime:mule-message-metadata-model:jar:1.1.5:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-json:jar:1.1.5:compile
[INFO] |  |  |  \- org.everit.json:org.everit.json.schema:jar:1.5.0:compile
[INFO] |  |  |     +- org.json:json:jar:20160810:compile
[INFO] |  |  |     \- commons-validator:commons-validator:jar:1.5.1:compile
[INFO] |  |  |        \- commons-digester:commons-digester:jar:1.8.1:compile
[INFO] |  |  +- commons-beanutils:commons-beanutils:jar:1.9.3:compile
[INFO] |  |  +- com.github.stephenc.eaio-uuid:uuid:jar:3.4.0:compile
[INFO] |  |  |  \- com.github.stephenc.eaio-grabbag:grabbag:jar:1.8.1:compile
[INFO] |  |  +- commons-cli:commons-cli:jar:1.2:compile
[INFO] |  |  +- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.6:compile
[INFO] |  |  +- commons-pool:commons-pool:jar:1.6:compile
[INFO] |  |  +- javax.transaction:javax.transaction-api:jar:1.2:compile
[INFO] |  |  +- org.apache.geronimo.specs:geronimo-j2ee-connector_1.5_spec:jar:2.0.0:compile
[INFO] |  |  +- javax.inject:javax.inject:jar:1:compile
[INFO] |  |  +- org.mule.mvel:mule-mvel2:jar:2.1.9-MULE-017:compile
[INFO] |  |  +- org.jgrapht:jgrapht-jdk1.5:jar:0.7.3:compile
[INFO] |  |  +- org.reflections:reflections:jar:0.9.10:compile
[INFO] |  |  +- org.mule.projectreactor:reactor-core:jar:3.2.0.M1-MULE-002:compile
[INFO] |  |  +- io.projectreactor.addons:reactor-extra:jar:3.1.2.RELEASE:compile
[INFO] |  |  +- org.yaml:snakeyaml:jar:1.18:compile
[INFO] |  |  +- cglib:cglib-nodep:jar:3.2.6:compile
[INFO] |  |  +- org.apache.commons:commons-pool2:jar:2.5.0:compile
[INFO] |  |  +- it.unimi.dsi:fastutil:jar:8.1.1:compile
[INFO] |  |  +- com.github.ben-manes.caffeine:caffeine:jar:2.6.2:compile
[INFO] |  |  \- javax.jms:javax.jms-api:jar:2.0.1:compile
[INFO] |  +- net.lingala.zip4j:zip4j:jar:1.3.2:compile
[INFO] |  \- log4j:log4j:jar:1.2.17:compile
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:test
[INFO]    |  |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:test
[INFO]    |  |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:test
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  +- org.jooq:joor:jar:0.9.6:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:1.7.25:compile
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] ----------< org.mule.distributions:mule-module-embedded-test >----------
[INFO] Building Mule Embedded Implementation Tests 4.1.4-HF-SNAPSHOT     [9/12]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-module-embedded-test ---
[WARNING] Failure to transfer org.mule.distributions:mule-module-embedded-impl:4.1.4-HF-SNAPSHOT/maven-metadata.xml from http://repo.spring.io/snapshot/ was cached in the local repository, resolution will not be reattempted until the update interval of spring-snapshot has elapsed or updates are forced. Original error: Could not transfer metadata org.mule.distributions:mule-module-embedded-impl:4.1.4-HF-SNAPSHOT/maven-metadata.xml from/to spring-snapshot (http://repo.spring.io/snapshot/): Authorization failed for http://repo.spring.io/snapshot/org/mule/distributions/mule-module-embedded-impl/4.1.4-HF-SNAPSHOT/maven-metadata.xml 403 Forbidden
[WARNING] Failure to transfer org.mule.distributions:mule-runtime-impl-bom:4.1.4-HF-SNAPSHOT/maven-metadata.xml from http://repo.spring.io/snapshot/ was cached in the local repository, resolution will not be reattempted until the update interval of spring-snapshot has elapsed or updates are forced. Original error: Could not transfer metadata org.mule.distributions:mule-runtime-impl-bom:4.1.4-HF-SNAPSHOT/maven-metadata.xml from/to spring-snapshot (http://repo.spring.io/snapshot/): Authorization failed for http://repo.spring.io/snapshot/org/mule/distributions/mule-runtime-impl-bom/4.1.4-HF-SNAPSHOT/maven-metadata.xml 403 Forbidden
[INFO] org.mule.distributions:mule-module-embedded-test:jar:4.1.4-HF-SNAPSHOT
[INFO] +- org.mule.runtime:mule-embedded-api:jar:1.1.3:compile
[INFO] |  +- org.mule:mule-maven-client-api:jar:1.1.4:compile
[INFO] |  |  +- org.apache.maven:maven-model:jar:3.3.9:compile
[INFO] |  |  +- org.codehaus.plexus:plexus-utils:jar:3.0.24:compile
[INFO] |  |  +- org.slf4j:slf4j-api:jar:1.7.25:compile
[INFO] |  |  \- org.mule.runtime:api-annotations:jar:1.0.1:compile
[INFO] |  +- org.mule:mule-maven-client-impl:jar:1.1.4:compile
[INFO] |  |  +- com.vdurmont:semver4j:jar:2.0.3:compile
[INFO] |  |  +- org.eclipse.aether:aether-api:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.eclipse.aether:aether-util:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.eclipse.aether:aether-connector-basic:jar:1.0.2.v20150114:compile
[INFO] |  |  |  \- org.eclipse.aether:aether-spi:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.eclipse.aether:aether-transport-file:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.eclipse.aether:aether-transport-http:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.apache.maven:maven-settings-builder:jar:3.3.9:compile
[INFO] |  |  |  +- org.apache.maven:maven-builder-support:jar:3.3.9:compile
[INFO] |  |  |  +- org.codehaus.plexus:plexus-interpolation:jar:1.21:compile
[INFO] |  |  |  +- org.apache.maven:maven-settings:jar:3.3.9:compile
[INFO] |  |  |  \- org.sonatype.plexus:plexus-sec-dispatcher:jar:1.3:compile
[INFO] |  |  |     \- org.sonatype.plexus:plexus-cipher:jar:1.4:compile
[INFO] |  |  +- org.apache.maven:maven-aether-provider:jar:3.3.9:compile
[INFO] |  |  |  +- org.apache.maven:maven-model-builder:jar:3.3.9:compile
[INFO] |  |  |  |  \- org.apache.maven:maven-artifact:jar:3.3.9:compile
[INFO] |  |  |  +- org.apache.maven:maven-repository-metadata:jar:3.3.9:compile
[INFO] |  |  |  \- org.eclipse.aether:aether-impl:jar:1.0.2.v20150114:compile
[INFO] |  |  +- com.beust:jcommander:jar:1.69:compile
[INFO] |  |  \- org.eclipse.sisu:org.eclipse.sisu.plexus:jar:0.3.2:compile
[INFO] |  +- com.google.code.gson:gson:jar:2.5:compile
[INFO] |  +- com.google.guava:guava:jar:25.1-jre:compile
[INFO] |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:2.0.0:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:compile
[INFO] |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:compile
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:compile
[INFO] |  \- commons-io:commons-io:jar:2.4:compile
[INFO] +- org.mule.distributions:mule-module-embedded-impl:jar:4.1.4-HF-SNAPSHOT:compile
[INFO] |  +- org.mule.distributions:mule-runtime-impl-bom:jar:4.1.4-HF-SNAPSHOT:compile
[INFO] |  |  +- org.mule.patches:SE-10300-4.1.4:jar:1.0:compile
[INFO] |  |  +- org.mule.patches:SE-10165-4.1.4:jar:1.0:compile
[INFO] |  |  +- org.mule.patches:SE-10038-4.1.4:jar:1.0:compile
[INFO] |  |  +- org.mule.patches:SE-9843-4.1.4:jar:1.0:compile
[INFO] |  |  +- org.mule.patches:SE-10007-4.1.4:jar:1.0:compile
[INFO] |  |  +- org.mule.runtime:mule-modules-all:pom:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-launcher:jar:4.1.4:compile
[INFO] |  |  |  |  +- org.mule.runtime:mule-module-deployment:jar:4.1.4:compile
[INFO] |  |  |  |  |  +- org.mule.runtime:mule-module-deployment-model-impl:jar:4.1.4:compile
[INFO] |  |  |  |  |  |  \- org.mule.tools.maven:mule-classloader-model:jar:3.1.7:compile
[INFO] |  |  |  |  |  \- org.mule.runtime:mule-module-service:jar:4.1.4:compile
[INFO] |  |  |  |  +- org.mule.runtime:mule-module-repository:jar:4.1.4:compile
[INFO] |  |  |  |  +- org.mule.runtime:mule-module-reboot:jar:4.1.4:compile
[INFO] |  |  |  |  \- tanukisoft:wrapper:jar:3.2.3:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-extensions-support:jar:4.1.4:compile
[INFO] |  |  |  |  +- org.mule.runtime:mule-extensions-api-dsql:jar:1.1.4:compile
[INFO] |  |  |  |  |  \- org.antlr:antlr-runtime:jar:3.5:compile
[INFO] |  |  |  |  |     \- org.antlr:stringtemplate:jar:3.2.1:compile
[INFO] |  |  |  |  +- org.mule.runtime:mule-extensions-api-persistence:jar:1.1.4:compile
[INFO] |  |  |  |  |  \- org.mule.runtime:mule-metadata-model-persistence:jar:1.1.5:compile
[INFO] |  |  |  |  \- org.mule.runtime:mule-module-deployment-model:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-extensions-soap-support:jar:4.1.4:compile
[INFO] |  |  |  |  \- org.mule.runtime:mule-extensions-soap-api:jar:1.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-extensions-spring-support:jar:4.1.4:compile
[INFO] |  |  |  |  +- org.mule.runtime:mule-module-tls:jar:4.1.4:compile
[INFO] |  |  |  |  +- jboss:javassist:jar:3.7.ga:compile
[INFO] |  |  |  |  +- joda-time:joda-time:jar:2.9.1:compile
[INFO] |  |  |  |  +- dom4j:dom4j:jar:1.6.1:compile
[INFO] |  |  |  |  \- jaxen:jaxen:jar:1.1.1:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-extensions-xml-support:jar:4.1.4:compile
[INFO] |  |  |  |  \- org.mule.runtime:mule-metadata-model-catalog:jar:1.1.5:compile
[INFO] |  |  |  |     \- org.mule.runtime:mule-metadata-model-raml:jar:1.1.5:compile
[INFO] |  |  |  |        \- org.raml:raml-parser-2:jar:1.0.27:compile
[INFO] |  |  |  |           +- org.raml:yagi:jar:1.0.27:compile
[INFO] |  |  |  |           |  +- commons-lang:commons-lang:jar:2.6:compile
[INFO] |  |  |  |           |  +- com.google.code.findbugs:annotations:jar:3.0.0:compile
[INFO] |  |  |  |           |  \- com.fasterxml.jackson.module:jackson-module-jsonSchema:jar:2.9.6:compile
[INFO] |  |  |  |           |     \- javax.validation:validation-api:jar:1.1.0.Final:compile
[INFO] |  |  |  |           +- com.github.java-json-tools:json-schema-validator:jar:2.2.8:compile
[INFO] |  |  |  |           |  +- com.github.java-json-tools:json-schema-core:jar:1.2.8:compile
[INFO] |  |  |  |           |  |  +- org.mozilla:rhino:jar:1.7R4:compile
[INFO] |  |  |  |           |  |  +- com.github.fge:jackson-coreutils:jar:1.8:compile
[INFO] |  |  |  |           |  |  |  \- com.github.fge:msg-simple:jar:1.1:compile
[INFO] |  |  |  |           |  |  |     \- com.github.fge:btf:jar:1.2:compile
[INFO] |  |  |  |           |  |  \- com.github.fge:uri-template:jar:0.9:compile
[INFO] |  |  |  |           |  +- javax.mail:mailapi:jar:1.4.3:compile
[INFO] |  |  |  |           |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.0.0:compile
[INFO] |  |  |  |           |  \- net.sf.jopt-simple:jopt-simple:jar:5.0.3:compile
[INFO] |  |  |  |           +- com.googlecode.juniversalchardet:juniversalchardet:jar:1.0.3:compile
[INFO] |  |  |  |           +- org.apache.ws.xmlschema:xmlschema-core:jar:2.2.1:compile
[INFO] |  |  |  |           +- javax.json:javax.json-api:jar:1.0:compile
[INFO] |  |  |  |           \- org.glassfish:javax.json:jar:1.0.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-policy-api:jar:1.1.2:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-spring-config:jar:4.1.4:compile
[INFO] |  |  |  |  +- org.mule.runtime:mule-module-artifact:jar:4.1.4:compile
[INFO] |  |  |  |  +- org.springframework:spring-context:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  |  |  +- org.springframework:spring-core:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  |  |  \- org.springframework:spring-expression:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  |  \- org.springframework:spring-aop:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-tooling-support:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-global-config:jar:4.1.4:compile
[INFO] |  |  |  |  +- com.typesafe:config:jar:1.3.1:compile
[INFO] |  |  |  |  \- org.mule.runtime:mule-module-container:jar:4.1.4:compile
[INFO] |  |  |  \- org.mule.runtime:mule-module-license-api:jar:4.1.4:compile
[INFO] |  |  +- org.mule.distributions:mule-services-all:pom:4.1.4-HF-SNAPSHOT:compile
[INFO] |  |  |  +- org.mule.services:mule-service-scheduler:jar:mule-service:1.1.7:compile
[INFO] |  |  |  +- org.mule.services:mule-service-oauth:jar:mule-service:1.1.5:compile
[INFO] |  |  |  +- org.mule.services:mule-service-http:jar:mule-service:1.1.4:compile
[INFO] |  |  |  +- org.mule.services:mule-service-soap:jar:mule-service:1.1.8:compile
[INFO] |  |  |  |  \- org.springframework:spring-beans:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  \- org.mule.services:mule-service-weave:jar:mule-service:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |     +- org.scala-lang:scala-library:jar:2.12.4:compile
[INFO] |  |  |     +- org.mule.weave:runtime:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |     |  +- org.mule.weave:wlang:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |     |  +- org.mule.weave:core:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |     |  |  \- org.typelevel:spire_2.12:jar:0.14.1:compile
[INFO] |  |  |     |  |     +- org.typelevel:spire-macros_2.12:jar:0.14.1:compile
[INFO] |  |  |     |  |     +- org.typelevel:machinist_2.12:jar:0.6.1:compile
[INFO] |  |  |     |  |     |  \- org.scala-lang:scala-reflect:jar:2.12.0:compile
[INFO] |  |  |     |  |     \- org.typelevel:algebra_2.12:jar:0.7.0:compile
[INFO] |  |  |     |  |        \- org.typelevel:cats-kernel_2.12:jar:0.9.0:compile
[INFO] |  |  |     |  +- org.mule.weave:core-modules:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |     |  |  +- com.fasterxml.woodstox:woodstox-core:jar:5.0.2:compile
[INFO] |  |  |     |  |  |  \- org.codehaus.woodstox:stax2-api:jar:3.1.4:compile
[INFO] |  |  |     |  |  +- com.fasterxml:aalto-xml:jar:1.0.0:compile
[INFO] |  |  |     |  |  \- javax.mail:mail:jar:1.4.7:compile
[INFO] |  |  |     |  |     \- javax.activation:activation:jar:1.1:compile
[INFO] |  |  |     |  \- org.mule.weave:debugger:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |     +- org.mule.weave:yaml-module:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |     |  \- org.mule.syaml:syaml_2.12:jar:0.1.11:compile
[INFO] |  |  |     |     \- org.mule.common:scala-common_2.12:jar:0.1.3:compile
[INFO] |  |  |     |        \- org.scalactic:scalactic_2.12:jar:3.0.1:compile
[INFO] |  |  |     \- org.mule.weave:mule-tooling-weave:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |        \- org.mule.weave:parser:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |  |  |           \- org.parboiled:parboiled_2.12:jar:2.1.4:compile
[INFO] |  |  |              \- com.chuusai:shapeless_2.12:jar:2.3.2:compile
[INFO] |  |  |                 \- org.typelevel:macro-compat_2.12:jar:1.1.1:compile
[INFO] |  |  +- org.mule.runtime:mule-service-soap-api:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-service-http-api:jar:4.1.4:compile
[INFO] |  |  \- org.mule.runtime:mule-service-oauth-api:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-core:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-api:jar:1.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-metadata-model-api:jar:1.1.5:compile
[INFO] |  |  |  \- org.mule.runtime:mule-artifact-declaration:jar:1.1.1:compile
[INFO] |  |  +- org.mule.runtime:mule-module-dsl-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-extensions-api:jar:1.1.4:compile
[INFO] |  |  |  \- org.reactivestreams:reactive-streams:jar:1.0.2:compile
[INFO] |  |  +- org.mule.runtime:mule-module-http-policy-api:jar:1.1.2:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-xml:jar:1.1.5:compile
[INFO] |  |  |  +- org.apache.xmlbeans:xmlbeans:jar:2.6.0:compile
[INFO] |  |  |  \- org.mule.apache:xerces2-xsd11:jar:2.11.3:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-java:jar:1.1.5:compile
[INFO] |  |  +- org.mule.runtime:mule-message-metadata-model:jar:1.1.5:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-json:jar:1.1.5:compile
[INFO] |  |  |  \- org.everit.json:org.everit.json.schema:jar:1.5.0:compile
[INFO] |  |  |     \- commons-validator:commons-validator:jar:1.5.1:compile
[INFO] |  |  |        \- commons-digester:commons-digester:jar:1.8.1:compile
[INFO] |  |  +- commons-beanutils:commons-beanutils:jar:1.9.3:compile
[INFO] |  |  +- com.github.stephenc.eaio-uuid:uuid:jar:3.4.0:compile
[INFO] |  |  |  \- com.github.stephenc.eaio-grabbag:grabbag:jar:1.8.1:compile
[INFO] |  |  +- commons-cli:commons-cli:jar:1.2:compile
[INFO] |  |  +- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.6:compile
[INFO] |  |  +- commons-pool:commons-pool:jar:1.6:compile
[INFO] |  |  +- javax.transaction:javax.transaction-api:jar:1.2:compile
[INFO] |  |  +- org.apache.geronimo.specs:geronimo-j2ee-connector_1.5_spec:jar:2.0.0:compile
[INFO] |  |  +- javax.inject:javax.inject:jar:1:compile
[INFO] |  |  +- org.mule.mvel:mule-mvel2:jar:2.1.9-MULE-017:compile
[INFO] |  |  +- org.jgrapht:jgrapht-jdk1.5:jar:0.7.3:compile
[INFO] |  |  +- org.reflections:reflections:jar:0.9.10:compile
[INFO] |  |  +- org.mule.projectreactor:reactor-core:jar:3.2.0.M1-MULE-002:compile
[INFO] |  |  +- io.projectreactor.addons:reactor-extra:jar:3.1.2.RELEASE:compile
[INFO] |  |  +- org.yaml:snakeyaml:jar:1.18:compile
[INFO] |  |  +- cglib:cglib-nodep:jar:3.2.6:compile
[INFO] |  |  +- org.apache.commons:commons-pool2:jar:2.5.0:compile
[INFO] |  |  +- it.unimi.dsi:fastutil:jar:8.1.1:compile
[INFO] |  |  +- com.github.ben-manes.caffeine:caffeine:jar:2.6.2:compile
[INFO] |  |  \- javax.jms:javax.jms-api:jar:2.0.1:compile
[INFO] |  +- net.lingala.zip4j:zip4j:jar:1.3.2:compile
[INFO] |  \- log4j:log4j:jar:1.2.17:compile
[INFO] +- org.mule.tests:mule-tests-unit:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-logging:jar:4.1.4:compile
[INFO] |  |  +- com.lmax:disruptor:jar:3.3.7:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-api:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-1.2-api:jar:2.11.0:compile
[INFO] |  |  +- org.slf4j:jcl-over-slf4j:jar:1.7.25:compile
[INFO] |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.25:compile
[INFO] |  +- junit:junit:jar:4.12:compile
[INFO] |  +- org.mockito:mockito-core:jar:1.10.19:compile
[INFO] |  |  \- org.objenesis:objenesis:jar:2.1:runtime
[INFO] |  +- org.hamcrest:hamcrest-library:jar:1.3:compile
[INFO] |  |  \- org.hamcrest:hamcrest-core:jar:1.3:compile
[INFO] |  \- org.mule.tests:mule-tests-model:jar:4.1.4:compile
[INFO] +- org.mule:mule-maven-client-test:jar:1.1.4:compile
[INFO] |  +- org.apache.logging.log4j:log4j-slf4j-impl:jar:2.11.0:compile
[INFO] |  \- org.apache.logging.log4j:log4j-core:jar:2.11.0:compile
[INFO] +- org.mule.tests:mule-tests-allure:jar:4.1.4:compile
[INFO] +- com.mashape.unirest:unirest-java:jar:1.4.9:compile
[INFO] |  +- org.apache.httpcomponents:httpclient:jar:4.5.2:compile
[INFO] |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.4:compile
[INFO] |  |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |  \- commons-codec:commons-codec:jar:1.9:compile
[INFO] |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.1:compile
[INFO] |  |  \- org.apache.httpcomponents:httpcore-nio:jar:4.4.4:compile
[INFO] |  +- org.apache.httpcomponents:httpmime:jar:4.5.2:compile
[INFO] |  \- org.json:json:jar:20160212:compile
[INFO] +- org.mule.tests:mule-tests-infrastructure:jar:4.1.4:test
[INFO] |  +- org.apache.activemq:activemq-broker:jar:5.11.1:test
[INFO] |  |  +- org.apache.activemq:activemq-client:jar:5.11.1:test
[INFO] |  |  |  \- org.fusesource.hawtbuf:hawtbuf:jar:1.11:test
[INFO] |  |  \- org.apache.activemq:activemq-openwire-legacy:jar:5.11.1:test
[INFO] |  +- org.apache.derby:derbynet:jar:10.13.1.1:test
[INFO] |  |  \- org.apache.derby:derby:jar:10.13.1.1:test
[INFO] |  +- org.apache.activemq:activemq-kahadb-store:jar:5.11.1:test
[INFO] |  |  +- org.apache.activemq.protobuf:activemq-protobuf:jar:1.1:test
[INFO] |  |  +- org.apache.geronimo.specs:geronimo-j2ee-management_1.1_spec:jar:1.0.1:test
[INFO] |  |  \- commons-net:commons-net:jar:3.5:test
[INFO] |  +- org.apache.commons:commons-exec:jar:1.2:test
[INFO] |  +- com.jayway.awaitility:awaitility:jar:1.7.0:test
[INFO] |  +- org.apache.ftpserver:ftpserver-core:jar:1.0.6:test
[INFO] |  |  +- org.apache.ftpserver:ftplet-api:jar:1.0.6:test
[INFO] |  |  \- org.apache.mina:mina-core:jar:2.0.4:test
[INFO] |  +- org.apache.sshd:sshd-core:jar:1.4.0:test
[INFO] |  +- org.bouncycastle:bcprov-jdk15on:jar:1.60:test
[INFO] |  +- org.codehaus.groovy:groovy-all:jar:2.4.4:test
[INFO] |  \- org.apache.maven.shared:maven-invoker:jar:3.0.0:test
[INFO] |     \- org.codehaus.plexus:plexus-component-annotations:jar:1.7:compile
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    \- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]       +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]       |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]       |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]       |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]       |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:compile
[INFO]       |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:compile
[INFO]       |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:compile
[INFO]       |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]       +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]       \- org.jooq:joor:jar:0.9.6:test
[INFO] 
[INFO] ---------< org.mule.distributions:mule-embedded-distribution >----------
[INFO] Building Embedded (Single jar file) 4.1.4-HF-SNAPSHOT            [10/12]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-embedded-distribution ---
[INFO] org.mule.distributions:mule-embedded-distribution:pom:4.1.4-HF-SNAPSHOT
[INFO] +- org.mule.runtime:mule-modules-all:pom:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-launcher:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-core:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-metadata-model-xml:jar:1.1.5:compile
[INFO] |  |  |  |  +- org.apache.xmlbeans:xmlbeans:jar:2.6.0:compile
[INFO] |  |  |  |  \- org.mule.apache:xerces2-xsd11:jar:2.11.3:compile
[INFO] |  |  |  +- org.mule.runtime:mule-metadata-model-java:jar:1.1.5:compile
[INFO] |  |  |  +- org.mule.runtime:mule-message-metadata-model:jar:1.1.5:compile
[INFO] |  |  |  +- org.mule.runtime:mule-metadata-model-json:jar:1.1.5:compile
[INFO] |  |  |  +- commons-beanutils:commons-beanutils:jar:1.9.3:compile
[INFO] |  |  |  +- com.github.stephenc.eaio-uuid:uuid:jar:3.4.0:compile
[INFO] |  |  |  |  \- com.github.stephenc.eaio-grabbag:grabbag:jar:1.8.1:compile
[INFO] |  |  |  +- commons-cli:commons-cli:jar:1.2:compile
[INFO] |  |  |  +- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |  |  +- commons-pool:commons-pool:jar:1.6:compile
[INFO] |  |  |  +- javax.transaction:javax.transaction-api:jar:1.2:compile
[INFO] |  |  |  +- org.apache.geronimo.specs:geronimo-j2ee-connector_1.5_spec:jar:2.0.0:compile
[INFO] |  |  |  +- javax.inject:javax.inject:jar:1:compile
[INFO] |  |  |  +- org.mule.mvel:mule-mvel2:jar:2.1.9-MULE-017:compile
[INFO] |  |  |  +- org.jgrapht:jgrapht-jdk1.5:jar:0.7.3:compile
[INFO] |  |  |  +- io.projectreactor.addons:reactor-extra:jar:3.1.2.RELEASE:compile
[INFO] |  |  |  +- org.yaml:snakeyaml:jar:1.18:compile
[INFO] |  |  |  +- cglib:cglib-nodep:jar:3.2.6:compile
[INFO] |  |  |  +- org.apache.commons:commons-pool2:jar:2.5.0:compile
[INFO] |  |  |  +- it.unimi.dsi:fastutil:jar:8.1.1:compile
[INFO] |  |  |  +- com.github.ben-manes.caffeine:caffeine:jar:2.6.2:compile
[INFO] |  |  |  \- javax.jms:javax.jms-api:jar:2.0.1:compile
[INFO] |  |  +- org.mule.runtime:mule-module-deployment:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-deployment-model-impl:jar:4.1.4:compile
[INFO] |  |  |  |  \- org.mule.tools.maven:mule-classloader-model:jar:3.1.7:compile
[INFO] |  |  |  +- org.mule:mule-maven-client-impl:jar:1.1.4:runtime
[INFO] |  |  |  |  +- org.apache.maven:maven-settings-builder:jar:3.3.9:runtime
[INFO] |  |  |  |  |  +- org.apache.maven:maven-builder-support:jar:3.3.9:compile
[INFO] |  |  |  |  |  +- org.codehaus.plexus:plexus-interpolation:jar:1.21:compile
[INFO] |  |  |  |  |  +- org.apache.maven:maven-settings:jar:3.3.9:runtime
[INFO] |  |  |  |  |  \- org.sonatype.plexus:plexus-sec-dispatcher:jar:1.3:runtime
[INFO] |  |  |  |  |     \- org.sonatype.plexus:plexus-cipher:jar:1.4:runtime
[INFO] |  |  |  |  +- com.beust:jcommander:jar:1.69:runtime
[INFO] |  |  |  |  \- org.eclipse.sisu:org.eclipse.sisu.plexus:jar:0.3.2:runtime
[INFO] |  |  |  \- org.mule.runtime:mule-module-service:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-repository:jar:4.1.4:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-api:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-spi:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-impl:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-connector-basic:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-transport-file:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.eclipse.aether:aether-transport-http:jar:1.0.2.v20150114:compile
[INFO] |  |  |  +- org.apache.httpcomponents:httpclient:jar:4.5.3:compile
[INFO] |  |  |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.6:compile
[INFO] |  |  |  |  \- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |  |  +- org.apache.maven:maven-aether-provider:jar:3.3.9:compile
[INFO] |  |  |  |  +- org.apache.maven:maven-model-builder:jar:3.3.9:compile
[INFO] |  |  |  |  |  \- org.apache.maven:maven-artifact:jar:3.3.9:compile
[INFO] |  |  |  |  +- org.apache.maven:maven-repository-metadata:jar:3.3.9:compile
[INFO] |  |  |  |  \- org.codehaus.plexus:plexus-component-annotations:jar:1.6:compile
[INFO] |  |  |  \- org.codehaus.plexus:plexus-utils:jar:3.0.24:compile
[INFO] |  |  +- org.mule.runtime:mule-module-reboot:jar:4.1.4:compile
[INFO] |  |  \- tanukisoft:wrapper:jar:3.2.3:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-support:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-api:jar:1.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-metadata-model-api:jar:1.1.5:compile
[INFO] |  |  |  +- com.google.code.gson:gson:jar:2.8.1:compile
[INFO] |  |  |  +- org.mule.runtime:mule-artifact-declaration:jar:1.1.1:compile
[INFO] |  |  |  \- org.mule.runtime:api-annotations:jar:1.0.1:compile
[INFO] |  |  +- org.mule.runtime:mule-extensions-api-dsql:jar:1.1.4:compile
[INFO] |  |  |  \- org.antlr:antlr-runtime:jar:3.5:compile
[INFO] |  |  |     \- org.antlr:stringtemplate:jar:3.2.1:compile
[INFO] |  |  +- org.mule.runtime:mule-extensions-api:jar:1.1.4:compile
[INFO] |  |  |  \- org.reactivestreams:reactive-streams:jar:1.0.2:compile
[INFO] |  |  +- org.mule.runtime:mule-extensions-api-persistence:jar:1.1.4:compile
[INFO] |  |  |  \- org.mule.runtime:mule-metadata-model-persistence:jar:1.1.5:compile
[INFO] |  |  +- org.mule.runtime:mule-module-deployment-model:jar:4.1.4:compile
[INFO] |  |  \- org.mule.projectreactor:reactor-core:jar:3.2.0.M1-MULE-002:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-soap-support:jar:4.1.4:compile
[INFO] |  |  \- org.mule.runtime:mule-extensions-soap-api:jar:1.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-spring-support:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-dsl-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-tls:jar:4.1.4:compile
[INFO] |  |  +- org.reflections:reflections:jar:0.9.10:compile
[INFO] |  |  +- jboss:javassist:jar:3.7.ga:compile
[INFO] |  |  +- joda-time:joda-time:jar:2.9.1:compile
[INFO] |  |  +- dom4j:dom4j:jar:1.6.1:compile
[INFO] |  |  \- jaxen:jaxen:jar:1.1.1:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-xml-support:jar:4.1.4:compile
[INFO] |  |  \- org.mule.runtime:mule-metadata-model-catalog:jar:1.1.5:compile
[INFO] |  |     \- org.mule.runtime:mule-metadata-model-raml:jar:1.1.5:compile
[INFO] |  |        \- org.raml:raml-parser-2:jar:1.0.27:compile
[INFO] |  |           +- org.raml:yagi:jar:1.0.27:compile
[INFO] |  |           |  +- commons-lang:commons-lang:jar:2.6:compile
[INFO] |  |           |  +- com.google.code.findbugs:annotations:jar:3.0.0:compile
[INFO] |  |           |  \- com.fasterxml.jackson.module:jackson-module-jsonSchema:jar:2.9.6:compile
[INFO] |  |           |     \- javax.validation:validation-api:jar:1.1.0.Final:compile
[INFO] |  |           +- com.github.java-json-tools:json-schema-validator:jar:2.2.8:compile
[INFO] |  |           |  +- com.github.java-json-tools:json-schema-core:jar:1.2.8:compile
[INFO] |  |           |  |  +- org.mozilla:rhino:jar:1.7R4:compile
[INFO] |  |           |  |  +- com.github.fge:jackson-coreutils:jar:1.8:compile
[INFO] |  |           |  |  |  \- com.github.fge:msg-simple:jar:1.1:compile
[INFO] |  |           |  |  |     \- com.github.fge:btf:jar:1.2:compile
[INFO] |  |           |  |  \- com.github.fge:uri-template:jar:0.9:compile
[INFO] |  |           |  +- javax.mail:mailapi:jar:1.4.3:compile
[INFO] |  |           |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.0.0:compile
[INFO] |  |           |  \- net.sf.jopt-simple:jopt-simple:jar:5.0.3:compile
[INFO] |  |           +- com.googlecode.juniversalchardet:juniversalchardet:jar:1.0.3:compile
[INFO] |  |           +- org.apache.ws.xmlschema:xmlschema-core:jar:2.2.1:compile
[INFO] |  |           +- javax.json:javax.json-api:jar:1.0:compile
[INFO] |  |           \- org.glassfish:javax.json:jar:1.0.4:compile
[INFO] |  +- org.mule.runtime:mule-module-http-policy-api:jar:1.1.2:compile
[INFO] |  +- org.mule.runtime:mule-module-policy-api:jar:1.1.2:compile
[INFO] |  +- org.mule.runtime:mule-module-spring-config:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-artifact:jar:4.1.4:compile
[INFO] |  |  |  +- com.vdurmont:semver4j:jar:2.0.3:compile
[INFO] |  |  |  \- org.eclipse.aether:aether-util:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.springframework:spring-context:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  +- org.springframework:spring-core:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  \- org.springframework:spring-expression:jar:4.3.19.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-aop:jar:4.3.19.RELEASE:compile
[INFO] |  +- org.mule.runtime:mule-module-tooling-support:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-global-config:jar:4.1.4:compile
[INFO] |  |  +- com.typesafe:config:jar:1.3.1:compile
[INFO] |  |  +- com.google.guava:guava:jar:25.1-jre:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:2.0.0:compile
[INFO] |  |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:compile
[INFO] |  |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:compile
[INFO] |  |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:compile
[INFO] |  |  +- org.mule:mule-maven-client-api:jar:1.1.4:compile
[INFO] |  |  |  \- org.apache.maven:maven-model:jar:3.3.9:compile
[INFO] |  |  +- org.everit.json:org.everit.json.schema:jar:1.5.1:compile
[INFO] |  |  |  +- org.json:json:jar:20160810:compile
[INFO] |  |  |  \- commons-validator:commons-validator:jar:1.5.1:compile
[INFO] |  |  |     \- commons-digester:commons-digester:jar:1.8.1:compile
[INFO] |  |  \- org.mule.runtime:mule-module-container:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-logging:jar:4.1.4:compile
[INFO] |  |  +- com.lmax:disruptor:jar:3.3.7:compile
[INFO] |  |  +- org.slf4j:slf4j-api:jar:1.7.25:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-api:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-core:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-slf4j-impl:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-1.2-api:jar:2.11.0:compile
[INFO] |  |  +- org.slf4j:jcl-over-slf4j:jar:1.7.25:compile
[INFO] |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.25:compile
[INFO] |  +- org.mule.runtime:mule-module-license-api:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-service-http-api:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-service-oauth-api:jar:4.1.4:compile
[INFO] |  \- org.mule.runtime:mule-service-soap-api:jar:4.1.4:compile
[INFO] +- org.mule.distributions:mule-services-all:pom:4.1.4-HF-SNAPSHOT:compile
[INFO] |  +- org.mule.services:mule-service-scheduler:jar:mule-service:1.1.7:compile
[INFO] |  |  \- org.apache.commons:commons-lang3:jar:3.6:compile
[INFO] |  +- org.mule.services:mule-service-oauth:jar:mule-service:1.1.5:compile
[INFO] |  |  \- commons-codec:commons-codec:jar:1.9:compile
[INFO] |  +- org.mule.services:mule-service-http:jar:mule-service:1.1.4:compile
[INFO] |  |  \- commons-io:commons-io:jar:2.4:compile
[INFO] |  +- org.mule.services:mule-service-soap:jar:mule-service:1.1.8:compile
[INFO] |  |  \- org.springframework:spring-beans:jar:4.3.19.RELEASE:compile
[INFO] |  \- org.mule.services:mule-service-weave:jar:mule-service:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     +- org.scala-lang:scala-library:jar:2.12.4:compile
[INFO] |     +- org.mule.weave:runtime:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  +- org.mule.weave:wlang:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  +- org.mule.weave:core:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  |  \- org.typelevel:spire_2.12:jar:0.14.1:compile
[INFO] |     |  |     +- org.typelevel:spire-macros_2.12:jar:0.14.1:compile
[INFO] |     |  |     +- org.typelevel:machinist_2.12:jar:0.6.1:compile
[INFO] |     |  |     |  \- org.scala-lang:scala-reflect:jar:2.12.0:compile
[INFO] |     |  |     \- org.typelevel:algebra_2.12:jar:0.7.0:compile
[INFO] |     |  |        \- org.typelevel:cats-kernel_2.12:jar:0.9.0:compile
[INFO] |     |  +- org.mule.weave:core-modules:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  |  +- com.fasterxml.woodstox:woodstox-core:jar:5.0.2:compile
[INFO] |     |  |  |  \- org.codehaus.woodstox:stax2-api:jar:3.1.4:compile
[INFO] |     |  |  +- com.fasterxml:aalto-xml:jar:1.0.0:compile
[INFO] |     |  |  \- javax.mail:mail:jar:1.4.7:compile
[INFO] |     |  |     \- javax.activation:activation:jar:1.1:compile
[INFO] |     |  \- org.mule.weave:debugger:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     +- org.mule.weave:yaml-module:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |     |  \- org.mule.syaml:syaml_2.12:jar:0.1.11:compile
[INFO] |     |     \- org.mule.common:scala-common_2.12:jar:0.1.3:compile
[INFO] |     |        \- org.scalactic:scalactic_2.12:jar:3.0.1:compile
[INFO] |     \- org.mule.weave:mule-tooling-weave:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |        \- org.mule.weave:parser:jar:2.1.6-CH-SE-10077-SE-10548-SE-10638-SE-10706-DW-112:compile
[INFO] |           \- org.parboiled:parboiled_2.12:jar:2.1.4:compile
[INFO] |              \- com.chuusai:shapeless_2.12:jar:2.3.2:compile
[INFO] |                 \- org.typelevel:macro-compat_2.12:jar:1.1.1:compile
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     +- com.fasterxml.jackson.core:jackson-databind:jar:2.7.0:compile
[INFO]    |  |     |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.7.0:compile
[INFO]    |  |     |  \- com.fasterxml.jackson.core:jackson-core:jar:2.7.0:compile
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  \- org.jooq:joor:jar:0.9.6:test
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] ---------------< org.mule.distributions:mule-standalone >---------------
[INFO] Building Full Distribution 4.1.4-HF-SNAPSHOT                     [11/12]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-standalone ---
[INFO] org.mule.distributions:mule-standalone:pom:4.1.4-HF-SNAPSHOT
[INFO] +- org.mule.runtime:mule-core:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-api:jar:1.1.5:compile
[INFO] |  |  +- org.slf4j:slf4j-api:jar:1.7.25:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.8.1:compile
[INFO] |  |  +- org.mule.runtime:mule-artifact-declaration:jar:1.1.1:compile
[INFO] |  |  \- org.mule.runtime:api-annotations:jar:1.0.1:compile
[INFO] |  +- org.mule.runtime:mule-module-dsl-api:jar:1.1.4:compile
[INFO] |  +- org.mule.runtime:mule-extensions-api:jar:1.1.4:compile
[INFO] |  |  \- org.reactivestreams:reactive-streams:jar:1.0.2:compile
[INFO] |  +- org.mule.runtime:mule-module-http-policy-api:jar:1.1.2:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-xml:jar:1.1.5:compile
[INFO] |  |  +- org.apache.xmlbeans:xmlbeans:jar:2.6.0:compile
[INFO] |  |  \- org.mule.apache:xerces2-xsd11:jar:2.11.3:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-java:jar:1.1.5:compile
[INFO] |  +- org.mule.runtime:mule-message-metadata-model:jar:1.1.5:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-json:jar:1.1.5:compile
[INFO] |  |  \- org.everit.json:org.everit.json.schema:jar:1.5.0:compile
[INFO] |  |     +- org.json:json:jar:20160810:compile
[INFO] |  |     \- commons-validator:commons-validator:jar:1.5.1:compile
[INFO] |  |        \- commons-digester:commons-digester:jar:1.8.1:compile
[INFO] |  +- commons-beanutils:commons-beanutils:jar:1.9.3:compile
[INFO] |  +- com.github.stephenc.eaio-uuid:uuid:jar:3.4.0:compile
[INFO] |  |  \- com.github.stephenc.eaio-grabbag:grabbag:jar:1.8.1:compile
[INFO] |  +- commons-cli:commons-cli:jar:1.2:compile
[INFO] |  +- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  +- commons-io:commons-io:jar:2.4:compile
[INFO] |  +- org.apache.commons:commons-lang3:jar:3.6:compile
[INFO] |  +- commons-pool:commons-pool:jar:1.6:compile
[INFO] |  +- javax.transaction:javax.transaction-api:jar:1.2:compile
[INFO] |  +- org.apache.geronimo.specs:geronimo-j2ee-connector_1.5_spec:jar:2.0.0:compile
[INFO] |  +- javax.inject:javax.inject:jar:1:compile
[INFO] |  +- org.mule.mvel:mule-mvel2:jar:2.1.9-MULE-017:compile
[INFO] |  +- org.jgrapht:jgrapht-jdk1.5:jar:0.7.3:compile
[INFO] |  +- com.google.guava:guava:jar:25.1-jre:compile
[INFO] |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:2.0.0:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:compile
[INFO] |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:compile
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:compile
[INFO] |  +- org.reflections:reflections:jar:0.9.10:compile
[INFO] |  +- org.mule.projectreactor:reactor-core:jar:3.2.0.M1-MULE-002:compile
[INFO] |  +- io.projectreactor.addons:reactor-extra:jar:3.1.2.RELEASE:compile
[INFO] |  +- org.yaml:snakeyaml:jar:1.18:compile
[INFO] |  +- cglib:cglib-nodep:jar:3.2.6:compile
[INFO] |  +- org.apache.commons:commons-pool2:jar:2.5.0:compile
[INFO] |  +- it.unimi.dsi:fastutil:jar:8.1.1:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:2.6.2:compile
[INFO] |  \- javax.jms:javax.jms-api:jar:2.0.1:compile
[INFO] +- org.mule.runtime:mule-modules-all:pom:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-support:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-extensions-api-dsql:jar:1.1.4:compile
[INFO] |  |  |  \- org.antlr:antlr-runtime:jar:3.5:compile
[INFO] |  |  |     \- org.antlr:stringtemplate:jar:3.2.1:compile
[INFO] |  |  \- org.mule.runtime:mule-extensions-api-persistence:jar:1.1.4:compile
[INFO] |  |     \- org.mule.runtime:mule-metadata-model-persistence:jar:1.1.5:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-soap-support:jar:4.1.4:compile
[INFO] |  |  \- org.mule.runtime:mule-extensions-soap-api:jar:1.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-spring-support:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-tls:jar:4.1.4:compile
[INFO] |  |  +- jboss:javassist:jar:3.7.ga:compile
[INFO] |  |  +- joda-time:joda-time:jar:2.9.1:compile
[INFO] |  |  +- dom4j:dom4j:jar:1.6.1:compile
[INFO] |  |  \- jaxen:jaxen:jar:1.1.1:compile
[INFO] |  +- org.mule.runtime:mule-module-extensions-xml-support:jar:4.1.4:compile
[INFO] |  |  \- org.mule.runtime:mule-metadata-model-catalog:jar:1.1.5:compile
[INFO] |  |     \- org.mule.runtime:mule-metadata-model-raml:jar:1.1.5:compile
[INFO] |  |        \- org.raml:raml-parser-2:jar:1.0.27:compile
[INFO] |  |           +- org.raml:yagi:jar:1.0.27:compile
[INFO] |  |           |  +- commons-lang:commons-lang:jar:2.6:compile
[INFO] |  |           |  +- com.google.code.findbugs:annotations:jar:3.0.0:compile
[INFO] |  |           |  \- com.fasterxml.jackson.module:jackson-module-jsonSchema:jar:2.9.6:compile
[INFO] |  |           |     \- javax.validation:validation-api:jar:1.1.0.Final:compile
[INFO] |  |           +- com.github.java-json-tools:json-schema-validator:jar:2.2.8:compile
[INFO] |  |           |  +- com.github.java-json-tools:json-schema-core:jar:1.2.8:compile
[INFO] |  |           |  |  +- org.mozilla:rhino:jar:1.7R4:compile
[INFO] |  |           |  |  +- com.github.fge:jackson-coreutils:jar:1.8:compile
[INFO] |  |           |  |  |  \- com.github.fge:msg-simple:jar:1.1:compile
[INFO] |  |           |  |  |     \- com.github.fge:btf:jar:1.2:compile
[INFO] |  |           |  |  \- com.github.fge:uri-template:jar:0.9:compile
[INFO] |  |           |  +- javax.mail:mailapi:jar:1.4.3:compile
[INFO] |  |           |  |  \- javax.activation:activation:jar:1.1:compile
[INFO] |  |           |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.0.0:compile
[INFO] |  |           |  \- net.sf.jopt-simple:jopt-simple:jar:5.0.3:compile
[INFO] |  |           +- com.googlecode.juniversalchardet:juniversalchardet:jar:1.0.3:compile
[INFO] |  |           +- org.apache.ws.xmlschema:xmlschema-core:jar:2.2.1:compile
[INFO] |  |           +- javax.json:javax.json-api:jar:1.0:compile
[INFO] |  |           \- org.glassfish:javax.json:jar:1.0.4:compile
[INFO] |  +- org.mule.runtime:mule-module-policy-api:jar:1.1.2:compile
[INFO] |  +- org.mule.runtime:mule-module-spring-config:jar:4.1.4:compile
[INFO] |  |  +- org.springframework:spring-context:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  +- org.springframework:spring-core:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  \- org.springframework:spring-expression:jar:4.3.19.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-aop:jar:4.3.19.RELEASE:compile
[INFO] |  +- org.mule.runtime:mule-module-tooling-support:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-global-config:jar:4.1.4:compile
[INFO] |  |  +- com.typesafe:config:jar:1.3.1:compile
[INFO] |  |  \- org.mule:mule-maven-client-api:jar:1.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-logging:jar:4.1.4:compile
[INFO] |  |  +- com.lmax:disruptor:jar:3.3.7:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-api:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-core:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-slf4j-impl:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-1.2-api:jar:2.11.0:compile
[INFO] |  |  +- org.slf4j:jcl-over-slf4j:jar:1.7.25:compile
[INFO] |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.25:compile
[INFO] |  +- org.mule.runtime:mule-module-license-api:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-service-http-api:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-service-oauth-api:jar:4.1.4:compile
[INFO] |  \- org.mule.runtime:mule-service-soap-api:jar:4.1.4:compile
[INFO] +- org.mule.distributions:mule-services-all:pom:4.1.4-HF-SNAPSHOT:compile
[INFO] |  +- org.mule.services:mule-service-scheduler:jar:mule-service:1.1.7:compile
[INFO] |  +- org.mule.services:mule-service-oauth:jar:mule-service:1.1.5:compile
[INFO] |  |  \- commons-codec:commons-codec:jar:1.9:compile
[INFO] |  +- org.mule.services:mule-service-http:jar:mule-service:1.1.4:compile
[INFO] |  \- org.mule.services:mule-service-soap:jar:mule-service:1.1.8:compile
[INFO] |     \- org.springframework:spring-beans:jar:4.3.19.RELEASE:compile
[INFO] +- org.mule.runtime:mule-module-reboot:jar:4.1.4:compile
[INFO] |  \- tanukisoft:wrapper:jar:3.2.3:compile
[INFO] +- org.mule.runtime:mule-module-launcher:jar:4.1.4:compile
[INFO] |  \- org.mule.runtime:mule-module-repository:jar:4.1.4:compile
[INFO] |     +- org.eclipse.aether:aether-api:jar:1.0.2.v20150114:compile
[INFO] |     +- org.eclipse.aether:aether-spi:jar:1.0.2.v20150114:compile
[INFO] |     +- org.eclipse.aether:aether-impl:jar:1.0.2.v20150114:compile
[INFO] |     +- org.eclipse.aether:aether-connector-basic:jar:1.0.2.v20150114:compile
[INFO] |     +- org.eclipse.aether:aether-transport-file:jar:1.0.2.v20150114:compile
[INFO] |     +- org.eclipse.aether:aether-transport-http:jar:1.0.2.v20150114:compile
[INFO] |     +- org.apache.httpcomponents:httpclient:jar:4.5.3:compile
[INFO] |     |  +- org.apache.httpcomponents:httpcore:jar:4.4.6:compile
[INFO] |     |  \- commons-logging:commons-logging:jar:1.2:compile
[INFO] |     +- org.apache.maven:maven-aether-provider:jar:3.3.9:compile
[INFO] |     |  +- org.apache.maven:maven-model-builder:jar:3.3.9:compile
[INFO] |     |  |  \- org.apache.maven:maven-artifact:jar:3.3.9:compile
[INFO] |     |  +- org.apache.maven:maven-repository-metadata:jar:3.3.9:compile
[INFO] |     |  \- org.codehaus.plexus:plexus-component-annotations:jar:1.6:compile
[INFO] |     \- org.codehaus.plexus:plexus-utils:jar:3.0.24:compile
[INFO] +- org.mule.runtime:mule-module-deployment:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-artifact:jar:4.1.4:compile
[INFO] |  |  +- com.vdurmont:semver4j:jar:2.0.3:compile
[INFO] |  |  \- org.eclipse.aether:aether-util:jar:1.0.2.v20150114:compile
[INFO] |  +- org.mule.runtime:mule-module-deployment-model:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-deployment-model-impl:jar:4.1.4:compile
[INFO] |  |  \- org.apache.maven:maven-model:jar:3.3.9:compile
[INFO] |  +- org.mule:mule-maven-client-impl:jar:1.1.4:runtime
[INFO] |  |  +- org.apache.maven:maven-settings-builder:jar:3.3.9:runtime
[INFO] |  |  |  +- org.apache.maven:maven-builder-support:jar:3.3.9:compile
[INFO] |  |  |  +- org.codehaus.plexus:plexus-interpolation:jar:1.21:compile
[INFO] |  |  |  +- org.apache.maven:maven-settings:jar:3.3.9:runtime
[INFO] |  |  |  \- org.sonatype.plexus:plexus-sec-dispatcher:jar:1.3:runtime
[INFO] |  |  |     \- org.sonatype.plexus:plexus-cipher:jar:1.4:runtime
[INFO] |  |  +- com.beust:jcommander:jar:1.69:runtime
[INFO] |  |  \- org.eclipse.sisu:org.eclipse.sisu.plexus:jar:0.3.2:runtime
[INFO] |  \- org.mule.runtime:mule-module-container:jar:4.1.4:compile
[INFO] +- org.mule.runtime:mule-module-service:jar:4.1.4:compile
[INFO] |  \- org.mule.tools.maven:mule-classloader-model:jar:3.1.7:compile
[INFO] +- org.aspectj:aspectjweaver:jar:1.8.10:compile
[INFO] +- org.mule.glassfish.jaxb:jaxb-runtime:jar:2.3.0-MULE-001:compile
[INFO] |  +- org.mule.glassfish.jaxb:jaxb-core:jar:2.3.0-MULE-001:compile
[INFO] |  |  +- javax.xml.bind:jaxb-api:jar:2.3.0:compile
[INFO] |  |  +- org.mule.glassfish.jaxb:txw2:jar:2.3.0-MULE-001:compile
[INFO] |  |  \- com.sun.istack:istack-commons-runtime:jar:3.0.5:compile
[INFO] |  +- org.jvnet.staxex:stax-ex:jar:1.7.8:compile
[INFO] |  \- com.sun.xml.fastinfoset:FastInfoset:jar:1.2.13:compile
[INFO] +- org.codehaus.groovy:groovy-all:jar:indy:2.4.4:compile
[INFO] +- com.fasterxml.jackson.core:jackson-core:jar:2.9.6:compile
[INFO] +- com.fasterxml.jackson.core:jackson-databind:jar:2.9.6:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.9.0:compile
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    +- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]    |  +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]    |  |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]    |  |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]    |  |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]    |  |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]    |  +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]    |  \- org.jooq:joor:jar:0.9.6:test
[INFO]    \- junit:junit:jar:4.12:test
[INFO] 
[INFO] --------------< org.mule.distributions:mule-distro-tests >--------------
[INFO] Building Mule Distribution Tests 4.1.4-HF-SNAPSHOT               [12/12]
[INFO] --------------------------------[ jar ]---------------------------------
Downloading from mulesoft-releases-ee: https://repository-master.mulesoft.org/nexus/service/local/repositories/releases-ee/content/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from mule: https://repository.mulesoft.org/nexus/content/repositories/public/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from spring: https://repo.spring.io/libs-release/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloading from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Progress (1): 979 B                   Downloading from spring-snapshot: http://repo.spring.io/snapshot/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/maven-metadata.xml
Downloaded from mule: https://repository.mulesoft.org/nexus/content/repositories/public/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/maven-metadata.xml (979 B at 1.2 kB/s)
Progress (1): 979 B                   Downloaded from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/maven-metadata.xml (979 B at 596 B/s)
[WARNING] Could not transfer metadata org.mule.distributions:mule-standalone:4.1.4-HF-SNAPSHOT/maven-metadata.xml from/to spring-snapshot (http://repo.spring.io/snapshot/): Authorization failed for http://repo.spring.io/snapshot/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/maven-metadata.xml 403 Forbidden
[INFO] 
[INFO] --- maven-dependency-plugin:3.0.2:tree (default-cli) @ mule-distro-tests ---
[WARNING] Failure to transfer org.mule.distributions:mule-standalone:4.1.4-HF-SNAPSHOT/maven-metadata.xml from http://repo.spring.io/snapshot/ was cached in the local repository, resolution will not be reattempted until the update interval of spring-snapshot has elapsed or updates are forced. Original error: Could not transfer metadata org.mule.distributions:mule-standalone:4.1.4-HF-SNAPSHOT/maven-metadata.xml from/to spring-snapshot (http://repo.spring.io/snapshot/): Authorization failed for http://repo.spring.io/snapshot/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/maven-metadata.xml 403 Forbidden
Downloading from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/mule-standalone-4.1.4-HF-20201029.023802-93.zip
Progress (1): 0.3/136 MBProgress (1): 0.5/136 MBProgress (1): 0.8/136 MBProgress (1): 1.1/136 MBProgress (1): 1.3/136 MBProgress (1): 1.6/136 MBProgress (1): 1.8/136 MBProgress (1): 2.1/136 MBProgress (1): 2.4/136 MBProgress (1): 2.6/136 MBProgress (1): 2.9/136 MBProgress (1): 3.1/136 MBProgress (1): 3.4/136 MBProgress (1): 3.7/136 MBProgress (1): 3.9/136 MBProgress (1): 4.2/136 MBProgress (1): 4.5/136 MBProgress (1): 4.7/136 MBProgress (1): 5.0/136 MBProgress (1): 5.2/136 MBProgress (1): 5.5/136 MBProgress (1): 5.8/136 MBProgress (1): 6.0/136 MBProgress (1): 6.3/136 MBProgress (1): 6.6/136 MBProgress (1): 6.8/136 MBProgress (1): 7.1/136 MBProgress (1): 7.3/136 MBProgress (1): 7.6/136 MBProgress (1): 7.9/136 MBProgress (1): 8.1/136 MBProgress (1): 8.4/136 MBProgress (1): 8.7/136 MBProgress (1): 8.9/136 MBProgress (1): 9.2/136 MBProgress (1): 9.4/136 MBProgress (1): 9.7/136 MBProgress (1): 10.0/136 MBProgress (1): 10/136 MB  Progress (1): 10/136 MBProgress (1): 11/136 MBProgress (1): 11/136 MBProgress (1): 11/136 MBProgress (1): 12/136 MBProgress (1): 12/136 MBProgress (1): 12/136 MBProgress (1): 12/136 MBProgress (1): 13/136 MBProgress (1): 13/136 MBProgress (1): 13/136 MBProgress (1): 13/136 MBProgress (1): 14/136 MBProgress (1): 14/136 MBProgress (1): 14/136 MBProgress (1): 14/136 MBProgress (1): 15/136 MBProgress (1): 15/136 MBProgress (1): 15/136 MBProgress (1): 15/136 MBProgress (1): 16/136 MBProgress (1): 16/136 MBProgress (1): 16/136 MBProgress (1): 17/136 MBProgress (1): 17/136 MBProgress (1): 17/136 MBProgress (1): 17/136 MBProgress (1): 18/136 MBProgress (1): 18/136 MBProgress (1): 18/136 MBProgress (1): 18/136 MBProgress (1): 19/136 MBProgress (1): 19/136 MBProgress (1): 19/136 MBProgress (1): 19/136 MBProgress (1): 20/136 MBProgress (1): 20/136 MBProgress (1): 20/136 MBProgress (1): 20/136 MBProgress (1): 21/136 MBProgress (1): 21/136 MBProgress (1): 21/136 MBProgress (1): 21/136 MBProgress (1): 22/136 MBProgress (1): 22/136 MBProgress (1): 22/136 MBProgress (1): 23/136 MBProgress (1): 23/136 MBProgress (1): 23/136 MBProgress (1): 23/136 MBProgress (1): 24/136 MBProgress (1): 24/136 MBProgress (1): 24/136 MBProgress (1): 24/136 MBProgress (1): 25/136 MBProgress (1): 25/136 MBProgress (1): 25/136 MBProgress (1): 25/136 MBProgress (1): 26/136 MBProgress (1): 26/136 MBProgress (1): 26/136 MBProgress (1): 26/136 MBProgress (1): 27/136 MBProgress (1): 27/136 MBProgress (1): 27/136 MBProgress (1): 28/136 MBProgress (1): 28/136 MBProgress (1): 28/136 MBProgress (1): 28/136 MBProgress (1): 29/136 MBProgress (1): 29/136 MBProgress (1): 29/136 MBProgress (1): 29/136 MBProgress (1): 30/136 MBProgress (1): 30/136 MBProgress (1): 30/136 MBProgress (1): 30/136 MBProgress (1): 31/136 MBProgress (1): 31/136 MBProgress (1): 31/136 MBProgress (1): 31/136 MBProgress (1): 32/136 MBProgress (1): 32/136 MBProgress (1): 32/136 MBProgress (1): 33/136 MBProgress (1): 33/136 MBProgress (1): 33/136 MBProgress (1): 33/136 MBProgress (1): 34/136 MBProgress (1): 34/136 MBProgress (1): 34/136 MBProgress (1): 34/136 MBProgress (1): 35/136 MBProgress (1): 35/136 MBProgress (1): 35/136 MBProgress (1): 35/136 MBProgress (1): 36/136 MBProgress (1): 36/136 MBProgress (1): 36/136 MBProgress (1): 36/136 MBProgress (1): 37/136 MBProgress (1): 37/136 MBProgress (1): 37/136 MBProgress (1): 37/136 MBProgress (1): 38/136 MBProgress (1): 38/136 MBProgress (1): 38/136 MBProgress (1): 39/136 MBProgress (1): 39/136 MBProgress (1): 39/136 MBProgress (1): 39/136 MBProgress (1): 40/136 MBProgress (1): 40/136 MBProgress (1): 40/136 MBProgress (1): 40/136 MBProgress (1): 41/136 MBProgress (1): 41/136 MBProgress (1): 41/136 MBProgress (1): 41/136 MBProgress (1): 42/136 MBProgress (1): 42/136 MBProgress (1): 42/136 MBProgress (1): 42/136 MBProgress (1): 43/136 MBProgress (1): 43/136 MBProgress (1): 43/136 MBProgress (1): 44/136 MBProgress (1): 44/136 MBProgress (1): 44/136 MBProgress (1): 44/136 MBProgress (1): 45/136 MBProgress (1): 45/136 MBProgress (1): 45/136 MBProgress (1): 45/136 MBProgress (1): 46/136 MBProgress (1): 46/136 MBProgress (1): 46/136 MBProgress (1): 46/136 MBProgress (1): 47/136 MBProgress (1): 47/136 MBProgress (1): 47/136 MBProgress (1): 47/136 MBProgress (1): 48/136 MBProgress (1): 48/136 MBProgress (1): 48/136 MBProgress (1): 49/136 MBProgress (1): 49/136 MBProgress (1): 49/136 MBProgress (1): 49/136 MBProgress (1): 50/136 MBProgress (1): 50/136 MBProgress (1): 50/136 MBProgress (1): 50/136 MBProgress (1): 51/136 MBProgress (1): 51/136 MBProgress (1): 51/136 MBProgress (1): 51/136 MBProgress (1): 52/136 MBProgress (1): 52/136 MBProgress (1): 52/136 MBProgress (1): 52/136 MBProgress (1): 53/136 MBProgress (1): 53/136 MBProgress (1): 53/136 MBProgress (1): 53/136 MBProgress (1): 54/136 MBProgress (1): 54/136 MBProgress (1): 54/136 MBProgress (1): 55/136 MBProgress (1): 55/136 MBProgress (1): 55/136 MBProgress (1): 55/136 MBProgress (1): 56/136 MBProgress (1): 56/136 MBProgress (1): 56/136 MBProgress (1): 56/136 MBProgress (1): 57/136 MBProgress (1): 57/136 MBProgress (1): 57/136 MBProgress (1): 57/136 MBProgress (1): 58/136 MBProgress (1): 58/136 MBProgress (1): 58/136 MBProgress (1): 58/136 MBProgress (1): 59/136 MBProgress (1): 59/136 MBProgress (1): 59/136 MBProgress (1): 60/136 MBProgress (1): 60/136 MBProgress (1): 60/136 MBProgress (1): 60/136 MBProgress (1): 61/136 MBProgress (1): 61/136 MBProgress (1): 61/136 MBProgress (1): 61/136 MBProgress (1): 62/136 MBProgress (1): 62/136 MBProgress (1): 62/136 MBProgress (1): 62/136 MBProgress (1): 63/136 MBProgress (1): 63/136 MBProgress (1): 63/136 MBProgress (1): 63/136 MBProgress (1): 64/136 MBProgress (1): 64/136 MBProgress (1): 64/136 MBProgress (1): 64/136 MBProgress (1): 65/136 MBProgress (1): 65/136 MBProgress (1): 65/136 MBProgress (1): 66/136 MBProgress (1): 66/136 MBProgress (1): 66/136 MBProgress (1): 66/136 MBProgress (1): 67/136 MBProgress (1): 67/136 MBProgress (1): 67/136 MBProgress (1): 67/136 MBProgress (1): 68/136 MBProgress (1): 68/136 MBProgress (1): 68/136 MBProgress (1): 68/136 MBProgress (1): 69/136 MBProgress (1): 69/136 MBProgress (1): 69/136 MBProgress (1): 69/136 MBProgress (1): 70/136 MBProgress (1): 70/136 MBProgress (1): 70/136 MBProgress (1): 71/136 MBProgress (1): 71/136 MBProgress (1): 71/136 MBProgress (1): 71/136 MBProgress (1): 72/136 MBProgress (1): 72/136 MBProgress (1): 72/136 MBProgress (1): 72/136 MBProgress (1): 73/136 MBProgress (1): 73/136 MBProgress (1): 73/136 MBProgress (1): 73/136 MBProgress (1): 74/136 MBProgress (1): 74/136 MBProgress (1): 74/136 MBProgress (1): 74/136 MBProgress (1): 75/136 MBProgress (1): 75/136 MBProgress (1): 75/136 MBProgress (1): 76/136 MBProgress (1): 76/136 MBProgress (1): 76/136 MBProgress (1): 76/136 MBProgress (1): 77/136 MBProgress (1): 77/136 MBProgress (1): 77/136 MBProgress (1): 77/136 MBProgress (1): 78/136 MBProgress (1): 78/136 MBProgress (1): 78/136 MBProgress (1): 78/136 MBProgress (1): 79/136 MBProgress (1): 79/136 MBProgress (1): 79/136 MBProgress (1): 79/136 MBProgress (1): 80/136 MBProgress (1): 80/136 MBProgress (1): 80/136 MBProgress (1): 80/136 MBProgress (1): 81/136 MBProgress (1): 81/136 MBProgress (1): 81/136 MBProgress (1): 82/136 MBProgress (1): 82/136 MBProgress (1): 82/136 MBProgress (1): 82/136 MBProgress (1): 83/136 MBProgress (1): 83/136 MBProgress (1): 83/136 MBProgress (1): 83/136 MBProgress (1): 84/136 MBProgress (1): 84/136 MBProgress (1): 84/136 MBProgress (1): 84/136 MBProgress (1): 85/136 MBProgress (1): 85/136 MBProgress (1): 85/136 MBProgress (1): 85/136 MBProgress (1): 86/136 MBProgress (1): 86/136 MBProgress (1): 86/136 MBProgress (1): 87/136 MBProgress (1): 87/136 MBProgress (1): 87/136 MBProgress (1): 87/136 MBProgress (1): 88/136 MBProgress (1): 88/136 MBProgress (1): 88/136 MBProgress (1): 88/136 MBProgress (1): 89/136 MBProgress (1): 89/136 MBProgress (1): 89/136 MBProgress (1): 89/136 MBProgress (1): 90/136 MBProgress (1): 90/136 MBProgress (1): 90/136 MBProgress (1): 90/136 MBProgress (1): 91/136 MBProgress (1): 91/136 MBProgress (1): 91/136 MBProgress (1): 91/136 MBProgress (1): 92/136 MBProgress (1): 92/136 MBProgress (1): 92/136 MBProgress (1): 93/136 MBProgress (1): 93/136 MBProgress (1): 93/136 MBProgress (1): 93/136 MBProgress (1): 94/136 MBProgress (1): 94/136 MBProgress (1): 94/136 MBProgress (1): 94/136 MBProgress (1): 95/136 MBProgress (1): 95/136 MBProgress (1): 95/136 MBProgress (1): 95/136 MBProgress (1): 96/136 MBProgress (1): 96/136 MBProgress (1): 96/136 MBProgress (1): 96/136 MBProgress (1): 97/136 MBProgress (1): 97/136 MBProgress (1): 97/136 MBProgress (1): 98/136 MBProgress (1): 98/136 MBProgress (1): 98/136 MBProgress (1): 98/136 MBProgress (1): 99/136 MBProgress (1): 99/136 MBProgress (1): 99/136 MBProgress (1): 99/136 MBProgress (1): 100/136 MBProgress (1): 100/136 MBProgress (1): 100/136 MBProgress (1): 100/136 MBProgress (1): 101/136 MBProgress (1): 101/136 MBProgress (1): 101/136 MBProgress (1): 101/136 MBProgress (1): 102/136 MBProgress (1): 102/136 MBProgress (1): 102/136 MBProgress (1): 103/136 MBProgress (1): 103/136 MBProgress (1): 103/136 MBProgress (1): 103/136 MBProgress (1): 104/136 MBProgress (1): 104/136 MBProgress (1): 104/136 MBProgress (1): 104/136 MBProgress (1): 105/136 MBProgress (1): 105/136 MBProgress (1): 105/136 MBProgress (1): 105/136 MBProgress (1): 106/136 MBProgress (1): 106/136 MBProgress (1): 106/136 MBProgress (1): 106/136 MBProgress (1): 107/136 MBProgress (1): 107/136 MBProgress (1): 107/136 MBProgress (1): 107/136 MBProgress (1): 108/136 MBProgress (1): 108/136 MBProgress (1): 108/136 MBProgress (1): 109/136 MBProgress (1): 109/136 MBProgress (1): 109/136 MBProgress (1): 109/136 MBProgress (1): 110/136 MBProgress (1): 110/136 MBProgress (1): 110/136 MBProgress (1): 110/136 MBProgress (1): 111/136 MBProgress (1): 111/136 MBProgress (1): 111/136 MBProgress (1): 111/136 MBProgress (1): 112/136 MBProgress (1): 112/136 MBProgress (1): 112/136 MBProgress (1): 112/136 MBProgress (1): 113/136 MBProgress (1): 113/136 MBProgress (1): 113/136 MBProgress (1): 114/136 MBProgress (1): 114/136 MBProgress (1): 114/136 MBProgress (1): 114/136 MBProgress (1): 115/136 MBProgress (1): 115/136 MBProgress (1): 115/136 MBProgress (1): 115/136 MBProgress (1): 116/136 MBProgress (1): 116/136 MBProgress (1): 116/136 MBProgress (1): 116/136 MBProgress (1): 117/136 MBProgress (1): 117/136 MBProgress (1): 117/136 MBProgress (1): 117/136 MBProgress (1): 118/136 MBProgress (1): 118/136 MBProgress (1): 118/136 MBProgress (1): 118/136 MBProgress (1): 119/136 MBProgress (1): 119/136 MBProgress (1): 119/136 MBProgress (1): 120/136 MBProgress (1): 120/136 MBProgress (1): 120/136 MBProgress (1): 120/136 MBProgress (1): 121/136 MBProgress (1): 121/136 MBProgress (1): 121/136 MBProgress (1): 121/136 MBProgress (1): 122/136 MBProgress (1): 122/136 MBProgress (1): 122/136 MBProgress (1): 122/136 MBProgress (1): 123/136 MBProgress (1): 123/136 MBProgress (1): 123/136 MBProgress (1): 123/136 MBProgress (1): 124/136 MBProgress (1): 124/136 MBProgress (1): 124/136 MBProgress (1): 125/136 MBProgress (1): 125/136 MBProgress (1): 125/136 MBProgress (1): 125/136 MBProgress (1): 126/136 MBProgress (1): 126/136 MBProgress (1): 126/136 MBProgress (1): 126/136 MBProgress (1): 127/136 MBProgress (1): 127/136 MBProgress (1): 127/136 MBProgress (1): 127/136 MBProgress (1): 128/136 MBProgress (1): 128/136 MBProgress (1): 128/136 MBProgress (1): 128/136 MBProgress (1): 129/136 MBProgress (1): 129/136 MBProgress (1): 129/136 MBProgress (1): 130/136 MBProgress (1): 130/136 MBProgress (1): 130/136 MBProgress (1): 130/136 MBProgress (1): 131/136 MBProgress (1): 131/136 MBProgress (1): 131/136 MBProgress (1): 131/136 MBProgress (1): 132/136 MBProgress (1): 132/136 MBProgress (1): 132/136 MBProgress (1): 132/136 MBProgress (1): 133/136 MBProgress (1): 133/136 MBProgress (1): 133/136 MBProgress (1): 133/136 MBProgress (1): 134/136 MBProgress (1): 134/136 MBProgress (1): 134/136 MBProgress (1): 134/136 MBProgress (1): 135/136 MBProgress (1): 135/136 MBProgress (1): 135/136 MBProgress (1): 136/136 MBProgress (1): 136/136 MBProgress (1): 136/136 MBProgress (1): 136 MB                        Downloaded from mulesoft-private: https://repository.mulesoft.org/nexus/content/repositories/private/org/mule/distributions/mule-standalone/4.1.4-HF-SNAPSHOT/mule-standalone-4.1.4-HF-20201029.023802-93.zip (136 MB at 2.4 MB/s)
[INFO] org.mule.distributions:mule-distro-tests:jar:4.1.4-HF-SNAPSHOT
[INFO] +- org.mule.tests:mule-tests-allure:jar:4.1.4:compile
[INFO] +- org.mule.tests:mule-tests-infrastructure:jar:4.1.4:compile
[INFO] |  +- commons-io:commons-io:jar:2.4:compile
[INFO] |  +- org.apache.activemq:activemq-broker:jar:5.11.1:compile
[INFO] |  |  +- org.apache.activemq:activemq-client:jar:5.11.1:compile
[INFO] |  |  |  \- org.fusesource.hawtbuf:hawtbuf:jar:1.11:compile
[INFO] |  |  \- org.apache.activemq:activemq-openwire-legacy:jar:5.11.1:compile
[INFO] |  +- org.apache.derby:derbynet:jar:10.13.1.1:compile
[INFO] |  |  \- org.apache.derby:derby:jar:10.13.1.1:compile
[INFO] |  +- org.apache.activemq:activemq-kahadb-store:jar:5.11.1:compile
[INFO] |  |  +- org.apache.activemq.protobuf:activemq-protobuf:jar:1.1:compile
[INFO] |  |  +- org.apache.geronimo.specs:geronimo-j2ee-management_1.1_spec:jar:1.0.1:compile
[INFO] |  |  \- commons-net:commons-net:jar:3.5:compile
[INFO] |  +- org.apache.commons:commons-exec:jar:1.2:compile
[INFO] |  +- com.jayway.awaitility:awaitility:jar:1.7.0:compile
[INFO] |  |  +- org.hamcrest:hamcrest-core:jar:1.3:compile
[INFO] |  |  \- org.objenesis:objenesis:jar:2.1:compile
[INFO] |  +- org.apache.ftpserver:ftpserver-core:jar:1.0.6:compile
[INFO] |  |  +- org.apache.ftpserver:ftplet-api:jar:1.0.6:compile
[INFO] |  |  \- org.apache.mina:mina-core:jar:2.0.4:compile
[INFO] |  +- org.apache.sshd:sshd-core:jar:1.4.0:compile
[INFO] |  |  \- org.slf4j:slf4j-api:jar:1.7.25:compile
[INFO] |  +- org.bouncycastle:bcprov-jdk15on:jar:1.60:compile
[INFO] |  +- org.codehaus.groovy:groovy-all:jar:2.4.4:compile
[INFO] |  \- org.apache.maven.shared:maven-invoker:jar:3.0.0:compile
[INFO] |     +- org.codehaus.plexus:plexus-utils:jar:3.0.24:compile
[INFO] |     \- org.codehaus.plexus:plexus-component-annotations:jar:1.7:compile
[INFO] +- org.mule.tests:mule-tests-unit:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-logging:jar:4.1.4:compile
[INFO] |  |  +- com.lmax:disruptor:jar:3.3.7:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-api:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-core:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-slf4j-impl:jar:2.11.0:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-1.2-api:jar:2.11.0:compile
[INFO] |  |  +- org.slf4j:jcl-over-slf4j:jar:1.7.25:compile
[INFO] |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.25:compile
[INFO] |  +- junit:junit:jar:4.12:compile
[INFO] |  +- org.mockito:mockito-core:jar:1.10.19:compile
[INFO] |  +- org.hamcrest:hamcrest-library:jar:1.3:compile
[INFO] |  \- org.mule.tests:mule-tests-model:jar:4.1.4:compile
[INFO] +- org.mule.runtime:mule-core:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-metadata-model-api:jar:1.1.5:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.8.1:compile
[INFO] |  |  +- org.mule.runtime:mule-artifact-declaration:jar:1.1.1:compile
[INFO] |  |  \- org.mule.runtime:api-annotations:jar:1.0.1:compile
[INFO] |  +- org.mule.runtime:mule-module-dsl-api:jar:1.1.4:compile
[INFO] |  +- org.mule.runtime:mule-extensions-api:jar:1.1.4:compile
[INFO] |  |  \- org.reactivestreams:reactive-streams:jar:1.0.2:compile
[INFO] |  +- org.mule.runtime:mule-module-http-policy-api:jar:1.1.2:compile
[INFO] |  |  \- org.mule.runtime:mule-module-policy-api:jar:1.1.2:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-xml:jar:1.1.5:compile
[INFO] |  |  +- org.apache.xmlbeans:xmlbeans:jar:2.6.0:compile
[INFO] |  |  \- org.mule.apache:xerces2-xsd11:jar:2.11.3:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-java:jar:1.1.5:compile
[INFO] |  +- org.mule.runtime:mule-message-metadata-model:jar:1.1.5:compile
[INFO] |  +- org.mule.runtime:mule-metadata-model-json:jar:1.1.5:compile
[INFO] |  |  \- org.everit.json:org.everit.json.schema:jar:1.5.0:compile
[INFO] |  |     +- org.json:json:jar:20160810:compile
[INFO] |  |     \- commons-validator:commons-validator:jar:1.5.1:compile
[INFO] |  |        +- commons-digester:commons-digester:jar:1.8.1:compile
[INFO] |  |        \- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  +- commons-beanutils:commons-beanutils:jar:1.9.3:compile
[INFO] |  +- com.github.stephenc.eaio-uuid:uuid:jar:3.4.0:compile
[INFO] |  |  \- com.github.stephenc.eaio-grabbag:grabbag:jar:1.8.1:compile
[INFO] |  +- commons-cli:commons-cli:jar:1.2:compile
[INFO] |  +- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  +- org.apache.commons:commons-lang3:jar:3.6:compile
[INFO] |  +- commons-pool:commons-pool:jar:1.6:compile
[INFO] |  +- javax.transaction:javax.transaction-api:jar:1.2:compile
[INFO] |  +- org.apache.geronimo.specs:geronimo-j2ee-connector_1.5_spec:jar:2.0.0:compile
[INFO] |  +- javax.inject:javax.inject:jar:1:compile
[INFO] |  +- org.mule.mvel:mule-mvel2:jar:2.1.9-MULE-017:compile
[INFO] |  +- org.jgrapht:jgrapht-jdk1.5:jar:0.7.3:compile
[INFO] |  +- com.google.guava:guava:jar:25.1-jre:compile
[INFO] |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:2.0.0:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.1.3:compile
[INFO] |  |  +- com.google.j2objc:j2objc-annotations:jar:1.1:compile
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.14:compile
[INFO] |  +- org.reflections:reflections:jar:0.9.10:compile
[INFO] |  +- org.mule.projectreactor:reactor-core:jar:3.2.0.M1-MULE-002:compile
[INFO] |  +- io.projectreactor.addons:reactor-extra:jar:3.1.2.RELEASE:compile
[INFO] |  +- org.yaml:snakeyaml:jar:1.18:compile
[INFO] |  +- cglib:cglib-nodep:jar:3.2.6:compile
[INFO] |  +- org.apache.commons:commons-pool2:jar:2.5.0:compile
[INFO] |  +- it.unimi.dsi:fastutil:jar:8.1.1:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:2.6.2:compile
[INFO] |  \- javax.jms:javax.jms-api:jar:2.0.1:compile
[INFO] +- org.mule.distributions:mule-standalone:zip:4.1.4-HF-SNAPSHOT:compile
[INFO] |  +- org.mule.runtime:mule-modules-all:pom:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-extensions-support:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-extensions-api-dsql:jar:1.1.4:compile
[INFO] |  |  |  |  \- org.antlr:antlr-runtime:jar:3.5:compile
[INFO] |  |  |  |     \- org.antlr:stringtemplate:jar:3.2.1:compile
[INFO] |  |  |  \- org.mule.runtime:mule-extensions-api-persistence:jar:1.1.4:compile
[INFO] |  |  |     \- org.mule.runtime:mule-metadata-model-persistence:jar:1.1.5:compile
[INFO] |  |  +- org.mule.runtime:mule-module-extensions-soap-support:jar:4.1.4:compile
[INFO] |  |  |  \- org.mule.runtime:mule-extensions-soap-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-extensions-spring-support:jar:4.1.4:compile
[INFO] |  |  |  +- org.mule.runtime:mule-module-tls:jar:4.1.4:compile
[INFO] |  |  |  +- jboss:javassist:jar:3.7.ga:compile
[INFO] |  |  |  +- joda-time:joda-time:jar:2.9.1:compile
[INFO] |  |  |  +- dom4j:dom4j:jar:1.6.1:compile
[INFO] |  |  |  \- jaxen:jaxen:jar:1.1.1:compile
[INFO] |  |  +- org.mule.runtime:mule-module-extensions-xml-support:jar:4.1.4:compile
[INFO] |  |  |  \- org.mule.runtime:mule-metadata-model-catalog:jar:1.1.5:compile
[INFO] |  |  |     \- org.mule.runtime:mule-metadata-model-raml:jar:1.1.5:compile
[INFO] |  |  |        \- org.raml:raml-parser-2:jar:1.0.27:compile
[INFO] |  |  |           +- org.raml:yagi:jar:1.0.27:compile
[INFO] |  |  |           |  +- commons-lang:commons-lang:jar:2.6:compile
[INFO] |  |  |           |  +- com.google.code.findbugs:annotations:jar:3.0.0:compile
[INFO] |  |  |           |  \- com.fasterxml.jackson.module:jackson-module-jsonSchema:jar:2.9.6:compile
[INFO] |  |  |           |     \- javax.validation:validation-api:jar:1.1.0.Final:compile
[INFO] |  |  |           +- com.github.java-json-tools:json-schema-validator:jar:2.2.8:compile
[INFO] |  |  |           |  +- com.github.java-json-tools:json-schema-core:jar:1.2.8:compile
[INFO] |  |  |           |  |  +- org.mozilla:rhino:jar:1.7R4:compile
[INFO] |  |  |           |  |  +- com.github.fge:jackson-coreutils:jar:1.8:compile
[INFO] |  |  |           |  |  |  \- com.github.fge:msg-simple:jar:1.1:compile
[INFO] |  |  |           |  |  |     \- com.github.fge:btf:jar:1.2:compile
[INFO] |  |  |           |  |  \- com.github.fge:uri-template:jar:0.9:compile
[INFO] |  |  |           |  +- javax.mail:mailapi:jar:1.4.3:compile
[INFO] |  |  |           |  |  \- javax.activation:activation:jar:1.1:compile
[INFO] |  |  |           |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.0.0:compile
[INFO] |  |  |           |  \- net.sf.jopt-simple:jopt-simple:jar:5.0.3:compile
[INFO] |  |  |           +- com.googlecode.juniversalchardet:juniversalchardet:jar:1.0.3:compile
[INFO] |  |  |           +- org.apache.ws.xmlschema:xmlschema-core:jar:2.2.1:compile
[INFO] |  |  |           +- javax.json:javax.json-api:jar:1.0:compile
[INFO] |  |  |           \- org.glassfish:javax.json:jar:1.0.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-spring-config:jar:4.1.4:compile
[INFO] |  |  |  +- org.springframework:spring-context:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  |  +- org.springframework:spring-core:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  |  \- org.springframework:spring-expression:jar:4.3.19.RELEASE:compile
[INFO] |  |  |  \- org.springframework:spring-aop:jar:4.3.19.RELEASE:compile
[INFO] |  |  +- org.mule.runtime:mule-module-tooling-support:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-global-config:jar:4.1.4:compile
[INFO] |  |  |  +- com.typesafe:config:jar:1.3.1:compile
[INFO] |  |  |  \- org.mule:mule-maven-client-api:jar:1.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-license-api:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-service-http-api:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-service-oauth-api:jar:4.1.4:compile
[INFO] |  |  \- org.mule.runtime:mule-service-soap-api:jar:4.1.4:compile
[INFO] |  +- org.mule.distributions:mule-services-all:pom:4.1.4-HF-SNAPSHOT:compile
[INFO] |  |  +- org.mule.services:mule-service-scheduler:jar:mule-service:1.1.7:compile
[INFO] |  |  +- org.mule.services:mule-service-oauth:jar:mule-service:1.1.5:compile
[INFO] |  |  |  \- commons-codec:commons-codec:jar:1.9:compile
[INFO] |  |  +- org.mule.services:mule-service-http:jar:mule-service:1.1.4:compile
[INFO] |  |  \- org.mule.services:mule-service-soap:jar:mule-service:1.1.8:compile
[INFO] |  |     \- org.springframework:spring-beans:jar:4.3.19.RELEASE:compile
[INFO] |  +- org.mule.runtime:mule-module-reboot:jar:4.1.4:compile
[INFO] |  |  \- tanukisoft:wrapper:jar:3.2.3:compile
[INFO] |  +- org.mule.runtime:mule-module-launcher:jar:4.1.4:compile
[INFO] |  |  \- org.mule.runtime:mule-module-repository:jar:4.1.4:compile
[INFO] |  |     +- org.eclipse.aether:aether-api:jar:1.0.2.v20150114:compile
[INFO] |  |     +- org.eclipse.aether:aether-spi:jar:1.0.2.v20150114:compile
[INFO] |  |     +- org.eclipse.aether:aether-impl:jar:1.0.2.v20150114:compile
[INFO] |  |     +- org.eclipse.aether:aether-connector-basic:jar:1.0.2.v20150114:compile
[INFO] |  |     +- org.eclipse.aether:aether-transport-file:jar:1.0.2.v20150114:compile
[INFO] |  |     +- org.eclipse.aether:aether-transport-http:jar:1.0.2.v20150114:compile
[INFO] |  |     +- org.apache.httpcomponents:httpclient:jar:4.5.3:compile
[INFO] |  |     |  \- org.apache.httpcomponents:httpcore:jar:4.4.6:compile
[INFO] |  |     \- org.apache.maven:maven-aether-provider:jar:3.3.9:compile
[INFO] |  |        +- org.apache.maven:maven-model-builder:jar:3.3.9:compile
[INFO] |  |        |  \- org.apache.maven:maven-artifact:jar:3.3.9:compile
[INFO] |  |        \- org.apache.maven:maven-repository-metadata:jar:3.3.9:compile
[INFO] |  +- org.mule.runtime:mule-module-deployment:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-artifact:jar:4.1.4:compile
[INFO] |  |  |  +- com.vdurmont:semver4j:jar:2.0.3:compile
[INFO] |  |  |  \- org.eclipse.aether:aether-util:jar:1.0.2.v20150114:compile
[INFO] |  |  +- org.mule.runtime:mule-module-deployment-model:jar:4.1.4:compile
[INFO] |  |  +- org.mule.runtime:mule-module-deployment-model-impl:jar:4.1.4:compile
[INFO] |  |  |  \- org.apache.maven:maven-model:jar:3.3.9:compile
[INFO] |  |  +- org.mule:mule-maven-client-impl:jar:1.1.4:runtime
[INFO] |  |  |  +- org.apache.maven:maven-settings-builder:jar:3.3.9:runtime
[INFO] |  |  |  |  +- org.apache.maven:maven-builder-support:jar:3.3.9:compile
[INFO] |  |  |  |  +- org.codehaus.plexus:plexus-interpolation:jar:1.21:compile
[INFO] |  |  |  |  +- org.apache.maven:maven-settings:jar:3.3.9:runtime
[INFO] |  |  |  |  \- org.sonatype.plexus:plexus-sec-dispatcher:jar:1.3:runtime
[INFO] |  |  |  |     \- org.sonatype.plexus:plexus-cipher:jar:1.4:runtime
[INFO] |  |  |  +- com.beust:jcommander:jar:1.69:runtime
[INFO] |  |  |  \- org.eclipse.sisu:org.eclipse.sisu.plexus:jar:0.3.2:runtime
[INFO] |  |  \- org.mule.runtime:mule-module-container:jar:4.1.4:compile
[INFO] |  +- org.mule.runtime:mule-module-service:jar:4.1.4:compile
[INFO] |  |  \- org.mule.tools.maven:mule-classloader-model:jar:3.1.7:compile
[INFO] |  +- org.aspectj:aspectjweaver:jar:1.8.10:compile
[INFO] |  +- org.mule.glassfish.jaxb:jaxb-runtime:jar:2.3.0-MULE-001:compile
[INFO] |  |  +- org.mule.glassfish.jaxb:jaxb-core:jar:2.3.0-MULE-001:compile
[INFO] |  |  |  +- javax.xml.bind:jaxb-api:jar:2.3.0:compile
[INFO] |  |  |  +- org.mule.glassfish.jaxb:txw2:jar:2.3.0-MULE-001:compile
[INFO] |  |  |  \- com.sun.istack:istack-commons-runtime:jar:3.0.5:compile
[INFO] |  |  +- org.jvnet.staxex:stax-ex:jar:1.7.8:compile
[INFO] |  |  \- com.sun.xml.fastinfoset:FastInfoset:jar:1.2.13:compile
[INFO] |  +- org.codehaus.groovy:groovy-all:jar:indy:2.4.4:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-core:jar:2.9.6:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-databind:jar:2.9.6:compile
[INFO] |     \- com.fasterxml.jackson.core:jackson-annotations:jar:2.9.0:compile
[INFO] +- com.googlecode.multithreadedtc:multithreadedtc:jar:1.01:test
[INFO] \- io.qameta.allure:allure-junit4:jar:2.0-BETA17:test
[INFO]    \- io.qameta.allure:allure-java-commons:jar:2.0-BETA17:test
[INFO]       +- io.qameta.allure:allure2-model-api:jar:1.0-BETA6:test
[INFO]       |  +- io.qameta.allure:allure2-model-pojo:jar:1.0-BETA6:test
[INFO]       |  |  \- org.apache.tika:tika-core:jar:1.14:test
[INFO]       |  \- io.qameta.allure:allure2-model-jackson:jar:1.0-BETA6:test
[INFO]       |     \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.7.0:test
[INFO]       +- org.aspectj:aspectjrt:jar:1.8.10:test
[INFO]       \- org.jooq:joor:jar:0.9.6:test
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for Mule Distributions 4.1.4-HF-SNAPSHOT:
[INFO] 
[INFO] Mule Distributions ................................. SUCCESS [  0.667 s]
[INFO] All Mule Services .................................. SUCCESS [  0.159 s]
[INFO] Mule Runtime BOM ................................... SUCCESS [  0.013 s]
[INFO] Mule Runtime APIs BOM .............................. SUCCESS [  0.012 s]
[INFO] Mule Runtime Implementation Libraries BOM .......... SUCCESS [  0.401 s]
[INFO] Mule Embedded ...................................... SUCCESS [  0.010 s]
[INFO] Mule Embedded Implementation ....................... SUCCESS [  5.482 s]
[INFO] Mule Module Embedded Implementation Distribution BOM SUCCESS [  4.321 s]
[INFO] Mule Embedded Implementation Tests ................. SUCCESS [  0.186 s]
[INFO] Embedded (Single jar file) ......................... SUCCESS [  0.108 s]
[INFO] Full Distribution .................................. SUCCESS [  0.130 s]
[INFO] Mule Distribution Tests ............................ SUCCESS [ 59.439 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:11 min
[INFO] Finished at: 2020-10-29T13:30:23-03:00
[INFO] ------------------------------------------------------------------------
