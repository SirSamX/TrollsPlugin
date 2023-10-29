package me.sirsam.trolls

import me.sirsam.trolls.commands.*
import me.sirsam.trolls.core.Main
import me.sirsam.trolls.core.registry.Registry
import me.sirsam.trolls.items.*
import me.sirsam.trolls.items.axe.JungleAxe
import me.sirsam.trolls.items.axe.Treecapitator
import me.sirsam.trolls.listeners.*
import me.sirsam.trolls.managers.IngredientManager
import me.sirsam.trolls.managers.ItemEvents
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger


class Trolls : JavaPlugin() {
    companion object {
        lateinit var instance: Trolls
        lateinit var config: FileConfiguration
        lateinit var logger: Logger
    }

    override fun onEnable() {
        instance = this
        Trolls.config = config
        Trolls.logger = logger
        Main.plugin = this

        with(Registry) {
            register(VacuumCleaner())
            register(Terminator())
            register(ExplosiveBow())
            register(ThrowableFireball())
            register(ThrowableTNT())
            register(GrapplingHook())
            register(Shuriken())
            register(MagicalWand())
            register(Chickzooka())
            register(Treecapitator())
            register(JungleAxe())
            register(Jukebox())
            register(PogeroniSword())

            register(IngredientManager.CompressedJungleWood())
            register(IngredientManager.CompressedNetherStar())
        }

        Main.init()

        registerCommands()
        registerEvents()

        logger.info("Plugin enabled!")
    }

    override fun onDisable() {
        saveConfig()
    }

    private fun registerCommands() {
        fun register(name: String, executor: CommandExecutor) {
            getCommand(name)?.setExecutor(executor)
        }

        register("tjump", Jump())
        register("tfly", Fly())
        register("tsudo", Sudo())
        register("tgodmode", Godmode())
        register("tvanish", Vanish())
        register("tfreeze", Freeze())
        register("repeat", Repeat())
    }

    private fun registerEvents() {
        fun register(listener: Listener) {
            Bukkit.getPluginManager().registerEvents(listener, this)
        }

        register(ItemEvents())
        register(Vanish())
        register(OnJoin())
        register(OnLeave())
        register(OnChat())
        register(OnUse())
        register(Freeze())
        register(OnDeath())
    }
}