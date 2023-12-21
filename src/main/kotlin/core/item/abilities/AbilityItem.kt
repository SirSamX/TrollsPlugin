package me.sirsam.trolls.core.item.abilities

import me.sirsam.trolls.core.item.Item
import me.sirsam.trolls.core.item.ItemProperties
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent

/**
 * For items with abilities.
 */
open class AbilityItem(properties: ItemProperties) : Item(properties) {
    open fun interact(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun rightClick(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun leftClick(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun rightClickBlock(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun leftClickBlock(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun rightClickAir(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun leftClickAir(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun fish(event: PlayerFishEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun interactPhysically(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun blockBreak(event: BlockBreakEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun shoot(event: EntityShootBowEvent): AbilityResult { return AbilityResult.SUCCESS }
}