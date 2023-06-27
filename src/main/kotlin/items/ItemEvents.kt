package me.sirsam.trolls.items

import me.sirsam.trolls.helpers.Utilities
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class ItemEvents : Listener {
    private val utils = Utilities()

    @EventHandler
    fun fishEvent(event: PlayerFishEvent) {
        val p = event.player
        val item = p.inventory.itemInMainHand
        val data = item.itemMeta.persistentDataContainer
        val strength = .3

        if (data.get(utils.nameKey, PersistentDataType.STRING) == "Grappling Hook" && event.state == PlayerFishEvent.State.REEL_IN) {
            val hookLocation = event.hook.location
            val change = hookLocation.subtract(p.location)
            p.velocity = change.toVector().multiply(strength)
        }
    }

    @EventHandler
    fun throwTNT(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand
        if(item.itemMeta != null) {
            val data = item.itemMeta.persistentDataContainer
            val strength = 1.4

            if (data.get(utils.nameKey, PersistentDataType.STRING) == "Throwable TNT" && event.action == Action.RIGHT_CLICK_BLOCK) { event.isCancelled = true }

            if (data.get(utils.nameKey, PersistentDataType.STRING) == "Throwable TNT" && event.action == Action.RIGHT_CLICK_AIR) {
                if (player.gameMode != GameMode.CREATIVE) { utils.destroy(item, 1) }

                val direction = player.location.direction
                val tntEntity = player.world.spawnEntity(player.location, EntityType.PRIMED_TNT)
                tntEntity.velocity = direction.multiply(strength)
            }
        }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) {
            if (item.type == Material.DISPENSER && player.isSneaking) {
                event.isCancelled = true

                player.openInventory(createDispenserGUI())
            }
        }
    }

    private fun createDispenserGUI(): Inventory {
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
    fun onProjectileLaunch(event: EntityShootBowEvent){
        if (event.entity is Player){
            val player = event.entity as Player
            if(player.inventory.itemInMainHand.itemMeta != null && player.inventory.itemInMainHand.itemMeta.lore != null && player.inventory.itemInMainHand.itemMeta.lore!!.contains("§7Creates an explosive...")){
                event.projectile.customName = "Explosive Bow"
            }
        }
    }

    @EventHandler
    fun onProjectileHit(event: ProjectileHitEvent){
        if (event.entity.customName != null) {
            if (event.entity.customName.equals("Explosive Bow")) {
                event.entity.world.createExplosion(event.entity.location, 5f, false, false)
            }
        }
    }
}