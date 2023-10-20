package me.sirsam.trolls.listeners

import core.helper.Utils
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.persistence.PersistentDataType

class OnDeath : Listener {
    @EventHandler
    fun onDeath(event: EntityDeathEvent) {
        if (event.entity.persistentDataContainer.get(Utils.MOB_KEY, PersistentDataType.STRING) == "explosive_chicken") {
            event.isCancelled = true
        }
    }
}