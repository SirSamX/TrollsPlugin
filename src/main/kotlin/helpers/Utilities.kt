package me.sirsam.trolls.helpers

import me.sirsam.trolls.Trolls
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class Utilities {
    private val plugin = Trolls.getPlugin()
    private val config = plugin.config

    val nameKey = NamespacedKey(plugin, "itemname")

    fun isTrollItem(i: ItemStack): Boolean { return i.itemMeta.persistentDataContainer.get(nameKey, PersistentDataType.STRING) !== null }

    fun receiveItemMessage(p: Player, a: Int, i: String) { p.sendMessage(Component.text("You received $a $i!")) }

    fun formattingErrorMessage(p: Player) { p.sendMessage(Component.text("Formatting error, check your command syntax!", NamedTextColor.RED)) }

    fun isNotPlayerMessage(p: CommandSender) { p.sendMessage(Component.text("Target is not a player.")) }

    fun isNumeric(input: String): Boolean { return input.toDoubleOrNull() != null }

    fun destroy(item: ItemStack, quantity: Int) {
        if (item.amount <= quantity) {
            item.amount = 0
        } else {
            item.amount = item.amount - quantity
        }
    }

    fun getStringFromConfig(path: String): String? { return config.getString(path) }
}