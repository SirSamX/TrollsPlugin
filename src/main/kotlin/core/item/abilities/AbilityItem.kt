package me.sirsam.trolls.core.item.abilities

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import me.sirsam.trolls.core.item.Item
import me.sirsam.trolls.core.item.ItemProperties
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.*

/**
 * For with abilities.
 */
@Suppress("unused")
open class AbilityItem(properties: ItemProperties) : Item(properties) {
    open fun interact(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun rightClick(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun leftClick(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun rightClickBlock(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun leftClickBlock(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun rightClickAir(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun leftClickAir(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun move(event: PlayerMoveEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun shear(event: PlayerShearEntityEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun teleport(event: PlayerTeleportEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun die(event: PlayerDeathEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun damage(event: EntityDamageEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun jump(event: PlayerJumpEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun toggleFlight(event: PlayerToggleFlightEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun toggleSneak(event: PlayerToggleSneakEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun toggleSprint(event: PlayerToggleSprintEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun fish(event: PlayerFishEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun interactEntity(event: PlayerInteractAtEntityEvent): AbilityResult { return AbilityResult.SUCCESS }

    open fun interactPhysically(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCCESS }
}