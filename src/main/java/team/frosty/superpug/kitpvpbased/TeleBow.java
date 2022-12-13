package team.frosty.superpug.kitpvpbased;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;


public class TeleBow implements Listener {
    //TODO add projectile trail to arrows for player firing
    //|| !event.getBow().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(KitpvpBased.getInstance(), "telebow"), PersistentDataType.STRING)


    //hashmap of the ID of the arrow fired by the player and the Display name of the firing player
    HashMap<Integer, UUID> arrows = new HashMap<>();
    //hashmap of the Display name of the player that shot and unix timestamp they shot at
    HashMap<UUID,Long> cooldown = new HashMap<>();

    @EventHandler
    public void onPlayerShoot(EntityShootBowEvent event){
        //logic to check that 1) a player is the one firing the arrow and 2) they are crouched when firing
        if(!(event.getEntity() instanceof Player) || !((Player)event.getEntity()).isSneaking() || !event.getBow().getItemMeta().getPersistentDataContainer().getKeys().toString().contains("telebow")){
            return;
        }

        //checks if the cooldown if expired before updating the hashmaps
        if(checkcooldown(event.getEntity().getUniqueId(),(Player) event.getEntity())){

            //adds the ID of the shot arrow and display name of shooter to hashmap
            arrows.put(event.getProjectile().getEntityId(),event.getEntity().getUniqueId());

            //adds the display name of shooter and current unix timestamp to hashmap
            cooldown.put(event.getEntity().getUniqueId(),System.currentTimeMillis());
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
        arrowpos.setDirection(player.getLocation().getDirection());


        player.teleport(arrowpos);
    }


    public boolean checkcooldown(UUID uuid, Player p){
        //if the player has not shot the telebow before (name not in cooldown hashmap) adds them to it and returns true
        if(cooldown.get(uuid) == null){
            return true;
        }
        //checks if the current unix timestamp - the cooldown has a difference of greater than 3000ms (3s)
        if(System.currentTimeMillis() - cooldown.get(uuid)  >3000){
            return true;
        }
        //returns false if cooldown if not expired
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.BOLD + "" + ChatColor.RED + "cooldown not expired"));
        return false;
    }
}
