package me.sirsam.trolls.core.item

/**
 * For items used in recipes.
 */
open class Ingredient(properties: ItemProperties) : Item(properties) {
    init {
        if (properties.raritySuffix.isEmpty()) {
            properties.raritySuffix = "INGREDIENT"
        }
    }
}