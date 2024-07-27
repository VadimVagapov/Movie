import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    java
    jacoco
    id("org.springframework.boot") version "2.7.18" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
    id("org.openapi.generator") version "6.4.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generated/src/main/java")
        }
    }
}

val springVersion = "2.7.18"
val springCloudVersion = "4.0.4"

jacoco {
    toolVersion = "0.8.7"
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${springVersion}")
    implementation("org.springframework.boot:spring-boot-starter-web:${springVersion}")
    implementation("org.springframework.cloud:spring-cloud-starter-config:${springCloudVersion}")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:${springCloudVersion}")
    implementation("org.springframework.kafka:spring-kafka:${springVersion}")

    //Swagger
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.9")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.openapitools:openapi-generator:6.6.0")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")

    //Liquibase для БД
    implementation("org.liquibase:liquibase-core:4.24.0")

    //jcache
    implementation("org.hibernate.orm:hibernate-jcache:6.4.4.Final")

    //Common
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //Mapstruct
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${springVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${springVersion}")
    testImplementation("org.springframework.kafka:spring-kafka-test:${springVersion}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val coverageExclusions = listOf(
    "**/*Application.class",
    "**/config/**",
    "**/model/**",
    "**/entity/**",
    "**/exception/**",
    "**/response/**",
)

sourceSets["main"].java {
    srcDir("$buildDir/generated/userService/src/main/java")
}

sourceSets["test"].java {
    srcDir("$buildDir/generated/openapiclient/src/main/java")
}

tasks {
    val generateUserApi = register<GenerateTask>("generateUserApi") {
        generatorName.set("spring")
        inputSpec.set("$rootDir/openapi/out/user-service/user-service-api.yaml")
        outputDir.set("$buildDir/generated/userService")
        apiPackage.set("vagapov.user_service.api")
        modelPackage.set("vagapov.user_service.models")
        configOptions.set(
            mapOf(
                "dateLibrary" to "java8",
                "interfaceOnly" to "true",
                "skipDefaultInterface" to "true",
                "useResponseEntity" to "false",
                "useSpringBoot3" to "true",
                "library" to "spring-boot"
            )
        )
    }
    val generateUserTestApi = register<GenerateTask>("generateUserTestApi") {
        generatorName.set("java")
        inputSpec.set("$rootDir/openapi/out/user-service/user-service-api.yaml")
        outputDir.set("$buildDir/generated/openapiclient")
        apiPackage.set("vagapov.user_service.test_client.api")
        invokerPackage.set("vagapov.user_service.test_client.invoker")
        modelPackage.set("vagapov.user_service.test_client.models")
        generateApiTests.set(false)
        generateApiDocumentation.set(false)
        generateModelTests.set(false)
        library.set("native")
        configOptions.set(
            mapOf(
                "dateLibrary" to "java8",
            )
        )
    }

    compileJava {
        dependsOn(
            generateUserApi
        )
    }

    compileTestJava {
        dependsOn(
            generateUserTestApi
        )
    }

    jar {
        enabled = false
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<Test> {
        systemProperty("file.encoding", "UTF-8")
    }

    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    register("prepareKotlinBuildScriptModel"){}
}
