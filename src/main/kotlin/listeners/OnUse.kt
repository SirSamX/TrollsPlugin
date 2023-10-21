package me.sirsam.trolls.listeners

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.Trolls
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable

class OnUse : Listener {

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
            if (Utils.randomPercentage(12)) {
                p.inventory.setItem(p.inventory.heldItemSlot, ItemStack(Material.CHIPPED_ANVIL))
                p.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 1f, 1f)
            }
            p.openAnvil(null, true)
        }
        if (m == Material.CHIPPED_ANVIL) {
            if (Utils.randomPercentage(12)) {
                p.inventory.setItem(p.inventory.heldItemSlot, ItemStack(Material.DAMAGED_ANVIL))
                p.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 1f, 1f)
            }
            p.openAnvil(null, true)

        }
        if (m == Material.DAMAGED_ANVIL) {
            if (Utils.randomPercentage(12)) {
                p.inventory.setItem(p.inventory.heldItemSlot, ItemStack(Material.AIR))
                p.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 1f, 1f)
            }
            else p.openAnvil(null, true)
        }
    }
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val item = event.player.inventory.itemInMainHand
        if (item.itemMeta == null) return
        val data = item.itemMeta.persistentDataContainer
        if (data.get(Utils.ID_KEY, PersistentDataType.STRING) != "jukebox" || !event.action.isRightClick) return

        event.isCancelled = true
        playJukeboxSound(event.player)
    }

    private fun playJukeboxSound(player: Player) {
        player.playSound(player.location, Sound.MUSIC_DISC_13, 1.0f, 1.0f)
        object : BukkitRunnable() {
            override fun run() {
                player.stopSound(Sound.MUSIC_DISC_13)
            }
        }.runTaskLater(Trolls.instance, 20*10L)
    }
}