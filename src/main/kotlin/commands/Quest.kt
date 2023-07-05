package me.sirsam.trolls.commands

import me.sirsam.trolls.guis.quests.Menu
import me.sirsam.trolls.helpers.Utilities
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Quest : CommandExecutor{
    private val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.isNotPlayerMessage(sender); return true }
        sender.openInventory(Menu().inventory)

        return true
    }
}