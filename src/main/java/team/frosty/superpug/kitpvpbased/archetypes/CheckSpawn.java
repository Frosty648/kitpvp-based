package team.frosty.superpug.kitpvpbased.archetypes;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import team.frosty.superpug.kitpvpbased.KitpvpBased;

import java.util.Collection;

public class CheckSpawn extends BukkitRunnable {

    @Override
    public void run() {

        int ceilingHeight = KitpvpBased.getInstance().getConfig().getInt("arena.ceiling");

        Collection<Player> players = (Collection<Player>) KitpvpBased.getInstance().getServer().getOnlinePlayers();
        NamespacedKey inSpawn = new NamespacedKey(KitpvpBased.getInstance(), "inSpawn");
        NamespacedKey kitLevel = new NamespacedKey(KitpvpBased.getInstance(), "kitLevel");
        NamespacedKey menuItem = new NamespacedKey(KitpvpBased.getInstance(), "menuItem");

        NamespacedKey armorerKey = new NamespacedKey(KitpvpBased.getInstance(), "armorer");
        NamespacedKey tricksterKey = new NamespacedKey(KitpvpBased.getInstance(), "trickster");
        NamespacedKey weaponsmithKey = new NamespacedKey(KitpvpBased.getInstance(), "weaponsmith");

        for (Player p : players) {
            ItemStack armorer = new ItemStack(Material.NETHERITE_INGOT);
            ItemMeta armorerItemMeta = armorer.getItemMeta();
            armorerItemMeta.getPersistentDataContainer().set(armorerKey, PersistentDataType.INTEGER, 1);
            armorerItemMeta.getPersistentDataContainer().set(menuItem, PersistentDataType.INTEGER, 1);
            armorerItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            armorerItemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.BLUE + "" + ChatColor.BOLD + "ARMORER");

            ItemStack trickster = new ItemStack(Material.BOW);
            ItemMeta tricksterItemMeta = trickster.getItemMeta();
            tricksterItemMeta.getPersistentDataContainer().set(tricksterKey, PersistentDataType.INTEGER, 1);
            tricksterItemMeta.getPersistentDataContainer().set(menuItem, PersistentDataType.INTEGER, 1);
            tricksterItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            tricksterItemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.BLUE + "" + ChatColor.BOLD + "TRICKSTER");

            ItemStack weaponsmith = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta weaponsmithItemMeta = weaponsmith.getItemMeta();
            weaponsmithItemMeta.getPersistentDataContainer().set(weaponsmithKey, PersistentDataType.INTEGER, 1);
            weaponsmithItemMeta.getPersistentDataContainer().set(menuItem, PersistentDataType.INTEGER, 1);
            weaponsmithItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            weaponsmithItemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.BLUE + "" + ChatColor.BOLD + "WEAPONSMITH");
            
            PersistentDataContainer data = p.getPersistentDataContainer();

            int selected = getArchetype(p);
            if (selected == 1) {
                armorerItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            }
            else if (selected == 2) {
                tricksterItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            }
            else {
                weaponsmithItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            }

            armorer.setItemMeta(armorerItemMeta);
            trickster.setItemMeta(tricksterItemMeta);
            weaponsmith.setItemMeta(weaponsmithItemMeta);

            if (p.getLocation().getY() > ceilingHeight) {
                data.set(kitLevel, PersistentDataType.INTEGER, 0);

                data.set(inSpawn, PersistentDataType.INTEGER, 1);
                PlayerInventory inv = p.getInventory();

                inv.setItem(3, armorer);
                inv.setItem(4, trickster);
                inv.setItem(5, weaponsmith);
            } else {
                data.set(inSpawn, PersistentDataType.INTEGER, 0);
            }

            if (p.getLocation().getY() == ceilingHeight) {
                p.getInventory().clear();
                data.set(kitLevel, PersistentDataType.INTEGER, 1);
            }
        }
    }


    // This is to simplify logic for adding enchantment glint to the selected classes item
    private static int getArchetype(Player p) {

        NamespacedKey playerArmorer = new NamespacedKey(KitpvpBased.getInstance(), "playerarmorer");
        NamespacedKey playerTrickster = new NamespacedKey(KitpvpBased.getInstance(), "playertrickster");
        NamespacedKey playerWeaponsmith = new NamespacedKey(KitpvpBased.getInstance(), "playerweaponsmith");

        if (p.getPersistentDataContainer().has(playerArmorer, PersistentDataType.INTEGER)) {
            return 1;
        }
        else if (p.getPersistentDataContainer().has(playerTrickster, PersistentDataType.INTEGER)) {
            return 2;
        }
        else {
            return 3;
        }

    }
}
