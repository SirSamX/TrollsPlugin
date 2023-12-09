package me.sirsam.trolls.item

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material

class Jukebox : AbilityItem(ItemProperties(
    id = "jukebox",
    material = Material.JUKEBOX,
    name = "Jukebox",
    abilities = listOf(Ability("Play Music!", AbilityType.RIGHT_CLICK)),
    rarity = Rarity.RARE
)) {
}