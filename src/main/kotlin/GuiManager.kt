package me.sirsam.trolls

import me.sirsam.trolls.guis.quests.Completed
import me.sirsam.trolls.guis.quests.Menu
import me.sirsam.trolls.guis.quests.Open
import me.sirsam.trolls.items.ItemManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack


class GuiManager : Listener {
    val instance = Trolls.getPlugin()

    fun itemGUI(): Inventory {
        val gui = Bukkit.createInventory(null, 54, Component.text("Items"))
        var slot = 0

        for (item in ItemManager().items.sortedBy { it.getName(false) }) {
            gui.setItem(slot, item.createItem())
            slot += 1
        }
        gui.setItem(45, item(Material.ARROW, name = Component.text("Previous Page")))
        gui.setItem(49, item(Material.BARRIER, name = Component.text("Close")))
        gui.setItem(53, item(Material.ARROW, name = Component.text("Next Page")))

        return gui
    }

    fun item(material: Material, amount: Int = 1, name: Component, lore: MutableList<Component>? = null): ItemStack {
        val item = ItemStack(material, amount)
        val meta = item.itemMeta

        meta.displayName(name)
        meta.lore(lore)
        item.itemMeta = meta

        return item
    }

    @EventHandler
    fun onclick(e: InventoryClickEvent) {
        val p = e.whoClicked
        when (e.clickedInventory?.holder) {
            is Menu -> {
                when (e.slot) {
                    1 -> p.openInventory(Open().inventory)
                    3 -> p.openInventory(Completed().inventory)
                }
                e.isCancelled = true
            }
        }
        if (e.clickedInventory?.type == InventoryType.PLAYER && e.currentItem?.type == Material.STONE && e.action == InventoryAction.MOVE_TO_OTHER_INVENTORY || e.isShiftClick) {
            e.isCancelled = true
        }
    }
}