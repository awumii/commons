import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

allprojects {
    group = "com.github.awumii"
    version = "2.0.0"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains:annotations:24.0.0")
    }

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    // Publishing to jitpack.org

    tasks.withType<ShadowJar> {
        archiveFileName.set("$archiveBaseName-$archiveVersion.$archiveExtension")
    }

    artifacts {
        archives(tasks["shadowJar"])
    }

    publishing {
        publications {
            create<MavenPublication>("shadow") {
                from(components["java"])
            }
        }
    }
}
