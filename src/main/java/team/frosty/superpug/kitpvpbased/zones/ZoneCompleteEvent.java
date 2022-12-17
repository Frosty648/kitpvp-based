package team.frosty.superpug.kitpvpbased.zones;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ZoneCompleteEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Zone finishedZone;

    public ZoneCompleteEvent(Zone zone){
        this.finishedZone = zone;
        zone.cancel();
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public void endZone(){
        finishedZone.cancel();
    }

}
