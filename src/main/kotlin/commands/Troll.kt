package me.sirsam.trolls.commands

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.helpers.Utilities
import me.sirsam.trolls.items.ItemManager
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Troll : CommandExecutor {
    private val items = ItemManager()//.items
    private val utils = Utilities()
    private val plugin = Trolls.instance

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.isNotPlayerMessage(sender); return true }
        if (args!!.size !in 3..3) { utils.formattingErrorMessage(sender); return true }

        if (args[0].lowercase() == "get") {
            if (!(utils.isNumeric(args[2]))) { utils.formattingErrorMessage(sender); return true; }
            val amount = args[2].toInt()
            var i = 0
            if (amount > 3000) { utils.formattingErrorMessage(sender); return true }
            while (i < amount) {
                i += 1
                if (args[1] == "grappling_hook") { grapplingHook(sender, amount) }
                else if (args[1] == "throwable_tnt") { throwableTNT(sender, amount) }
                else { utils.formattingErrorMessage(sender); break }
                /*for (item in items) {
                    if ( == args[1]) {

                    }
                }*/
            }
        }
        else if (args[0].lowercase() == "reload") {
            plugin.reloadConfig()
                sender.sendMessage(Component.text("asd"))
        }
        else utils.formattingErrorMessage(sender)

        return true
    }

    private fun grapplingHook(sender: Player, amount: Int) {
        sender.inventory.addItem(items.grapplingHook())
        utils.receiveItemMessage(sender, amount, "Grappling Hook")
    }

    private fun throwableTNT(sender: Player, amount: Int) {
        sender.inventory.addItem(items.throwableTNT())
        utils.receiveItemMessage(sender, amount, "Throwable TNT")
    }
}