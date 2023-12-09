package me.sirsam.trolls.item.axe

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import org.bukkit.Material

class JungleAxe : AbilityItem(ItemProperties(
    id = "jungle_axe",
    material = Material.WOODEN_AXE,
    name = "Jungle Axe",
    description = "Break wood to cut down a whole tree.",
    note = "Blocks: 25, Delay: 3 Tick",
    rarity = Rarity.UNCOMMON,
    raritySuffix = "AXE"
)) {
}