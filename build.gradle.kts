plugins {
    java
    kotlin("jvm") version "1.3.72"
    application
    id("com.heroku.sdk.heroku-gradle") version "1.0.4"
}

group = "com.github.mrindeciso"
version = "1.0-SNAPSHOT"

val mainClassName = "com.github.mrindeciso.pagnobot.App"

defaultTasks("clean", "build")

repositories {
    mavenCentral()
    jcenter()
    maven("https://oss.jfrog.org/artifactory/libs-release")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit", "junit", "4.13")

    implementation("ch.qos.logback", "logback-classic", "1.2.3")
    implementation("com.github.twitch4j", "twitch4j", "1.0.0-alpha.20")

    implementation("org.mapdb", "mapdb", "3.0.8")

    implementation("org.koin", "koin-core", "2.1.5")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    val stage by registering {
        dependsOn("build")
    }
}