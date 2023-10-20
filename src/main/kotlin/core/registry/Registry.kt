package me.sirsam.trolls.core.registry

import core.item.recipes.RecipeItem
import core.item.recipes.Recipe
import me.sirsam.trolls.core.item.TrollItem
import me.sirsam.trolls.core.item.abilities.AbilityItem


/**
 * [TrollItem], [AbilityItem], [RecipeItem] and [Recipe] need to get registered to show up in the GUIs.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object Registry {
    private val items = mutableMapOf<Identifier, TrollItem>()
    private val recipeItems = mutableMapOf<Identifier, RecipeItem>()
    private val abilityItems = mutableMapOf<Identifier, AbilityItem>()
    private val recipes = mutableMapOf<Identifier, Recipe>()


    fun register(identifier: Identifier, item: TrollItem) {
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

    private fun registerItem(identifier: Identifier, item: TrollItem) {
        items[identifier] = item
    }
}