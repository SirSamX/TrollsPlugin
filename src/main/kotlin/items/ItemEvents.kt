package me.sirsam.trolls.items

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.helpers.Utilities
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent
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

        if (data.get(utils.idKey, PersistentDataType.STRING) == "grappling_hook" && event.state == PlayerFishEvent.State.REEL_IN) {
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

            if (data.get(utils.idKey, PersistentDataType.STRING) == "throwable_tnt" && event.action.isRightClick) {
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

        if (item.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING) == "shooty_box" && event.action.isRightClick) {
            event.isCancelled = true
            if (player.isSneaking) {
                val inv = utils.getInventoryInItem(item)
                if (inv == null) {
                    player.openInventory(Bukkit.createInventory(null, InventoryType.DISPENSER, Component.text("Shooty Box")))
                } else {
                    player.openInventory(inv)
                }
            }
        }
    }

    @EventHandler
    fun closeShootyBoxGui(event: InventoryCloseEvent) {
        val item = event.player.inventory.itemInMainHand
        if (event.view.title().contains(Component.text("Shooty Box")) && item.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING) == "shooty_box") {
            utils.storeInventoryInItem(item, event.inventory)
        }
    }

    @EventHandler
    fun explosiveBowShoot(event: EntityShootBowEvent){
        if (event.entity is Player){
            val player = event.entity as Player
            if (player.inventory.itemInMainHand.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING) == "explosive_bow") {
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

        if (player.inventory.itemInMainHand.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING) == "leap" && event.action.isRightClick) {
            player.velocity = player.eyeLocation.toVector().multiply(strength)
        }
    }

    @EventHandler
    fun throwFireball(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if(item.itemMeta != null) {
            val data = item.itemMeta.persistentDataContainer

            if (data.get(utils.idKey, PersistentDataType.STRING) == "throwable_fireball" && event.action.isRightClick) {
                val strength = 2
                event.isCancelled = true
                if (player.gameMode != GameMode.CREATIVE) { utils.destroy(item, 1) }
                player.launchProjectile(LargeFireball::class.java, player.eyeLocation.direction.normalize().multiply(strength))
            }
        }
    }

    @EventHandler
    fun terminatorShoot(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if(item.itemMeta != null) {
            val data = item.itemMeta.persistentDataContainer
            if (event.action.isLeftClick && data.get(utils.idKey, PersistentDataType.STRING) == "terminator") {
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

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block: Block = event.block

        val logMaterials = setOf(
            Material.ACACIA_LOG,
            Material.BIRCH_LOG,
            Material.DARK_OAK_LOG,
            Material.JUNGLE_LOG,
            Material.OAK_LOG,
            Material.SPRUCE_LOG,
            Material.CHERRY_LOG,
            Material.MANGROVE_LOG,
            Material.MANGROVE_ROOTS,
            Material.MUDDY_MANGROVE_ROOTS
        )

        if (block.type in logMaterials) {
            when (event.player.inventory.itemInMainHand.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING)) {
                "treecapitator" -> { breakTree(block, logMaterials, 50, 1L); event.isCancelled = true }
                "jungle_axe" -> { breakTree(block, logMaterials, 25, 3L); event.isCancelled = true }
            }
        }
    }

    private fun breakTree(centerBlock: Block, logMaterials: Set<Material>, maxBlocks: Int, delay: Long) {
        val treeBlocks: MutableList<Block> = mutableListOf(centerBlock)
        val processedBlocks: MutableSet<Block> = mutableSetOf(centerBlock)

        var currentIndex = 0

        while (currentIndex < treeBlocks.size && currentIndex < maxBlocks) {
            val currentBlock = treeBlocks[currentIndex]
            for (x in -1..1) {
                for (y in -1..1) {
                    for (z in -1..1) {
                        val relativeBlock: Block = currentBlock.getRelative(x, y, z)
                        if (relativeBlock.type in logMaterials) {
                            if (relativeBlock !in processedBlocks) {
                                treeBlocks.add(relativeBlock)
                                processedBlocks.add(relativeBlock)
                            }
                        }
                    }
                }
            }
            currentIndex++
        }

        for ((index, block) in treeBlocks.withIndex()) {
            plugin.server.scheduler.runTaskLater(plugin, Runnable {
                block.breakNaturally()
                block.world.spawnParticle(Particle.VILLAGER_HAPPY, block.location, 1, 0.0, 0.0, 0.0, 0.0)
            }, delay * index)
        }
    }
}