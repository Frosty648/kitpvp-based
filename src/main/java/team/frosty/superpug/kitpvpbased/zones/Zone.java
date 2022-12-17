package team.frosty.superpug.kitpvpbased.zones;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import team.frosty.superpug.kitpvpbased.KitpvpBased;
import team.frosty.superpug.kitpvpbased.utils.ZoneUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Zone extends BukkitRunnable{
    private Location zoneLocation;
    private ArrayList<Double> Distances;
    private int size;
    private int charge;

    public Zone(Location l,int size){
        this.zoneLocation = l;
        this.size = size;
        this.runTaskTimer(KitpvpBased.getInstance(), 0,1);
    }

    @Override
    public void run(){
        drawZone();

        increaseCharge(playersInZone());


        if(this.charge >= 300){
            Bukkit.broadcastMessage("zone finished");
            Bukkit.getPluginManager().callEvent(new ZoneCompleteEvent(this));
        }



    }


    public void drawZone(){
        for (int d = 0; d <= 90; d += 1) {
            Location particleLoc = new Location(zoneLocation.getWorld(), zoneLocation.getX(), zoneLocation.getY(), zoneLocation.getZ());
            particleLoc.setX(zoneLocation.getX() + Math.cos(d) * size);
            particleLoc.setZ(zoneLocation.getZ() + Math.sin(d) * size);
            zoneLocation.getWorld().spawnParticle(Particle.CRIT, particleLoc, 1, 0d, 0d, 0d, 0d);
        }
    }

    public int playersInZone(){
        int inZone = 0;
        for (Player p : Bukkit.getOnlinePlayers()){
            if(p.getLocation().distanceSquared(zoneLocation) < 7){
                inZone += 1;
            }
        }
        return inZone;
    }

    public void increaseCharge(int amount){
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
