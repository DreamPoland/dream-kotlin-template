package cc.dreamcode.template.config

import cc.dreamcode.notice.NoticeType
import cc.dreamcode.notice.bungee.BungeeNotice
import cc.dreamcode.platform.bungee.component.configuration.Configuration
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.*

@Configuration(child = "message.yml")
@Headers(
    Header("## Dream-Template (Message-Config) ##"),
    Header("Dostepne type: (CHAT, ACTION_BAR, SUBTITLE, TITLE, TITLE_SUBTITLE)")
)
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
class MessageConfig : OkaeriConfig() {
    var usage = BungeeNotice(NoticeType.CHAT, "&7Poprawne uzycie: &c{usage}")
    var noPermission = BungeeNotice(NoticeType.CHAT, "&4Nie posiadasz uprawnien.")
    var noPlayer = BungeeNotice(NoticeType.CHAT, "&4Podanego gracza &cnie znaleziono.")
    var playerIsOffline = BungeeNotice(NoticeType.CHAT, "&4Podany gracz &cjest offline.")
    var notPlayer = BungeeNotice(NoticeType.CHAT, "&4Nie jestes graczem.")
    var notNumber = BungeeNotice(NoticeType.CHAT, "&4Podana liczba &cnie jest cyfra.")
    var playerIsMe = BungeeNotice(NoticeType.CHAT, "&4Nie rob tego &cna sobie.")
}