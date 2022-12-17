package team.frosty.superpug.kitpvpbased.kills;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class Levels implements Listener {
    @EventHandler
    public void onKill(EntityDamageByEntityEvent event){
        Player killer = (event.getDamager() instanceof Player)? (Player) event.getDamager(): null;
        //Player killed = (event.getEntity() instanceof Player)? (Player) event.getEntity() : null;
        LivingEntity killed = (LivingEntity) event.getEntity();
        Bukkit.broadcastMessage(String.valueOf(killed.getHealth()));
        if (killer == null || killed == null){
            return;
        }
        if(event.getFinalDamage() > killed.getHealth()){
            Bukkit.broadcastMessage(killer + " killed " + killed);
            killer.setLevel(killer.getLevel() +1);
        }
    }

    @EventHandler
    public void OnEntityDeath(EntityDeathEvent event){
        event.setDroppedExp(0);
    }
}
