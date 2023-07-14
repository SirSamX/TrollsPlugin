package me.sirsam.trolls.helpers

import org.bukkit.entity.Player
import java.util.UUID

class Cooldown {
    private var cooldown = HashMap<UUID, Long>()

    fun add(player: Player, cooldown: Long) {
        this.cooldown[player.uniqueId] = System.currentTimeMillis() + cooldown
    }

    fun can(player: Player): Boolean {
        val playerCooldown = cooldown[player.uniqueId] ?: return true
        return System.currentTimeMillis() >= playerCooldown
    }

    fun eta(player: Player): Long {
        return this.cooldown[player.uniqueId]!! - System.currentTimeMillis()
    }
}