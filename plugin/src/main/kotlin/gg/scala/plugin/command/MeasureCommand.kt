package gg.scala.plugin.command

import gg.scala.plugin.AnomalyPlugin
import gg.scala.plugin.listener.ClickListener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class MeasureCommand : CommandExecutor
{
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean
    {
        if (args.size != 1)
        {
            sender.sendMessage("${ChatColor.RED}Usage: /$label <target>")
            return true
        }

        val target = Bukkit.getPlayer(args[0])

        if (target == null)
        {
            sender.sendMessage("${ChatColor.RED}That player does not exist.")
            return true
        }

        ClickListener.START_MEASURING[target.uniqueId] = System.currentTimeMillis()
        return true
    }
}