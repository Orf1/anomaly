package gg.scala.plugin.listener

import gg.scala.plugin.check.CheckHandler
import gg.scala.plugin.check.EventCheck
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass

class EventCheckListener(
    plugin: JavaPlugin
) : Listener
{
    init
    {
        CheckHandler.CHECKS.forEach { check ->
            if (check is EventCheck)
            {
                check.eventTypes.forEach { type ->
                    Bukkit.getPluginManager().registerEvent(
                        type,
                        this,
                        EventPriority.HIGHEST,
                        plugin
                    ) {
                        check.handleEvent(it)
                    }
                }
            }
        }
    }

    private fun <T : Event> PluginManager.registerEvent(
        type: KClass<T>,
        listener: EventCheckListener,
        priority: EventPriority,
        plugin: JavaPlugin,
        action: (T) -> Unit
    )
    {
        Bukkit.getPluginManager()
            .registerEvent(
                type.java,
                listener,
                priority,
                { _, event ->
                    run {
                        action.invoke(event as T)
                    }
                },
                plugin
            )
    }
}