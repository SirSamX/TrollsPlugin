package me.sirsam.trolls.listeners

import me.sirsam.trolls.helpers.Ranks
import me.sirsam.trolls.items.ItemRecipes
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
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
        p.world.spawnParticle(Particle.REVERSE_PORTAL, loc, 50)

        fun setRank(rank: Ranks, p: Player) {
            val name = Component.text(rank.prefix, rank.color).append(p.name().append(Component.text(rank.suffix)))
            p.displayName(name)
            p.playerListName(name)
            p.customName(name)
        }

        when (p.name) {
            "niceleumas" -> {
                setRank(Ranks.OWNER, p)
                event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("Se Owner of le Server is wieder da um euch wegzubannen!", NamedTextColor.DARK_RED)))
                //p.getAttribute(Attribute.GENERIC_ATTACK_SPEED)?.baseValue = 100.0
            }
            "Blueberry1873" -> {
                setRank(Ranks.ADMIN, p)
                event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("*Emanuel zieht schwanz ein*", NamedTextColor.GOLD).decorate(TextDecoration.ITALIC)))

            }
            "EnderMo23" -> {
                setRank(Ranks.ADMIN, p)
                event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("Ender Klo", NamedTextColor.GOLD)))
            }
            "RedstoneKaiser" -> {
                setRank(Ranks.ADMIN, p)
                event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("Se Kaiser of la Redstöne is da!", NamedTextColor.DARK_PURPLE)))
            }
            "hbjju" -> {
                setRank(Ranks.ADMIN, p)
                event.joinMessage(Component.text("Herobrine joined the game", NamedTextColor.YELLOW))
            }
            "xTHEscienceCATx" -> {
                setRank(Ranks.MODERATOR, p)
            }
            else -> setRank(Ranks.PLAYER, p)
        }
    }
}