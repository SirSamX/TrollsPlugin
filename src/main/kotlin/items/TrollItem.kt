package me.sirsam.trolls.items

import me.sirsam.trolls.helpers.Utilities
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class TrollItem(
    private val id: String = "unfinished_item",
    private val material: Material = Material.BARRIER,
    private val name: String = "Unfinished Item",
    private val description: String? = null,
    private val note: String? = null,
    private val rarity: TrollRarity = TrollRarity.UNFINISHED,
    private val raritySuffix: String = "",
    private val stackable: Boolean = true,
    private val enchantments: MutableMap<Enchantment, Int>? = null,
    private val abilities: Array<String>? = null,
    private val oneTimeUse: Boolean = false,
    private val unbreakable: Boolean = true
) {
    private val utils = Utilities()

    fun createItem(): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        val formattedName = rarity.color + name

        meta.persistentDataContainer.set(utils.nameKey, PersistentDataType.STRING, id)
        meta.displayName(Component.text(formattedName))
        meta.lore(addLore())
        if (enchantments != null) { item.addUnsafeEnchantments(enchantments) }
        meta.isUnbreakable = unbreakable
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        item.itemMeta = meta

        return item
    }

    fun getId(): String { return id }

    fun getName(color: Boolean = true): String {
        return if (color) {
            rarity.color + name
        }
        else name
    }


    private fun addLore(): MutableList<Component> {
        val lore = mutableListOf<Component>()

        if (rarity === TrollRarity.UNFINISHED) {
            lore.add(Component.text("§cThis item is UNFINISHED"))
            lore.add(Component.text("§cIt may not perform as expected"))
            lore.add(Component.text(""))
        }

        if (!(description.isNullOrBlank())) {
            lore.add(Component.text("§7$description"))
            lore.add(Component.text(""))
        }

        if (!(note.isNullOrBlank())) {
            lore.add(Component.text("§8$note"))
            lore.add(Component.text(""))

        }

        if (oneTimeUse) {
            lore.add(Component.text("§8(consumed on use)"))
            lore.add(Component.text(""))
        }

        lore.add(Component.text(rarity.color + rarity + " " + raritySuffix.uppercase()))

        return lore
    }
}