package me.sirsam.trolls.managers

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.items.VacuumCleaner
import org.bukkit.Material
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.recipe.CraftingBookCategory

class RecipeManager {
    fun getRecipes(): List<Recipe> {
        val recipeKey = Utils.RECIPE_KEY
        return listOf<Recipe>(
            ShapedRecipe(recipeKey, VacuumCleaner().item()).apply {
                shape(
                    " X ",
                    " S ",
                    " S "
                )
                setIngredient("S"[0], Material.STICK)
                setIngredient("X"[0], Material.COBWEB)
                category = CraftingBookCategory.EQUIPMENT
            }
        )
    }
}