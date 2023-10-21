package me.sirsam.trolls.core.helper

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import java.util.UUID

class Cooldown {
    private var cooldown = HashMap<UUID, Long>()

    fun set(player: Player, cooldown: Long) {
        this.cooldown[player.uniqueId] = System.currentTimeMillis() + cooldown
    }

    fun can(player: Player): Boolean {
        val playerCooldown = cooldown[player.uniqueId] ?: return true
        return System.currentTimeMillis() >= playerCooldown
    }

    fun eta(player: Player): Long {
        return this.cooldown[player.uniqueId]!! - System.currentTimeMillis()
    }

    fun cooldownMessage(p: Player) {
        p.sendMessage(Component.text("Please wait ${"%.1f".format(eta(p).toFloat())}s before using this!", NamedTextColor.YELLOW))
    }
}