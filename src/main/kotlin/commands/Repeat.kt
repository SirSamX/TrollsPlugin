package me.sirsam.trolls.commands

import me.sirsam.trolls.helpers.Utilities
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Repeat : CommandExecutor {
    private val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        if (args!!.size <= 1) { utils.formattingErrorMessage(sender); return true }

        val amount = args[0].toInt()
        val performedCommand = args.drop(1).joinToString(" ")
        for (i in 1..amount) {
            sender.performCommand(performedCommand)
        }

        return true
    }
}