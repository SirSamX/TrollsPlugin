package me.sirsam.trolls.items

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

class ItemManager{
    val items = listOf(
        TrollItem(
            id = "grappling_hook",
            material = Material.FISHING_ROD,
            name = "Grappling Hook",
            description = "Travel in style...",
            note = null,
            rarity = TrollRarity.RARE
        ),
        TrollItem(
            id = "throwable_tnt",
            material = Material.TNT,
            name = "Throwable TNT",
            note = "Don't grief!",
            rarity = TrollRarity.UNCOMMON,
            raritySuffix = "TNT",
            oneTimeUse = true,
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Throw TNT")
        ),
        TrollItem(
            id = "throwable_fireball",
            material = Material.FIRE_CHARGE,
            name = "Throwable Fireball",
            rarity = TrollRarity.UNCOMMON,
            raritySuffix = "FIREBALL",
            oneTimeUse = true,
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Throw Fireball")
        ),
        TrollItem(
            id = "explosive_bow",
            material = Material.BOW,
            name = "Explosive Bow",
            description = "Shoot to create an explosion.",
            note = null,
            rarity = TrollRarity.LEGENDARY,
            raritySuffix = "BOW"
        ),
        TrollItem(
            id = "pogeroni_sword",
            material = Material.NETHERITE_SWORD,
            name = "Pogeroni Sword",
            description = "Fickt Emanuel.",
            note = "Hallo",
            rarity = TrollRarity.RARE,
            raritySuffix = "SWORD",
            enchantments = mutableMapOf(Enchantment.DAMAGE_ALL to 5, Enchantment.DURABILITY to 3),
            unbreakable = false
        ),
        TrollItem(
            id = "shooty_box",
            material = Material.DISPENSER,
            name = "Shooty Box",
            description = "Shift + Right-click to open GUI. Right click to shoot.", rarity = TrollRarity.UNFINISHED
        ),
        TrollItem(
            id = "terminator",
            material = Material.BOW,
            name = "Terminator",
            description = "Left-click to shoot 3 arrows.",
            note = null,
            rarity = TrollRarity.MYTHIC,
            raritySuffix = "BOW"
        ),
        TrollItem(
            id = "treecapitator",
            material = Material.GOLDEN_AXE,
            name = "Treecapitator",
            description = "Break wood to cut down a whole tree.",
            note = "Blocks: 50, Delay: 1 Ticks",
            rarity = TrollRarity.EPIC,
            raritySuffix = "AXE"
        ),
        TrollItem(
            id = "jungle_axe",
            material = Material.WOODEN_AXE,
            name = "Jungle Axe",
            description = "Break wood to cut down a whole tree.",
            note = "Blocks: 25, Delay: 3 Tick",
            rarity = TrollRarity.UNCOMMON,
            raritySuffix = "AXE"
        ),
        TrollItem(
            id = "magical_wand",
            material = Material.BLAZE_ROD,
            name = "Magical Wand",
            description = "Left-click to switch modes. Right-click to cast spell.",
            note = "TestItem",
            rarity = TrollRarity.ADMIN,
            raritySuffix = "WAND"
        )
    )
}