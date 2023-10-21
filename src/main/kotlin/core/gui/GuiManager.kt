package me.sirsam.trolls.core.gui

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.Trolls
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable


class GuiManager : Listener {
    fun item(material: Material, name: Component, lore: MutableList<Component>? = null, amount: Int = 1): ItemStack {
        val item = ItemStack(material, amount)
        val meta = item.itemMeta

        meta.displayName(name)
        meta.lore(lore)
        item.itemMeta = meta

        return item
    }

    @EventHandler
    fun onClick(e: InventoryClickEvent) {
        val p = e.whoClicked
        when (e.clickedInventory?.holder) {
            is Items -> {
                val itemsGUI = e.inventory.holder as Items
                when (e.slot) {
                    45 -> {
                        e.isCancelled = true
                        if (itemsGUI.page <= 1) return
                        if (e.isShiftClick) {
                            itemsGUI.page = 1
                        } else itemsGUI.page--
                        p.openInventory(itemsGUI.inventory)
                    }
                    49 -> {
                        e.isCancelled = true
                        object : BukkitRunnable() {
                            override fun run() {
                                p.closeInventory()
                            }
                        }.runTaskLater(Trolls.instance, 1L)
                    }
                    53 -> {
                        e.isCancelled = true
                        if (itemsGUI.page >= itemsGUI.maxPage) return
                        if (e.isShiftClick) {
                            itemsGUI.page = itemsGUI.maxPage
                        } else itemsGUI.page++
                        p.openInventory(itemsGUI.inventory)
                    }
                }
                if (e.slot <= 44) {
                    if (p.gameMode == GameMode.CREATIVE) {
                        val item = e.currentItem?.clone()
                        if (item != null) {
                            if (e.isShiftClick) {
                                if (e.isLeftClick && item.itemMeta.persistentDataContainer.get(Utils.STACKABLE_KEY, PersistentDataType.BOOLEAN) == true) {
                                    item.amount = item.maxStackSize
                                }
                                p.inventory.addItem(item)
                            } else if (e.isRightClick) {
                                p.setItemOnCursor(item)
                            } else {
                                if (item.itemMeta.persistentDataContainer.get(Utils.STACKABLE_KEY, PersistentDataType.BOOLEAN) == true) {
                                    item.amount = item.maxStackSize
                                }
                                p.setItemOnCursor(item)
                            }
                        }
                    } else p.sendMessage(Component.text("Recipe GUI is not implemented yet!", NamedTextColor.RED).decorate(TextDecoration.BOLD))
                }
                e.isCancelled = true
            }
        }
        if (e.action == InventoryAction.MOVE_TO_OTHER_INVENTORY || e.isShiftClick) {
            if (p.openInventory.topInventory.holder is Items) {
                e.isCancelled = true
            }
        }
    }
}