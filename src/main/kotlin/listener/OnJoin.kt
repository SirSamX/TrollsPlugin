package me.sirsam.trolls.listener

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
        val player = event.player
        val playerCount = Bukkit.getOnlinePlayers().size
        player.sendMessage(Component.text("Welcome back ${player.name}!\nThere are currently ${playerCount - 1} other players online.", NamedTextColor.BLUE))
        player.sendMessage(Component.text("Herzlich Willkommen auf dem Server ${player.name}!"))

        val loc = player.location
        loc.y++
        player.world.spawnParticle(Particle.REVERSE_PORTAL, loc, 50)

        when (player.name) {
            "niceleumas" -> {
                event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("ඞ", NamedTextColor.DARK_RED)))
            }
            else ->  event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text(player.name, NamedTextColor.GOLD)))
        }
    }
}
