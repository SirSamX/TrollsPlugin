package me.sirsam.trolls.core.listener.ability

import me.sirsam.trolls.core.item.abilities.AbilityItem
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action.*
import org.bukkit.event.player.PlayerInteractEvent

class AbilityEvents : Listener {
    private val items = listOf<AbilityItem>()

    // TODO: add missing events
    private fun runEvent(function: AbilityItem.() -> Unit) {
        items.forEach {
            it.function()
        }
    }

    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        when (event.action) {
            PHYSICAL -> {
                runEvent { interactPhysically(event) }
            }

            LEFT_CLICK_BLOCK -> {
                runEvent { leftClickBlock(event) }
            }

            RIGHT_CLICK_BLOCK -> {
                runEvent { rightClickBlock(event) }
            }

            LEFT_CLICK_AIR -> {
                runEvent { leftClickAir(event) }
            }

            RIGHT_CLICK_AIR -> {
                runEvent { rightClickAir(event) }
            }
        }

        if (event.action.isLeftClick) {
            runEvent { leftClick(event) }
        }

        if (event.action.isRightClick) {
            runEvent { rightClick(event) }
        }
    }
}