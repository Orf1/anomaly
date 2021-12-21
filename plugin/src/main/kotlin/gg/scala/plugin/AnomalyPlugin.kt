package gg.scala.plugin

import gg.scala.plugin.command.MeasureCommand
import org.bukkit.plugin.java.JavaPlugin

class AnomalyPlugin: JavaPlugin() {
    override fun onEnable() {
        this.getCommand("measure")!!.setExecutor(MeasureCommand())
    }

    override fun onDisable() {
        println("Goodbye!")
    }
}