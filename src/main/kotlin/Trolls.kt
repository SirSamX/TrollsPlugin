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
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


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
        getCommand("freeze")?.setExecutor(Freeze())
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

    @EventHandler
    fun playerDamageEvent(event: EntityDamageEvent) {
        if (event.entity is Player) {
            (event.entity as Player).addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, 999, 3))
            (event.entity as Player).addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 999, 3))
        }
    }
}