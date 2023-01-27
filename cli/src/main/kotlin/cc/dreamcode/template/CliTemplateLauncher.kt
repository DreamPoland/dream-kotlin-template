package cc.dreamcode.template

import cc.dreamcode.platform.cli.DreamCliPlatform

object CliTemplateLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        // Use argument or create config.
        DreamCliPlatform.run(CliTemplateApplication())
    }
}