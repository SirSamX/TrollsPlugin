package me.sirsam.trolls.core.registry

import me.sirsam.trolls.core.item.Ingredient
import me.sirsam.trolls.core.item.Item
import me.sirsam.trolls.core.item.abilities.AbilityItem
import org.bukkit.NamespacedKey
import org.bukkit.inventory.Recipe
import java.net.URL


/**
 * [Item], [AbilityItem], [Ingredient] and [Recipe] need to get registered to show up in the GUIs.
 * Do not modify any of the values, or it could cause errors!
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object Registry {
    val items = mutableListOf<Item>()
    val ingredients = mutableListOf<Ingredient>()
    val abilityItems = mutableListOf<AbilityItem>()
    val recipes = mutableListOf<Recipe>()
    val recipeKeys = mutableListOf<NamespacedKey>()
    val heads = mutableListOf<URL>()

    fun register(item: Item) {
        registerItem(item)
    }

    fun register(item: Ingredient) {
        ingredients.add(item)
        registerItem(item)
    }

    fun register(item: AbilityItem) {
        abilityItems.add(item)
        registerItem(item)
    }
    fun register(recipe: Recipe, key: NamespacedKey) {
        recipes.add(recipe)
        recipeKeys.add(key)
    }

    fun register(skin: URL?) {
        heads.add(skin ?: throw NullPointerException("Skin must not be null!"))
    }

    private fun registerItem(item: Item) {
        items.add(item)
        item.recipe?.let { register(it, item.recipeKey ?: throw NullPointerException("Item with recipe must also have recipeKey!")) }
    }
}