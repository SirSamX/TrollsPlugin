package me.sirsam.trolls.guis.quests

import me.sirsam.trolls.GuiManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class Open : InventoryHolder {
    private val gui = GuiManager()

    private fun openQuestGUI(): Inventory {
        val inv = Bukkit.createInventory(this, 54, Component.text("§eCompleted Quests"))

        for (quests in Menu().quests) {
            inv.addItem(gui.item(Material.BARRIER, 69 , Component.text("Test")))
        }

        return inv
    }

    override fun getInventory(): Inventory { return openQuestGUI()}
}