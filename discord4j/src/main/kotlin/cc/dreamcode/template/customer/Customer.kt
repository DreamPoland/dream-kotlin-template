package cc.dreamcode.template.customer

import eu.okaeri.configs.annotation.NameModifier
import eu.okaeri.configs.annotation.NameStrategy
import eu.okaeri.configs.annotation.Names
import eu.okaeri.persistence.document.Document
import lombok.Data
import lombok.EqualsAndHashCode
import java.util.*

@Data
@EqualsAndHashCode(callSuper = false)
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
data class Customer(var name: String) : Document() {
    val uniqueId: UUID
        get() = path.toUUID()
}