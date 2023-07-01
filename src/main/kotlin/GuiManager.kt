package me.sirsam.trolls

import me.sirsam.trolls.items.ItemManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack


class GuiManager : Listener {
    val instance = Trolls.getPlugin()
    fun questGUI(): Inventory {
        val gui = Bukkit.createInventory(null, InventoryType.HOPPER, Component.text("Quests"))

        gui.setItem(1, guiItem(Material.WRITABLE_BOOK, 1, Component.text("§eQuests")))
        gui.setItem(3, guiItem(Material.KNOWLEDGE_BOOK,1, Component.text("§2Completed Quests")))

        return gui
    }

    fun itemGUI(): Inventory {
        val gui = Bukkit.createInventory(null, 54, Component.text("Items"))
        var slot = 0

        for (item in ItemManager().items) {
            gui.setItem(slot, item.createItem())
            slot += 1
        }
        gui.setItem(45, guiItem(Material.ARROW, name = Component.text("Previous Page")))
        gui.setItem(49, guiItem(Material.BARRIER, name = Component.text("Close")))
        gui.setItem(53, guiItem(Material.ARROW, name = Component.text("Next Page")))

        return gui
    }

    private fun guiItem(material: Material, amount: Int = 1, name: Component, lore: MutableList<Component>? = null): ItemStack {
        val item = ItemStack(material, amount)
        val meta = item.itemMeta

        meta.displayName(name)
        meta.lore(lore)
        item.itemMeta = meta

        return item
    }

    @EventHandler
    fun onclick(e: InventoryClickEvent) {
        if (e.inventory != questGUI()) return
        e.isCancelled = true
    }
}