package me.sirsam.trolls.listeners

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class OnJoin : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val p = event.player
        val playerCount = Bukkit.getOnlinePlayers().size
        p.sendMessage(Component.text("Welcome back ${p.name}!\nThere are currently ${playerCount - 1} other players online.", NamedTextColor.BLUE))
        p.sendMessage(Component.text("Herzlich Willkommen auf dem Server ${p.name}!"))

        val loc = p.location
        loc.y++
        p.world.spawnParticle(Particle.REVERSE_PORTAL, loc, 50)

        when (p.name) {
            "niceleumas" -> {
                event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("ඞ", NamedTextColor.DARK_RED)))
            }
            else ->  event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text(p.name, NamedTextColor.GOLD)))
        }
    }
}
