package me.sirsam.trolls.guis

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.helpers.Utilities
import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType


class GuiManager : Listener {
    private val instance = Trolls.getPlugin()
    private val utils = Utilities()

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
                val itemsGUI = e.inventory.holder as Items
                when (e.slot) {
                    45 -> {
                        if (itemsGUI.page <= 1) return
                        itemsGUI.page--
                        p.openInventory(itemsGUI.inventory)
                        p.sendMessage(Component.text("Page " + itemsGUI.page))
                    }
                    49 -> {
                        e.isCancelled = true
                        p.closeInventory()
                    }
                    53 -> {
                        itemsGUI.page++
                        p.openInventory(itemsGUI.inventory)
                        p.sendMessage(Component.text("Page " + itemsGUI.page))
                    }
                }
                if (p.gameMode == GameMode.CREATIVE && e.slot < 45) {
                    val item = e.currentItem?.clone()
                    if (item != null) {
                        if (e.isShiftClick) {
                            if (e.isLeftClick && item.itemMeta.persistentDataContainer.get(utils.stackableKey, PersistentDataType.BOOLEAN) == true) {
                                item.amount = item.maxStackSize
                            }
                            p.inventory.addItem(item)
                        } else if (e.isRightClick) {
                            p.setItemOnCursor(item)
                        } else {
                            if (item.itemMeta.persistentDataContainer.get(utils.stackableKey, PersistentDataType.BOOLEAN) == true) {
                                item.amount = item.maxStackSize
                            }
                            p.setItemOnCursor(item)
                        }
                    }
                }
                e.isCancelled = true
            }
        }
        if (e.clickedInventory?.type == InventoryType.PLAYER && e.action == InventoryAction.MOVE_TO_OTHER_INVENTORY || e.isShiftClick) {
            if (e.whoClicked.openInventory.topInventory.holder == Items()) e.isCancelled = true
        }
    }
}