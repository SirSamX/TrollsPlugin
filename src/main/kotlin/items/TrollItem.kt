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
import java.util.*

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
    private val abilities: Map<AbilityType, String>? = null,
    private val oneTimeUse: Boolean = false,
    private val unbreakable: Boolean = true,
    private val customModelData: Int? = null,
    private val headTexture: TrollHead? = null,
    private val color: NamedTextColor? = null
) {
    private val utils = Utilities()

    fun item(): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        val data = meta.persistentDataContainer

        data.set(utils.idKey, PersistentDataType.STRING, id)
        data.set(utils.stackableKey, PersistentDataType.BOOLEAN, stackable)
        if (!stackable) { data.set(utils.uuidKey, PersistentDataType.STRING, UUID.randomUUID().toString()) }
        meta.displayName(nameComponent())
        meta.lore(addLore())
        meta.isUnbreakable = unbreakable
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)

        if (enchantments != null) { item.addUnsafeEnchantments(enchantments) }
        if (customModelData != null) { item.itemMeta.setCustomModelData(customModelData) }
        if (headTexture != null) {
            TrollHead.valueOf(headTexture.name).setTexture(meta)
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