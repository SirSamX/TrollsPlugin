package me.sirsam.trolls.core

import me.sirsam.trolls.core.command.Troll
import me.sirsam.trolls.core.gui.ItemsGUI
import me.sirsam.trolls.core.item.Head
import me.sirsam.trolls.core.listener.ability.AbilityEvents
import me.sirsam.trolls.core.listener.MiscEvents
import me.sirsam.trolls.core.registry.Registry
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger


/**
 * Initialize this at [JavaPlugin.onEnable] AFTER registering everything!
 */
object Main {
    lateinit var plugin: JavaPlugin
    lateinit var logger: Logger
    lateinit var server: Server

    fun init(plugin: JavaPlugin) {
        this.plugin = plugin
        logger = plugin.logger
        server = plugin.server
        registerCommands()
        registerEvents()
        registerRecipes()
        for (head in Head.values()) {
            Registry.register(head.skin)
        }

        logger.info("TrollsCore initialized!")
    }

    private fun registerRecipes() {
        for (recipe in Registry.recipes) {
            server.addRecipe(recipe)
        }
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

        register(AbilityEvents())
        register(ItemsGUI())
        register(MiscEvents())
    }
}