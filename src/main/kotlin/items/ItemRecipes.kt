package me.sirsam.trolls.items

import me.sirsam.trolls.Trolls
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.RecipeChoice
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.recipe.CraftingBookCategory

class ItemRecipes {
    private val plugin = Trolls.getPlugin()
    private val items = ItemManager().items
    private val ingredients = ItemManager().ingredients
    private val discoverRecipes = NamespacedKey(plugin, "recipe")

    fun unlockRecipes(player: Player) {
        player.discoverRecipes(setOf(discoverRecipes))
    }

    fun registerRecipes() {
        val expBottle = ShapedRecipe(discoverRecipes, items[8].createItem())
        expBottle.shape(
            " WW",
            " SW",
            " S "
        )
        expBottle.setIngredient("W"[0], RecipeChoice.ExactChoice(ingredients[0].createItem()))
        expBottle.setIngredient("S"[0], Material.STICK)
        expBottle.category = CraftingBookCategory.MISC
        Bukkit.addRecipe(expBottle)
    }
}