package gg.scala.plugin.listener

import gg.scala.plugin.AnomalyPlugin
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import java.util.*

class ClickListener(val plugin: AnomalyPlugin) : Listener {

    private val startMeasuring = hashMapOf<UUID, Long>()
    private val clickMap = hashMapOf<UUID, MutableList<Long>>()

    @EventHandler
    fun click(event: PlayerInteractEvent) {
        val player = event.player

        if (startMeasuring.containsKey(player.uniqueId)) {
            val startTime = startMeasuring[player.uniqueId]!!

            if (System.currentTimeMillis() - startTime <= 5000) {
                if (event.action.name.contains("LEFT")) {
                    clickMap
                        .putIfAbsent(player.uniqueId, mutableListOf())!!
                        .add(System.currentTimeMillis() - startTime)
                }
            } else {


                clickMap[player.uniqueId]?.get(0)
                player.sendMessage("Total Clicks: ${clickMap[player.uniqueId]!!.size}")
                clickMap[player.uniqueId]?.forEach { player.sendMessage("" + it); }
                for (i in 0..clickMap.size - 1) {
                    if (i == 0) {
                        player.sendMessage("Click Offsets:")
                    } else {
                        val offset = clickMap[player.uniqueId]!![i] - clickMap[player.uniqueId]!![i - 1]
                        player.sendMessage("Offset: $offset")
                    }
                }
                clickMap[player.uniqueId] = mutableListOf()
                startMeasuring.remove(player.uniqueId)

            }
        }
    }

    fun measure(player: Player) {
        plugin.clickListener.startMeasuring[player.uniqueId] = System.currentTimeMillis()
    }
}