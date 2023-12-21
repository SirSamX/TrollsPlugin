package me.sirsam.trolls.item.axe

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import org.bukkit.Material
import org.bukkit.event.block.BlockBreakEvent

class Treecapitator : AbilityItem(ItemProperties(
    id = "treecapitator",
    material = Material.GOLDEN_AXE,
    name = "Treecapitator",
    description = "Break wood to cut down a whole tree.",
    note = "Blocks: 50, Delay: 1 Tick",
    rarity = Rarity.EPIC,
    raritySuffix = "AXE"
)) {
    override fun blockBreak(event: BlockBreakEvent): AbilityResult {
        Utils.veinMine(event.block, Utils.logMaterials, 50, 1L); event.isCancelled = true

        return AbilityResult.SUCCESS
    }
}