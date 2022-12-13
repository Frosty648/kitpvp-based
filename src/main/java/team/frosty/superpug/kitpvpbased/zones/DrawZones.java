package team.frosty.superpug.kitpvpbased.zones;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.Color;

public class DrawZones extends BukkitRunnable{
        @Override
        public void run(){
            for(Location l: ZoneCheck.circleLocations.keySet()){
                for (int d = 0; d <= 90; d += 1) {
                    Location particleLoc = new Location(l.getWorld(), l.getX(), l.getY(), l.getZ());
                    particleLoc.setX(l.getX() + Math.cos(d) * ZoneCheck.zonesize);
                    particleLoc.setZ(l.getZ() + Math.sin(d) * ZoneCheck.zonesize);
                    l.getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 1, new Particle.DustOptions(Color.WHITE, 5));
                }
            }
        }
}
