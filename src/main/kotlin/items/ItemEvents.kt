package me.sirsam.trolls.items

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent
import me.sirsam.trolls.Trolls
import me.sirsam.trolls.helpers.Cooldown
import me.sirsam.trolls.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.block.data.Orientable
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
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin


class ItemEvents : Listener {
    private val utils = Utilities()
    private val plugin = Trolls.getPlugin()

    @EventHandler
    fun grapplingHookLaunch(event: PlayerFishEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand
        val data = item.itemMeta.persistentDataContainer

        if (data.get(utils.idKey, PersistentDataType.STRING) != "grappling_hook" || event.state != PlayerFishEvent.State.REEL_IN) return

        val strength = .3
        val hookLocation = event.hook.location
        val change = hookLocation.subtract(player.location)
        player.velocity = change.toVector().multiply(strength)
    }

    @EventHandler
    fun throwTNT(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.itemMeta == null) return
        val data = item.itemMeta.persistentDataContainer
        if (data.get(utils.idKey, PersistentDataType.STRING) != "throwable_tnt" || !event.action.isRightClick) return

        val strength = 1.4
        event.isCancelled = true
        if (player.gameMode != GameMode.CREATIVE) {
            utils.destroy(item, 1)
        }
        val direction = player.location.direction
        val tntEntity = player.world.spawnEntity(player.location, EntityType.PRIMED_TNT)
        tntEntity.velocity = direction.multiply(strength)
    }

    @EventHandler
    fun throwSword(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.itemMeta == null) return
        if (item.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING) != "pogeroni_sword" || !event.action.isRightClick) return

        val location = player.location
        val armorStand = location.world!!.dropItem(player.eyeLocation, item)

        val strength = 2.0
        event.isCancelled = true
        if (player.gameMode != GameMode.CREATIVE) {
            utils.destroy(item, 1)
        }
        val direction = player.location.direction
        armorStand.velocity = direction.multiply(strength)
    }


    @EventHandler
    fun shootyBox(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.itemMeta != null) return
        if (item.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING) != "shooty_box" || !event.action.isRightClick) return

        event.isCancelled = true
        if (player.isSneaking) {
            val inv = utils.getInventoryInItem(item)
            if (inv == null) {
                player.openInventory(
                    Bukkit.createInventory(
                        null,
                        InventoryType.DISPENSER,
                        Component.text("Shooty Box")
                    )
                )
            } else {
                player.openInventory(inv)
            }
        } else return //Code für shooting
    }

    @EventHandler
    fun closeShootyBoxGui(event: InventoryCloseEvent) {
        val item = event.player.inventory.itemInMainHand
        if (item.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING) != "shooty_box") return
        utils.storeInventoryInItem(item, event.inventory)
    }

    @EventHandler
    fun explosiveBowShoot(event: EntityShootBowEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player
        if (player.inventory.itemInMainHand.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING) != "explosive_bow") return
        event.projectile.customName(Component.text("Explosive Arrow"))
    }

    @EventHandler
    fun explosiveBowHit(event: ProjectileHitEvent) {
        if (event.entity.customName() == null) return
        if (event.entity.customName() != Component.text("Explosive Arrow")) return

        event.entity.world.createExplosion(event.entity.location, 2.5f, false, true)
        event.entity.remove()
    }

    @EventHandler
    fun throwFireball(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.itemMeta != null) return
        val data = item.itemMeta.persistentDataContainer
        if (data.get(utils.idKey, PersistentDataType.STRING) != "throwable_fireball" || !event.action.isRightClick) return

        val strength = 2
        event.isCancelled = true
        if (player.gameMode != GameMode.CREATIVE) {
            utils.destroy(item, 1)
        }
        player.launchProjectile(LargeFireball::class.java, player.eyeLocation.direction.normalize().multiply(strength))
    }

    @EventHandler
    fun terminatorShoot(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.itemMeta == null) return
        val data = item.itemMeta.persistentDataContainer
        if (data.get(utils.idKey, PersistentDataType.STRING) != "terminator") return

        val arrow = player.launchProjectile(Arrow::class.java)
        event.isCancelled = true
        shootSpreadArrows(player, arrow, 3, 5f, 8.0)
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

        if (block !is Orientable) return

        when (event.player.inventory.itemInMainHand.itemMeta.persistentDataContainer.get(utils.idKey, PersistentDataType.STRING)) {
            "treecapitator" -> {
                breakTree(block, 50, 1L); event.isCancelled = true
            }
            "jungle_axe" -> {
                breakTree(block, 25, 3L); event.isCancelled = true
            }
        }
    }

    private fun breakTree(centerBlock: Block, maxBlocks: Int, delay: Long) {
        val treeBlocks: MutableList<Block> = mutableListOf(centerBlock)
        val processedBlocks: MutableSet<Block> = mutableSetOf(centerBlock)

        var currentIndex = 0

        while (currentIndex < treeBlocks.size && currentIndex < maxBlocks) {
            val currentBlock = treeBlocks[currentIndex]
            for (x in -1..1) {
                for (y in -1..1) {
                    for (z in -1..1) {
                        val relativeBlock: Block = currentBlock.getRelative(x, y, z)
                        if (relativeBlock is Orientable) {
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
                block.world.spawnParticle(Particle.COMPOSTER, block.location.toCenterLocation(), 1)
            }, delay * index)
        }
    }

    private fun circle(loc: Location, rotation: Float = 0f) {
        object : BukkitRunnable() {
            var t = 0.0
            override fun run() {
                t += Math.PI / 16
                val x: Double = rotation * cos(t)
                val y: Double = rotation * sin(t)
                val z: Double = rotation * sin(t)
                loc.add(x, y, z)
                loc.world.spawnParticle(Particle.DRIP_LAVA, loc, 1, 0.0, 0.0, 0.0)
                loc.subtract(x, y, z)
                if (t > Math.PI * 8) {
                    cancel()
                }
            }
        }.runTaskTimer(plugin, 0, 1)
    }

    private fun bigParticle(loc: Location) {
        object : BukkitRunnable() {
            var t = Math.PI / 4
            override fun run() {
                t += 0.1 * Math.PI
                var theta = 0.0
                while (theta <= 2 * Math.PI) {
                    var x = t * cos(theta)
                    var y = 2 * exp(-0.1 * t) * sin(t) + 1.5
                    var z = t * sin(theta)
                    loc.add(x, y, z)
                    loc.world.spawnParticle(Particle.FIREWORKS_SPARK, loc, 1, 0.0, 0.0, 0.0)
                    loc.subtract(x, y, z)
                    theta += Math.PI / 64
                    x = t * cos(theta)
                    y = 2 * exp(-0.1 * t) * sin(t) + 1.5
                    z = t * sin(theta)
                    loc.add(x, y, z)
                    loc.world.spawnParticle(Particle.SPELL_WITCH, loc, 1, 0.0, 0.0, 0.0)
                    loc.subtract(x, y, z)
                    theta += Math.PI / 32
                }
                if (t > 20) {
                    cancel()
                }
            }
        }.runTaskTimer(plugin, 0, 1)
    }

    private fun shootParticle(loc: Location) {
        object : BukkitRunnable() {
            var t = 0.0
            var speed = 3.0
            val direction: Vector = loc.direction.normalize()
            override fun run() {
                t += 0.5
                val x: Double = direction.x * t
                val y: Double = direction.y * t + speed
                val z: Double = direction.z * t
                loc.add(x, y, z)
                loc.world.spawnParticle(Particle.SPELL_WITCH, loc, 1, 0.0, 0.0, 0.0)
                loc.subtract(x, y, z)
                if (t > 30) {
                    cancel()
                }
            }
        }.runTaskTimer(plugin, 0, 1)
    }

    private val wandCooldown = Cooldown()
    @EventHandler
    fun magicalWand(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.itemMeta == null) return
        val itemMeta = item.itemMeta
        val data = itemMeta.persistentDataContainer

        if (data.get(utils.idKey, PersistentDataType.STRING) != "magical_wand") return
        val wandKey = NamespacedKey(plugin, "ability")
        if (event.action.isRightClick && wandCooldown.can(player)) {
            wandCooldown.add(player, 1000)
            val loc = player.location
            when (data.get(wandKey, PersistentDataType.STRING)) {
                "Circle" -> circle(loc, 2f)
                "Wave" -> bigParticle(loc)
                "Shoot" -> shootParticle(loc)
                else -> {
                    player.sendMessage(Component.text("Invalid spell!", NamedTextColor.RED))
                }
            }
        } else if (event.action.isLeftClick) {
            val newAbility = when (data.get(wandKey, PersistentDataType.STRING)) {
                "Circle" -> "Wave"
                "Wave" -> "Shoot"
                "Shoot" -> "Circle"
                else -> "Circle"
            }
            data.set(wandKey, PersistentDataType.STRING, newAbility)
            item.itemMeta = itemMeta
            player.sendMessage(Component.text("§eChanged ability to §a§l$newAbility"))
        } else utils.cooldownMessage(player, wandCooldown.eta(player))
    }

    private val magicalBootsPlayers = ArrayList<Player>()
    @EventHandler
    fun magicalBoots(event: PlayerArmorChangeEvent) {
        val p = event.player
        if (event.newItem == ItemStack(Material.GOLDEN_BOOTS) || event.slotType == PlayerArmorChangeEvent.SlotType.FEET) {
            magicalBootsPlayers.add(p)
            object : BukkitRunnable() {
                override fun run() {
                    if (p.inventory.boots != ItemStack(Material.GOLDEN_BOOTS)) { cancel() }
                    circle(p.location)
                }
            }.runTask(plugin)
        } else magicalBootsPlayers.remove(p)
    }
}