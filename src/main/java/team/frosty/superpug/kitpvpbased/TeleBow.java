package team.frosty.superpug.kitpvpbased;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import java.util.HashMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;


public class TeleBow implements Listener {

    //TODO add projectile trail to arrows for player firing

    //hashmap of the ID of the arrow fired by the player and the Display name of the firing player
    HashMap<Integer,String> arrows = new HashMap<>();
    //hashmap of the Display name of the player that shot and unix timestamp they shot at
    HashMap<String,Long> cooldown = new HashMap<>();


    @EventHandler
    public void onPlayerShoot(EntityShootBowEvent event){
        //logic to check that 1) a player is the one firing the arrow and 2) they are crouched when firing
        if(!(event.getEntity() instanceof Player) || !((Player)event.getEntity()).isSneaking()){
            return;
        }

        //checks if the cooldown if expired before updating the hashmaps
        if(checkcooldown(((Player) event.getEntity()).getDisplayName())){

            //adds the ID of the shot arrow and display name of shooter to hashmap
            arrows.put(event.getProjectile().getEntityId(),((Player) event.getEntity()).getDisplayName());

            //adds the display name of shooter and current unix timestamp to hashmap
            cooldown.put(((Player) event.getEntity()).getDisplayName(),System.currentTimeMillis());
        }
    }


    @EventHandler
    public void onBlockHit(ProjectileHitEvent event){
        //get the Player from the item ID stored in the hashmap, uses the entity ID of the arrow as the key
        Player player = Bukkit.getPlayer(arrows.get(event.getEntity().getEntityId()));

        //makes a new Location of the hit block and adds 2 to the Y coordinate

        //TODO change to single line or move inside player.teleport
        Location arrowpos = event.getHitBlock().getLocation();
        arrowpos.setY(arrowpos.getY() +2);


        player.teleport(arrowpos);
    }


    public boolean checkcooldown(String DisplayName){
        //if the player has not shot the telebow before (name not in cooldown hashmap) adds them to it and returns true
        if(cooldown.get(DisplayName) == null){
            return true;
        }
        //checks if the current unix timestamp - the cooldown has a difference of greater than 3000ms (3s)
        if(System.currentTimeMillis() - cooldown.get(DisplayName)  >3000){
            Bukkit.broadcastMessage("cooldown expired");
            return true;
        }else{
            Bukkit.broadcastMessage("cooldown not expired");
            return false;
        }
    }
}
