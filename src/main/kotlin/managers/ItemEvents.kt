package me.sirsam.trolls.managers

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.core.helper.Cooldown
import me.sirsam.trolls.core.helper.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.entity.Chicken
import org.bukkit.entity.EntityType
import org.bukkit.entity.LargeFireball
import org.bukkit.entity.Player
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
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin


class ItemEvents : Listener {
    private val plugin = Trolls.instance

    @EventHandler
    fun grapplingHookLaunch(event: PlayerFishEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand
        val data = item.itemMeta.persistentDataContainer

        if (data.get(Utils.ID_KEY, PersistentDataType.STRING) != "grappling_hook" || event.state != PlayerFishEvent.State.REEL_IN) return

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
        if (data.get(Utils.ID_KEY, PersistentDataType.STRING) != "throwable_tnt" || !event.action.isRightClick) return

        val strength = 1.4
        event.isCancelled = true
        if (player.gameMode != GameMode.CREATIVE) {
            Utils.destroy(item, 1)
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
        if (item.itemMeta.persistentDataContainer.get(Utils.ID_KEY, PersistentDataType.STRING) != "pogeroni_sword" || !event.action.isRightClick) return

        Utils.throwItem(player, item, 1.2f, Particle.FLAME)
    }



    @EventHandler
    fun shootyBox(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.itemMeta == null) return
        if (item.itemMeta.persistentDataContainer.get(Utils.ID_KEY, PersistentDataType.STRING) != "shooty_box" || !event.action.isRightClick) return

        event.isCancelled = true
        if (player.isSneaking) {
            val inv = Utils.getInventoryInItem(item)
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
        if (item.itemMeta == null) return
        if (item.itemMeta.persistentDataContainer.get(Utils.ID_KEY, PersistentDataType.STRING) != "shooty_box") return
        Utils.storeInventoryInItem(item, event.inventory)
    }

    @EventHandler
    fun explosiveBowShoot(event: EntityShootBowEvent) {
        if (event.entity !is Player) return
        event.entity as Player
        if (event.bow == null || event.bow?.itemMeta == null) return
        if (event.bow!!.itemMeta.persistentDataContainer.get(Utils.ID_KEY, PersistentDataType.STRING) != "explosive_bow") return
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

        if (item.itemMeta == null) return
        val data = item.itemMeta.persistentDataContainer
        if (data.get(Utils.ID_KEY, PersistentDataType.STRING) != "throwable_fireball" || !event.action.isRightClick) return

        val strength = 2
        event.isCancelled = true
        if (player.gameMode != GameMode.CREATIVE) {
            Utils.destroy(item, 1)
        }
        player.launchProjectile(LargeFireball::class.java, player.eyeLocation.direction.normalize().multiply(strength))
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block

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
            Material.MUDDY_MANGROVE_ROOTS,
            Material.MUSHROOM_STEM,
            Material.CRIMSON_STEM,
            Material.WARPED_STEM
        )

        if (block.type !in logMaterials) return

        val meta = event.player.inventory.itemInMainHand.itemMeta ?: return
        when (meta.persistentDataContainer.get(Utils.ID_KEY, PersistentDataType.STRING)) {
            "treecapitator" -> {
                veinMine(block, logMaterials, 50, 1L); event.isCancelled = true
            }
            "jungle_axe" -> {
                veinMine(block, logMaterials, 50, 1L); event.isCancelled = true
            }
        }
    }

    private fun veinMine(centerBlock: Block, materials: Set<Material>, maxBlocks: Int, delay: Long) {
        val treeBlocks: MutableList<Block> = mutableListOf(centerBlock)
        val processedBlocks: MutableSet<Block> = mutableSetOf(centerBlock)

        var currentIndex = 0

        while (currentIndex < treeBlocks.size && currentIndex < maxBlocks) {
            val currentBlock = treeBlocks[currentIndex]
            for (x in -1..1) {
                for (y in -1..1) {
                    for (z in -1..1) {
                        val relativeBlock: Block = currentBlock.getRelative(x, y, z)
                        if (relativeBlock.type in materials) {
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

    private fun circle(loc: Location, size: Float) {
        object : BukkitRunnable() {
            var t = 0.0
            override fun run() {
                t += Math.PI / 16
                val x: Double = size * cos(t)
                val y: Double = size * sin(t)
                val z: Double = size * sin(t)
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

        if (data.get(Utils.ID_KEY, PersistentDataType.STRING) != "magical_wand") return
        val wandKey = NamespacedKey(plugin, "ability")
        if (event.action.isRightClick && wandCooldown.can(player)) {
            wandCooldown.set(player, 1000)
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
        } else wandCooldown.cooldownMessage(player)
    }

    private val chickzookaCooldown = Cooldown()

    @EventHandler
    fun chickzooka(event: PlayerInteractEvent) {
        val player = event.player
        val item = player.inventory.itemInMainHand

        if (item.itemMeta == null) return
        if (item.itemMeta.persistentDataContainer.get(Utils.ID_KEY, PersistentDataType.STRING) != "chickzooka") return
        if (!chickzookaCooldown.can(player)) { chickzookaCooldown.cooldownMessage(player); return }

        chickzookaCooldown.set(player, 250)

        val loc = player.eyeLocation
        loc.add(loc.direction.normalize())

        val chicken = player.world.spawn(loc, Chicken::class.java)
        chicken.persistentDataContainer.set(Utils.MOB_KEY, PersistentDataType.STRING, "explosive_chicken")
        chicken.velocity = loc.direction.multiply(2)

        object : BukkitRunnable() {
            override fun run() {
                if (!chicken.isValid) { cancel(); return }

                chicken.remove()
                chicken.location.createExplosion(chicken, 1f, false, true)
            }
        }.runTaskLater(plugin, 99)

        object : BukkitRunnable() {
            override fun run() {
                if (!chicken.isValid) { cancel(); return }
                chicken.eggLayTime = 0
            }
        }.runTaskTimer(plugin, 20, 20)
    }
}