package team.frosty.superpug.kitpvpbased.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class ZoneUtils {
    // Rand range generator
    public static int genRangeInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    // Finds the lowest air block above a Y value
    public static double findLowestAir(Location location) {
        World w = location.getWorld();
        int y = location.getBlockY();
        while (location.getBlock().getType() != Material.AIR) {
            Bukkit.broadcastMessage(""+y);
            location.setY(location.getBlockY() + 1);
            y = location.getBlockY();
        }
        return y+.5;
    }

    public static Location genRandomCircleLoc(int minX, int maxX, int minZ, int maxZ, int minY) {
        int x = genRangeInt(minX, maxX);
        int z = genRangeInt(minZ, maxZ);
        Location loc = new Location(Bukkit.getWorlds().get(0), x, minY, z);
        double y = findLowestAir(loc);
        loc.setY(y);
        return loc;
    }
}
