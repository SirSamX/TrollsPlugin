package me.sirsam.trolls.item

import me.sirsam.trolls.core.Main
import me.sirsam.trolls.core.helper.Cooldown
import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.Head
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import org.bukkit.Material
import org.bukkit.entity.Chicken
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable

class Chickzooka : AbilityItem(ItemProperties(
    id = "chickzooka",
    material = Material.PLAYER_HEAD,
    name = "Chickzooka",
    rarity = Rarity.SPECIAL,
    headTexture = Head.CHICKEN,
    stackable = false
)) {
    private val chickzookaCooldown = Cooldown()

    override fun rightClick(event: PlayerInteractEvent): AbilityResult {
        val player = event.player

        if (!chickzookaCooldown.can(player)) { chickzookaCooldown.cooldownMessage(player); return AbilityResult.FAIL}

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
                chicken.location.createExplosion(chicken, 2.5f, false, true)
            }
        }.runTaskLater(Main.plugin, 99L)

        object : BukkitRunnable() {
            override fun run() {
                if (!chicken.isValid) { cancel(); return }
                chicken.eggLayTime = 0
            }
        }.runTaskTimer(Main.plugin, 20L, 20L)

        return AbilityResult.SUCCESS
    }
}