package me.sirsam.trolls.core.registry

import me.sirsam.trolls.core.helper.InvalidStringException

data class Identifier(var namespace: String, var path: String) {
    private val idPattern = Regex("[a-z0-9_]+")

    init {
        if (!namespace.matches(idPattern)) throw InvalidStringException("Input namespace does not match identifier regex pattern!")
    }

    override fun toString(): String {
        return "$namespace:$path"
    }
}