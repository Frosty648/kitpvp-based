package team.frosty.superpug.kitpvpbased.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugGetPlayerPD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            Bukkit.broadcastMessage(p.getPersistentDataContainer().getKeys().toString());
        }

        return true;
    }
}
