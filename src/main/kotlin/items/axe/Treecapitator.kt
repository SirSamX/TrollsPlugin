package me.sirsam.trolls.items.axe

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import org.bukkit.Material

class Treecapitator : AbilityItem(ItemProperties(
    id = "treecapitator",
    material = Material.GOLDEN_AXE,
    name = "Treecapitator",
    description = "Break wood to cut down a whole tree.",
    note = "Blocks: 50, Delay: 1 Ticks",
    rarity = Rarity.EPIC,
    raritySuffix = "AXE"
)) {
}