package me.sirsam.trolls.core.item

import me.sirsam.trolls.core.item.abilities.AbilityType
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
    var abilities: Map<AbilityType, String>? = null,
    var oneTimeUse: Boolean = false,
    var unbreakable: Boolean = true,
    var customModelData: Int? = null,
    var headTexture: Head? = null,
    var color: NamedTextColor? = null
)