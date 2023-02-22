package cc.dreamcode.template.config

import cc.dreamcode.platform.bungee.component.configuration.Configuration
import cc.dreamcode.platform.bungee.persistence.StorageConfig
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.*

@Configuration(child = "config.yml")
@Header("## Dream-Template (Main-Config) ##")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
class PluginConfig : OkaeriConfig() {
    @Comment("Debug pokazuje dodatkowe informacje do konsoli. Lepiej wylaczyc. :P")
    var debug = true

    @Comment("Uzupelnij ponizsze menu danymi.")
    var storageConfig = StorageConfig("dreamtemplate")
}