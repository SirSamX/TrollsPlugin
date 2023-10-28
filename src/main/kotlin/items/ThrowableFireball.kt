package me.sirsam.trolls.items

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material

class ThrowableFireball : AbilityItem(ItemProperties(
    id = "throwable_fireball",
    material = Material.FIRE_CHARGE,
    name = "Throwable Fireball",
    rarity = Rarity.UNCOMMON,
    raritySuffix = "FIREBALL",
    abilities = listOf(Ability("Throw Fireball", AbilityType.RIGHT_CLICK, oneTimeUse = true)),
    stackable = true
)) {
}