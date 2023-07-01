package me.sirsam.trolls.commands

import me.sirsam.trolls.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class Jump : CommandExecutor {
    private val utils = Utilities()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.isNotPlayerMessage(sender); return true }
        var target = Bukkit.getPlayer(args!![0])
        if (target == null) {
            target = sender
        }

        target.sendMessage(Component.text("JUMP!", NamedTextColor.GREEN))
        target.velocity = target.velocity.add(Vector(0.0, 7.0, 0.0))

        return true
    }
}