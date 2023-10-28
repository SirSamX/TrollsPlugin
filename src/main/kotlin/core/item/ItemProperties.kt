package me.sirsam.trolls.core.item

import me.sirsam.trolls.core.item.abilities.Ability
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

data class ItemProperties(
    var id: String,
    var material: Material,
    var name: String,
    var description: String? = null,
    var note: String? = null,
    var rarity: Rarity = Rarity.UNFINISHED,
    var raritySuffix: String = "",
    var stackable: Boolean = false,
    var enchantments: MutableMap<Enchantment, Int>? = null,
    var abilities: List<Ability>? = null,
    var glint: Boolean = false,
    var customModelData: Int? = null,
    var headTexture: Head? = null,
    var color: NamedTextColor? = null
)