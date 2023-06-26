package me.sirsam.trolls.helpers

import net.kyori.adventure.text.format.NamedTextColor

enum class Ranks(val prefix: String, val color: NamedTextColor, val suffix: String) {
    OWNER("[OWNER] ", NamedTextColor.DARK_RED, ""),
    ADMIN("[ADMIN] ", NamedTextColor.DARK_BLUE, ""),
    MODERATOR("[MOD] ", NamedTextColor.YELLOW, ""),
    PLAYER("[PLAYER] ", NamedTextColor.DARK_GRAY, "")
}