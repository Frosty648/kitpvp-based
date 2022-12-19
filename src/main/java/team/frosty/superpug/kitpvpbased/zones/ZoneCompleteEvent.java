package team.frosty.superpug.kitpvpbased.zones;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ZoneCompleteEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Zone finishedZone;
    private final Player closestPlayer;

    public ZoneCompleteEvent(Zone zone, Player p){
        this.finishedZone = zone;
        this.closestPlayer = p;
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
