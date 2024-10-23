plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":spec"))
    runtimeOnly("com.mysql:mysql-connector-j:8.4.0")
    runtimeOnly(project(":pdf"))
    runtimeOnly(project(":csv"))


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

// ovaj ne pakuje dobro servise
tasks.jar {
    manifest {
        attributes["Main-Class"] = "main.MainKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

application {
    mainClass.set("main.MainKt")
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveFileName.set("app.jar") // Optional: Set the desired jar name
        mergeServiceFiles() // Merge META-INF/services

        manifest {
            attributes["Main-Class"] = "maine.MainKt" // Replace with your main class
        }
    }
}
