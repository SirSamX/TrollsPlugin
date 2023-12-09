    package me.sirsam.trolls.commands

    import me.sirsam.trolls.core.helper.Utils
    import net.kyori.adventure.text.Component
    import net.kyori.adventure.text.format.NamedTextColor
    import org.bukkit.Bukkit
    import org.bukkit.command.Command
    import org.bukkit.command.CommandExecutor
    import org.bukkit.command.CommandSender
    import org.bukkit.entity.Player

    class Fly : CommandExecutor{
        override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
            if (sender !is Player) { Utils.notPlayerMessage(sender); return true }
            if (!sender.hasPermission("trolls.utils.fly")) { Utils.noPermissionMessage(sender); return true }
            var target: Player? = null
            if (args!!.isNotEmpty()) {
                target = Bukkit.getPlayer(args[0])
            }
            if (target == null) {
                target = sender
            }

            if (target.allowFlight) {
                target.allowFlight = false
                target.sendMessage(Component.text("You cannot fly anymore!", NamedTextColor.RED))
            }
            else {
                target.allowFlight = true
                target.sendMessage(Component.text("You can now fly!", NamedTextColor.GREEN))
            }
            return true
        }
    }