package me.sirsam.trolls.items

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.player.PlayerInteractEvent

class PogeroniSword: AbilityItem(ItemProperties(
    id = "pogeroni_sword",
    material = Material.NETHERITE_SWORD,
    name = "Pogeroni Sword",
    description = "Nimmt Emanuel hops.",
    note = "Hallo",
    rarity = Rarity.LEGENDARY,
    raritySuffix = "SWORD",
    enchantments = mutableMapOf(Enchantment.FIRE_ASPECT to 5, Enchantment.DURABILITY to 3),
    abilities = listOf(Ability("Throw Sword!", AbilityType.RIGHT_CLICK)),
)) {
    override fun rightClick(event: PlayerInteractEvent): AbilityResult {
        Utils.throwItem(event.player, event.item!!, 1.2f, 13.5)

        return AbilityResult.SUCCESS
    }
}