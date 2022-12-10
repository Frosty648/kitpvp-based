package team.frosty.superpug.kitpvpbased;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class Scheduler extends BukkitRunnable {

    private CheckType type;

    public Scheduler(CheckType type) {
        this.type = type;
    }

    @Override
    public void run() {
        switch(this.type) {
            case CHECK_Y_LEVEL -> {
                //Bukkit.broadcastMessage("Check Y Level Was Executed!");
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
