plugins {
    kotlin("jvm") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.sirsam.trolls"
version = "1.0.2"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper", "paper-api", "1.19.4-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(17)
}

tasks {
    processResources {
        expand("version" to project.version)
    }

    shadowJar {
        archiveFileName.set("Trolls-${version}.jar")
        dependencyFilter.apply {
            exclude(dependency("org.jetbrains:annotations"))
        }
    }

    test {
        useJUnitPlatform()
    }
}