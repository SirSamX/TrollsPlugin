package me.sirsam.trolls.items

import org.bukkit.Material

class ItemManager{
    val items = listOf(
        TrollItem("grappling_hook", Material.FISHING_ROD, "Grappling Hook", "Travel in style...", "", TrollRarity.RARE),
        TrollItem("throwable_tnt", Material.TNT, "Throwable TNT", "Right-click to Throw TNT.", "Don't grief!", TrollRarity.UNCOMMON, "TNT", true, null, true),
        TrollItem("explosive_bow", Material.BOW, "Explosive Bow", "Shoot to create an explosion.", "", TrollRarity.LEGENDARY, "Bow")
    )
}