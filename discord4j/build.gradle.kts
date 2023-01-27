import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    // -- discord4j --
    implementation("com.discord4j:discord4j-core:3.2.3")

    // -- logging --
    implementation("ch.qos.logback:logback-core:1.4.5")
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("org.slf4j:slf4j-api:2.0.6")

    // -- dream-platform --
    implementation("cc.dreamcode.platform:core:1.4.1")
    implementation("cc.dreamcode.platform:discord4j:1.4.1")

    // -- dream-utilities --
    implementation("cc.dreamcode:utilities:1.1")

    // -- configs--
    implementation("eu.okaeri:okaeri-configs-yaml-snakeyaml:4.0.8")
    implementation("eu.okaeri:okaeri-configs-serdes-commons:4.0.8")

    // -- json configure --
    implementation("eu.okaeri:okaeri-configs-json-simple:4.0.8")

    // -- persistence data --
    implementation("eu.okaeri:okaeri-persistence-flat:2.0.0-beta.1")
    implementation("eu.okaeri:okaeri-persistence-jdbc:2.0.0-beta.1")
    implementation("eu.okaeri:okaeri-persistence-redis:2.0.0-beta.1")
    implementation("eu.okaeri:okaeri-persistence-mongo:2.0.0-beta.1")

    // -- injector --
    implementation("eu.okaeri:okaeri-injector:2.1.0")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("Dream-Template-Discord4J-${project.version}.jar")

    manifest {
        attributes["Main-Class"] = "cc.dreamcode.template.Discord4JTemplateLauncher"
    }

    relocate("eu.okaeri", "cc.dreamcode.template.libs.eu.okaeri")
    relocate("cc.dreamcode.utilities", "cc.dreamcode.template.libs.cc.dreamcode.utilities")

    relocate("io.lettuce", "cc.dreamcode.template.libs.io.lettuce")
    relocate("io.netty", "cc.dreamcode.template.libs.io.netty")
    relocate("reactor", "cc.dreamcode.template.libs.reactor")
    relocate("org.reactivestreams", "cc.dreamcode.template.libs.org.reactivestreams")
    relocate("org.bson", "cc.dreamcode.template.libs.org.bson")
    relocate("com.mongodb", "cc.dreamcode.template.libs.com.mongodb")
    relocate("com.zaxxer", "cc.dreamcode.template.libs.com.zaxxer")
    relocate("org.mariadb", "cc.dreamcode.template.libs.org.mariadb")
    relocate("org.json", "cc.dreamcode.template.libs.org.json")
    relocate("com.google.gson", "cc.dreamcode.template.libs.com.google.gson")
}