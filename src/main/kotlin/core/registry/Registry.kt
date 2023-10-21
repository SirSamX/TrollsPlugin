package me.sirsam.trolls.core.registry

import me.sirsam.trolls.core.item.Item
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.recipes.Recipe
import me.sirsam.trolls.core.item.recipes.RecipeItem
import java.net.URL


/**
 * [Item], [AbilityItem], [RecipeItem] and [Recipe] need to get registered to show up in the GUIs.
 * Do not modify any of the values, or it could cause errors!
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object Registry {
    val items = mutableListOf<Item>()
    val recipeItems = mutableListOf<RecipeItem>()
    val abilityItems = mutableListOf<AbilityItem>()
    val recipes = mutableListOf<Recipe>()
    val heads = mutableListOf<URL>()

    fun register(item: Item) {
        registerItem(item)
    }

    fun register(item: RecipeItem) {
        recipeItems.add(item)
        registerItem(item)
    }

    fun register(item: AbilityItem) {
        abilityItems.add(item)
        registerItem(item)
    }
    fun register(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun register(skin: URL?) {
        heads.add(skin ?: throw NullPointerException("Skin must not be null!"))
    }

    private fun registerItem(item: Item) {
        items.add(item)
    }
}