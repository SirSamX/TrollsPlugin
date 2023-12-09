plugins {
    kotlin("jvm") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.2.2"
}

group = "me.sirsam.trolls"
version = "0.22.1"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20-R0.1-SNAPSHOT")
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

    runServer {
        minecraftVersion("1.20.2")
    }
}