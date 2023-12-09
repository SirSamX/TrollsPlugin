package me.sirsam.trolls.listener

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.inventory.ItemStack

class OnDeath : Listener {
    private fun drop(item: ItemStack, location: Location) {
        location.world.dropItem(location, item)
    }

    @EventHandler
    fun onDeath(event: EntityDeathEvent) {
        val entity = event.entity
        val loc = entity.location

        when (entity.type) {
            EntityType.WARDEN -> {
                drop(ItemStack(Material.ECHO_SHARD), loc)
            }

            EntityType.ENDER_DRAGON -> {
                for (player in loc.getNearbyPlayers(250.0)) {
                    drop(ItemStack(Material.ECHO_SHARD), player.location)
                }
            }

            EntityType.WITHER -> {
                drop(ItemStack(Material.ECHO_SHARD), loc)
            }

            EntityType.ELDER_GUARDIAN -> {
                drop(ItemStack(Material.ECHO_SHARD), loc)
            }

            else -> {}
        }
    }
}