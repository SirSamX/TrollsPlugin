package me.sirsam.trolls.items

import me.sirsam.trolls.core.item.Head
import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import org.bukkit.Material

class Chickzooka : AbilityItem(ItemProperties(
    id = "chickzooka",
    material = Material.PLAYER_HEAD,
    name = "Chickzooka",
    rarity = Rarity.SPECIAL,
    headTexture = Head.CHICKEN,
    stackable = false
)) {
}