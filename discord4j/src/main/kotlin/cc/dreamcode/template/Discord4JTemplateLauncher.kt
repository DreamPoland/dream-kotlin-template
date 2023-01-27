package cc.dreamcode.template

import cc.dreamcode.platform.discord4j.DreamDiscord4JPlatform

object Discord4JTemplateLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        // Use argument or create config.
        DreamDiscord4JPlatform.run(Discord4JTemplateBot())
    }
}