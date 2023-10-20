package me.sirsam.trolls.core

import me.sirsam.trolls.commands.Troll
import me.sirsam.trolls.core.gui.GuiManager
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main(val plugin: JavaPlugin) {
    private val logger = plugin.logger

    init {
        registerCommands()
        registerEvents()

        logger.info("TrollsCore initialized!")
    }

    private fun registerCommands() {
        fun register(name: String, executor: CommandExecutor, completer: TabCompleter?) {
            plugin.getCommand(name)?.setExecutor(executor)

            if (completer == null) return
            plugin.getCommand(name)?.tabCompleter = completer
        }

        register("troll", Troll(), Troll())
    }

    private fun registerEvents() {
        fun register(listener: Listener) {
            Bukkit.getPluginManager().registerEvents(listener, plugin)
        }

        register(GuiManager())
    }
}