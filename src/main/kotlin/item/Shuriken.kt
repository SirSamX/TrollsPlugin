package me.sirsam.trolls.item

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent

class Shuriken : AbilityItem(ItemProperties(
    id = "shuriken",
    material = Material.NETHER_STAR,
    name = "Shuriken",
    abilities = listOf(Ability("Throw Shuriken", AbilityType.RIGHT_CLICK)),
    rarity = Rarity.UNCOMMON,
    raritySuffix = "SHURIKEN",
    customModelData = 69001,
    stackable = true
)) {
    override fun rightClick(event: PlayerInteractEvent): AbilityResult {
        Utils.throwItem(event.player, event.item!!, 1.7f, 7.5)

        return AbilityResult.SUCCESS
    }
}