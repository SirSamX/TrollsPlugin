package me.sirsam.trolls.item.axe

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import org.bukkit.Material
import org.bukkit.event.block.BlockBreakEvent

class JungleAxe : AbilityItem(ItemProperties(
    id = "jungle_axe",
    material = Material.WOODEN_AXE,
    name = "Jungle Axe",
    description = "Break wood to cut down a whole tree.",
    note = "Blocks: 25, Delay: 3 Ticks",
    rarity = Rarity.UNCOMMON,
    raritySuffix = "AXE"
)) {
    override fun blockBreak(event: BlockBreakEvent): AbilityResult {
        Utils.veinMine(event.block, Utils.logMaterials, 25, 3L); event.isCancelled = true

        return AbilityResult.SUCCESS
    }
}