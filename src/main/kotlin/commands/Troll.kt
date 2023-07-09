package me.sirsam.trolls.commands

import com.destroystokyo.paper.profile.ProfileProperty
import me.sirsam.trolls.Trolls
import me.sirsam.trolls.guis.Items
import me.sirsam.trolls.helpers.Utilities
import me.sirsam.trolls.items.ItemManager
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

class Troll : CommandExecutor {
    private val itemManager = ItemManager()
    private val utils = Utilities()
    private val plugin = Trolls.instance

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) { utils.isNotPlayerMessage(sender); return true }
        if (args == null  || args.isEmpty()) { utils.formattingErrorMessage(sender); return true }

        when (args[0].lowercase()) {
            "get" -> {
                if (args.size < 3) { utils.formattingErrorMessage(sender); return true }
                if (!(utils.isNumeric(args[2]))) { utils.formattingErrorMessage(sender); return true; }
                val amount = args[2].toInt()
                if (amount > 3000) { utils.formattingErrorMessage(sender); return true }

                lateinit var givenItem: ItemStack
                for (item in itemManager.items) {
                    if (item.getId() == args[1]) {
                        givenItem = item.createItem()
                        var i = 0
                        while (i < amount) {
                            i += 1
                            sender.inventory.addItem(givenItem)
                        }
                        utils.receiveItemMessage(sender, amount, item.getNameComponent())
                        return true
                    }
                }
            }

            "reload", "rl", "r" -> {
                plugin.server.reload()
                sender.sendMessage(Component.text("Reloaded Server"))
                return true
            }

            "items" -> {
                sender.openInventory(Items().inventory)
            }

            "head" -> {
                val head = ItemStack(Material.PLAYER_HEAD)
                val meta = head.itemMeta as SkullMeta
                //meta.setOwningPlayer(Bukkit.getOfflinePlayer(args[1]))
                val profile = Bukkit.createProfile(UUID.fromString("bb109d67-4d3b-41ce-8323-8f08a5c50558"), "skin6bc042a0")
                profile.setProperty(ProfileProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTY4ODkyNzIzNTI2MSwKICAicHJvZmlsZUlkIiA6ICI0YjJlMGM1ODliZjU0ZTk1OWM1ZmJlMzg5MjQ1MzQzZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJfTmVvdHJvbl8iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJkMjBhZGQ1MTM0ZGM1ZGFhMmU4NjkwYzYyMzk5NDkzOGQ2Zjc3NDliOGMwYzY4YWI5Njg2MDA3NTI5YTRhNyIKICAgIH0KICB9Cn0=", "anQzSPDpA+Dys7PxyDLmlw47txcMkWeMjzFjmEYaH6JacamfV8qaLNEgWdh6xsVolVI2kcLXGYdRmAQF9wMqMPMA2A1eOZ4pdsn/U/KEpue8WdGPPCcNGWu8oj/1njfxM+T6t+72idyZ+tj36c4+iF6+f2ind0mH6x5NNxNXS2uQWTyTkccZBv8eXA3OHtjaa4l8AyEnstui85doXQ3slDdDsIJa8Fn5UYewoEXwto6QekVetB/o0cmAqmaPAbwxHqrthFhhY5zB2PurjRMxLkU3fcgZ9sXnZQfSDzK2XzVjXVM+V3Iuf5NchHoDfKeR6uSotV6WGl02nHhk59gcm0dQrSlAkz9YBVM5z3k17V3sKQa+qh/bjUqBvRIszLIhtPbcmMu58+jRJGsSIyvv7duqYrJmSCUgm9gVLWRoVBE6WxNOw2Rs+L4IRs0o2abrL77bdLjAYLZnpzscUqwhZ1kVWJFwmJzBxtZ4+I9OSoFzkmfOR0R/JQL0RMjyTlPE9FPBZow14yHFgbuG7f57/OHchXM+E7pjQL8n2hZrZKr/XWGld4kNxQqkDS8PG5TJ/8T3CXeMLnYfSTMY+10NeuuHYgTb0RFjMzjFD/Qnli04irhrDqAUJuFBXScj5T0VfH/ryZJCP/68oLAKhf31fcyqv9Cb/YqKsj6bzhK6ZVI="))
                meta.playerProfile = profile
                head.itemMeta = meta
                sender.inventory.addItem(head)
            }

            else -> { utils.formattingErrorMessage(sender); return true }
        }

        return true
    }
}