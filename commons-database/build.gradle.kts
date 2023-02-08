import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    compileOnly(project(":commons-core"))

    implementation("com.zaxxer:HikariCP:5.0.1")
}

tasks.withType<ShadowJar> {
    relocate("com.zaxxer.hikari", "com.github.awumii.commons.libs.hikari")
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

