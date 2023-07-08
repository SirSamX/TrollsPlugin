package me.sirsam.trolls.guis

import me.sirsam.trolls.items.ItemManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import kotlin.IllegalArgumentException

class Items : InventoryHolder {
    private val gui = GuiManager()
    private val items = ItemManager()
    var page = 1

    override fun getInventory(): Inventory {
        if (page <= 0) {
            throw IllegalArgumentException("page cant be negative")
        }

        val inv = Bukkit.createInventory(this, 54, Component.text("Items"))
        val registeredItems = items.items + items.ingredients
        registeredItems.sortedBy { it.getName() }.forEachIndexed { index, item ->
            if (index <= 44) {
                inv.setItem(index, item.createItem())
            }
        }

        if (page != 1) {
            inv.setItem(45, gui.item(Material.ARROW, name = Component.text("Previous Page")))
        }
        inv.setItem(49, gui.item(Material.BARRIER, name = Component.text("Close")))
        inv.setItem(53, gui.item(Material.ARROW, name = Component.text("Next Page")))

        return inv
    }
}