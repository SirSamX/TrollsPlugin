package me.sirsam.trolls.mobs

import me.sirsam.trolls.Trolls
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.entity.TNTPrimed
import org.bukkit.entity.Zombie
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable
import kotlin.random.Random

class Bomber(loc: Location) {
    init {
        val zombie = loc.world.spawnEntity(loc, EntityType.ZOMBIE) as Zombie
        zombie.setBaby()
        zombie.isCustomNameVisible = true
        zombie.customName(Component.text("BOMBER", NamedTextColor.RED))
        val helmet = ItemStack(Material.TNT)
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 255)
        zombie.equipment.helmet = helmet
        zombie.equipment.setItemInMainHand(ItemStack(Material.FLINT_AND_STEEL))

        object : BukkitRunnable() {
            override fun run() {
                if (zombie.isDead) {
                    cancel()
                    return
                }

                val tnt = zombie.location.world.spawnEntity(zombie.location, EntityType.PRIMED_TNT) as TNTPrimed
                tnt.fuseTicks  = 20
                zombie.world.spawnParticle(Particle.VILLAGER_ANGRY, zombie.location, 15, 1.0, 0.1, 1.0)
                zombie.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 120, 2))
                val radius = 10.0
                val explosionLoc = zombie.location.clone().add(
                    Random.nextDouble(-radius, radius),
                    0.0,
                    Random.nextDouble(-radius, radius)
                )
                zombie.world.createExplosion(explosionLoc, 2f)
            }
        }.runTaskTimer(Trolls.getPlugin(), 40L, 40L)
    }
}