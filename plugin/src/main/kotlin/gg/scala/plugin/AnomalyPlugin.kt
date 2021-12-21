package gg.scala.plugin

import gg.scala.plugin.command.MeasureCommand
import gg.scala.plugin.listener.ClickListener
import org.bukkit.plugin.java.JavaPlugin

class AnomalyPlugin: JavaPlugin() {
    val clickListener = ClickListener(this);

    override fun onEnable() {
        this.getCommand("measure")!!.setExecutor(MeasureCommand(this))
        this.server.pluginManager.registerEvents(clickListener, this)
    }

    override fun onDisable() {
        println("Goodbye!")
    }
}