package team.frosty.superpug.kitpvpbased.zones;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

public class DrawZones extends BukkitRunnable{
        @Override
        public void run(){
            for(Location l: ZoneCheck.circleLocations.keySet()){
                for (int d = 0; d <= 90; d += 1) {
                    Location particleLoc = new Location(l.getWorld(), l.getX(), l.getY(), l.getZ());
                    particleLoc.setX(l.getX() + Math.cos(d) * ZoneCheck.zoneSize);
                    particleLoc.setZ(l.getZ() + Math.sin(d) * ZoneCheck.zoneSize);
                    l.getWorld().spawnParticle(Particle.CRIT, particleLoc, 1, 0d, 0d, 0d, 0d);
                }
            }
        }
}
