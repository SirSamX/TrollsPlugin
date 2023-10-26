package me.sirsam.trolls.items

import me.sirsam.trolls.core.Main
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.LivingEntity
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable

class FlySwatter : AbilityItem(ItemProperties(
    "fly_swatter",
    Material.IRON_SHOVEL,
    "Fliegenklatsche",
    abilities = listOf(Ability("Suck!", AbilityType.RIGHT_CLICK))
)) {
    override fun rightClickBlock(event: PlayerInteractEvent): AbilityResult {
        val player = event.player
        val location = event.clickedBlock!!.location
        val world = location.world

        location.world.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f)

        val entities = world.getNearbyEntities(location, 10.0, 10.0, 10.0) { entity ->
            entity != player && entity is LivingEntity
        }

        for (entity in entities) {
            val levitation = 20
            (entity as LivingEntity).addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, levitation, 0))
            val vectorToExplosion = location.toVector().subtract(entity.location.toVector())
            entity.velocity = vectorToExplosion.normalize().multiply(1.5)
        }

        val explosionDelay = 20L
        object : BukkitRunnable() {
            override fun run() {
                //explosionLocation.createExplosion(1f, false)
            }
        }.runTaskLater(Main.plugin, explosionDelay)

        return AbilityResult.SUCCESS
    }
}