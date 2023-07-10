package me.sirsam.trolls

import me.sirsam.trolls.commands.*
import me.sirsam.trolls.guis.GuiManager
import me.sirsam.trolls.items.ItemEvents
import me.sirsam.trolls.items.ItemRecipes
import me.sirsam.trolls.listeners.OnJoin
import me.sirsam.trolls.listeners.OnLeave
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin


class Trolls : JavaPlugin(), Listener {
    companion object {
        lateinit var instance: Trolls

        fun getPlugin(): Trolls {
            return instance
        }
    }

    override fun onEnable() {
        instance = this

        config.addDefault("aaaaa.aaaa.aaa.aa.a", "ello")
        saveDefaultConfig()

        registerCommands()
        registerEvents()

        ItemRecipes().registerRecipes()
        Bukkit.getOnlinePlayers().forEach { p -> ItemRecipes().unlockRecipes(p)}

        logger.info("Plugin enabled!")
    }

    override fun onDisable() {
        saveConfig()
    }

    private fun registerCommands() {
        getCommand("jump")?.setExecutor(Jump())
        getCommand("fly")?.setExecutor(Fly())
        getCommand("sudo")?.setExecutor(Sudo())
        getCommand("troll")?.setExecutor(Troll())
        getCommand("godmode")?.setExecutor(Godmode())
        getCommand("vanish")?.setExecutor(Vanish())
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(this,this)
        Bukkit.getPluginManager().registerEvents(ItemEvents(), this)
        Bukkit.getPluginManager().registerEvents(GuiManager(), this)
        Bukkit.getPluginManager().registerEvents(Vanish(), this)
        Bukkit.getPluginManager().registerEvents(OnJoin(), this)
        Bukkit.getPluginManager().registerEvents(OnLeave(), this)
    }
}