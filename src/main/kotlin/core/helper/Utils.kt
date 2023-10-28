package me.sirsam.trolls.core.helper

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.core.helper.ActionSound.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import kotlin.random.Random

object Utils {
    private val plugin = Trolls.instance // FIXME: Use Main 

    private val COMPACT_INVENTORY_KEY = NamespacedKey(plugin, "compact_inventory")
    val ID_KEY = NamespacedKey(plugin, "id")
    val RECIPE_KEY = NamespacedKey(plugin, "recipe")
    val STACKABLE_KEY = NamespacedKey(plugin, "stackable")
    val UNSTACKABLE_KEY = NamespacedKey(plugin, "unstackable")
    val MOB_KEY = NamespacedKey(plugin, "troll_mob")

    fun isTrollItem(item: ItemStack?): Boolean {
        if (item == null || !item.hasItemMeta()) return false
        return item.itemMeta.persistentDataContainer.get(ID_KEY, PersistentDataType.STRING) != null
    }

    fun getTrollItemId(item: ItemStack): String? {
        if (!item.hasItemMeta()) return null
        return item.itemMeta.persistentDataContainer.get(ID_KEY, PersistentDataType.STRING)
    }

    fun receiveItemMessage(player: Player, amount: Int, item: Component) {
        player.sendMessage(Component.text("You received $amount ").append(item).append(Component.text("!")))
    }

    fun formattingErrorMessage(player: Player) {
        player.sendMessage(Component.text("Formatting error, check your command syntax!", NamedTextColor.RED))
    }

    fun notPlayerMessage(player: CommandSender) {
        player.sendMessage(Component.text("Sender is not a player."))
    }

    fun playerNotExistingMessage(player: CommandSender) {
        player.sendMessage(Component.text("This player does not exist!", NamedTextColor.RED))
    }

    fun noPermissionMessage(player: CommandSender) {
        player.sendMessage(Component.text("You don't have permission to do that!", NamedTextColor.RED))
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

    fun playSound(sound: ActionSound, player: Player) {
        when (sound) {
            OPEN -> player.playSound(player.location, Sound.BLOCK_CHEST_OPEN, 1.0f, 1.0f)
            MODIFY -> player.playSound(player.location, Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f)
            SELECT -> player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f)
            CLICK -> player.playSound(player.location, Sound.UI_BUTTON_CLICK, 1.0f, 1.0f)
            POP -> player.playSound(player.location, Sound.ENTITY_CHICKEN_EGG, 1.0f, 1.0f)
            BREAK -> player.playSound(player.location, Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f)
            ERROR -> player.playSound(player.location, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.5f)
        }
    }

    fun throwItem(player: Player, item: ItemStack, strength: Float, particle: Particle, keepItem: Boolean = false) {
        val thrownItem = player.world.dropItem(player.eyeLocation, item.clone().apply { amount = 1 })
        val loc = player.eyeLocation
        thrownItem.velocity = player.eyeLocation.direction.multiply(strength)
        player.world.spawnParticle(particle, loc, 25)
        if (!keepItem) {
            destroy(item, 1)
        }
    }
}