package me.sirsam.trolls.items

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

class ItemManager{
    val items = listOf(
        TrollItem(//0
            id = "grappling_hook",
            material = Material.FISHING_ROD,
            name = "Grappling Hook",
            description = "Travel in style...",
            note = null,
            rarity = TrollRarity.RARE
        ),
        TrollItem(//1
            id = "throwable_tnt",
            material = Material.TNT,
            name = "Throwable TNT",
            note = "Don't grief!",
            rarity = TrollRarity.UNCOMMON,
            raritySuffix = "TNT",
            oneTimeUse = true,
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Throw TNT"),
            stackable = true
        ),
        TrollItem(//2
            id = "throwable_fireball",
            material = Material.FIRE_CHARGE,
            name = "Throwable Fireball",
            rarity = TrollRarity.UNCOMMON,
            raritySuffix = "FIREBALL",
            oneTimeUse = true,
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Throw Fireball"),
            stackable = true
        ),
        TrollItem(//3
            id = "explosive_bow",
            material = Material.BOW,
            name = "Explosive Bow",
            description = "Shoot to create an explosion.",
            note = null,
            rarity = TrollRarity.LEGENDARY,
            raritySuffix = "BOW"
        ),
        TrollItem(//4
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
        TrollItem(//5
            id = "shooty_box",
            material = Material.DISPENSER,
            name = "Shooty Box",
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Shoot!", TrollAbility.SHIFT_RIGHT_CLICK to "Open inventory"),
            rarity = TrollRarity.UNFINISHED
        ),
        TrollItem(//6
            id = "terminator",
            material = Material.BOW,
            name = "Terminator",
            abilities = mapOf(TrollAbility.LEFT_CLICK to "Shoot 3 arrows!"),
            note = "Infinite arrows!",
            rarity = TrollRarity.MYTHIC,
            raritySuffix = "BOW"
        ),
        TrollItem(//7
            id = "treecapitator",
            material = Material.GOLDEN_AXE,
            name = "Treecapitator",
            description = "Break wood to cut down a whole tree.",
            note = "Blocks: 50, Delay: 1 Ticks",
            rarity = TrollRarity.EPIC,
            raritySuffix = "AXE"
        ),
        TrollItem(//8
            id = "jungle_axe",
            material = Material.WOODEN_AXE,
            name = "Jungle Axe",
            description = "Break wood to cut down a whole tree.",
            note = "Blocks: 25, Delay: 3 Tick",
            rarity = TrollRarity.UNCOMMON,
            raritySuffix = "AXE"
        ),
        TrollItem(//9
            id = "magical_wand",
            material = Material.BLAZE_ROD,
            name = "Magical Wand",
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Cast spell", TrollAbility.LEFT_CLICK to "Switch ability"),
            note = "TestItem",
            rarity = TrollRarity.ADMIN,
            raritySuffix = "WAND"
        )
    )
    val ingredients = listOf(
        TrollItem(//0
            id = "compressed_jungle_wood",
            material = Material.JUNGLE_LOG,
            name = "Compressed Jungle Log",
            rarity = TrollRarity.COMMON,
            raritySuffix = "INGREDIENT",
            stackable = true
        )
    )
}