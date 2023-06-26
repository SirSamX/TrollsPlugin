package me.sirsam.trolls

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
        gui.setItem(1, guiItem(Material.WRITABLE_BOOK, 1, Component.text("§eQuests"), null))
        gui.setItem(3, guiItem(Material.KNOWLEDGE_BOOK,1, Component.text("§2Completed Quests"), null))

        return gui
    }

    fun guiItem(material: Material, amount: Int, name: Component, lore: MutableList<Component>?): ItemStack {
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