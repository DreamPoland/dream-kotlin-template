repositories {
    maven("https://repo.codemc.io/repository/nms")
}

dependencies {
    implementation(project(":bukkit:mcversion:api"))

    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}