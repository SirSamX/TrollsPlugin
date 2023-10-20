package me.sirsam.trolls.core.listener.ability

import me.sirsam.trolls.item.TestItem
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action.*
import org.bukkit.event.player.PlayerInteractEvent

class AbilityEvents : Listener {
    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        when (event.action) {
            PHYSICAL -> {
                TestItem().interactPhysically(event)
            }

            LEFT_CLICK_BLOCK -> {
                TestItem().leftClickBlock(event)
            }

            RIGHT_CLICK_BLOCK -> {
                TestItem().rightClickBlock(event)
            }

            LEFT_CLICK_AIR -> {
                TestItem().leftClickAir(event)
            }

            RIGHT_CLICK_AIR -> {
                TestItem().rightClickAir(event)
            }
        }

        if (event.action.isLeftClick) {
            TestItem().leftClick(event)
        }

        if (event.action.isRightClick) {
            TestItem().leftClick(event)
        }
    }
}