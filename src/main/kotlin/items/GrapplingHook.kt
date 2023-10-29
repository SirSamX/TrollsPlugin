package me.sirsam.trolls.items

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import org.bukkit.Material
import org.bukkit.event.player.PlayerFishEvent

class GrapplingHook : AbilityItem(ItemProperties(
    id = "grappling_hook",
    material = Material.FISHING_ROD,
    name = "Grappling Hook",
    description = "Travel in style...",
    rarity = Rarity.RARE)
) {
    override fun fish(event: PlayerFishEvent): AbilityResult {
        if (event.state == PlayerFishEvent.State.FISHING) return AbilityResult.FAIL

        val player = event.player
        val strength = .3
        val hookLocation = event.hook.location
        val change = hookLocation.subtract(player.location)
        player.velocity = change.toVector().multiply(strength)

        return AbilityResult.SUCCESS
    }
}