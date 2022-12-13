package team.frosty.superpug.kitpvpbased.zones;

import jdk.jshell.execution.LoaderDelegate;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.HashMap;

public class ZoneCheck extends BukkitRunnable{
    public static HashMap<Location, Double> circleLocations = new HashMap<>();
    public static int zoneSize = 4;
    @Override
    public void run() {
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player p : players) {
            for (Location l : circleLocations.keySet())
                if (p.getLocation().distanceSquared(l) < Math.pow(zoneSize, 2)) {
                    circleLocations.put(l, circleLocations.get(l) + 0.5d);
                    Bukkit.broadcastMessage("player in radius of" + " X: " + l.getX() + " Y:  " + l.getY() + " Z: " + l.getZ() + " with charge of " + circleLocations.get(l) + "%");
                }
        }
    }
}
