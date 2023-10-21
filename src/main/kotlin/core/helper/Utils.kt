package me.sirsam.trolls.core.helper

import me.sirsam.trolls.Trolls
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import kotlin.random.Random

object Utils {
    private val plugin = Trolls.instance

    private val COMPACT_INVENTORY_KEY = NamespacedKey(plugin, "compact_inventory")
    val ID_KEY = NamespacedKey(plugin, "id")
    val STACKABLE_KEY = NamespacedKey(plugin, "stackable")
    val UNSTACKABLE_KEY = NamespacedKey(plugin, "unstackable")
    val MOB_KEY = NamespacedKey(plugin, "troll_mob")

    fun isTrollItem(i: ItemStack): Boolean {
        return i.itemMeta.persistentDataContainer.get(ID_KEY, PersistentDataType.STRING) != null
    }

    fun receiveItemMessage(p: Player, a: Int, i: Component) {
        p.sendMessage(Component.text("You received $a ").append(i).append(Component.text("!")))
    }

    fun formattingErrorMessage(p: Player) {
        p.sendMessage(Component.text("Formatting error, check your command syntax!", NamedTextColor.RED))
    }

    fun notPlayerMessage(p: CommandSender) {
        p.sendMessage(Component.text("Sender is not a player."))
    }

    fun playerNotExistingMessage(p: CommandSender) {
        p.sendMessage(Component.text("This player does not exist!", NamedTextColor.RED))
    }

    fun noPermissionMessage(p: CommandSender) {
        p.sendMessage(Component.text("You don't have permission to do that!", NamedTextColor.RED))
    }

    fun isNumeric(input: String): Boolean {
        return input.toDoubleOrNull() != null
    }

    fun destroy(item: ItemStack, quantity: Int) {
        if (item.amount <= quantity) {
            item.amount = 0
        } else {
            item.amount -= quantity
        }
    }

    fun randomPercentage(percentage: Int, scale: Int = 1): Boolean {
        val r = Random.nextInt(100 * scale)
        return r < percentage * scale
    }

    fun storeInventoryInItem(item: ItemStack, inv: Inventory) {
        val meta = item.itemMeta
        meta.persistentDataContainer.set(COMPACT_INVENTORY_KEY, PersistentDataType.STRING, BukkitSerialization().toBase64(inv))
        item.setItemMeta(meta)
    }

    fun getInventoryInItem(item: ItemStack): Inventory? {
        val inventoryData = item.itemMeta.persistentDataContainer.get(COMPACT_INVENTORY_KEY, PersistentDataType.STRING)
        return BukkitSerialization().fromBase64(inventoryData)
    }

    fun guiItem(material: Material, name: Component, lore: MutableList<Component>? = null, amount: Int = 1): ItemStack {
        val item = ItemStack(material, amount)
        val meta = item.itemMeta

        meta.displayName(name)
        meta.lore(lore)
        item.itemMeta = meta

        return item
    }
}