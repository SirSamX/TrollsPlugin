package me.sirsam.trolls.items

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material

class Shuriken : AbilityItem(ItemProperties(
    id = "shuriken",
    material = Material.NETHER_STAR,
    name = "Shuriken",
    abilities = listOf(Ability("Throw Shuriken", AbilityType.RIGHT_CLICK)),
    rarity = Rarity.UNCOMMON,
    raritySuffix = "SHURIKEN",
    customModelData = 69001
)) {
}