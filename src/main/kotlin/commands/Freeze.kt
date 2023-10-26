package me.sirsam.trolls.commands

import me.sirsam.trolls.core.helper.Utils
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
import java.util.UUID

class Freeze : CommandExecutor, Listener {
    companion object {
        private val frozen = ArrayList<UUID>()
    }

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

        if (!frozen.contains(target.uniqueId)) {
            frozen.add(target.uniqueId)
            target.sendMessage((Component.text("You are now frozen!", NamedTextColor.GREEN)))
        } else {
            frozen.remove(target.uniqueId)
            target.sendMessage((Component.text("You are not frozen anymore!", NamedTextColor.RED)))
        }
        return true
    }

    @EventHandler
    fun freeze(e: PlayerMoveEvent) {
        val p = e.player
        if (!frozen.contains(p.uniqueId) || !e.hasChangedPosition()) return
        p.freezeTicks = 100
        e.isCancelled = true
    }
}