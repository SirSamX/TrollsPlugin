package me.sirsam.trolls.commands

import core.helpers.Utils
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Sudo : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { Utils.notPlayerMessage(sender); return true }
        if (!sender.hasPermission("trolls.utils.sudo")) { Utils.noPermissionMessage(sender); return true }
        if (args!!.isEmpty()) { Utils.formattingErrorMessage(sender); return true }
        val target = Bukkit.getPlayer(args[0])
        if (target == null) {
            Utils.playerNotExistingMessage(sender)
            return true
        }
        val message = args.drop(1).joinToString(" ")
        target.chat(message)
        return true
    }
}