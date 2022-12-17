package team.frosty.superpug.kitpvpbased.rewards;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Bow {

    private final ItemStack itemStack = new ItemStack(Material.BOW);

    public Bow() {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.RED + "Damaged Bow");
        ((Damageable) meta).setDamage(100);
        itemStack.setItemMeta(meta);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public static ItemStack createBow() {
        return new GoldenApple().getItemStack();
    }
}
