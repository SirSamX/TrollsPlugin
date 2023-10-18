package me.sirsam.trolls.core.registry

import org.bukkit.plugin.java.JavaPlugin

class Identifier {
    var namespace: String
    var path: String

    constructor(namespace: String, path: String) {
        this.namespace = namespace
        this.path = path
    }

    constructor(plugin: JavaPlugin, path: String) {
        this.namespace = plugin.name
        this.path = path
    }
}