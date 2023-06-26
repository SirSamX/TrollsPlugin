package me.sirsam.trolls.commands

import me.sirsam.trolls.Trolls
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class Vanish : CommandExecutor, Listener {
    private val vanished = ArrayList<Player>()
    private val plugin = Trolls.getPlugin()

    override fun onCommand(p: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (p !is Player) return false

        if (!vanished.contains(p)) {
            for (pl in Bukkit.getServer().onlinePlayers) {
                pl.hidePlayer(plugin, p)
            }
            vanished.add(p)
            p.isInvulnerable = true
            p.allowFlight = true
            p.sendMessage((Component.text("You are now vanished!", NamedTextColor.GREEN)))
            return true
        } else {
            for (pl in Bukkit.getServer().onlinePlayers) {
                pl.showPlayer(plugin, p)
            }
            vanished.remove(p)
            p.isInvulnerable = false
            p.allowFlight = false
            p.sendMessage((Component.text("You are not vanished anymore!", NamedTextColor.RED)))
            return true

        }
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        for (p in vanished) {
            e.player.hidePlayer(plugin, p)
        }
    }
}