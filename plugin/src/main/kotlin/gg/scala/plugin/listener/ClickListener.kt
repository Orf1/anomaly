package gg.scala.plugin.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import java.util.*

class ClickListener : Listener
{

    companion object
    {
        @JvmStatic
        val START_MEASURING = hashMapOf<UUID, Long>()
    }

    private val clickMap = hashMapOf<UUID, MutableList<Long>>()

    @EventHandler
    fun click(
        event: PlayerInteractEvent
    )
    {
        val player = event.player

        if (START_MEASURING.containsKey(player.uniqueId))
        {
            val startTime = START_MEASURING[player.uniqueId]!!

            if (startTime - System.currentTimeMillis() <= 5000)
            {
                if (event.action.name.contains("LEFT"))
                {
                    clickMap
                        .putIfAbsent(player.uniqueId, mutableListOf())!!
                        .add(System.currentTimeMillis() - startTime)
                }
            } else
            {
                player.sendMessage(
                    "Total Clicks: ${clickMap[player.uniqueId]!!.size}"
                )

                clickMap[player.uniqueId] = mutableListOf()
                START_MEASURING.remove(player.uniqueId)
            }
        }
    }
}