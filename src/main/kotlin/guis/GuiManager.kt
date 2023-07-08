package me.sirsam.trolls.guis

import me.sirsam.trolls.Trolls
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack


class GuiManager : Listener {
    val instance = Trolls.getPlugin()



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
            is Items -> {
                when (e.slot) {
                    45 -> {
                        e.isCancelled = true
                    }
                    49 -> {
                        e.isCancelled = true
                        p.closeInventory()
                    }
                    53 -> {
                        e.isCancelled = true
                        val itemsGUI = Items()
                        itemsGUI.page++
                        p.openInventory(itemsGUI.inventory)
                    }
                }
                e.isCancelled = true
            }
        }
        if (e.clickedInventory?.type == InventoryType.PLAYER && e.currentItem?.type == Material.STONE && e.action == InventoryAction.MOVE_TO_OTHER_INVENTORY || e.isShiftClick) {
            e.isCancelled = true
        }
    }
}