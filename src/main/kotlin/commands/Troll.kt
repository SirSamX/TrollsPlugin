package me.sirsam.trolls.commands

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.guis.Items
import me.sirsam.trolls.helpers.Utilities
import me.sirsam.trolls.items.ItemManager
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Troll : CommandExecutor {
    private val itemManager = ItemManager()
    private val utils = Utilities()
    private val plugin = Trolls.instance

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.isNotPlayerMessage(sender); return true }
        if (args == null  || args.isEmpty()) { utils.formattingErrorMessage(sender); return true }

        when (args[0].lowercase()) {
            "get" -> {
                if (args.size < 3) { utils.formattingErrorMessage(sender); return true }
                if (!(utils.isNumeric(args[2]))) { utils.formattingErrorMessage(sender); return true; }
                val amount = args[2].toInt()
                if (amount > 3000) { utils.formattingErrorMessage(sender); return true }

                lateinit var givenItem: ItemStack
                for (item in itemManager.items) {
                    if (item.getId() == args[1]) {
                        givenItem = item.createItem()
                        var i = 0
                        while (i < amount) {
                            i += 1
                            sender.inventory.addItem(givenItem)
                        }
                        utils.receiveItemMessage(sender, amount, item.getNameComponent())
                        return true
                    }
                }
            }

            "reload", "rl", "r" -> {
                plugin.server.reload()
                sender.sendMessage(Component.text("Reloaded Server"))
                return true
            }

            "items" -> {
                sender.openInventory(Items().inventory)
            }

            "ingredients" -> {
                sender.openInventory(Items().inventory)
            }

            else -> { utils.formattingErrorMessage(sender); return true }
        }

        return true
    }
}