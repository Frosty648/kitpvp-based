package team.frosty.superpug.kitpvpbased.zones;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class ZoneCheck implements Listener {
    @EventHandler
    public static void onZone(ZoneCompleteEvent event){
        event.endZone();
    }

}