package me.sirsam.trolls.core.gui

import me.sirsam.trolls.Trolls
import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.Item
import me.sirsam.trolls.core.registry.Registry
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.ceil

class ItemsGUI : InventoryHolder, Listener {
    private val itemsPerPage = 45
    private var page = 1 // TODO: make page argument in command
    private var maxPage = 1


    override fun getInventory(): Inventory {
        if (page <= 0) {
            throw IllegalArgumentException("Page cannot be negative")
        }

        val registeredItems: List<Item> = Registry.items.toList()

        val startIdx = (page - 1) * itemsPerPage
        val endIdx = minOf(startIdx + itemsPerPage, registeredItems.size)
        maxPage = ceil(registeredItems.size / itemsPerPage.toDouble()).toInt()
        val inv = Bukkit.createInventory(this, 54, Component.text("Items & Ingredients ($page/$maxPage)"))

        registeredItems.sortedBy { it.name() }.subList(startIdx, endIdx).forEachIndexed { index, item ->
            inv.setItem(index, item.item())
        }

        if (page != 1) {
            inv.setItem(45, Utils.guiItem(Material.ARROW, Component.text("← Previous Page", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)))
        }

        inv.setItem(49, Utils.guiItem(Material.BARRIER, Component.text("Close ❌", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)))

        if (endIdx < registeredItems.size) {
            inv.setItem(53, Utils.guiItem(Material.ARROW, Component.text("Next Page →", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)))
        }

        return inv
    }

    @EventHandler
    fun onClick(e: InventoryClickEvent) {
        val p = e.whoClicked

        if (e.action == InventoryAction.MOVE_TO_OTHER_INVENTORY || e.isShiftClick) {
            if (p.openInventory.topInventory.holder is ItemsGUI) {
                e.isCancelled = true
            }
        }

        if (e.clickedInventory?.holder !is ItemsGUI) return
        val itemsGUI = e.inventory.holder as ItemsGUI

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