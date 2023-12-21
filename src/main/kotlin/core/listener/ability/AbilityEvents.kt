package me.sirsam.trolls.core.listener.ability

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.registry.Registry
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action.*
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class AbilityEvents : Listener {
    private val items = Registry.abilityItems

    // TODO: add missing events
    private fun runEvent(function: AbilityItem.() -> Unit, item: ItemStack?) {
        for (it in items) {
            if (item == null) break
            if (it.id != Utils.getTrollItemId(item)) continue
            it.function()
            break
        }
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        when (event.action) {
            PHYSICAL -> {
                runEvent({ interactPhysically(event) }, event.item)
            }

            LEFT_CLICK_BLOCK -> {
                runEvent ({ leftClickBlock(event) }, event.item)
            }

            RIGHT_CLICK_BLOCK -> {
                runEvent ({ rightClickBlock(event) }, event.item)
            }

            LEFT_CLICK_AIR -> {
                runEvent ({ leftClickAir(event) }, event.item)
            }

            RIGHT_CLICK_AIR -> {
                runEvent ({ rightClickAir(event) }, event.item)
            }
        }

        runEvent({ interact(event) }, event.item)

        if (event.action.isLeftClick) {(
            runEvent ({ leftClick(event) }, event.item))
        }

        if (event.action.isRightClick) {
            runEvent ({ rightClick(event) }, event.item)
        }
    }

    @EventHandler
    fun onFish(event: PlayerFishEvent) {
        runEvent({ fish(event) }, event.player.inventory.itemInMainHand)
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        runEvent({ blockBreak(event) }, event.player.inventory.itemInMainHand)
    }

    @EventHandler
    fun onShoot(event: EntityShootBowEvent) {
        runEvent({ shoot(event) }, event.bow)
    }
}