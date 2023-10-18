package me.sirsam.trolls.listeners

import core.helpers.Utils
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.persistence.PersistentDataType

class OnDeath : Listener {
    private val utils = Utils

    fun onDeath(event: EntityDeathEvent) {
        if (event.entity.persistentDataContainer.get(Utils.MOB_KEY, PersistentDataType.STRING) == "explosive_chicken") {
            event.isCancelled = true
        }
    }
}