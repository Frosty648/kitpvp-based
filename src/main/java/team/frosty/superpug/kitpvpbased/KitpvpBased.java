package team.frosty.superpug.kitpvpbased;

import org.bukkit.plugin.java.JavaPlugin;

// Fire
public final class KitpvpBased extends JavaPlugin {
    private static KitpvpBased instance;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new TeleBow(), this);
        instance = this;
    }

    @Override
    public void onDisable() {

    }

    public static KitpvpBased getInstance(){
        return instance;
    }
}
