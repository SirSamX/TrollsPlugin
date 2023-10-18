package me.sirsam.trolls.commands

import core.helpers.Utils
import me.sirsam.trolls.mobs.Bomber
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BomberCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { Utils.notPlayerMessage(sender); return true }

        Bomber(sender.location)

        return true
    }
}