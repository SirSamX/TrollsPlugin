package me.sirsam.trolls.listeners

import me.sirsam.trolls.helpers.Ranks
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class OnLeave : Listener {
    @EventHandler
    fun onLeave(event: PlayerQuitEvent) {
        val p = event.player
        event.quitMessage(Component.text("- ", NamedTextColor.RED).append(Component.text("${p.name} hatte keinen Bock mehr!", NamedTextColor.GOLD)))

        val loc = p.location
        loc.y++
        p.world.spawnParticle(Particle.PORTAL, loc, 75)
    }
}