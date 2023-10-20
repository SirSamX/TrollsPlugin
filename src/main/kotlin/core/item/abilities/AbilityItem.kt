package me.sirsam.trolls.core.item.abilities

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.*

interface AbilityItem {
    fun rightClick(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCESS }

    fun leftClick(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCESS }

    fun rightClickBlock(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCESS }

    fun leftClickBlock(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCESS }

    fun rightClickAir(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCESS }

    fun leftClickAir(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCESS }

    fun move(event: PlayerMoveEvent): AbilityResult { return AbilityResult.SUCESS }

    fun shear(event: PlayerShearEntityEvent): AbilityResult { return AbilityResult.SUCESS }

    fun teleport(event: PlayerTeleportEvent): AbilityResult { return AbilityResult.SUCESS }

    fun die(event: PlayerDeathEvent): AbilityResult { return AbilityResult.SUCESS }

    fun damage(event: EntityDamageEvent): AbilityResult { return AbilityResult.SUCESS }

    fun jump(event: PlayerJumpEvent): AbilityResult { return AbilityResult.SUCESS }

    fun toggleFlight(event: PlayerToggleFlightEvent): AbilityResult { return AbilityResult.SUCESS }

    fun toggleSneak(event: PlayerToggleSneakEvent): AbilityResult { return AbilityResult.SUCESS }

    fun toggleSprint(event: PlayerToggleSprintEvent): AbilityResult { return AbilityResult.SUCESS }

    fun fish(event: PlayerFishEvent): AbilityResult { return AbilityResult.SUCESS }

    fun interactEntity(event: PlayerInteractAtEntityEvent): AbilityResult { return AbilityResult.SUCESS }

    fun interactPhysically(event: PlayerInteractEvent): AbilityResult { return AbilityResult.SUCESS }
}