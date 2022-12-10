package team.frosty.superpug.kitpvpbased;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class Scheduler extends BukkitRunnable {

    private CheckType type;

    public Scheduler(CheckType type) {
        this.type = type;
    }

    @Override
    public void run() {
        switch(this.type) {
            case CHECK_Y_LEVEL -> {
                int ceilingHeight = KitpvpBased.getInstance().getConfig().getInt("arena.ceiling");

                Collection<Player> players = (Collection<Player>) KitpvpBased.getInstance().getServer().getOnlinePlayers();
                NamespacedKey inSpawn = new NamespacedKey(KitpvpBased.getInstance(), "inSpawn");

                for (Player p : players) {
                    PersistentDataContainer data = p.getPersistentDataContainer();

                    if (p.getLocation().getY() > ceilingHeight) {
                        data.set(inSpawn, PersistentDataType.INTEGER, 1);
                        PlayerInventory inv = p.getInventory();

                        NamespacedKey armorerKey = new NamespacedKey(KitpvpBased.getInstance(), "armorer");
                        NamespacedKey tricksterKey = new NamespacedKey(KitpvpBased.getInstance(), "trickster");
                        NamespacedKey weaponsmithKey = new NamespacedKey(KitpvpBased.getInstance(), "weaponsmith");

                        ItemStack armorer = new ItemStack(Material.NETHERITE_INGOT);
                        ItemMeta armorerItemMeta = armorer.getItemMeta();
                        armorerItemMeta.getPersistentDataContainer().set(armorerKey, PersistentDataType.INTEGER, 1);
                        armorer.setItemMeta(armorerItemMeta);

                        ItemStack trickster = new ItemStack(Material.BOW);
                        ItemMeta tricksterItemMeta = trickster.getItemMeta();
                        tricksterItemMeta.getPersistentDataContainer().set(tricksterKey, PersistentDataType.INTEGER, 1);
                        trickster.setItemMeta(tricksterItemMeta);

                        ItemStack weaponsmith = new ItemStack(Material.DIAMOND_SWORD);
                        ItemMeta weaponsmithItemMeta = weaponsmith.getItemMeta();
                        weaponsmithItemMeta.getPersistentDataContainer().set(weaponsmithKey, PersistentDataType.INTEGER, 1);
                        weaponsmith.setItemMeta(weaponsmithItemMeta);

                        inv.setItem(3, armorer);
                        inv.setItem(4, trickster);
                        inv.setItem(5, weaponsmith);
                    } else {
                        data.set(inSpawn, PersistentDataType.INTEGER, 0);
                    }
                }
            }

            case CHECK_ARCHETYPE -> {

                NamespacedKey playerArmorer = new NamespacedKey(KitpvpBased.getInstance(), "playerArmorer");
                NamespacedKey playerTrickster = new NamespacedKey(KitpvpBased.getInstance(), "playerTrickster");
                NamespacedKey playerWeaponsmith = new NamespacedKey(KitpvpBased.getInstance(), "playerWeaponsmith");

                for (Player plr : KitpvpBased.getInstance().getServer().getOnlinePlayers()) {
                    if (plr.getPersistentDataContainer().has(playerArmorer, PersistentDataType.INTEGER)) {
                        Bukkit.broadcastMessage(plr.getDisplayName() + " is armorer!!!");
                    } else {
                        Bukkit.broadcastMessage((plr.getDisplayName()) + " has no class!");
                    }
                }
            }
        }
    }

}
