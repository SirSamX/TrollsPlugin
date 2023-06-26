    package me.sirsam.trolls.commands

    import net.kyori.adventure.text.Component
    import net.kyori.adventure.text.format.NamedTextColor
    import org.bukkit.command.Command
    import org.bukkit.command.CommandExecutor
    import org.bukkit.command.CommandSender
    import org.bukkit.entity.Player

    class Fly : CommandExecutor{

        override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
            if (sender !is Player) return true

            if (sender.allowFlight) {
                sender.allowFlight = false
                sender.sendMessage(Component.text("You cannot fly anymore!", NamedTextColor.RED))
            }
            else {
                sender.allowFlight = true
                sender.sendMessage(Component.text("You can now fly!", NamedTextColor.GREEN))
            }
            return true
        }
    }