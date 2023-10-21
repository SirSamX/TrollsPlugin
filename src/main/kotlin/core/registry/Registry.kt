package me.sirsam.trolls.core.registry

import me.sirsam.trolls.core.item.Item
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.recipes.Recipe
import me.sirsam.trolls.core.item.recipes.RecipeItem
import org.bukkit.profile.PlayerTextures


/**
 * [Item], [AbilityItem], [RecipeItem] and [Recipe] need to get registered to show up in the GUIs.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object Registry {
    val items = mutableMapOf<Identifier, Item>()
    val recipeItems = mutableMapOf<Identifier, RecipeItem>()
    val abilityItems = mutableMapOf<Identifier, AbilityItem>()
    val recipes = mutableMapOf<Identifier, Recipe>()
    val heads = mutableMapOf<Identifier, PlayerTextures>()

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

    fun register(identifier: Identifier, headTextures: PlayerTextures) {
        heads[identifier] = headTextures
    }

    private fun registerItem(identifier: Identifier, item: Item) {
        items[identifier] = item
    }
}