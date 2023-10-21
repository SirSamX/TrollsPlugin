package me.sirsam.trolls.core.item

import me.sirsam.trolls.core.helper.Utils
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityType
import me.sirsam.trolls.core.item.recipes.RecipeItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*

/**
 * Only use if you know how to use this!
 * Use [AbilityItem] or [RecipeItem] instead.
 */
open class Item(properties: ItemProperties) {
    private val id = properties.id
    private val material = properties.material
    private val name = properties.name
    private val description = properties.description
    private val note = properties.note
    private val rarity = properties.rarity
    private val raritySuffix = properties.raritySuffix
    private val stackable = properties.stackable
    private val enchantments = properties.enchantments
    private val abilities = properties.abilities
    private val oneTimeUse = properties.oneTimeUse
    private val unbreakable = properties.unbreakable
    private val customModelData = properties.customModelData
    private val headTexture = properties.headTexture
    private val color = properties.color

    fun item(): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        val data = meta.persistentDataContainer

        data.set(Utils.ID_KEY, PersistentDataType.STRING, id)

        data.set(Utils.STACKABLE_KEY, PersistentDataType.BOOLEAN, stackable)
        if (!stackable) { data.set(Utils.UNSTACKABLE_KEY, PersistentDataType.STRING, UUID.randomUUID().toString()) }
        meta.displayName(nameComponent())
        meta.lore(addLore())
        meta.isUnbreakable = unbreakable
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)

        if (enchantments != null) { item.addUnsafeEnchantments(enchantments) }
        if (customModelData != null) { item.itemMeta.setCustomModelData(customModelData) }
        if (headTexture != null) {
            Head.valueOf(headTexture.name).setTexture(meta)
        }

        item.setItemMeta(meta)
        return item
    }

    fun id(): String { return id }

    fun name(): String { return name }

    fun nameComponent(colored: Boolean = true): Component {
        return if (colored) {
            if (color != null) {
                Component.text(name, color).decoration(TextDecoration.ITALIC, false)
            }
            Component.text(name, rarity.color).decoration(TextDecoration.ITALIC, false)
        }
        else Component.text(name)
    }

    private fun addLore(): MutableList<Component> {
        val lore = mutableListOf<Component>()

        if (rarity === Rarity.UNFINISHED) {
            lore.add(Component.text("§cThis item is UNFINISHED"))
            lore.add(Component.text("§cIt may not perform as expected"))
            lore.add(Component.text(""))
        }

        if (!description.isNullOrBlank()) {
            lore.add(Component.text("$description", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false))
            lore.add(Component.text(""))
        }

        if (!abilities.isNullOrEmpty()) {
            for (ability in abilities) {
                val type = ability.key.toString().replace("_", " ")
                val name = ability.value
                when (ability.key) {
                    AbilityType.RIGHT_CLICK -> { lore.add(Component.text("Item Ability: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    AbilityType.LEFT_CLICK -> { lore.add(Component.text("Item Ability: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    AbilityType.SHIFT_RIGHT_CLICK -> { lore.add(Component.text("Item Ability: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    AbilityType.SHIFT_LEFT_CLICK -> { lore.add(Component.text("Item Ability: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    AbilityType.FULL_SET_BONUS -> { lore.add(Component.text("Set Bonus: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    AbilityType.PIECE_BONUS -> { lore.add(Component.text("Piece Bonus: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                }
            }
            lore.add(Component.text(""))
        }

        if (!note.isNullOrBlank()) {
            lore.add(Component.text("$note", NamedTextColor.DARK_GRAY))
            lore.add(Component.text(""))

        }

        if (oneTimeUse) {
            lore.add(Component.text("(consumed on use)", NamedTextColor.DARK_GRAY).decoration(TextDecoration.ITALIC, false))
            lore.add(Component.text(""))
        }

        lore.add(Component.text(rarity.toString().replace("_", " ") + " " + raritySuffix.uppercase(), rarity.color).decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false))

        return lore
    }
}