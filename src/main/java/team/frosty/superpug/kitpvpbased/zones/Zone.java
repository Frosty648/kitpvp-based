package team.frosty.superpug.kitpvpbased.zones;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import team.frosty.superpug.kitpvpbased.KitpvpBased;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Zone extends BukkitRunnable{
    private Location zoneLocation;
    private ArrayList<Double> Distances;
    private int charge;
    private int chargeRate = 1;

    public Zone(Location l){
        this.zoneLocation = l;
        this.runTaskTimer(KitpvpBased.getInstance(), 0,5);
    }

    public Zone(Location l, Integer chargeRate){
        this.zoneLocation = l;
        this.chargeRate = chargeRate;
        this.runTaskTimer(KitpvpBased.getInstance(), 0,5);
    }

    @Override
    public void run(){
        drawZone();


        if(shouldCharge()){
            increaseCharge(20);
        }

        if(this.charge >= 100){
            this.cancel();
            Bukkit.getPluginManager().callEvent(new ZoneCompleteEvent(this));
            Bukkit.broadcastMessage("zone finished");
        }



    }


    public void drawZone(){
        for (int d = 0; d <= 90; d += 1) {
            Location particleLoc = new Location(zoneLocation.getWorld(), zoneLocation.getX(), zoneLocation.getY(), zoneLocation.getZ());
            particleLoc.setX(zoneLocation.getX() + Math.cos(d) * ZoneCheck.zoneSize);
            particleLoc.setZ(zoneLocation.getZ() + Math.sin(d) * ZoneCheck.zoneSize);
            zoneLocation.getWorld().spawnParticle(Particle.CRIT, particleLoc, 1, 0d, 0d, 0d, 0d);
        }
    }

    public boolean shouldCharge(){
        for (Player p : Bukkit.getOnlinePlayers()){
            if(p.getLocation().distanceSquared(zoneLocation) < 7){
                return true;
            }
        }
        return false;
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
