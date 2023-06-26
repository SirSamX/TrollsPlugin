package me.sirsam.trolls.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Sudo : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        val target = Bukkit.getPlayer(args!![0])
        if (target == null) {
            sender.sendMessage(Component.text("This player does not exist!", NamedTextColor.RED))
            return false
        }
        val message = args.drop(1).joinToString(" ")
        target.chat(message)
        return false
    }
}