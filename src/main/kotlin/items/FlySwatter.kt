package me.sirsam.trolls.items

import me.sirsam.trolls.core.Main
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.scheduler.BukkitRunnable

class FlySwatter : AbilityItem(ItemProperties(
    "fly_swatter",
    Material.COBWEB,
    "Fliegenklatsche",
    abilities = listOf(Ability("Suck!", AbilityType.SHIFT_RIGHT_CLICK))
)) {
    override fun rightClickBlock(event: PlayerInteractEvent): AbilityResult {
        val player = event.player
        val explosionLocation = event.clickedBlock!!.location
        val world = explosionLocation.world

        val entities = world.getNearbyEntities(explosionLocation, 10.0, 10.0, 10.0) { entity ->
            entity != player
        }

        for (entity in entities) {
            val vectorToExplosion = explosionLocation.toVector().subtract(entity.location.toVector())
            entity.velocity = vectorToExplosion.normalize().multiply(1.0)
        }

        val explosionDelay = 40L
        object : BukkitRunnable() {
            override fun run() {
                explosionLocation.createExplosion(50f, false)
            }
        }.runTaskLater(Main.plugin, explosionDelay)

        return AbilityResult.SUCCESS
    }
}