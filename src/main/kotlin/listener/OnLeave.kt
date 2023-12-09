package me.sirsam.trolls.listener

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class OnLeave : Listener {
    @EventHandler
    fun onLeave(event: PlayerQuitEvent) {
        val p = event.player


        val loc = p.location
        loc.y++
        p.world.spawnParticle(Particle.PORTAL, loc, 50)

        when (p.name) {
            "niceleumas" -> {
                event.quitMessage(Component.text("- ", NamedTextColor.RED).append(Component.text("Der Bannhammer hat gesprochen!", NamedTextColor.DARK_RED)))
            }
            "Blueberry1873" -> {
                event.quitMessage(Component.text("- ", NamedTextColor.RED).append(Component.text("*Emanuel fährt Schwanz aus*", NamedTextColor.DARK_PURPLE).decorate(TextDecoration.ITALIC)))
            }
            "EnderMo23" -> {
                event.quitMessage(Component.text("- ", NamedTextColor.RED).append(Component.text("EnderMo23 war zu cool für den Server!", NamedTextColor.GOLD)))
            }
            "RedstoneKaiser" -> {
                event.quitMessage(Component.text("- ", NamedTextColor.RED).append(Component.text("Se Kaiser of la Redstöne is weg!", NamedTextColor.RED)))
            }
            "hbjju" -> {
                event.quitMessage(Component.text("Herobrine left the game", NamedTextColor.YELLOW))
            }
            else -> event.quitMessage(Component.text("- ", NamedTextColor.RED).append(Component.text(p.name, NamedTextColor.GOLD)))
        }
    }
}