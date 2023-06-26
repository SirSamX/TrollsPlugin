package me.sirsam.trolls.items

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ItemManager{
    fun grapplingHook(): ItemStack { return TrollItem("grappling_hook", Material.FISHING_ROD, "Grappling Hook", "Travel in style...", "", TrollRarity.UNCOMMON).createItem() }
    fun throwableTNT(): ItemStack { return TrollItem("grappling_hook", Material.TNT, "Throwable TNT", "Right-click to Throw TNT.", "Don't grief!", TrollRarity.RARE, "TNT", true, null, true).createItem() }
    val items = listOf(
        TrollItem("grappling_hook", Material.FISHING_ROD, "Grappling Hook", "Travel in style...", "", TrollRarity.UNCOMMON).createItem(),
        TrollItem("grappling_hook", Material.TNT, "Throwable TNT", "Right-click to Throw TNT.", "Don't grief!", TrollRarity.RARE, "TNT", true, null, true).createItem()
    )
}