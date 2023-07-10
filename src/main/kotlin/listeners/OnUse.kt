package me.sirsam.trolls.listeners

import me.sirsam.trolls.helpers.Utilities
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class OnUse : Listener {
    val utils = Utilities()

    @EventHandler
    fun onUse(event: PlayerInteractEvent) {
        val p = event.player
        val m = event.material
        if (event.action !== Action.LEFT_CLICK_AIR) return

        if (m == Material.CRAFTING_TABLE) {
            p.openWorkbench(null, true)
        }
        if (m == Material.ENDER_CHEST) {
            p.openInventory(p.enderChest)
        }
        if (m == Material.ENCHANTING_TABLE) {
            p.openEnchanting(null, true)
        }
        if (m == Material.CARTOGRAPHY_TABLE) {
            p.openCartographyTable(null, true)
        }
        if (m == Material.GRINDSTONE) {
            p.openGrindstone(null, true)
        }
        if (m == Material.LOOM) {
            p.openLoom(null, true)
        }
        if (m == Material.SMITHING_TABLE) {
            p.openSmithingTable(null, true)
        }
        if (m == Material.STONECUTTER) {
            p.openStonecutter(null, true)
        }
        if (m == Material.ANVIL) {
            if (utils.randomPercentage(12)) {
                p.inventory.setItem(p.inventory.heldItemSlot, ItemStack(Material.CHIPPED_ANVIL))
                p.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 1f, 1f)
            }
            p.openAnvil(null, true)
        }
        if (m == Material.CHIPPED_ANVIL) {
            if (utils.randomPercentage(12)) {
                p.inventory.setItem(p.inventory.heldItemSlot, ItemStack(Material.DAMAGED_ANVIL))
                p.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 1f, 1f)
            }
            p.openAnvil(null, true)

        }
        if (m == Material.DAMAGED_ANVIL) {
            if (utils.randomPercentage(12)) {
                p.inventory.setItem(p.inventory.heldItemSlot, ItemStack(Material.AIR))
                p.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 1f, 1f)
            }
            else p.openAnvil(null, true)
        }
    }
}