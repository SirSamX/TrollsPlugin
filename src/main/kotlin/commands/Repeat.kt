package me.sirsam.trolls.commands

import core.helpers.Utils
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Repeat : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { Utils.notPlayerMessage(sender); return true }
        if (args!!.size <= 1) { Utils.formattingErrorMessage(sender); return true }

        val amount = args[0].toInt()
        val performedCommand = args.drop(1).joinToString(" ")
        for (i in 1..amount) {
            sender.performCommand(performedCommand)
        }

        return true
    }
}