package me.sirsam.trolls.item

import me.sirsam.trolls.core.item.ItemProperties
import me.sirsam.trolls.core.item.Rarity
import me.sirsam.trolls.core.item.abilities.AbilityItem
import me.sirsam.trolls.core.item.abilities.AbilityResult
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.entity.EntityShootBowEvent

class ExplosiveBow : AbilityItem(ItemProperties(
    id = "explosive_bow",
    material = Material.BOW,
    name = "Explosive Bow",
    description = "Shoot to create an explosion.",
    note = null,
    rarity = Rarity.LEGENDARY,
    raritySuffix = "BOW")
) {
    override fun shoot(event: EntityShootBowEvent): AbilityResult {
        event.projectile.customName(Component.text("Explosive Arrow"))

        return AbilityResult.SUCCESS
    }
}