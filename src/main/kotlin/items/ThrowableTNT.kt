package me.sirsam.trolls.items

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material

class ThrowableTNT : AbilityItem(ItemProperties(
    id = "throwable_tnt",
    material = Material.TNT,
    name = "Throwable TNT",
    note = "Don't grief!",
    rarity = Rarity.UNCOMMON,
    raritySuffix = "TNT",
    abilities = listOf(Ability("Throw TNT", AbilityType.RIGHT_CLICK, oneTimeUse = true)),
    stackable = true
)) {
}