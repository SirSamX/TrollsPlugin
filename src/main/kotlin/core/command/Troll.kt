package me.sirsam.trolls.core.command

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.Trolls
import me.sirsam.trolls.core.gui.ItemsGUI
import me.sirsam.trolls.core.registry.Registry
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class Troll : CommandExecutor, TabCompleter {
    private val utils = Utils
    private val plugin = Trolls.instance

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.notPlayerMessage(sender); return true }
        if (args == null  || args.isEmpty()) { utils.formattingErrorMessage(sender); return true }

        when (args[0].lowercase()) {
            "get" -> {
                if (!sender.hasPermission("trolls.troll.get")) { utils.noPermissionMessage(sender); return true }
                if (args.size < 3) { utils.formattingErrorMessage(sender); return true }
                if (!(utils.isNumeric(args[2]))) { utils.formattingErrorMessage(sender); return true; }
                val amount = args[2].toInt()
                if (amount > 3000) { utils.formattingErrorMessage(sender); return true }

                lateinit var givenItem: ItemStack
                for (item in Registry.items) {
                    if (item.id == args[1]) {
                        givenItem = item.item()
                        var i = 0
                        while (i < amount) {
                            i += 1
                            sender.inventory.addItem(givenItem)
                        }
                        utils.receiveItemMessage(sender, amount, item.nameComponent())
                        return true
                    }
                }
            }

            "reload", "rl", "r" -> {
                if (!sender.hasPermission("trolls.troll.reload")) { utils.noPermissionMessage(sender); return true }
                Bukkit.broadcast(Component.text("Reloading...", NamedTextColor.YELLOW), "op")
                plugin.reloadConfig()
                Bukkit.broadcast(Component.text("Reloaded Config!", NamedTextColor.YELLOW), "op")
                plugin.server.reloadPermissions()
                Bukkit.broadcast(Component.text("Reloaded Permissions!", NamedTextColor.YELLOW), "op")
                plugin.server.reload()
                Bukkit.broadcast(Component.text("Reloaded Server!", NamedTextColor.YELLOW), "op")
            }

            "item" -> {
                if (!sender.hasPermission("trolls.troll.items")) { utils.noPermissionMessage(sender); return true }
                sender.openInventory(ItemsGUI().inventory) // TODO: ADD SORTING FOR RECIPE ITEMS & ABILITY ITEMS AND RARITIES
            }

            "heads" -> {
                // TODO: ADD GUI
            }

            "head" -> {
                if (!sender.hasPermission("trolls.troll.head")) { utils.noPermissionMessage(sender); return true }
                val head = ItemStack(Material.PLAYER_HEAD)
                val meta = head.itemMeta as SkullMeta
                if (args.size == 2) {
                    meta.setOwningPlayer(Bukkit.getOfflinePlayer(args[1]))
                } else {
                    meta.setOwningPlayer(Bukkit.getOfflinePlayer(sender.name))
                }
                head.itemMeta = meta
                sender.inventory.addItem(head)
            }

            "version", "ver", "v" -> {
                if (!sender.hasPermission("trolls.troll.version")) { utils.noPermissionMessage(sender); return true }
                @Suppress("UnstableApiUsage")
                sender.sendMessage(Component.text("Version: ${plugin.pluginMeta.version}", NamedTextColor.YELLOW))
            }

            "help", "info" -> {
                if (!sender.hasPermission("trolls.troll.help")) { utils.noPermissionMessage(sender); return true }
                sender.sendMessage(Component.text("i cant help", NamedTextColor.YELLOW))
            }

            "pack" -> {
                sender.setResourcePack("", "")
            }
            else -> { utils.formattingErrorMessage(sender)}
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String> {
        return mutableListOf("get", "reload", "head", "item", "version", "help", "pack")
    }
}