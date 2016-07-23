package us.universalpvp.kotlin

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

/**
 * Created by avigh on 7/11/2016.
 */
class Main : JavaPlugin(), Listener, CommandExecutor {

    /*
    This is an example plugin in kotlin.

    There is no override annotation, you use the keyword "override"
    You probably have noticed that there are no semi-colons, that's because they are redundant
     */
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
        getCommand("example").executor = this
    }

    /*
    In kotlin, you don't have to cast, it automatically does that for you.
     */
    override fun onCommand(sender: CommandSender?, cmd: Command?, label: String?, args: Array<out String>?): Boolean {
        if (sender is Player) {
            if (cmd?.name.equals("example")) {
                sender.sendMessage("The kotlin plugin works! Have a cake!")
                sender.giveExp(100000)
                sender.inventory.addItem(ItemStack(Material.CAKE))
            }
        }
        return false
    }

    /*
    Example of using an anonymous class. There is no "new" keyword in Kotlin.
     */
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val p: Player = e.player

        object : BukkitRunnable() {
            override fun run() {
                p.inventory.addItem(ItemStack(Material.DIAMOND))
            }
        }
    }

}
