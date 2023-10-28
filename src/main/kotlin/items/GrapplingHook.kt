package me.sirsam.trolls.items

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import org.bukkit.Material

class GrapplingHook : AbilityItem(ItemProperties(
    id = "grappling_hook",
    material = Material.FISHING_ROD,
    name = "Grappling Hook",
    description = "Travel in style...",
    note = null,
    rarity = Rarity.RARE)
) {
}