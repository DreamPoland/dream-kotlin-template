package cc.dreamcode.template

import cc.dreamcode.platform.DreamVersion
import cc.dreamcode.platform.cli.DreamCliPlatform
import cc.dreamcode.platform.cli.component.ConfigurationComponentResolver
import cc.dreamcode.platform.cli.component.DocumentPersistenceComponentResolver
import cc.dreamcode.platform.cli.component.DocumentRepositoryComponentResolver
import cc.dreamcode.platform.component.ComponentManager
import cc.dreamcode.template.config.PluginConfig
import cc.dreamcode.template.user.UserRepository
import eu.okaeri.configs.serdes.OkaeriSerdesPack
import eu.okaeri.persistence.document.DocumentPersistence
import lombok.NoArgsConstructor

@NoArgsConstructor
class CliTemplateApplication : DreamCliPlatform() {
    override fun enable(componentManager: ComponentManager) {
        componentManager.registerResolver(ConfigurationComponentResolver::class.java)
        componentManager.registerComponent(PluginConfig::class.java) { pluginConfig: PluginConfig ->
            // register persistence + repositories
            this.registerInjectable(pluginConfig.storageConfig)
            componentManager.registerResolver(DocumentPersistenceComponentResolver::class.java)
            componentManager.registerResolver(DocumentRepositoryComponentResolver::class.java)
            componentManager.registerComponent(DocumentPersistence::class.java)
            componentManager.registerComponent(UserRepository::class.java)
        }
    }

    override fun disable() {
        // features need to be call when bot is stopping
    }

    override fun getDreamVersion(): DreamVersion {
        return DreamVersion.create("Dream-Template", "1.0", "author")
    }

    override fun getPluginSerdesPack(): OkaeriSerdesPack {
        return OkaeriSerdesPack { }
    }
}