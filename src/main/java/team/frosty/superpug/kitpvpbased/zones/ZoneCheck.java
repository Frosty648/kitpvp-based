package team.frosty.superpug.kitpvpbased.zones;

import jdk.jshell.execution.LoaderDelegate;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class ZoneCheck extends BukkitRunnable{
    public static HashMap<Location, Double> circleLocations = new HashMap<>();
    public static int zoneSize = 4;
    private HashMap<Double,Player> playerdistances = new HashMap<>();
    private ArrayList<Double> distances = new ArrayList<>();
    @Override
    public void run() {
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player p : players) {
            for (Location l : circleLocations.keySet()) {
                if (p.getLocation().distanceSquared(l) < Math.pow(zoneSize, 2)) {
                    circleLocations.put(l, circleLocations.get(l) + 5.0d);
                    Bukkit.broadcastMessage("player in radius of" + " X: " + l.getX() + " Y:  " + l.getY() + " Z: " + l.getZ() + " with charge of " + circleLocations.get(l) + "%");
                }
                if(circleLocations.get(l) == 100d){
                    for(Player p2 : players){
                        playerdistances.put(p.getLocation().distanceSquared(l), p2);
                        distances.add(p2.getLocation().distanceSquared(l));
                    }
                    Collections.sort(distances);
                    Bukkit.broadcastMessage(String.valueOf(distances));
                    Bukkit.broadcastMessage(String.valueOf(playerdistances.get(distances.get(distances.size()-1))));
                }
            }

        }
    }
}