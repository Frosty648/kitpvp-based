package team.frosty.superpug.kitpvpbased.archetypes;

import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import team.frosty.superpug.kitpvpbased.KitpvpBased;


public class ArchetypeSelection implements Listener {

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {

        ItemStack is = e.getItemDrop().getItemStack();
        PersistentDataContainer data = is.getItemMeta().getPersistentDataContainer();
        NamespacedKey menuItem = new NamespacedKey(KitpvpBased.getInstance(), "menuItem");

        if (!data.has(menuItem, PersistentDataType.INTEGER))
            return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onUse(PlayerInteractEvent e) {

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR)
            return;

        ItemStack item = e.getItem();
        ItemMeta im = item.getItemMeta();
        PersistentDataContainer data = im.getPersistentDataContainer();

        NamespacedKey armorer = new NamespacedKey(KitpvpBased.getInstance(), "armorer");
        NamespacedKey trickster = new NamespacedKey(KitpvpBased.getInstance(), "trickster");
        NamespacedKey weaponsmith = new NamespacedKey(KitpvpBased.getInstance(), "weaponsmith");

        NamespacedKey playerArmorer = new NamespacedKey(KitpvpBased.getInstance(), "playerArmorer");
        NamespacedKey playerTrickster = new NamespacedKey(KitpvpBased.getInstance(), "playerTrickster");
        NamespacedKey playerWeaponsmith = new NamespacedKey(KitpvpBased.getInstance(), "playerWeaponsmith");


        if (data.has(armorer, PersistentDataType.INTEGER)) {
            e.setCancelled(true);
            e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.C));
            e.getPlayer().sendMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "ARCHETYPES" + ChatColor.YELLOW + "] " + ChatColor.GREEN + "You have selected the Armorer Class!");
            e.getPlayer().getPersistentDataContainer().set(playerArmorer, PersistentDataType.INTEGER, 1);
            e.getPlayer().getPersistentDataContainer().remove(playerTrickster);
            e.getPlayer().getPersistentDataContainer().remove(playerWeaponsmith);
        } else if (data.has(trickster, PersistentDataType.INTEGER)) {
            e.setCancelled(true);
            e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.C));
            e.getPlayer().sendMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "ARCHETYPES" + ChatColor.YELLOW + "] " + ChatColor.GREEN + "You have selected the Trickster Class!");
            e.getPlayer().getPersistentDataContainer().set(playerTrickster, PersistentDataType.INTEGER, 1);
            e.getPlayer().getPersistentDataContainer().remove(playerArmorer);
            e.getPlayer().getPersistentDataContainer().remove(playerWeaponsmith);
        } else if (data.has(weaponsmith, PersistentDataType.INTEGER)) {
            e.setCancelled(true);
            e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.C));
            e.getPlayer().sendMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "ARCHETYPES" + ChatColor.YELLOW + "] " + ChatColor.GREEN + "You have selected the Weaponsmith Class!");
            e.getPlayer().getPersistentDataContainer().set(playerWeaponsmith, PersistentDataType.INTEGER, 1);
            e.getPlayer().getPersistentDataContainer().remove(playerTrickster);
            e.getPlayer().getPersistentDataContainer().remove(playerArmorer);
        }
    }

    @EventHandler
    public void onClickItem(InventoryClickEvent e) {
        ItemStack is = e.getCurrentItem();
        ItemMeta meta = is.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();

        NamespacedKey menuItem = new NamespacedKey(KitpvpBased.getInstance(), "menuItem");

        if (data.has(menuItem, PersistentDataType.INTEGER)) {
            e.setCancelled(true);
        }
    }
}
