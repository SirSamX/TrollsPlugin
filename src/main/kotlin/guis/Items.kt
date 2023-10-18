package me.sirsam.trolls.guis

import item.ItemManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import kotlin.math.ceil

class Items : InventoryHolder {
    private val gui = GuiManager()
    private val itemsPerPage = 45
    var page = 1
    var maxPage = 1


    override fun getInventory(): Inventory {
        if (page <= 0) {
            throw IllegalArgumentException("Page cannot be negative")
        }

        val registeredItems: Array<ItemManager> = ItemManager.values()

        val startIdx = (page - 1) * itemsPerPage
        val endIdx = minOf(startIdx + itemsPerPage, registeredItems.size)
        maxPage = ceil(registeredItems.size / itemsPerPage.toDouble()).toInt()
        val inv = Bukkit.createInventory(this, 54, Component.text("Items & Ingredients ($page/$maxPage)"))

        registeredItems.sortedBy { it.item.name() }.subList(startIdx, endIdx).forEachIndexed { index, item ->
            inv.setItem(index, item.item.item())
        }

        if (page != 1) {
            inv.setItem(45, gui.item(Material.ARROW, Component.text("← Previous Page", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)))
        }

        inv.setItem(49, gui.item(Material.BARRIER, Component.text("Close ❌", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)))

        if (endIdx < registeredItems.size) {
            inv.setItem(53, gui.item(Material.ARROW, Component.text("Next Page →", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)))
        }

        return inv
    }
}