package me.sirsam.trolls.item

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import org.bukkit.Material

class ExplosiveBow : AbilityItem(ItemProperties(
    id = "explosive_bow",
    material = Material.BOW,
    name = "Explosive Bow",
    description = "Shoot to create an explosion.",
    note = null,
    rarity = Rarity.LEGENDARY,
    raritySuffix = "BOW")
) {
}