package me.sirsam.trolls.items

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material

class MagicalWand : AbilityItem(ItemProperties(
    id = "magical_wand",
    material = Material.BLAZE_ROD,
    name = "Magical Wand",
    abilities = listOf(Ability("Cast spell", AbilityType.RIGHT_CLICK), Ability("Switch ability", AbilityType.LEFT_CLICK)),
    note = "TestItem",
    rarity = Rarity.ADMIN,
    raritySuffix = "WAND"
)) {
}