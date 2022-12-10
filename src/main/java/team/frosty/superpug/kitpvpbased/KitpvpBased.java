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

        getConfig().addDefault("arena.x1", 200);
        getConfig().addDefault("arena.z1", 200);
        getConfig().addDefault("arena.x2", 400);
        getConfig().addDefault("arena.z2", 400);
        getConfig().addDefault("arena.ceiling", 140);
        getConfig().options().copyDefaults(true);
        saveConfig();


        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new Archetypes(), this);

        Scheduler checkY = new Scheduler(CheckType.CHECK_Y_LEVEL);
        checkY.runTaskTimer(this, 0, 1);

//        Scheduler checkArch = new Scheduler(CheckType.CHECK_ARCHETYPE);
//        checkArch.runTaskTimer(this, 0, 20);

        this.getCommand("debug_add_pd").setExecutor(new DebugAddPD());
        this.getCommand("debug_get_player_pd").setExecutor(new DebugGetPlayerPD());
    }

    @Override
    public void onDisable() {

    }

    public static KitpvpBased getInstance() {
        return instance;
    }

}