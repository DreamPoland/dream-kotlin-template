package cc.dreamcode.template

import cc.dreamcode.platform.DreamVersion
import cc.dreamcode.platform.component.ComponentManager
import cc.dreamcode.platform.discord4j.DreamDiscord4JPlatform
import cc.dreamcode.platform.discord4j.component.*
import cc.dreamcode.template.config.PluginConfig
import cc.dreamcode.template.customer.CustomerRepository
import discord4j.core.DiscordClient
import discord4j.core.GatewayDiscordClient
import eu.okaeri.configs.serdes.OkaeriSerdesPack
import eu.okaeri.persistence.document.DocumentPersistence
import lombok.NoArgsConstructor
import reactor.core.publisher.Mono
import java.util.concurrent.atomic.AtomicReference

@NoArgsConstructor
class Discord4JTemplateBot : DreamDiscord4JPlatform() {
    override fun load(componentManager: ComponentManager): Mono<GatewayDiscordClient> {
        componentManager.registerResolver(ConfigurationComponentResolver::class.java)
        val atomicGateway = AtomicReference<Mono<GatewayDiscordClient>>()

        componentManager.registerComponent(PluginConfig::class.java) { pluginConfig: PluginConfig ->
            // register jda
            atomicGateway.set(DiscordClient.create(pluginConfig.token).login())

            // register storage (can be registered in enable method)
            this.registerInjectable(pluginConfig.storageConfig)
            componentManager.registerResolver(DocumentPersistenceComponentResolver::class.java)
            componentManager.registerResolver(DocumentRepositoryComponentResolver::class.java)
        }

        return atomicGateway.get()
    }

    override fun enable(componentManager: ComponentManager) {
        componentManager.registerResolver(ListenerComponentResolver::class.java)
        componentManager.registerResolver(CommandComponentResolver::class.java)
        componentManager.registerResolver(TimerTaskComponentResolver::class.java)

        componentManager.registerComponent(DocumentPersistence::class.java)
        componentManager.registerComponent(CustomerRepository::class.java)
    }

    override fun disable() {
        // features need to be call when server is stopping
    }

    override fun getDreamVersion(): DreamVersion {
        return DreamVersion.create("Dream-Template", "1.0", "author")
    }

    override fun getPluginSerdesPack(): OkaeriSerdesPack {
        return OkaeriSerdesPack { }
    }
}