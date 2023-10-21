package me.sirsam.trolls.core.registry

import me.sirsam.trolls.core.item.recipes.RecipeItem
import me.sirsam.trolls.core.item.recipes.Recipe
import me.sirsam.trolls.core.item.TrollItem
import me.sirsam.trolls.core.item.abilities.AbilityItem


/**
 * [TrollItem], [AbilityItem], [RecipeItem] and [Recipe] need to get registered to show up in the GUIs.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object Registry {
    val items = mutableMapOf<Identifier, TrollItem>()
    val recipeItems = mutableMapOf<Identifier, RecipeItem>()
    val abilityItems = mutableMapOf<Identifier, AbilityItem>()
    val recipes = mutableMapOf<Identifier, Recipe>()

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