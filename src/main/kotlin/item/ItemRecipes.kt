package me.sirsam.trolls.item

import me.sirsam.trolls.Trolls
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

class ItemRecipes {
    private val plugin = Trolls.instance
    private val discoverRecipes = NamespacedKey(plugin, "recipe")

    fun unlockRecipes(player: Player) {
        player.discoverRecipes(setOf(discoverRecipes))
    }

    /*fun registerRecipes() {
        val jungleAxe = ShapedRecipe(discoverRecipes, me.sirsam.trolls.item.ItemManager.JUNGLE_AXE.item.item())
        jungleAxe.shape(
            " WW",
            " SW",
            " S "
        )
        jungleAxe.setIngredient("W"[0], RecipeChoice.ExactChoice(me.sirsam.trolls.item.ItemManager.COMPRESSED_JUNGLE_WOOD.item.item()))
        jungleAxe.setIngredient("S"[0], Material.STICK)
        jungleAxe.category = CraftingBookCategory.MISC
        Bukkit.addRecipe(jungleAxe)
    }*/
}