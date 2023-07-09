package me.sirsam.trolls.listeners

import me.sirsam.trolls.helpers.Ranks
import me.sirsam.trolls.items.ItemRecipes
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class OnJoin : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val p = event.player
        val playerCount = Bukkit.getOnlinePlayers().size
        p.sendMessage(Component.text("Welcome back ${p.name}!\nThere are currently ${playerCount - 1} other players online.", NamedTextColor.BLUE))

        val header = Component.text("GAMING LEGENDEN SERVER", NamedTextColor.RED)
        val footer = Component.text("1.20 Trails and Tails", NamedTextColor.GREEN)
        p.sendPlayerListHeaderAndFooter(header, footer)

        ItemRecipes().unlockRecipes(p)
        event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("${p.name} ist motiviert etwas großes zu bauen!", NamedTextColor.GOLD)))

        val loc = p.location
        loc.y++
        p.world.spawnParticle(Particle.REVERSE_PORTAL, loc, 75)

        fun setRank(rank: Ranks, p: Player) {
            val name = Component.text(rank.prefix, rank.color).append(p.name().append(Component.text(rank.suffix)))
            p.displayName(name)
            p.playerListName(name)
            p.customName(name)
        }

        when (p.name) {
            "niceleumas" -> {
                setRank(Ranks.OWNER, p)
            }
            "Blueberry1873" -> {
                setRank(Ranks.ADMIN, p)
            }
            "EnderMo23" -> {
                setRank(Ranks.ADMIN, p)
            }
            "RedstoneKaiser" -> {
                setRank(Ranks.ADMIN, p)
            }
            "hbjju" -> {
                setRank(Ranks.ADMIN, p)
            }
            "xTHEscienceCATx" -> {
                setRank(Ranks.MODERATOR, p)
            }
            else -> setRank(Ranks.PLAYER, p)
        }
    }
}