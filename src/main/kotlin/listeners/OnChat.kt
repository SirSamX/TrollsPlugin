package me.sirsam.trolls.listeners

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class OnChat : Listener {
    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        var message = PlainTextComponentSerializer.plainText().serialize(event.message())
        if (message.contains(":yin_yang:", true)) {
            message = message.replace(":yin_yang:", "☯", true)
        }

        if (message.contains("x", true)) {
            event.player.kick(Component.text("Don't offend others!", NamedTextColor.RED))
            message = message.replace("x", "☯", true)
        }
        event.message(PlainTextComponentSerializer.plainText().deserialize(message))
        event.message(PlainTextComponentSerializer.plainText().deserialize(message))
    }
}