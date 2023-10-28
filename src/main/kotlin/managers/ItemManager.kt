package me.sirsam.trolls.managers

import me.sirsam.trolls.core.item.Head
import me.sirsam.trolls.core.item.Item
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.Ability
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
                abilities = listOf(Ability("Throw TNT", AbilityType.RIGHT_CLICK, oneTimeUse = true)),
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
                abilities = listOf(Ability("Throw Fireball", AbilityType.RIGHT_CLICK, oneTimeUse = true)),
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
                abilities = listOf(Ability("Throw Sword!", AbilityType.RIGHT_CLICK)),
            )
        )
    ),
    SHOOTY_BOX(
        Item(
            ItemProperties(
                id = "shooty_box",
                material = Material.DISPENSER,
                name = "Shooty Box",
                abilities = listOf(Ability("Shoot!", AbilityType.RIGHT_CLICK), Ability("Open inventory", AbilityType.SHIFT_RIGHT_CLICK)),
                rarity = Rarity.UNFINISHED
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
                abilities = listOf(Ability("Cast spell", AbilityType.RIGHT_CLICK), Ability("Switch ability", AbilityType.LEFT_CLICK)),
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
                abilities = listOf(Ability("Play Music!", AbilityType.RIGHT_CLICK)),
                rarity = Rarity.RARE
            )
        )
    ),
    SHURIKEN(
        Item(
            ItemProperties(
                id = "shuriken",
                material = Material.NETHER_STAR,
                name = "Shuriken",
                abilities = listOf(Ability("Throw Shuriken", AbilityType.RIGHT_CLICK)),
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
}