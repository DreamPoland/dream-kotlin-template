import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

repositories {
    maven("https://repo.codemc.io/repository/nms")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    // -- bukkit-versions --
    project(":bukkit:mcversion").dependencyProject.subprojects.forEach {
        implementation(it)
    }

    // -- spigot api -- (base)
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    // -- dream-platform --
    implementation("cc.dreamcode.platform:core:1.5.3")
    implementation("cc.dreamcode.platform:bukkit:1.5.3")

    // -- dream-utilities --
    implementation("cc.dreamcode:utilities:1.1.2")
    implementation("cc.dreamcode:utilities-bukkit:1.1.2")

    // -- dream-notice --
    implementation("cc.dreamcode.notice:core:1.2.2")
    implementation("cc.dreamcode.notice:bukkit:1.2.2")
    implementation("cc.dreamcode.notice:bukkit-okaeri-serdes:1.2.2")

    // -- dream-command --
    implementation("cc.dreamcode.command:core:1.2.4")
    implementation("cc.dreamcode.command:bukkit:1.2.4")

    // -- dream-menu --
    implementation("cc.dreamcode.menu:core:0.4.7")
    implementation("cc.dreamcode.menu:bukkit:0.4.7")
    implementation("cc.dreamcode.menu:serdes-bukkit:0.4.7")
    implementation("cc.dreamcode.menu:serdes-bukkit-okaeri:0.4.7")

    // -- configs--
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:4.0.9")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:4.0.9")
    implementation("eu.okaeri:okaeri-configs-serdes-commons:4.0.9")

    // -- json configure --
    implementation("eu.okaeri:okaeri-configs-json-simple:4.0.9")

    // -- persistence data --
    implementation("eu.okaeri:okaeri-persistence-flat:2.0.0-beta.1")
    implementation("eu.okaeri:okaeri-persistence-jdbc:2.0.0-beta.1")
    implementation("eu.okaeri:okaeri-persistence-redis:2.0.0-beta.1")
    implementation("eu.okaeri:okaeri-persistence-mongo:2.0.0-beta.1")

    // -- injector --
    implementation("eu.okaeri:okaeri-injector:2.1.0")

    // -- placeholders --
    implementation("eu.okaeri:okaeri-placeholders-bukkit:4.0.7")

    // -- tasker (easy sync/async scheduler) --
    implementation("eu.okaeri:okaeri-tasker-bukkit:1.2.0")

    // -- Multi-Version Items helper --
    implementation("com.github.cryptomorin:XSeries:9.3.0")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("Dream-Template-Bukkit-${project.version}.jar")

    minimize()

    relocate("com.cryptomorin", "cc.dreamcode.template.libs.com.cryptomorin")
    relocate("eu.okaeri", "cc.dreamcode.template.libs.eu.okaeri")
    relocate("net.kyori", "cc.dreamcode.template.libs.net.kyori")

    relocate("cc.dreamcode.platform", "cc.dreamcode.template.libs.cc.dreamcode.platform")
    relocate("cc.dreamcode.utilities", "cc.dreamcode.template.libs.cc.dreamcode.utilities")
    relocate("cc.dreamcode.menu", "cc.dreamcode.template.libs.cc.dreamcode.menu")
    relocate("cc.dreamcode.command", "cc.dreamcode.template.libs.cc.dreamcode.command")
    relocate("cc.dreamcode.notice", "cc.dreamcode.template.libs.cc.dreamcode.notice")

    relocate("io.lettuce", "cc.dreamcode.template.libs.io.lettuce")
    relocate("io.netty", "cc.dreamcode.template.libs.io.netty")
    relocate("reactor", "cc.dreamcode.template.libs.reactor")
    relocate("org.reactivestreams", "cc.dreamcode.template.libs.org.reactivestreams")
    relocate("org.bson", "cc.dreamcode.template.libs.org.bson")
    relocate("com.mongodb", "cc.dreamcode.template.libs.com.mongodb")
    relocate("com.zaxxer", "cc.dreamcode.template.libs.com.zaxxer")
    relocate("org.slf4j", "cc.dreamcode.template.libs.org.slf4j")
    relocate("org.mariadb", "cc.dreamcode.template.libs.org.mariadb")
    relocate("org.json", "cc.dreamcode.template.libs.org.json")
    relocate("com.google.gson", "cc.dreamcode.template.libs.com.google.gson")
}