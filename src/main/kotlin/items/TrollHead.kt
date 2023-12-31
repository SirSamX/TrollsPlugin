package me.sirsam.trolls.items

import me.sirsam.trolls.helpers.Utilities
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta

@Suppress("MemberVisibilityCanBePrivate")
enum class TrollHead(val id: String) {
    //https://minecraft-heads.com/custom-heads/tags/var/Device?start=320

    PHONE_BLACK("8e07ac02d9e947e562a7f73952c29ef682a1fc60da115cbc8154a5c4b99120e9"),
    CHICKEN("9f26883dc28a49cb582b60c6dccdca173d45f7b1a7b8690ca8ce43cde0e13589");

    companion object {
        fun createSkull(id: String): ItemStack {
            val head = ItemStack(Material.PLAYER_HEAD)
            val headMeta = head.itemMeta as SkullMeta
            headMeta.playerProfile = Utilities().getProfileById(id)
            head.setItemMeta(headMeta)
            return head
        }

        fun setTexture(skullMeta: SkullMeta, id: String): SkullMeta {
            skullMeta.playerProfile = Utilities().getProfileById(id)
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