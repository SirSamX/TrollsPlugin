package me.sirsam.trolls.items

import net.kyori.adventure.text.format.NamedTextColor

enum class TrollRarity(val color: NamedTextColor) {
    COMMON(NamedTextColor.WHITE),
    UNCOMMON(NamedTextColor.GREEN),
    RARE(NamedTextColor.BLUE),
    EPIC(NamedTextColor.DARK_PURPLE),
    LEGENDARY(NamedTextColor.GOLD),
    MYTHIC(NamedTextColor.LIGHT_PURPLE),
    DIVINE(NamedTextColor.AQUA),
    SPECIAL(NamedTextColor.RED),
    VERY_SPECIAL(NamedTextColor.DARK_RED),
    ADMIN(NamedTextColor.DARK_RED),
    UNFINISHED(NamedTextColor.DARK_RED)
}