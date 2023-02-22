package cc.dreamcode.template

import cc.dreamcode.command.bungee.BungeeCommandProvider
import cc.dreamcode.notice.bungee.BungeeNoticeProvider
import cc.dreamcode.notice.bungee.okaeri_serdes.BungeeNoticeSerdes
import cc.dreamcode.platform.DreamVersion
import cc.dreamcode.platform.bungee.DreamBungeePlatform
import cc.dreamcode.platform.bungee.component.*
import cc.dreamcode.platform.component.ComponentManager
import cc.dreamcode.template.config.MessageConfig
import cc.dreamcode.template.config.PluginConfig
import cc.dreamcode.template.user.UserRepository
import eu.okaeri.configs.serdes.OkaeriSerdesPack
import eu.okaeri.configs.serdes.SerdesRegistry
import eu.okaeri.persistence.document.DocumentPersistence

class BungeeTemplatePlugin : DreamBungeePlatform() {
    override fun load(componentManager: ComponentManager) {
        bungeeTemplatePlugin = this
    }

    override fun enable(componentManager: ComponentManager) {
        this.registerInjectable(BungeeNoticeProvider.create(this))
        this.registerInjectable(BungeeCommandProvider.create(this, injector))

        componentManager.registerResolver(CommandComponentResolver::class.java)
        componentManager.registerResolver(ListenerComponentResolver::class.java)
        componentManager.registerResolver(RunnableComponentResolver::class.java)
        componentManager.registerResolver(ConfigurationComponentResolver::class.java)

        componentManager.registerComponent(MessageConfig::class.java) { messageConfig: MessageConfig ->
            this.getInject(
                BungeeCommandProvider::class.java
            ).ifPresent { bungeeCommandProvider: BungeeCommandProvider ->
                bungeeCommandProvider.setNoPermissionMessage(messageConfig.noPermission)
                bungeeCommandProvider.setNoPlayerMessage(messageConfig.noPlayer)
            }
        }

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
        // features need to be call when server is stopping
    }

    override fun getDreamVersion(): DreamVersion {
        return DreamVersion.create("Dream-Template", "1.0", "author")
    }

    override fun getConfigurationSerdesPack(): OkaeriSerdesPack {
        return OkaeriSerdesPack { registry: SerdesRegistry ->
            registry.register(BungeeNoticeSerdes())
        }
    }

    override fun getPersistenceSerdesPack(): OkaeriSerdesPack {
        return OkaeriSerdesPack {

        }
    }

    companion object {
        lateinit var bungeeTemplatePlugin: BungeeTemplatePlugin
    }
}