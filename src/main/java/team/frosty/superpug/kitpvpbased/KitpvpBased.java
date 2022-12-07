package team.frosty.superpug.kitpvpbased;

import org.bukkit.plugin.java.JavaPlugin;

// Fire
public final class KitpvpBased extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new TeleBow(), this);
    }

    @Override
    public void onDisable() {

    }
}
