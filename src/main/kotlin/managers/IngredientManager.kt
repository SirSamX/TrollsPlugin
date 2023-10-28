package me.sirsam.trolls.managers

import me.sirsam.trolls.core.item.Ingredient
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import org.bukkit.Material

sealed class IngredientManager {
    class CompressedJungleWood : Ingredient(ItemProperties(
            id = "compressed_jungle_wood",
            material = Material.JUNGLE_LOG,
            name = "Compressed Jungle Log",
            rarity = Rarity.COMMON,
            stackable = true
    ))

    class CompressedNetherStar : Ingredient(ItemProperties(
        id = "compressed_nether_star",
        material = Material.NETHER_STAR,
        name = "Compressed Nether Star",
        rarity = Rarity.RARE,
        raritySuffix = "INGREDIENT",
        stackable = true
    ))
}