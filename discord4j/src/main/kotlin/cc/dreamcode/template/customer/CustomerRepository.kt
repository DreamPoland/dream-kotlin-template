package cc.dreamcode.template.customer

import discord4j.common.util.Snowflake
import eu.okaeri.persistence.repository.DocumentRepository
import eu.okaeri.persistence.repository.annotation.DocumentCollection
import java.util.*
import java.util.concurrent.CompletableFuture

@DocumentCollection(path = "user", keyLength = 36)
interface CustomerRepository : DocumentRepository<Snowflake, Customer> {
    fun findOrCreate(snowflake: Snowflake, userName: String?): Customer {
        val user = findOrCreateByPath(snowflake)
        if (user != null && userName != null) {
            user.name = userName
        }
        return user
    }

    fun findOrCreateFuture(snowflake: Snowflake, userName: String): CompletableFuture<Customer> {
        return CompletableFuture.supplyAsync { findOrCreate(snowflake, userName) }
    }

    fun findOrCreateBySnowflake(snowflake: Snowflake): Customer {
        return findOrCreate(snowflake, null)
    }

    fun findOrCreateBySnowflakeFuture(snowflake: Snowflake): CompletableFuture<Customer> {
        return CompletableFuture.supplyAsync { findOrCreate(snowflake, null) }
    }

    fun findByName(name: String, ignoreCase: Boolean): Optional<Customer> {
        return streamAll()
            .filter { customer: Customer ->
                if (ignoreCase) customer.name.equals(
                    name,
                    ignoreCase = true
                ) else customer.name == name
            }
            .findFirst()
    }

    fun findByNameFuture(name: String, ignoreCase: Boolean): CompletableFuture<Optional<Customer>> {
        return CompletableFuture.supplyAsync { findByName(name, ignoreCase) }
    }
}