package me.sirsam.trolls.items

import me.sirsam.trolls.core.Main
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material
import org.bukkit.entity.AbstractArrow
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.scheduler.BukkitRunnable

class Terminator : AbilityItem(ItemProperties(
    id = "terminator",
    material = Material.BOW,
    name = "Terminator",
    abilities = listOf(Ability("Shoot 3 arrows!", AbilityType.LEFT_CLICK)),
    note = "Infinite arrows!",
    rarity = Rarity.MYTHIC,
    raritySuffix = "BOW"
)) {
    override fun leftClick(event: PlayerInteractEvent): AbilityResult {
        val player = event.player

        val arrow = player.launchProjectile(Arrow::class.java)
        event.isCancelled = true
        shootSpreadArrows(player, arrow, 3, 5f, 8.0)

        return AbilityResult.SUCCESS
    }

    private fun shootSpreadArrows(player: Player, arrow: Arrow, amount: Int, angle: Float, damage: Double) {
        for (i in 0 until amount) {
            val rotation = Math.toRadians(angle * (i - (amount - 1) / 2.0))
            val direction = arrow.velocity.clone().rotateAroundY(rotation)
            val newArrow = player.launchProjectile(Arrow::class.java)
            newArrow.damage = damage
            newArrow.pickupStatus = AbstractArrow.PickupStatus.CREATIVE_ONLY
            newArrow.velocity = direction
            object : BukkitRunnable() {
                override fun run() {
                    newArrow.remove()
                }
            }.runTaskLater(Main.plugin, 60L)
        }
        arrow.remove()
    }
}