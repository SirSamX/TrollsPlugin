package me.sirsam.trolls.commands

import core.helper.Utils
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class Jump : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { Utils.notPlayerMessage(sender); return true }
        if (!sender.hasPermission("trolls.utils.jump")) { Utils.noPermissionMessage(sender); return true }
        var target: Player? = null
        if (args!!.isNotEmpty()) {
            target = Bukkit.getPlayer(args[0])
        }
        if (target == null) {
            target = sender
        }

        target.sendMessage(Component.text("JUMP!", NamedTextColor.GREEN))
        target.velocity = target.velocity.add(Vector(0.0, 7.0, 0.0))

        return true
    }
}