package team.frosty.superpug.kitpvpbased;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Team;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Chat implements Listener {

    @EventHandler
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
        String msg = messages[(int) (r.nextDouble() * 5)];
        e.setDeathMessage(ChatColor.GRAY + dead.getName() + msg + killer.getName());
    }

    private static String getTeamPrefix(Set<Team> teams, Player plr) {
        if (teams.isEmpty())
            return "";

        for (Team team : teams) {
            if (team.hasEntry(plr.getDisplayName()))
                return team.getPrefix();
        }
        return "";
    }

    private static ChatColor getTeamColor(Set<Team> teams, Player plr) {
        if (teams.isEmpty())
            return ChatColor.RESET;

        for (Team team : teams) {
            if (team.hasEntry(plr.getDisplayName()))
                return team.getColor();
        }
        return ChatColor.RESET;
    }

    @EventHandler
    public void onMessageSent(AsyncPlayerChatEvent e) {
        Player plr = e.getPlayer();
        Set<Team> teams = plr.getScoreboard().getTeams();
        ChatColor cc = getTeamColor(teams, plr);
        String teamPrefix = getTeamPrefix(teams, plr);
        e.setFormat(cc + teamPrefix + "%1$s" + ChatColor.RESET + ": %2$s");
    }
}
