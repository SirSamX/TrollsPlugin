plugins {
    kotlin("jvm") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.2.2"
}

group = "me.sirsam.trolls"
version = "0.23.0"
val minecraftVersion = "1.20.4"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${minecraftVersion}-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(17)
}

tasks {
    processResources {
        expand("version" to project.version)
    }

    shadowJar {
        archiveFileName.set("Trolls-${version}-${minecraftVersion}.jar")
        dependencyFilter.apply {
            exclude(dependency("org.jetbrains:annotations"))
        }
    }

    test {
        useJUnitPlatform()
    }

    runServer {
        minecraftVersion(minecraftVersion)
    }
}