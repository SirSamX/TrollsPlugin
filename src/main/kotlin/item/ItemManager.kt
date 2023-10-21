package me.sirsam.trolls.item

import me.sirsam.trolls.core.item.Head
import me.sirsam.trolls.core.item.Item
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityType
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

enum class ItemManager(val item: Item) {
    GRAPPLING_HOOK(
        Item(
            ItemProperties(
                id = "grappling_hook",
                material = Material.FISHING_ROD,
                name = "Grappling Hook",
                description = "Travel in style...",
                note = null,
                rarity = Rarity.RARE
            )
        )
    ),
    THROWABLE_TNT(
        Item(
            ItemProperties(
                id = "throwable_tnt",
                material = Material.TNT,
                name = "Throwable TNT",
                note = "Don't grief!",
                rarity = Rarity.UNCOMMON,
                raritySuffix = "TNT",
                oneTimeUse = true,
                abilities = mapOf(AbilityType.RIGHT_CLICK to "Throw TNT"),
                stackable = true
            )
        )
    ),

    THROWABLE_FIREBALL(
        Item(
            ItemProperties(
                id = "throwable_fireball",
                material = Material.FIRE_CHARGE,
                name = "Throwable Fireball",
                rarity = Rarity.UNCOMMON,
                raritySuffix = "FIREBALL",
                oneTimeUse = true,
                abilities = mapOf(AbilityType.RIGHT_CLICK to "Throw Fireball"),
                stackable = true
            )
        )
    ),
    EXPLOSIVE_BOW(
        Item(
            ItemProperties(
                id = "explosive_bow",
                material = Material.BOW,
                name = "Explosive Bow",
                description = "Shoot to create an explosion.",
                note = null,
                rarity = Rarity.LEGENDARY,
                raritySuffix = "BOW"
            )
        )
    ),
    POGERONI_SWORD(
        Item(
            ItemProperties(
                id = "pogeroni_sword",
                material = Material.NETHERITE_SWORD,
                name = "Pogeroni Sword",
                description = "Fickt Emanuel.",
                note = "Hallo",
                rarity = Rarity.LEGENDARY,
                raritySuffix = "SWORD",
                enchantments = mutableMapOf(Enchantment.FIRE_ASPECT to 5, Enchantment.DURABILITY to 3),
                abilities = mapOf(AbilityType.RIGHT_CLICK to "Throw Sword!"),
                unbreakable = true
            )
        )
    ),
    SHHOTY_BOX(
        Item(
            ItemProperties(
                id = "shooty_box",
                material = Material.DISPENSER,
                name = "Shooty Box",
                abilities = mapOf(AbilityType.RIGHT_CLICK to "Shoot!", AbilityType.SHIFT_RIGHT_CLICK to "Open inventory"),
                rarity = Rarity.UNFINISHED
            )
        )
    ),
    TERMINATOR(
        Item(
            ItemProperties(
                id = "terminator",
                material = Material.BOW,
                name = "Terminator",
                abilities = mapOf(AbilityType.LEFT_CLICK to "Shoot 3 arrows!"),
                note = "Infinite arrows!",
                rarity = Rarity.MYTHIC,
                raritySuffix = "BOW"
            )
        )
    ),
    TREECAPITATOR(
        Item(
            ItemProperties(
                id = "treecapitator",
                material = Material.GOLDEN_AXE,
                name = "Treecapitator",
                description = "Break wood to cut down a whole tree.",
                note = "Blocks: 50, Delay: 1 Ticks",
                rarity = Rarity.EPIC,
                raritySuffix = "AXE"
            )
        )
    ),
    JUNGLE_AXE(
        Item(
            ItemProperties(
                id = "jungle_axe",
                material = Material.WOODEN_AXE,
                name = "Jungle Axe",
                description = "Break wood to cut down a whole tree.",
                note = "Blocks: 25, Delay: 3 Tick",
                rarity = Rarity.UNCOMMON,
                raritySuffix = "AXE"
            )
        )
    ),
    MAGICAL_WAND(
        Item(
            ItemProperties(
                id = "magical_wand",
                material = Material.BLAZE_ROD,
                name = "Magical Wand",
                abilities = mapOf(AbilityType.RIGHT_CLICK to "Cast spell", AbilityType.LEFT_CLICK to "Switch ability"),
                note = "TestItem",
                rarity = Rarity.ADMIN,
                raritySuffix = "WAND"
            )
        )
    ),
    JUKEBOX(
        Item(
            ItemProperties(
                id = "jukebox",
                material = Material.JUKEBOX,
                name = "Jukebox",
                abilities = mapOf(AbilityType.RIGHT_CLICK to "Play Music!"),
                rarity = Rarity.RARE
            )
        )
    ),
    SHURIKEN(
        Item(
            ItemProperties(
                id = "shuriken",
                material = Material.IRON_SWORD,
                name = "Shuriken",
                abilities = mapOf( AbilityType.RIGHT_CLICK to "Throw Shuriken"),
                rarity = Rarity.UNCOMMON,
                raritySuffix = "SHURIKEN",
                customModelData = 69001
            )
        )
    ),
    PIRATE_HAT(
        Item(
            ItemProperties(
                id = "pirate_hat",
                material = Material.CARVED_PUMPKIN,
                name = "Pirate Hat",
                rarity = Rarity.UNCOMMON,
                raritySuffix = "HAT",
                customModelData = 690003
            )
        )
    ),
    COMPRESSED_JUNGLE_WOOD(
        Item(
            ItemProperties(
                id = "compressed_jungle_wood",
                material = Material.JUNGLE_LOG,
                name = "Compressed Jungle Log",
                rarity = Rarity.COMMON,
                raritySuffix = "INGREDIENT",
                stackable = true
            )
        )
    ),
    COMPRESSED_NETHER_STAR(
        Item(
            ItemProperties(
                id = "compressed_nether_star",
                material = Material.NETHER_STAR,
                name = "Compressed Nether Star",
                rarity = Rarity.RARE,
                raritySuffix = "INGREDIENT",
                stackable = true
            )
        ),
    ),
    CHICKZOOKA(
        Item(
            ItemProperties(
                id = "chickzooka",
                material = Material.PLAYER_HEAD,
                name = "Chickzooka",
                rarity = Rarity.SPECIAL,
                headTexture = Head.CHICKEN,
                stackable = false
            )
        )
    ),
    CRAFTYPHONE_X(
        Item(
            ItemProperties(
                id = "craftyphone_x",
                material = Material.PLAYER_HEAD,
                name = "Craftyphone",
                headTexture = Head.PHONE_BLACK,
                stackable = false
            )
        )
    ),
    COLORED_CRAFTYCASE(
        Item(
            ItemProperties(
                id = "colored_craftycase",
                material = Material.PLAYER_HEAD,
                name = "Colored Craftycase",
                headTexture = Head.PHONE_BLACK,
                stackable = false
            )
        )
    )
}