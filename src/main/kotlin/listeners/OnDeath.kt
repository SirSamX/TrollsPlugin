package me.sirsam.trolls.listeners

import me.sirsam.trolls.helpers.Utilities
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.persistence.PersistentDataType

class OnDeath : Listener {
    private val utils = Utilities()

    fun onDeath(event: EntityDeathEvent) {
        if (event.entity.persistentDataContainer.get(utils.mobKey, PersistentDataType.STRING) == "explosive_chicken") {
            event.isCancelled = true
        }
    }
}