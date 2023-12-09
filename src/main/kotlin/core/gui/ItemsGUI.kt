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
    enum class Type {
        ITEMS,
        ALL,
        INGREDIENTS
    }

    private var type = Type.ITEMS
    private val itemsPerPage = 45
    private var page = 1
    private var maxPage = 1

    private val previousPage = 45
    private val close = 49
    private val sort = 50
    private val nextPage = 53


    override fun getInventory(): Inventory {
        if (page <= 0) {
            throw IllegalArgumentException("Page cannot be negative")
        }

        val items: List<Item> = when (type) {
            Type.ALL -> Registry.items.toList().sortedBy { it.name }
            Type.ITEMS -> Registry.abilityItems.toList().sortedBy { it.name }
            Type.INGREDIENTS -> Registry.ingredients.toList().sortedBy { it.name }
        }


        val startIdx = (page - 1) * itemsPerPage
        val endIdx = minOf(startIdx + itemsPerPage, items.size)
        maxPage = ceil(items.size / itemsPerPage.toDouble()).toInt()
        val inv = Bukkit.createInventory(this, 54, Component.text(when (type) {
            Type.ITEMS -> "Items ($page/$maxPage)"
            Type.ALL -> "Items & Ingredients ($page/$maxPage)"
            Type.INGREDIENTS -> "Ingredients ($page/$maxPage)"
        }))

        items.subList(startIdx, endIdx).forEachIndexed { index, item ->
            inv.setItem(index, item.item())
        }

        if (page != 1) {
            inv.setItem(previousPage, Utils.guiItem(Material.ARROW, Component.text("← Previous Page", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)))
        }

        inv.setItem(close, Utils.guiItem(Material.BARRIER, Component.text("Close ❌", NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)))
        inv.setItem(sort, Utils.guiItem(Material.HOPPER, Component.text("Sort", NamedTextColor.DARK_AQUA).decoration(TextDecoration.ITALIC, false), mutableListOf(
            Component.text("Items", NamedTextColor.BLUE).decoration(TextDecoration.BOLD, type == Type.ITEMS).decoration(TextDecoration.ITALIC, false),
            Component.text("All", NamedTextColor.BLUE).decoration(TextDecoration.BOLD, type == Type.ALL).decoration(TextDecoration.ITALIC, false),
            Component.text("Ingredients", NamedTextColor.BLUE).decoration(TextDecoration.BOLD, type == Type.INGREDIENTS).decoration(TextDecoration.ITALIC, false),
        )))

        if (endIdx < items.size) {
            inv.setItem(nextPage, Utils.guiItem(Material.ARROW, Component.text("Next Page →", NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)))
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
            previousPage -> {
                e.isCancelled = true
                if (itemsGUI.page <= 1) return
                if (e.isShiftClick) {
                    itemsGUI.page = 1
                } else itemsGUI.page--
                p.openInventory(itemsGUI.inventory)
            }

            close -> {
                object : BukkitRunnable() {
                    override fun run() {
                        p.closeInventory()
                    }
                }.runTaskLater(Trolls.instance, 1L)
            }

            sort -> {
                e.isCancelled = true
                if (e.isLeftClick) {
                    when (itemsGUI.type) {
                        Type.ITEMS -> itemsGUI.type = Type.ALL
                        Type.ALL -> itemsGUI.type = Type.INGREDIENTS
                        Type.INGREDIENTS -> itemsGUI.type = Type.ITEMS
                    }
                } else if (e.isRightClick) {
                    when (itemsGUI.type) {
                        Type.ITEMS -> itemsGUI.type = Type.INGREDIENTS
                        Type.ALL -> itemsGUI.type = Type.ITEMS
                        Type.INGREDIENTS -> itemsGUI.type = Type.ALL
                    }
                }
                p.openInventory(itemsGUI.inventory)
            }

            nextPage -> {
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