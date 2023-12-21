package me.sirsam.trolls.manager

import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent


class ItemEvents : Listener {
    @EventHandler
    fun explosiveBowHit(event: ProjectileHitEvent) {
        if (event.entity.customName() == null) return
        if (event.entity.customName() != Component.text("Explosive Arrow")) return

        event.entity.world.createExplosion(event.entity.location, 2.5f, false, true)
        event.entity.remove()
    }
}