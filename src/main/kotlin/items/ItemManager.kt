package me.sirsam.trolls.items

import org.bukkit.Material

class ItemManager{
    val items = listOf(
        TrollItem("grappling_hook", Material.FISHING_ROD, "Grappling Hook", "Travel in style...", "", TrollRarity.UNCOMMON),
        TrollItem("throwable_tnt", Material.TNT, "Throwable TNT", "Right-click to Throw TNT.", "Don't grief!", TrollRarity.RARE, "TNT", true, null, true)
    )
}