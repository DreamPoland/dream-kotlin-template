import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("checkstyle")
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

allprojects {
    group = "cc.dreamcode"
    version = "1.0-SNAPSHOT"

    apply(plugin = "kotlin")
    apply(plugin = "checkstyle")

    repositories {
        mavenCentral()
        maven("https://repo.dreamcode.cc/releases")
        maven("https://storehouse.okaeri.eu/repository/maven-public")
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }
}

subprojects {
    apply(plugin = "com.github.johnrengelman.shadow")

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")
        testCompileOnly("org.projectlombok:lombok:1.18.24")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
    }
}