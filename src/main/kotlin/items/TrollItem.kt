package me.sirsam.trolls.items

import com.destroystokyo.paper.profile.ProfileProperty
import me.sirsam.trolls.helpers.Utilities
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType
import java.util.UUID

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
    private val skullMeta: String? = null
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
        item.itemMeta = meta

        if (enchantments != null) { item.addUnsafeEnchantments(enchantments) }
        if (customModelData != null) { item.itemMeta.setCustomModelData(customModelData) }
        if (skullMeta != null) {
            val skullMeta = item.itemMeta as SkullMeta
            skullMeta.playerProfile
            val profile = Bukkit.createProfile(UUID.randomUUID(), "player_head")
            profile.setProperty(ProfileProperty(
                "textures",
                this.skullMeta, "anQzSPDpA+Dys7PxyDLmlw47txcMkWeMjzFjmEYaH6JacamfV8qaLNEgWdh6xsVolVI2kcLXGYdRmAQF9wMqMPMA2A1eOZ4pdsn/U/KEpue8WdGPPCcNGWu8oj/1njfxM+T6t+72idyZ+tj36c4+iF6+f2ind0mH6x5NNxNXS2uQWTyTkccZBv8eXA3OHtjaa4l8AyEnstui85doXQ3slDdDsIJa8Fn5UYewoEXwto6QekVetB/o0cmAqmaPAbwxHqrthFhhY5zB2PurjRMxLkU3fcgZ9sXnZQfSDzK2XzVjXVM+V3Iuf5NchHoDfKeR6uSotV6WGl02nHhk59gcm0dQrSlAkz9YBVM5z3k17V3sKQa+qh/bjUqBvRIszLIhtPbcmMu58+jRJGsSIyvv7duqYrJmSCUgm9gVLWRoVBE6WxNOw2Rs+L4IRs0o2abrL77bdLjAYLZnpzscUqwhZ1kVWJFwmJzBxtZ4+I9OSoFzkmfOR0R/JQL0RMjyTlPE9FPBZow14yHFgbuG7f57/OHchXM+E7pjQL8n2hZrZKr/XWGld4kNxQqkDS8PG5TJ/8T3CXeMLnYfSTMY+10NeuuHYgTb0RFjMzjFD/Qnli04irhrDqAUJuFBXScj5T0VfH/ryZJCP/68oLAKhf31fcyqv9Cb/YqKsj6bzhK6ZVI="
            ))
            skullMeta.playerProfile = profile
        }

        return item
    }

    fun id(): String { return id }

    fun name(): String { return name }

    fun nameComponent(colored: Boolean = true): Component {
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