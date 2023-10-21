package me.sirsam.trolls.core.helper

import com.destroystokyo.paper.profile.PlayerProfile
import me.sirsam.trolls.Trolls
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.profile.PlayerTextures
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import kotlin.random.Random

object Utils {
    private val plugin = Trolls.instance

    private val compactInventoryKey = NamespacedKey(plugin, "compactInventory")
    val ID_KEY = NamespacedKey(plugin, "ID")
    val STACKABLE_KEY = NamespacedKey(plugin, "stackable")
    val MOB_KEY = NamespacedKey(plugin, "trollMob")
    val UUID_KEY = NamespacedKey(plugin, "uuid")

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
        meta.persistentDataContainer.set(compactInventoryKey, PersistentDataType.STRING, BukkitSerialization().toBase64(inv))
        item.setItemMeta(meta)
    }

    fun getInventoryInItem(item: ItemStack): Inventory? {
        val inventoryData = item.itemMeta.persistentDataContainer.get(compactInventoryKey, PersistentDataType.STRING)
        return BukkitSerialization().fromBase64(inventoryData)
    }

    fun getProfile(url: String): PlayerProfile {
        val profile: PlayerProfile = Bukkit.createProfile(UUID.randomUUID())
        val textures: PlayerTextures = profile.textures
        val urlObject: URL
        try {
            urlObject = URL(url)
        } catch (exception: MalformedURLException) {
            throw RuntimeException("Invalid URL", exception)
        }
        textures.skin = urlObject
        profile.setTextures(textures)
        return profile
    }

    fun getProfileById(id: String): PlayerProfile {
        return getProfile("http://textures.minecraft.net/texture/$id")
    }
}