plugins {
    java
    idea
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        dependencyLocking {
            lockAllConfigurations()
            lockFile.set(file("${rootDir}/gradle/dependency-locks/gradle-${parent?.let { "$it.name}-"} ?: ""}${project.name}.lockfile"))
        }
    }

    tasks.register("resolveAndLockAll") {
        doLast {
            configurations.filter {
                it.isCanBeResolved
            }.forEach {it.resolve()}
        }
    }
}

idea {
    module {
        outputDir = file("${projectDir}/build/classes/java/main")
        testOutputDir = file("${projectDir}/build/classes/java/test")
    }
}