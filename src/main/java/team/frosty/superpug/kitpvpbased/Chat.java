package team.frosty.superpug.kitpvpbased;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Random;

public class Chat implements Listener {

    public void onKillEvent(PlayerDeathEvent e) {
        final Random r = new Random();
        final String[] messages = {
                " was obliterated by ",
                " got domed by",
                " was styled on by ",
                " fell pathetically to ",
                " was smartphOWNED.com'ed by "
        };
        final Player dead = e.getEntity();
        final Player killer = e.getEntity().getKiller();
        String msg = messages[(int) r.nextDouble() * 5];
        e.setDeathMessage(ChatColor.GRAY + dead.getName() + msg + killer.getName());
    }

    public void onMessageSent(AsyncPlayerChatEvent e) {
        e.setFormat(ChatColor.BOLD + "%1$s: " + ChatColor.RESET + "%2$s");
    }
}
