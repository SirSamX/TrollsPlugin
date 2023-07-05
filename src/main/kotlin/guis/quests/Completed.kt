package me.sirsam.trolls.guis.quests

import me.sirsam.trolls.GuiManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class Completed : InventoryHolder{
    private val gui = GuiManager()

    private fun completedQuestGUI(): Inventory {
        val inv = Bukkit.createInventory(this, 54, Component.text("§2Quests"))

        return inv
    }

    override fun getInventory(): Inventory { return completedQuestGUI()}
}