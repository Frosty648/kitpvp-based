package team.frosty.superpug.kitpvpbased;

import org.bukkit.plugin.java.JavaPlugin;

// Fire
public final class KitpvpBased extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Chat(), this);
    }

    @Override
    public void onDisable() {

    }
}
