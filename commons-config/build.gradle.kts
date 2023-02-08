import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    compileOnly(project(":commons-core"))

    implementation("org.spongepowered:configurate-hocon:4.1.2")
}

tasks.withType<ShadowJar> {
    relocate("com.typesafe.config", "com.github.awumii.libs.config")
    relocate("org.spongepowered.configurate", "com.github.awumii.libs.configurate")
    relocate("io.leangen.geantyref", "com.github.awumii.libs.geantyref")
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}