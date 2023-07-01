package me.sirsam.trolls.items

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

class ItemManager{
    val items = listOf(
        TrollItem("grappling_hook", Material.FISHING_ROD, "Grappling Hook", "Travel in style...", null, TrollRarity.RARE),
        TrollItem("throwable_tnt", Material.TNT, "Throwable TNT", "Right-click to Throw TNT.", "Don't grief!", TrollRarity.UNCOMMON, "TNT", oneTimeUse = true),
        TrollItem("throwable_fireball", Material.FIRE_CHARGE, "Throwable Fireball", "Right-click to throw a Fireball.", null, TrollRarity.UNCOMMON, "FIREBALL", oneTimeUse = true),
        TrollItem("explosive_bow", Material.BOW, "Explosive Bow", "Shoot to create an explosion.", null, TrollRarity.LEGENDARY, "BOW"),
        //TrollItem("leap", Material.FEATHER, "Leap", "Right-click to launch you in the direction you're' looking.", rarity = TrollRarity.EPIC),         ### NOT WORKING BECAUSE OF LAGGING BACK WHEN USING ABILITY! ###
        TrollItem("pogeroni_sword", Material.NETHERITE_SWORD, "Pogeroni Sword", "Fickt Emanuel.", "Hallo", TrollRarity.RARE, "SWORD", enchantments = mutableMapOf(Enchantment.DAMAGE_ALL to 5, Enchantment.DURABILITY to 3), unbreakable = false),
        TrollItem("shooty_box", Material.DISPENSER, "Shooty Box", "Shift + Right-click to open GUI. Right click to shoot.", rarity = TrollRarity.UNFINISHED),
        TrollItem("terminator", Material.BOW, "Terminator", "Left-click to shoot 5 arrows.", null, TrollRarity.MYTHIC, "BOW")
    )
}