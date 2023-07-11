package me.sirsam.trolls.commands

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.helpers.Utilities
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
    private val utils = Utilities()


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        if (!sender.hasPermission("trolls.utils.vanish")) { utils.noPermissionMessage(sender); return true }
        var target: Player? = null
        if (args!!.isNotEmpty()) {
            target = Bukkit.getPlayer(args[0])
        }
        if (target == null) {
            target = sender
        }

        if (!vanished.contains(target)) {
            for (pl in Bukkit.getServer().onlinePlayers) {
                pl.hidePlayer(plugin, target)
            }
            vanished.add(target)
            target.isInvulnerable = true
            target.allowFlight = true
            target.sendMessage((Component.text("You are now vanished!", NamedTextColor.GREEN)))
        } else {
            for (pl in Bukkit.getServer().onlinePlayers) {
                pl.showPlayer(plugin, target)
            }
            vanished.remove(target)
            target.isInvulnerable = false
            target.allowFlight = false
            target.sendMessage((Component.text("You are not vanished anymore!", NamedTextColor.RED)))
        }
        return true
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        for (p in vanished) {
            e.player.hidePlayer(plugin, p)
        }
    }
}