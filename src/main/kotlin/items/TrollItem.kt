package me.sirsam.trolls.items

import me.sirsam.trolls.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class TrollItem(
    private val id: String,
    private val material: Material,
    private val name: String,
    private val description: String? = null,
    private val note: String? = null,
    private val rarity: TrollRarity = TrollRarity.UNFINISHED,
    private val raritySuffix: String = "",
    private val stackable: Boolean = false,
    private val enchantments: MutableMap<Enchantment, Int>? = null,
    private val abilities: Map<TrollAbility, String>? = null,
    private val oneTimeUse: Boolean = false,
    private val unbreakable: Boolean = true,
    private val customModelData: Int? = null
) {
    private val utils = Utilities()

    fun createItem(): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta

        meta.persistentDataContainer.set(utils.idKey, PersistentDataType.STRING, id)
        meta.persistentDataContainer.set(utils.stackableKey, PersistentDataType.BOOLEAN, stackable)
        meta.displayName(getNameComponent())
        meta.lore(addLore())
        meta.isUnbreakable = unbreakable
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        if (enchantments != null) { item.addUnsafeEnchantments(enchantments) }
        if (customModelData != null) { item.itemMeta.setCustomModelData(customModelData) }
        item.itemMeta = meta

        return item
    }

    fun getId(): String { return id }

    fun getName(): String { return name }

    fun getNameComponent(colored: Boolean = true): Component {
        return if (colored) {
            Component.text(name, rarity.color).decoration(TextDecoration.ITALIC, false)
        }
        else Component.text(name)
    }

    private fun addLore(): MutableList<Component> {
        val lore = mutableListOf<Component>()

        if (rarity === TrollRarity.UNFINISHED) {
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
                    TrollAbility.RIGHT_CLICK -> { lore.add(Component.text("Item Ability: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    TrollAbility.LEFT_CLICK -> { lore.add(Component.text("Item Ability: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    TrollAbility.SHIFT_RIGHT_CLICK -> { lore.add(Component.text("Item Ability: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    TrollAbility.SHIFT_LEFT_CLICK -> { lore.add(Component.text("Item Ability: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    TrollAbility.FULL_SET_BONUS -> { lore.add(Component.text("Set Bonus: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
                    TrollAbility.PIECE_BONUS -> { lore.add(Component.text("Piece Bonus: $name ", NamedTextColor.GOLD).append(Component.text(type, NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false)) }
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