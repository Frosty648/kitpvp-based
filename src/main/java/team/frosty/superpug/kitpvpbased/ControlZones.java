package team.frosty.superpug.kitpvpbased;

import jdk.jshell.execution.LoaderDelegate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import java.util.ArrayList;

public class ControlZones extends JavaPlugin {
    static ArrayList<Location> circleLocations = new ArrayList<Location>();
    static Runnable runnable = () -> {
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        for(Player p:players){
            for(Location l: circleLocations)
            if(p.getLocation().distanceSquared(l) < 49){
                Bukkit.broadcastMessage("player in radius");
            }
        }
    };
}
