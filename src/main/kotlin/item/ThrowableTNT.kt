package me.sirsam.trolls.item

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.player.PlayerInteractEvent

class ThrowableTNT : AbilityItem(ItemProperties(
    id = "throwable_tnt",
    material = Material.TNT,
    name = "Throwable TNT",
    note = "Don't grief!",
    rarity = Rarity.UNCOMMON,
    raritySuffix = "TNT",
    abilities = listOf(Ability("Throw TNT", AbilityType.RIGHT_CLICK, oneTimeUse = true)),
    stackable = true
)) {
    override fun rightClick(event: PlayerInteractEvent): AbilityResult {
        val player = event.player
        val strength = 1.4

        event.isCancelled = true
        if (player.gameMode != GameMode.CREATIVE) {
            Utils.destroy(event.item!!, 1)
        }
        val direction = player.location.direction
        val tntEntity = player.world.spawnEntity(player.location, EntityType.PRIMED_TNT)
        tntEntity.velocity = direction.multiply(strength)

        return AbilityResult.SUCCESS
    }
}