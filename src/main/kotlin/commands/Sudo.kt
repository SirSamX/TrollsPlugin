package me.sirsam.trolls.commands

import me.sirsam.trolls.helpers.Utilities
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Sudo : CommandExecutor {
    private val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        if (!sender.hasPermission("trolls.utils.sudo")) { utils.noPermissionMessage(sender); return true }
        if (args!!.isEmpty()) { utils.formattingErrorMessage(sender); return true }
        val target = Bukkit.getPlayer(args[0])
        if (target == null) {
            Utilities().playerNotExistingMessage(sender)
            return true
        }
        val message = args.drop(1).joinToString(" ")
        target.chat(message)
        return true
    }
}