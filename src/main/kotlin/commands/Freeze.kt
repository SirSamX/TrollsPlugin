package me.sirsam.trolls.commands

import core.helpers.Utils
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
import org.bukkit.event.player.PlayerMoveEvent

class Freeze : CommandExecutor, Listener {
    private val plugin = Trolls.instance
    private val frozen = ArrayList<Player>()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) {
            Utils.notPlayerMessage(sender); return true
        }
        if (!sender.hasPermission("trolls.utils.freeze")) {
            Utils.noPermissionMessage(sender); return true
        }
        var target: Player? = null
        if (args!!.isNotEmpty()) {
            target = Bukkit.getPlayer(args[0])
        }
        if (target == null) {
            target = sender
        }

        if (!frozen.contains(target)) {
            frozen.add(target)
            target.sendMessage((Component.text("You are now frozen!", NamedTextColor.GREEN)))
        } else {
            frozen.remove(target)
            target.sendMessage((Component.text("You are not frozen anymore!", NamedTextColor.RED)))
        }
        return true
    }

    @EventHandler
    fun freeze(e: PlayerMoveEvent) {
        val p = e.player
        if (!frozen.contains(p)) return
        p.freezeTicks = 60
            e.isCancelled = true
    }
}