package me.sirsam.trolls.core.item

import net.kyori.adventure.text.format.NamedTextColor

enum class Rarity(val color: NamedTextColor, private val level: Int) {
    COMMON(NamedTextColor.WHITE, 0),
    UNCOMMON(NamedTextColor.GREEN, 1),
    RARE(NamedTextColor.BLUE, 2),
    EPIC(NamedTextColor.DARK_PURPLE, 3),
    LEGENDARY(NamedTextColor.GOLD, 4),
    MYTHIC(NamedTextColor.LIGHT_PURPLE, 5),
    DIVINE(NamedTextColor.AQUA, 6),
    SPECIAL(NamedTextColor.RED, 7),
    VERY_SPECIAL(NamedTextColor.DARK_RED, 8),
    ADMIN(NamedTextColor.DARK_RED, 9),
    UNFINISHED(NamedTextColor.DARK_RED, -1);

    fun isRarerThan(rarity: Rarity): Boolean {
        val current: Int = this.level
        val param: Int = rarity.level
        return current > param
    }
}