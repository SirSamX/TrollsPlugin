package me.sirsam.trolls

import me.sirsam.trolls.commands.*
import me.sirsam.trolls.guis.GuiManager
import me.sirsam.trolls.items.ItemEvents
import me.sirsam.trolls.items.ItemRecipes
import me.sirsam.trolls.listeners.OnChat
import me.sirsam.trolls.listeners.OnJoin
import me.sirsam.trolls.listeners.OnLeave
import me.sirsam.trolls.listeners.OnUse
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
        getCommand("tjump")?.setExecutor(Jump())
        getCommand("tfly")?.setExecutor(Fly())
        getCommand("tsudo")?.setExecutor(Sudo())
        getCommand("tgodmode")?.setExecutor(Godmode())
        getCommand("tvanish")?.setExecutor(Vanish())
        getCommand("tfreeze")?.setExecutor(Freeze())
        getCommand("troll")?.setExecutor(Troll())
        getCommand("troll")?.tabCompleter = Troll()
        getCommand("bomber")?.setExecutor(BomberCommand())
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(this,this)
        Bukkit.getPluginManager().registerEvents(ItemEvents(), this)
        Bukkit.getPluginManager().registerEvents(GuiManager(), this)
        Bukkit.getPluginManager().registerEvents(Vanish(), this)
        Bukkit.getPluginManager().registerEvents(OnJoin(), this)
        Bukkit.getPluginManager().registerEvents(OnLeave(), this)
        Bukkit.getPluginManager().registerEvents(OnChat(), this)
        Bukkit.getPluginManager().registerEvents(OnUse(), this)
        Bukkit.getPluginManager().registerEvents(Freeze(), this)
    }
}