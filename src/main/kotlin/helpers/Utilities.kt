package me.sirsam.trolls.helpers

import me.sirsam.trolls.Trolls
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import kotlin.random.Random

class Utilities {
    private val plugin = Trolls.getPlugin()
    private val config = plugin.config

    private val compactInventoryKey = NamespacedKey(plugin, "compactInventory")
    val idKey = NamespacedKey(plugin, "ID")
    val stackableKey = NamespacedKey(plugin, "stackable")
    val uuidKey = NamespacedKey(plugin, "uuid")

    fun isTrollItem(i: ItemStack): Boolean { return i.itemMeta.persistentDataContainer.get(idKey, PersistentDataType.STRING) !== null }

    fun receiveItemMessage(p: Player, a: Int, i: Component) { p.sendMessage(Component.text("You received $a ").append(i).append(Component.text("!"))) }

    fun formattingErrorMessage(p: Player) { p.sendMessage(Component.text("Formatting error, check your command syntax!", NamedTextColor.RED)) }

    fun notPlayerMessage(p: CommandSender) { p.sendMessage(Component.text("Sender is not a player.")) }

    fun playerNotExistingMessage(p: CommandSender) { p.sendMessage(Component.text("This player does not exist!", NamedTextColor.RED)) }

    fun noPermissionMessage(p: CommandSender) { p.sendMessage(Component.text("You don't have permission to do that!", NamedTextColor.RED)) }

    fun cooldownMessage(p: Player, eta: Long) { p.sendMessage(Component.text("Please wait ${"%.1f".format(eta.toFloat())}s before using this!", NamedTextColor.YELLOW)) }

    fun isNumeric(input: String): Boolean { return input.toDoubleOrNull() != null }

    fun destroy(item: ItemStack, quantity: Int) {
        if (item.amount <= quantity) {
            item.amount = 0
        } else {
            item.amount = item.amount - quantity
        }
    }

    fun randomPercentage(percentage: Int, scale: Int = 1): Boolean {
        val r = Random.nextInt(100 * scale)
        return r < percentage * scale
    }

    fun storeInventoryInItem(item: ItemStack, inv: Inventory) {
        val meta = item.itemMeta
        meta.persistentDataContainer.set(compactInventoryKey, PersistentDataType.STRING, BukkitSerialization().toBase64(inv))
        item.setItemMeta(meta)
    }

    fun getInventoryInItem(item: ItemStack): Inventory? {
        val inventoryData = item.itemMeta.persistentDataContainer.get(compactInventoryKey, PersistentDataType.STRING)
        return BukkitSerialization().fromBase64(inventoryData)
    }
}