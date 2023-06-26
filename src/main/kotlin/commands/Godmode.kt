package me.sirsam.trolls.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Godmode : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false

        if (sender.isInvulnerable) {
            sender.isInvulnerable = false
            sender.sendMessage(Component.text("You are not invulnerable anymore!", NamedTextColor.RED).color(TextColor.color(255, 0, 75)))
        }
        else {
            sender.isInvulnerable = true
            sender.sendMessage(Component.text("You are now invulnerable!", NamedTextColor.GREEN).color(TextColor.color(0, 255, 75)))
        }
        return false
    }
}