package me.sirsam.trolls.items

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.LargeFireball
import org.bukkit.event.player.PlayerInteractEvent

class ThrowableFireball : AbilityItem(ItemProperties(
    id = "throwable_fireball",
    material = Material.FIRE_CHARGE,
    name = "Throwable Fireball",
    rarity = Rarity.UNCOMMON,
    raritySuffix = "FIREBALL",
    abilities = listOf(Ability("Throw Fireball", AbilityType.RIGHT_CLICK, oneTimeUse = true)),
    stackable = true
)) {
    override fun rightClick(event: PlayerInteractEvent): AbilityResult {
        val player = event.player
        val strength = 2

        event.isCancelled = true
        if (player.gameMode != GameMode.CREATIVE) {
            Utils.destroy(event.item!!, 1)
        }
        player.launchProjectile(LargeFireball::class.java, player.eyeLocation.direction.normalize().multiply(strength))

        return AbilityResult.SUCCESS
    }
}