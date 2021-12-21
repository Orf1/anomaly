package gg.scala.plugin

import org.bukkit.plugin.java.JavaPlugin

class AnomalyPlugin: JavaPlugin() {
    override fun onEnable() {
        println("Hello!")
    }

    override fun onDisable() {
        println("Goodbye!")
    }
}