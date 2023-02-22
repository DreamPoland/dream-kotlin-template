package cc.dreamcode.template

import cc.dreamcode.command.bukkit.BukkitCommandProvider
import cc.dreamcode.menu.bukkit.BukkitMenuProvider
import cc.dreamcode.menu.serdes.bukkit.okaeri.MenuBuilderSerdes
import cc.dreamcode.notice.bukkit.BukkitNoticeProvider
import cc.dreamcode.notice.bukkit.okaeri_serdes.BukkitNoticeSerdes
import cc.dreamcode.platform.DreamVersion
import cc.dreamcode.platform.bukkit.DreamBukkitPlatform
import cc.dreamcode.platform.bukkit.component.*
import cc.dreamcode.platform.component.ComponentManager
import cc.dreamcode.template.config.MessageConfig
import cc.dreamcode.template.config.PluginConfig
import cc.dreamcode.template.mcversion.VersionProvider
import cc.dreamcode.template.user.UserRepository
import eu.okaeri.configs.serdes.OkaeriSerdesPack
import eu.okaeri.configs.serdes.SerdesRegistry
import eu.okaeri.persistence.document.DocumentPersistence
import eu.okaeri.tasker.bukkit.BukkitTasker

class BukkitTemplatePlugin : DreamBukkitPlatform() {
    override fun load(componentManager: ComponentManager) {
        bukkitTemplatePlugin = this
    }

    override fun enable(componentManager: ComponentManager) {
        this.registerInjectable(VersionProvider.versionAccessor)
        this.registerInjectable(BukkitTasker.newPool(this))
        this.registerInjectable(BukkitMenuProvider.create(this))
        this.registerInjectable(BukkitNoticeProvider.create(this))
        this.registerInjectable(BukkitCommandProvider.create(this, injector))

        componentManager.registerResolver(CommandComponentResolver::class.java)
        componentManager.registerResolver(ListenerComponentResolver::class.java)
        componentManager.registerResolver(RunnableComponentResolver::class.java)
        componentManager.registerResolver(ConfigurationComponentResolver::class.java)

        componentManager.registerComponent(MessageConfig::class.java) { messageConfig: MessageConfig ->
            this.getInject(
                BukkitCommandProvider::class.java
            ).ifPresent { bukkitCommandProvider: BukkitCommandProvider ->
                bukkitCommandProvider.setNoPermissionMessage(messageConfig.noPermission)
                bukkitCommandProvider.setNoPlayerMessage(messageConfig.noPlayer)
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
            registry.register(BukkitNoticeSerdes())
            registry.register(MenuBuilderSerdes())
        }
    }

    override fun getPersistenceSerdesPack(): OkaeriSerdesPack {
        return OkaeriSerdesPack {

        }
    }


    companion object {
        lateinit var bukkitTemplatePlugin: BukkitTemplatePlugin
    }
}