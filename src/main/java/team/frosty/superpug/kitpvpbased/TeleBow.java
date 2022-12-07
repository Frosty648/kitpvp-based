package team.frosty.superpug.kitpvpbased;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import java.util.HashMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;


public class TeleBow implements Listener {
    HashMap<Integer,String> arrows = new HashMap<>();
    HashMap<String,Long> cooldown = new HashMap<>();

    @EventHandler
    public void onPlayerShoot(EntityShootBowEvent event){
        Bukkit.broadcastMessage("test");
        if(event.getEntity() instanceof Player&& checkcooldown(((Player) event.getEntity()).getDisplayName())){
            arrows.put(event.getProjectile().getEntityId(),((Player) event.getEntity()).getDisplayName());
            Bukkit.broadcastMessage((((Player) event.getEntity()).getDisplayName()));
            cooldown.put(((Player) event.getEntity()).getDisplayName(),System.currentTimeMillis());
        }
    }
    @EventHandler
    public void onBlockHit(ProjectileHitEvent event){
        Player player = Bukkit.getPlayer(arrows.get(event.getEntity().getEntityId()));
        player.teleport(event.getHitBlock().getLocation());
        Bukkit.broadcastMessage(String.valueOf(event.getHitBlock().getLocation()));
    }

    public boolean checkcooldown(String ID){
        if(cooldown.get(ID) == null){
            Bukkit.broadcastMessage(ID + "not in HashMap, adding");
            return true;
        }
        if(System.currentTimeMillis() - cooldown.get(ID)  >3000){
            Bukkit.broadcastMessage("cooldown expired");
            return true;
        }else{
            Bukkit.broadcastMessage("cooldown not expired");
            return false;
        }
    }
}
