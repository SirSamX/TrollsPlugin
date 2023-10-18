package me.sirsam.trolls.core.item

import core.helpers.Utils
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta

@Suppress("MemberVisibilityCanBePrivate")
enum class TrollHead(val id: String) {
    /**
    * Use https://minecraft-heads.com/custom-heads/ to get custom heads.
    * Register Them using // TODO: Add register for custom heads
     **/

    PHONE_BLACK("8e07ac02d9e947e562a7f73952c29ef682a1fc60da115cbc8154a5c4b99120e9"),
    CHICKEN("9f26883dc28a49cb582b60c6dccdca173d45f7b1a7b8690ca8ce43cde0e13589");

    companion object {
        fun createSkull(id: String): ItemStack {
            val head = ItemStack(Material.PLAYER_HEAD)
            val headMeta = head.itemMeta as SkullMeta
            headMeta.playerProfile = Utils.getProfileById(id)
            head.setItemMeta(headMeta)
            return head
        }

        fun setTexture(skullMeta: SkullMeta, id: String): SkullMeta {
            skullMeta.playerProfile = Utils.getProfileById(id)
            return skullMeta
        }

        fun setTexture(meta: ItemMeta, id: String): SkullMeta {
            return setTexture(meta as SkullMeta, id)
        }
    }

    val itemStack: ItemStack by lazy {
        createSkull(id)
    }

    fun setTexture(meta: ItemMeta): SkullMeta {
        return setTexture(meta, id)
    }

    fun setTexture(meta: SkullMeta): SkullMeta {
        return setTexture(meta, id)
    }
}