package me.sirsam.trolls.items

import me.sirsam.trolls.Trolls
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.RecipeChoice
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.recipe.CraftingBookCategory

class ItemRecipes {
    private val plugin = Trolls.instance
    private val discoverRecipes = NamespacedKey(plugin, "recipe")

    fun unlockRecipes(player: Player) {
        player.discoverRecipes(setOf(discoverRecipes))
    }

    fun registerRecipes() {
        val jungleAxe = ShapedRecipe(discoverRecipes, ItemManager.JUNGLE_AXE.item.item())
        jungleAxe.shape(
            " WW",
            " SW",
            " S "
        )
        jungleAxe.setIngredient("W"[0], RecipeChoice.ExactChoice(ItemManager.COMPRESSED_JUNGLE_WOOD.item.item()))
        jungleAxe.setIngredient("S"[0], Material.STICK)
        jungleAxe.category = CraftingBookCategory.MISC
        Bukkit.addRecipe(jungleAxe)
    }
}