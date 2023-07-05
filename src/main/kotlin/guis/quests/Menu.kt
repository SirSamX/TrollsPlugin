package me.sirsam.trolls.guis.quests

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import me.sirsam.trolls.GuiManager

class Menu : InventoryHolder {
    private val gui = GuiManager()
    val quests = mutableMapOf(
        "Wood" to 0,
        "Stone" to 0,
        "Dirt" to 0
    )

    private fun questGUI(): Inventory {
        val inv = Bukkit.createInventory(this, InventoryType.HOPPER, Component.text("Quests"))

        inv.setItem(1, gui.item(Material.WRITABLE_BOOK, 1, Component.text("§eOpen Quests")))
        inv.setItem(3, gui.item(Material.KNOWLEDGE_BOOK,1, Component.text("§2Completed Quests")))

        return inv
    }

    override fun getInventory(): Inventory { return questGUI()}
}
