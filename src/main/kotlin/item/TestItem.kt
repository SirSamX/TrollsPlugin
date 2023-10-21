package me.sirsam.trolls.item

import me.sirsam.trolls.core.item.TrollItemProperties
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent

class TestItem : AbilityItem(TrollItemProperties("test", Material.BARRIER, "Test")) {
    override fun move(event: PlayerMoveEvent): AbilityResult {
        event.isCancelled = true
        return super.move(event)
    }

    override fun rightClick(event: PlayerInteractEvent): AbilityResult {
        event.player.sendMessage("a")
        return super.rightClick(event)
    }
}