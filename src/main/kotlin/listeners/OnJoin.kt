package me.sirsam.trolls.listeners

import me.sirsam.trolls.items.ItemRecipes
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
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

        val header = Component.text("GAMING LEGENDEN SERVER", NamedTextColor.RED)
        val footer = Component.text("1.20 Trails and Tails", NamedTextColor.GREEN)
        p.sendPlayerListHeaderAndFooter(header, footer)

        ItemRecipes().unlockRecipes(p)

        val loc = p.location
        loc.y++
        p.world.spawnParticle(Particle.REVERSE_PORTAL, loc, 50)

        when (p.name) {
            "niceleumas" -> {
                event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("Se Owner of le Server is wieder da um euch wegzubannen!", NamedTextColor.DARK_RED)))
                //p.getAttribute(Attribute.GENERIC_ATTACK_SPEED)?.baseValue = 100.0
            }
            "Blueberry1873" -> {
                event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("*Ich Blaubeere bekenne mich offiziel als dumm*", NamedTextColor.GOLD).decorate(TextDecoration.ITALIC)))

            }
            "EnderMo23" -> {
                event.joinMessage(Component.text("⠀⠀⠀⠀⠀⠀⡠⠔⠚⡏⠉⠉⠙⠛⠓⠒⠒⠒⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⡇⠀⠀⡇⠀⠀⢀⣰⠁⠀⠐⡆⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⡇⠀⠀⡇⢰⢲⢺⠀⠀⡤⡤⣧⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⡇⠀⠀⡇⢸⠉⢉⣀⣀⡉⠉⡇⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⢀⡤⠤⠧⢤⣀⣧⣼⣀⣈⣠⣍⣡⣠⣇⡇⢀⡀⢀⡤⠶⠶⡚⢖⣄⠀⠀\n" +
                        "⠀⠀⢀⡎⢀⠀⠀⠈⡇⠀⠀⡆⡇⠀⣰⠚⠀⡏⠉⢀⡾⠁⠀⣀⣤⣛⠉⠙⣷⡀\n" +
                        "⠀⢠⠏⢃⠜⠀⠐⢲⡇⠀⠀⡇⠀⠀⠀⠀⠀⣿⢎⠉⠉⠓⢻⡽⢡⣬⣧⡀⠘⣿\n" +
                        "⢠⠋⢀⠎⠉⠁⢲⡇⡗⠰⠴⠇⠀⠀⠀⠀⠀⣿⠀⠑⡄⢀⣶⢁⣾⡞⠉⠷⢶⣿\n" +
                        "⡇⠠⠃⠀⠀⣠⠃⡇⡇⠀⠀⠀⠀⠀⠀⠀⠀⡟⢆⠀⠈⣺⠃⡞⢾⣙⣆⠀⠀⠀\n" +
                        "⠳⣁⣀⣀⡰⠃⠀⠇⡇⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⢱⣤⠏⡜⢨⠏⠀⡇⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⢸⡗⡇⠀⠀⠒⠒⠒⠒⠒⣒⡇⠀⠈⢯⣤⠥⠯⠖⠚⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⣿⠉⠉⠉⠉⢯⠉⠉⠉⠙⡅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⢹⡇⠀⠀⠀⢸⡦⠤⠤⠤⡧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⢸⢡⠀⠀⠀⠈⡅⠀⠀⢠⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠈⣼⠀⠀⠀⠀⡇⠀⠀⡎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⡇⡆⠀⠀⠀⢱⠤⠼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⢷⢧⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⢸⡌⠉⠉⠉⡽⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀", NamedTextColor.RED))

            }
            "RedstoneKaiser" -> {
                event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("Se Kaiser of la Redstöne is da!", NamedTextColor.DARK_PURPLE)))
            }
            "hbjju" -> {
                event.joinMessage(Component.text("Tabeas dummer Bruder is da!", NamedTextColor.YELLOW))
                p.kick(Component.text("Du Huso!"))
            }
            else ->  event.joinMessage(Component.text("+ ", NamedTextColor.GREEN).append(Component.text("${p.name} moin bein neu hier oder spiele nicht so aktiv!", NamedTextColor.GOLD)))
        }
    }
}
