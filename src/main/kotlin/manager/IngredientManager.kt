package me.sirsam.trolls.manager

import me.sirsam.trolls.core.Main
import me.sirsam.trolls.core.item.Head
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
        material = Material.JUNGLE_WOOD,
        name = "Compressed Jungle Wood",
        rarity = Rarity.COMMON,
        stackable = true
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
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
        stackable = true
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
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

    class Filter : Ingredient(ItemProperties(
        id = "filter",
        material = Material.COBWEB,
        name = "Filter",
        rarity = Rarity.RARE,
        stackable = false
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
                shape(
                    "X X",
                    "RSR",
                    "X X"
                )
                setIngredient("X"[0], DoubleCompressedCobweb().item())
                setIngredient("R"[0], ReinforcedStick().item())
                setIngredient("S"[0], Material.STRING)
                category = CraftingBookCategory.MISC
            }
        }
    }

    class CompressedCobweb : Ingredient(ItemProperties(
        id = "compressed_cobweb",
        material = Material.COBWEB,
        name = "Compressed Cobweb",
        rarity = Rarity.UNCOMMON,
        stackable = true
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
                shape(
                    "XXX",
                    "XXX",
                    "XXX"
                )
                setIngredient("X"[0], Material.COBWEB)
                category = CraftingBookCategory.MISC
            }
        }
    }

    class DoubleCompressedCobweb : Ingredient(ItemProperties(
        id = "double_compressed_cobweb",
        material = Material.WHITE_WOOL,
        name = "Double Compressed Cobweb",
        rarity = Rarity.RARE,
        stackable = true
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
                shape(
                    "XXX",
                    "XXX",
                    "XXX"
                )
                setIngredient("X"[0], CompressedCobweb().item())
                category = CraftingBookCategory.MISC
            }
        }
    }

    class CompressedObsidian : Ingredient(ItemProperties(
        id = "compressed_obsidian",
        material = Material.BEDROCK,
        name = "Compressed Obsidian",
        rarity = Rarity.UNCOMMON,
        stackable = true
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
                shape(
                    "XXX",
                    "XXX",
                    "XXX"
                )
                setIngredient("X"[0], Material.OBSIDIAN)
                category = CraftingBookCategory.MISC
            }
        }
    }

    class CompressedStone : Ingredient(ItemProperties(
        id = "compressed_stone",
        material = Material.STONE,
        name = "Compressed Stone",
        rarity = Rarity.COMMON,
        stackable = true
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
                shape(
                    "XXX",
                    "XXX",
                    "XXX"
                )
                setIngredient("X"[0], Material.STONE)
                category = CraftingBookCategory.MISC
            }
        }
    }

    class DoubleCompressedStone : Ingredient(ItemProperties(
        id = "double_compressed_stone",
        material = Material.STONE_BRICKS,
        name = "Double Compressed Stone",
        rarity = Rarity.COMMON,
        stackable = true
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
                shape(
                    "XXX",
                    "XXX",
                    "XXX"
                )
                setIngredient("X"[0], CompressedStone().item())
                category = CraftingBookCategory.MISC
            }
        }
    }

    class TripleCompressedStone : Ingredient(ItemProperties(
        id = "triple_compressed_stone",
        material = Material.SMOOTH_STONE,
        name = "Triple Compressed Stone",
        rarity = Rarity.UNCOMMON,
        stackable = true
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
                shape(
                    "XXX",
                    "XXX",
                    "XXX"
                )
                setIngredient("X"[0], DoubleCompressedStone().item())
                category = CraftingBookCategory.MISC
            }
        }
    }

    class ReinforcedStick : Ingredient(ItemProperties(
        id = "reinforced_stick",
        material = Material.STICK,
        name = "Reinforced Stick",
        rarity = Rarity.EPIC,
        stackable = true
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
                shape(
                    "XNX",
                    "XSX",
                    "XNX"
                )
                setIngredient("X"[0], CompressedObsidian().item())
                setIngredient("S"[0], Material.STICK)
                setIngredient("N"[0], TripleCompressedStone().item())
                category = CraftingBookCategory.MISC
            }
        }
    }

    class ExplosiveEngine : Ingredient(ItemProperties(
        id = "explosive_engine",
        material = Material.PLAYER_HEAD,
        name = "Explosive Engine",
        rarity = Rarity.EPIC,
        stackable = true,
        headTexture = Head.EXPLOSIVE_ENGINE
    )) {
        init {
            recipeKey = NamespacedKey(Main.plugin, id)
            recipe = ShapedRecipe(recipeKey!!, item()).apply {
                shape(
                    "XNX",
                    "XSX",
                    "XNX"
                )
                setIngredient("X"[0], CompressedObsidian().item())
                setIngredient("S"[0], Material.STICK)
                setIngredient("N"[0], TripleCompressedStone().item())
                category = CraftingBookCategory.MISC
            }
        }
    }
}