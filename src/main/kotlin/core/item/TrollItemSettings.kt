package me.sirsam.trolls.core.item

import me.sirsam.trolls.core.item.abilities.AbilityType
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

class TrollItemSettings {
    constructor(
        id: String,
        material: Material,
        name: String,
        description: String? = null,
        note: String? = null,
        rarity: TrollRarity = TrollRarity.UNFINISHED,
        raritySuffix: String = "",
        stackable: Boolean = false,
        enchantments: MutableMap<Enchantment, Int>? = null,
        abilities: Map<AbilityType, String>? = null,
        oneTimeUse: Boolean = false,
        unbreakable: Boolean = true,
        pcustomModelData: Int? = null,
        headTexture: TrollHead? = null,
        color: NamedTextColor? = null
    )
}