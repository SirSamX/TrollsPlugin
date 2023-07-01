package me.sirsam.trolls.items

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.helpers.Utilities
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.AbstractArrow
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.LargeFireball
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable

class ItemEvents : Listener {
    private val utils = Utilities()
    private val plugin = Trolls.getPlugin()

    @EventHandler
    fun grapplingHookLaunch(event: PlayerFishEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand
        val data = item.itemMeta.persistentDataContainer
        val strength = .3

        if (data.get(utils.nameKey, PersistentDataType.STRING) == "grappling_hook" && event.state == PlayerFishEvent.State.REEL_IN) {
            val hookLocation = event.hook.location
            val change = hookLocation.subtract(player.location)
            player.velocity = change.toVector().multiply(strength)
        }
    }

    @EventHandler
    fun throwTNT(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if(item.itemMeta != null) {
            val data = item.itemMeta.persistentDataContainer

            if (data.get(utils.nameKey, PersistentDataType.STRING) == "throwable_tnt" && event.action.isRightClick) {
                val strength = 1.4
                event.isCancelled = true
                if (player.gameMode != GameMode.CREATIVE) { utils.destroy(item, 1) }
                val direction = player.location.direction
                val tntEntity = player.world.spawnEntity(player.location, EntityType.PRIMED_TNT)
                tntEntity.velocity = direction.multiply(strength)
            }
        }
    }

    @EventHandler
    fun openShootyBoxGui(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand
        val data = item.itemMeta.persistentDataContainer

        if (data.get(utils.nameKey, PersistentDataType.STRING) == "shooty_box" && event.action.isRightClick) {
            event.isCancelled = true
            if (player.isSneaking) {
                player.openInventory(shootyBoxGUI())
            }
        }
    }

    private fun shootyBoxGUI(): Inventory {
        val gui = Bukkit.createInventory(null, InventoryType.DISPENSER, Component.text("Shooty Box"))
        val item = ItemStack(Material.DIAMOND)
        val meta = item.itemMeta

        meta.displayName(Component.text("Example Item"))
        item.itemMeta = meta

        for (i in 0..8){
            gui.setItem(i, item)
        }

        return gui
    }


    @EventHandler
    fun explosiveBowShoot(event: EntityShootBowEvent){
        if (event.entity is Player){
            val player = event.entity as Player
            if (player.inventory.itemInMainHand.itemMeta.persistentDataContainer.get(utils.nameKey, PersistentDataType.STRING) == "explosive_bow") {
                event.projectile.customName(Component.text("Explosive Arrow"))
            }
        }
    }

    @EventHandler
    fun explosiveBowHit(event: ProjectileHitEvent){
        if (event.entity.customName() != null) {
            if (event.entity.customName() == Component.text("Explosive Arrow")) {
                event.entity.world.createExplosion(event.entity.location, 2.5f, false, true)
                event.entity.remove()
            }
        }
    }

    @EventHandler
    fun leap(event: PlayerInteractEvent) {
        val player = event.player
        val strength = .3

        if (player.inventory.itemInMainHand.itemMeta.persistentDataContainer.get(utils.nameKey, PersistentDataType.STRING) == "leap" && event.action.isRightClick) {
            player.velocity = player.eyeLocation.toVector().multiply(strength)
        }
    }

    @EventHandler
    fun throwFireball(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if(item.itemMeta != null) {
            val data = item.itemMeta.persistentDataContainer

            if (data.get(utils.nameKey, PersistentDataType.STRING) == "throwable_fireball" && event.action.isRightClick) {
                val strength = 2
                event.isCancelled = true
                if (player.gameMode != GameMode.CREATIVE) { utils.destroy(item, 1) }
                player.launchProjectile(LargeFireball::class.java, player.eyeLocation.direction.normalize().multiply(strength))
            }
        }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if(item.itemMeta != null) {
            val data = item.itemMeta.persistentDataContainer
            if (event.action.isLeftClick && data.get(utils.nameKey, PersistentDataType.STRING) == "terminator") {
                val arrow = player.launchProjectile(Arrow::class.java)
                shootSpreadArrows(player, arrow, 3, 5f, 8.0)
            }
        }
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
            }.runTaskLater(plugin, 60L)
        }
        arrow.remove()
    }
}