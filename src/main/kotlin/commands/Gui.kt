package me.sirsam.trolls.commands

import me.sirsam.trolls.GuiManager
import me.sirsam.trolls.helpers.Utilities
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Gui : CommandExecutor{
    private val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.isNotPlayerMessage(sender); return true }
        sender.openInventory(GuiManager().questGUI())

        return true
    }
}