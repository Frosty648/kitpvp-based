package team.frosty.superpug.kitpvpbased.zones;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Zone {
    private Location zoneLocation;
    private ArrayList<Double> Distances;
    private int charge;


    public Zone(Location l){
        zoneLocation = l;
    }

    public void increaseCharge(int amount){
        if(charge + amount >100){
            return;
        }
        charge += amount;
    }

    public Location getZoneLocation(){
        return zoneLocation;
    }

    public int getCharge(){
        return charge;
    }

    public Player getClosestPlayer(){
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        HashMap<Double,Player> playerDistances = new HashMap<>();
        ArrayList<Double> distances = new ArrayList<>();

        for(Player p: players){
            playerDistances.put(p.getLocation().distanceSquared(zoneLocation), p);
            Distances.add(p.getLocation().distanceSquared(zoneLocation));
        }
        Distances.sort(Comparator.reverseOrder());
        return playerDistances.get(Distances.get(0));



    }

}
