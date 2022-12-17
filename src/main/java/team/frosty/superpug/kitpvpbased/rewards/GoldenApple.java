package team.frosty.superpug.kitpvpbased.rewards;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GoldenApple {

    private final ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE);

    public GoldenApple() {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.YELLOW + "Tasty Apple!");
        itemStack.setItemMeta(meta);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public static ItemStack createGoldenApple() {
        return new GoldenApple().getItemStack();
    }
}
