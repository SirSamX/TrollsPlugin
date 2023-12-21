package me.sirsam.trolls.item

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.core.helper.Cooldown
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import me.sirsam.trolls.core.item.abilities.AbilityType
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Particle
import org.bukkit.entity.EntityType
import org.bukkit.entity.IronGolem
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin

class MagicalWand : AbilityItem(ItemProperties(
    id = "magical_wand",
    material = Material.BLAZE_ROD,
    name = "Magical Wand",
    abilities = listOf(Ability("Cast spell", AbilityType.RIGHT_CLICK), Ability("Switch ability", AbilityType.LEFT_CLICK)),
    note = "TestItem",
    rarity = Rarity.ADMIN,
    raritySuffix = "WAND"
)) {
    val plugin = Trolls.instance

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
                    loc.world.spawnParticle(Particle.FLAME, loc, 1, 0.0, 0.0, 0.0)
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

    private fun shootParticle(player: Player) {
        object : BukkitRunnable() {
            var t = 0.0
            val speed = 3.0
            val damageAmount = 6.5
            val damageRadius = 0.8
            val loc = player.location.add(0.0, player.eyeHeight, 0.0)
            val direction: Vector = loc.direction.normalize()
            override fun run() {
                t += 0.5
                val x: Double = direction.x * t
                val y: Double = direction.y * t
                val z: Double = direction.z * t
                loc.add(x, y, z)

                val nearbyEntities = loc.getNearbyLivingEntities(damageRadius).filter { it != player }
                for (entity in nearbyEntities) {
                    entity.damage(damageAmount, player)
                }

                loc.world.spawnParticle(Particle.SPELL_WITCH, loc, 1, 0.0, 0.0, 0.0)
                loc.subtract(x, y, z)
                if (t > 30) {
                    cancel()
                }
            }
        }.runTaskTimer(plugin, 0, 1)
    }

    private fun summonDefender(player: Player) {
        val ironGolem = player.world.spawnEntity(player.location, EntityType.IRON_GOLEM) as IronGolem

        object : BukkitRunnable() {
            override fun run() {
                ironGolem.remove()
                cancel()
                return
            }
        }.runTaskLater(plugin, 15 * 20L) // Run every tick
    }

    private val wandCooldown = Cooldown()

    override fun interact(event: PlayerInteractEvent): AbilityResult {
        val player = event.player
        val item = player.inventory.itemInMainHand
        val itemMeta = item.itemMeta
        val data = itemMeta.persistentDataContainer
        val wandKey = NamespacedKey(plugin, "ability")
        if (event.action.isRightClick && wandCooldown.can(player)) {
            val loc = player.location
            when (data.get(wandKey, PersistentDataType.STRING)) {
                "Circle" -> {
                    wandCooldown.set(player, 3000)
                    circle(loc, 2f)
                }
                "Wave" -> {
                    bigParticle(loc)
                    wandCooldown.set(player, 7000)
                }
                "Shoot" -> {
                    shootParticle(player)
                    wandCooldown.set(player, 700)
                }
                "Summon" -> {
                    summonDefender(player)
                    wandCooldown.set(player, 25000)
                }
                else -> {
                    player.sendMessage(Component.text("Invalid spell!", NamedTextColor.RED))
                    return AbilityResult.FAIL
                }
            }
            return AbilityResult.SUCCESS
        } else if (event.action.isLeftClick) {
            val newAbility = when (data.get(wandKey, PersistentDataType.STRING)) {
                "Summon" -> "Wave"
                "Wave" -> "Shoot"
                "Shoot" -> "Circle"
                "Circle" -> "Summon"
                else -> "Shoot"
            }
            data.set(wandKey, PersistentDataType.STRING, newAbility)
            item.itemMeta = itemMeta
            player.sendMessage(
                Component.text("Changed ability to ", NamedTextColor.YELLOW).append(
                    Component.text(newAbility, NamedTextColor.GREEN).decorate(TextDecoration.BOLD)
                )
            )
            return AbilityResult.SUCCESS
        } else {
            wandCooldown.cooldownMessage(player)
            return AbilityResult.FAIL
        }
    }
}