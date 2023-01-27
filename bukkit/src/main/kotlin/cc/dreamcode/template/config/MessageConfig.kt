package cc.dreamcode.template.config

import cc.dreamcode.notice.NoticeType
import cc.dreamcode.notice.bukkit.BukkitNotice
import cc.dreamcode.platform.bukkit.component.configuration.Configuration
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.*

@Configuration(child = "message.yml")
@Headers(
    Header("## Dream-Template (Message-Config) ##"),
    Header("Dostepne type: (CHAT, ACTION_BAR, SUBTITLE, TITLE, TITLE_SUBTITLE)")
)
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
class MessageConfig : OkaeriConfig() {
    var usage = BukkitNotice(NoticeType.CHAT, "&7Poprawne uzycie: &c{usage}")
    var noPermission = BukkitNotice(NoticeType.CHAT, "&4Nie posiadasz uprawnien.")
    var noPlayer = BukkitNotice(NoticeType.CHAT, "&4Podanego gracza &cnie znaleziono.")
    var playerIsOffline = BukkitNotice(NoticeType.CHAT, "&4Podany gracz &cjest offline.")
    var notPlayer = BukkitNotice(NoticeType.CHAT, "&4Nie jestes graczem.")
    var notNumber = BukkitNotice(NoticeType.CHAT, "&4Podana liczba &cnie jest cyfra.")
    var playerIsMe = BukkitNotice(NoticeType.CHAT, "&4Nie rob tego &cna sobie.")
}