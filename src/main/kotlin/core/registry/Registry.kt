package me.sirsam.trolls.core.registry

import me.sirsam.trolls.core.item.Item
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.recipes.Recipe
import me.sirsam.trolls.core.item.recipes.RecipeItem
import java.net.URL


/**
 * [Item], [AbilityItem], [RecipeItem] and [Recipe] need to get registered to show up in the GUIs.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object Registry {
    private val items = mutableMapOf<Identifier, Item>()
    private val recipeItems = mutableMapOf<Identifier, RecipeItem>()
    private val abilityItems = mutableMapOf<Identifier, AbilityItem>()
    private val recipes = mutableMapOf<Identifier, Recipe>()
    private val heads = mutableMapOf<Identifier, URL>()

    fun register(identifier: Identifier, item: Item) {
        registerItem(identifier, item)
    }

    fun register(identifier: Identifier, item: RecipeItem) {
        recipeItems[identifier] = item
        registerItem(identifier, item)
    }

    fun register(identifier: Identifier, item: AbilityItem) {
        abilityItems[identifier] = item
        registerItem(identifier, item)
    }
    fun register(identifier: Identifier, recipe: Recipe) {
        recipes[identifier] = recipe
    }

    fun register(identifier: Identifier, skin: URL?) {
        heads[identifier] = skin ?: throw NullPointerException("Skin must not be null!")
    }

    private fun registerItem(identifier: Identifier, item: Item) {
        items[identifier] = item
    }
}