package team.frosty.superpug.kitpvpbased;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import team.frosty.superpug.kitpvpbased.Commands.DebugAddPD;
import team.frosty.superpug.kitpvpbased.Commands.DebugGetPlayerPD;


public final class KitpvpBased extends JavaPlugin {

    private static KitpvpBased instance;

    @Override
    public void onEnable() {

        instance = this;

        // Config stuff so we can find spawn and add the charging circles later down the line.
        getConfig().addDefault("arena.x1", 200);
        getConfig().addDefault("arena.z1", 200);
        getConfig().addDefault("arena.x2", 400);
        getConfig().addDefault("arena.z2", 400);
        getConfig().addDefault("arena.ceiling", 139);
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Registering event modules
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new Archetypes(), this);

        // Adding in scheduler instances (SUBJECT TO CHANGE THIS IS NOT WELL DESIGNED AND IS NOT EXPANDABLE UPON)
        Scheduler checkY = new Scheduler(CheckType.CHECK_Y_LEVEL);
        checkY.runTaskTimer(this, 0, 1);

        //  Scheduler checkArch = new Scheduler(CheckType.CHECK_ARCHETYPE);
        //  checkArch.runTaskTimer(this, 0, 20);

        // Registering commands (all for debug)
        this.getCommand("debug_add_pd").setExecutor(new DebugAddPD());
        this.getCommand("debug_get_player_pd").setExecutor(new DebugGetPlayerPD());
    }

    // Instance getter so we can use the plugin class methods in event classes.
    public static KitpvpBased getInstance() {
        return instance;
    }

}