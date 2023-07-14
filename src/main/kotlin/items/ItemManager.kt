package me.sirsam.trolls.items

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

enum class ItemManager(val item: TrollItem) {
    GRAPPLING_HOOK(
        TrollItem(
            id = "grappling_hook",
            material = Material.FISHING_ROD,
            name = "Grappling Hook",
            description = "Travel in style...",
            note = null,
            rarity = TrollRarity.RARE
        )
    ),
    THROWABLE_TNT(
        TrollItem(
            id = "throwable_tnt",
            material = Material.TNT,
            name = "Throwable TNT",
            note = "Don't grief!",
            rarity = TrollRarity.UNCOMMON,
            raritySuffix = "TNT",
            oneTimeUse = true,
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Throw TNT"),
            stackable = true
        )
    ),

    THROWABLE_FIREBALL(
        TrollItem(
            id = "throwable_fireball",
            material = Material.FIRE_CHARGE,
            name = "Throwable Fireball",
            rarity = TrollRarity.UNCOMMON,
            raritySuffix = "FIREBALL",
            oneTimeUse = true,
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Throw Fireball"),
            stackable = true
        )
    ),
    EXPLOSIVE_BOW(
        TrollItem(
            id = "explosive_bow",
            material = Material.BOW,
            name = "Explosive Bow",
            description = "Shoot to create an explosion.",
            note = null,
            rarity = TrollRarity.LEGENDARY,
            raritySuffix = "BOW"
        )
    ),
    POGERONI_SWORD(
        TrollItem(
            id = "pogeroni_sword",
            material = Material.NETHERITE_SWORD,
            name = "Pogeroni Sword",
            description = "Fickt Emanuel.",
            note = "Hallo",
            rarity = TrollRarity.RARE,
            raritySuffix = "SWORD",
            enchantments = mutableMapOf(Enchantment.FIRE_ASPECT to 5, Enchantment.DURABILITY to 3),
            unbreakable = false

        )
    ),
    SHHOTY_BOX(
        TrollItem(
            id = "shooty_box",
            material = Material.DISPENSER,
            name = "Shooty Box",
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Shoot!", TrollAbility.SHIFT_RIGHT_CLICK to "Open inventory"),
            rarity = TrollRarity.UNFINISHED
        )
    ),
    TERMINATOR(
        TrollItem(
            id = "terminator",
            material = Material.BOW,
            name = "Terminator",
            abilities = mapOf(TrollAbility.LEFT_CLICK to "Shoot 3 arrows!"),
            note = "Infinite arrows!",
            rarity = TrollRarity.MYTHIC,
            raritySuffix = "BOW"
        )
    ),
    TREECAPITATOR(
        TrollItem(
            id = "treecapitator",
            material = Material.GOLDEN_AXE,
            name = "Treecapitator",
            description = "Break wood to cut down a whole tree.",
            note = "Blocks: 50, Delay: 1 Ticks",
            rarity = TrollRarity.EPIC,
            raritySuffix = "AXE"
        )
    ),
    JUNGLE_AXE(
        TrollItem(
            id = "jungle_axe",
            material = Material.WOODEN_AXE,
            name = "Jungle Axe",
            description = "Break wood to cut down a whole tree.",
            note = "Blocks: 25, Delay: 3 Tick",
            rarity = TrollRarity.UNCOMMON,
            raritySuffix = "AXE"
        )
    ),
    MAGICAL_WAND(
        TrollItem(
            id = "magical_wand",
            material = Material.BLAZE_ROD,
            name = "Magical Wand",
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Cast spell", TrollAbility.LEFT_CLICK to "Switch ability"),
            note = "TestItem",
            rarity = TrollRarity.ADMIN,
            raritySuffix = "WAND"
        )
    ),
    JUKEBOX(
        TrollItem(
            id = "jukebox",
            material = Material.JUKEBOX,
            name = "Jukebox",
            abilities = mapOf(TrollAbility.RIGHT_CLICK to "Play Music!"),
            rarity = TrollRarity.RARE,
            stackable = false
        )
    ),
    COMPRESSED_JUNGLE_WOOD(
        TrollItem(
            id = "compressed_jungle_wood",
            material = Material.JUNGLE_LOG,
            name = "Compressed Jungle Log",
            rarity = TrollRarity.COMMON,
            raritySuffix = "INGREDIENT",
            stackable = true
        )
    ),
    COMPRESSED_NETHER_STAR(
        TrollItem(
            id = "compressed_nether_star",
            material = Material.NETHER_STAR,
            name = "Compressed Nether Star",
            rarity = TrollRarity.RARE,
            raritySuffix = "INGREDIENT",
            stackable = true
        ),
    ),
}