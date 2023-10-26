package me.sirsam.trolls.core.listener

import me.sirsam.trolls.core.helper.ActionSound
import me.sirsam.trolls.core.helper.Utils
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.entity.EntityResurrectEvent
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.FurnaceSmeltEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType

class MiscEvents : Listener {
    @EventHandler
    fun onTotemUse(event: EntityResurrectEvent) {
        val entity = event.entity
        if (entity !is Player) return
        if (!Utils.isTrollItem(entity.inventory.itemInMainHand) && !Utils.isTrollItem(entity.inventory.itemInOffHand)) return
        event.isCancelled = true
    }

    @EventHandler // TODO: fix
    fun onItemSmelt(event: FurnaceSmeltEvent) {
        if (!Utils.isTrollItem(event.source)) return
        event.isCancelled = true
    }

    @EventHandler
    fun onEnchant(event: EnchantItemEvent) {
        if (!Utils.isTrollItem(event.item)) return
        event.isCancelled = true
        Utils.playSound(ActionSound.ERROR, event.enchanter)
    }

    @EventHandler // TODO: fix
    fun onCraft(event: CraftItemEvent) {
        for (item in event.inventory.matrix) {
            if (!Utils.isTrollItem(item)) return
        }
        event.isCancelled = true
        Utils.playSound(ActionSound.ERROR, event.whoClicked as Player)
    }

    @EventHandler
    fun renameItem(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (event.view.type == InventoryType.ANVIL && event.slotType == InventoryType.SlotType.RESULT) {
            if (Utils.isTrollItem(event.view.getItem(0)) || Utils.isTrollItem(event.view.getItem(1)) || Utils.isTrollItem(event.view.getItem(2))) {
                event.isCancelled = true
                Utils.playSound(ActionSound.ERROR, player)
            }
        }
    }

    @EventHandler
    fun blockPlace(event: BlockPlaceEvent) {
        if (!Utils.isTrollItem(event.itemInHand)) return
        event.isCancelled = true
    }
}