package gg.scala.plugin.check.checks

import gg.scala.plugin.check.EventCheck
import org.bukkit.event.Event
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.reflect.KClass

class ExampleCheck : EventCheck()
{
    override val name = "Example"

    override val eventTypes: List<KClass<out Event>> = listOf(
        AsyncPlayerChatEvent::class,
        PlayerMoveEvent::class
    )

    override fun handleEvent(
        event: Event
    )
    {
        when (event)
        {
            is PlayerMoveEvent ->
            {
                val player = event.player
                val location = event.to

                if (location!!.x >= 5)
                {
                    player.sendMessage("I think you're gay. (X is greater than 5)")
                }
            }
            is AsyncPlayerChatEvent ->
            {
                val player = event.player
                val message = event.message

                if (message.contains("hey"))
                {
                    player.sendMessage("How could you type that? Rude cunt. $message")
                }
            }
        }
    }
}