package me.sirsam.trolls.core.item

import me.sirsam.trolls.core.helper.Utils
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta

/**
 * Use [<a href="https://minecraft-heads.com/custom-heads/">minecraft-heads.com</a>] to get custom heads.
 * Additionally you can use templates from here.
 **/
@Suppress("MemberVisibilityCanBePrivate")
enum class Head(val id: String) {
    PHONE_BLACK("8e07ac02d9e947e562a7f73952c29ef682a1fc60da115cbc8154a5c4b99120e9"),
    CHICKEN("9f26883dc28a49cb582b60c6dccdca173d45f7b1a7b8690ca8ce43cde0e13589"),
    SNIFFER("fe5a8341c478a134302981e6a7758ea4ecfd8d62a0df4067897e75502f9b25de"),
    TRICK_OR_TREAT_BASKET("a7edc909808940f6564e96086c9b98cdaa859f627eed556ec375d6ed2704c506"),
    EVIL_STEVE("62a744b85e49b74dcb3d0f079c664d6c0036bf5cc3b7ad272efdd8f0b7ac6b24"),
    SKELETON_GHOST("d45dc55daba4e2d32a284448bb9e38335d843f93c4a776885fd35653fdfc758");

    companion object {
        fun createHead(id: String): ItemStack {
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
        createHead(id)
    }

    fun setTexture(meta: ItemMeta): SkullMeta {
        return setTexture(meta, id)
    }

    fun setTexture(meta: SkullMeta): SkullMeta {
        return setTexture(meta, id)
    }
}