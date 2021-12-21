package gg.scala.plugin.check

import org.bukkit.event.Event
import kotlin.reflect.KClass

abstract class EventCheck : Check()
{
    abstract val eventTypes: List<KClass<out Event>>

    abstract fun handleEvent(
        event: Event
    )
}