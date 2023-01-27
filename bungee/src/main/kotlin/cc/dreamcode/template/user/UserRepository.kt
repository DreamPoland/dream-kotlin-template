package cc.dreamcode.template.user

import eu.okaeri.persistence.repository.DocumentRepository
import eu.okaeri.persistence.repository.annotation.DocumentCollection
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.*
import java.util.concurrent.CompletableFuture

@DocumentCollection(path = "user", keyLength = 36)
interface UserRepository : DocumentRepository<UUID, User> {
    fun findOrCreate(uuid: UUID, userName: String?): User {
        val user = findOrCreateByPath(uuid)
        if (user != null && userName != null) {
            user.name = userName
        }
        return user
    }

    fun findOrCreateFuture(uuid: UUID, userName: String): CompletableFuture<User> {
        return CompletableFuture.supplyAsync { findOrCreate(uuid, userName) }
    }

    fun findOrCreateByUUID(uuid: UUID): User {
        return findOrCreate(uuid, null)
    }

    fun findOrCreateByUUIDFuture(uuid: UUID): CompletableFuture<User> {
        return CompletableFuture.supplyAsync { findOrCreate(uuid, null) }
    }

    fun findOrCreateByHumanEntity(player: ProxiedPlayer): User {
        return findOrCreate(player.uniqueId, player.name)
    }

    fun findOrCreateByHumanEntityFuture(player: ProxiedPlayer): CompletableFuture<User> {
        return CompletableFuture.supplyAsync { findOrCreate(player.uniqueId, player.name) }
    }

    fun findByName(name: String, ignoreCase: Boolean): Optional<User> {
        return streamAll()
            .filter { user: User ->
                if (ignoreCase) user.name.equals(
                    name,
                    ignoreCase = true
                ) else user.name == name
            }
            .findFirst()
    }

    fun findByNameFuture(name: String, ignoreCase: Boolean): CompletableFuture<Optional<User>> {
        return CompletableFuture.supplyAsync { findByName(name, ignoreCase) }
    }
}