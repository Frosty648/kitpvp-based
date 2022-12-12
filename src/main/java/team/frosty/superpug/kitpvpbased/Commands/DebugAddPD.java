package team.frosty.superpug.kitpvpbased.Commands;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import team.frosty.superpug.kitpvpbased.KitpvpBased;

public class DebugAddPD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3)
            return false;

        if (sender instanceof Player) {
            Player p = (Player) sender;

            ItemStack item = p.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();

            PersistentDataContainer data = meta.getPersistentDataContainer();
            NamespacedKey dataToSet = new NamespacedKey(KitpvpBased.getInstance(), args[0]);
            PersistentDataType type = toPDType(args[1]);

            data.set(dataToSet, type, castPDType(args[1], args[2]));
            item.setItemMeta(meta);
        }

        return true;
    }

    private PersistentDataType toPDType(String input) {
        if (input.equals("INTEGER")) {
            return PersistentDataType.INTEGER;
        }
        else if (input.equals("FLOAT")) {
            return PersistentDataType.FLOAT;
        }
        else if (input.equals("DOUBLE")) {
            return PersistentDataType.DOUBLE;
        }
        else if (input.equals("LONG")) {
            return PersistentDataType.LONG;
        }
        else if (input.equals("SHORT")) {
            return PersistentDataType.SHORT;
        }
        else {
            return PersistentDataType.STRING;
        }
    }

    private Object castPDType(String input, String toCast) {
        if (input.equals("INTEGER")) {
            return Integer.parseInt(toCast);
        }
        else if (input.equals("FLOAT")) {
            return Float.parseFloat(toCast);
        }
        else if (input.equals("DOUBLE")) {
            return Double.parseDouble(toCast);
        }
        else if (input.equals("SHORT")) {
            return Short.parseShort(toCast);
        }
        else if (input.equals("LONG")) {
            return Long.parseLong(toCast);
        }
        else {
            return toCast;
        }
    }
}
