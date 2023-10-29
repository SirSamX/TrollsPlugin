package me.sirsam.trolls.managers

import me.sirsam.trolls.core.Main
import me.sirsam.trolls.core.item.Ingredient
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.recipe.CraftingBookCategory

sealed class IngredientManager {
    class CompressedJungleWood : Ingredient(ItemProperties(
        id = "compressed_jungle_wood",
        material = Material.JUNGLE_LOG,
        name = "Compressed Jungle Log",
        rarity = Rarity.COMMON,
        raritySuffix = "INGREDIENT",
        stackable = true,
        glint = true
    )) {
        init {
            recipe = ShapedRecipe(NamespacedKey(Main.plugin, id), item()).apply {
                shape(
                    "XXX",
                    "XXX",
                    "XXX"
                )
                setIngredient("X"[0], Material.JUNGLE_WOOD)
                category = CraftingBookCategory.MISC
            }
        }
    }

    class CompressedNetherStar : Ingredient(ItemProperties(
        id = "compressed_nether_star",
        material = Material.NETHER_STAR,
        name = "Compressed Nether Star",
        rarity = Rarity.RARE,
        raritySuffix = "INGREDIENT",
        stackable = true
    )) {
        init {
            recipe = ShapedRecipe(NamespacedKey(Main.plugin, id), item()).apply {
                shape(
                    "XXX",
                    "XXX",
                    "XXX"
                )
                setIngredient("X"[0], Material.NETHER_STAR)
                category = CraftingBookCategory.MISC
            }
        }
    }
}