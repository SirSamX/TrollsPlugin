package me.sirsam.trolls

import io.papermc.paper.event.player.AsyncChatEvent
import me.sirsam.trolls.commands.*
import me.sirsam.trolls.helpers.Ranks
import me.sirsam.trolls.items.ItemEvents
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random


class Trolls : JavaPlugin(), Listener {
    companion object {
        lateinit var instance: Trolls

        fun getPlugin(): Trolls {
            return instance
        }
    }

    override fun onEnable() {
        instance = this

        logger.info("Plugin enabled!")
        registerCommands()
        registerEvents()
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
        getCommand("quest")?.setExecutor(Quest())
        getCommand("vanish")?.setExecutor(Vanish())
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(this,this)
        Bukkit.getPluginManager().registerEvents(ItemEvents(), this)
        Bukkit.getPluginManager().registerEvents(GuiManager(), this)
        Bukkit.getPluginManager().registerEvents(Vanish(), this)
    }

    fun randomPercentage(percentage: Int, scale: Int = 1): Boolean {
        val r = Random.nextInt(100 * scale)
        return r < percentage * scale
    }

    @EventHandler
    fun joinEvent(event: PlayerJoinEvent) {
        val p = event.player
        val playerCount = Bukkit.getOnlinePlayers().size
        p.sendMessage(Component.text("Welcome back ${p.name}!\nThere are currently ${playerCount - 1} other players online.", NamedTextColor.BLUE))

        val header = Component.text("GAMING LEGENDEN SERVER", NamedTextColor.RED)
        val footer = Component.text("1.20 Trails and Tails", NamedTextColor.GREEN)
        p.sendPlayerListHeaderAndFooter(header, footer)

        fun setRank(rank: Ranks, p: Player) {
            val name = Component.text(rank.prefix, rank.color).append(p.name().append(Component.text(rank.suffix)))
            p.displayName(name)
            p.playerListName(name)
            p.customName(name)
        }

        when (p.name) {
            "niceleumas" -> {
                p.isOp = true
                setRank(Ranks.OWNER, p)
            }
            "Blueberry1873" -> {
                setRank(Ranks.ADMIN, p)
            }
            "EnderMo23" -> {
                setRank(Ranks.ADMIN, p)
            }
            "RedstoneKaiser" -> {
                setRank(Ranks.ADMIN, p)
            }
            "hbjju" -> {
                setRank(Ranks.ADMIN, p)
            }
            "xTHEscienceCATx" -> {
                setRank(Ranks.MODERATOR, p)
            }
            else -> setRank(Ranks.PLAYER, p)
        }
    }

    @EventHandler
    fun openInventories(event: PlayerInteractEvent) {
        val p = event.player
        val m = event.material
        if (event.action !== Action.LEFT_CLICK_AIR) return

        if (m == Material.CRAFTING_TABLE) {
            p.openWorkbench(null, true)
        }
        if (m == Material.ENDER_CHEST) {
            p.openInventory(p.enderChest)
        }
        if (m == Material.ENCHANTING_TABLE) {
            p.openEnchanting(null, true)
        }
        if (m == Material.CARTOGRAPHY_TABLE) {
            p.openCartographyTable(null, true)
        }
        if (m == Material.GRINDSTONE) {
            p.openGrindstone(null, true)
        }
        if (m == Material.LOOM) {
            p.openLoom(null, true)
        }
        if (m == Material.SMITHING_TABLE) {
            p.openSmithingTable(null, true)
        }
        if (m == Material.STONECUTTER) {
            p.openStonecutter(null, true)
        }
        if (m == Material.ANVIL) {
            if (randomPercentage(12)) {
                p.inventory.setItem(p.inventory.heldItemSlot, ItemStack(Material.CHIPPED_ANVIL))
                p.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 1f, 1f)
            }
            p.openAnvil(null, true)
        }
        if (m == Material.CHIPPED_ANVIL) {
            if (randomPercentage(12)) {
                p.inventory.setItem(p.inventory.heldItemSlot, ItemStack(Material.DAMAGED_ANVIL))
                p.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 1f, 1f)
            }
            p.openAnvil(null, true)

        }
        if (m == Material.DAMAGED_ANVIL) {
            if (randomPercentage(12)) {
                p.inventory.setItem(p.inventory.heldItemSlot, ItemStack(Material.AIR))
                p.playSound(p.location, Sound.BLOCK_ANVIL_DESTROY, 1f, 1f)
            }
            else p.openAnvil(null, true)
        }
    }

    @EventHandler
    fun onChatEvent(event: AsyncChatEvent) {
        var message = PlainTextComponentSerializer.plainText().serialize(event.message())
        if (message.contains(":yin_yang:", true)) {
            message = message.replace(":yin_yang:", "☯", true)
        }
        event.message(PlainTextComponentSerializer.plainText().deserialize(message))
    }
}