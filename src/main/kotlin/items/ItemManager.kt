package me.sirsam.trolls.items

import org.bukkit.Material

class ItemManager{
    val items = listOf(
        TrollItem("grappling_hook", Material.FISHING_ROD, "Grappling Hook", "Travel in style...", "", TrollRarity.RARE),
        TrollItem("throwable_tnt", Material.TNT, "Throwable TNT", "Right-click to Throw TNT.", "Don't grief!", TrollRarity.UNCOMMON, "TNT", true, null, true),
        TrollItem("explosive_bow", Material.BOW, "Explosive Bow", "Shoot to create an explosion.", "", TrollRarity.LEGENDARY, "Bow"),
        TrollItem("leap", Material.FEATHER, "Leap", "Right-click to launch you in the direction you're' looking.", "", TrollRarity.EPIC),
        TrollItem("pogeroni_sword", Material.NETHERITE_SWORD, "Pogeroni Sword", "Fickt Emanuel.", "Hallo", TrollRarity.RARE, "Sword")
    )
}