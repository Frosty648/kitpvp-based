package team.frosty.superpug.kitpvpbased;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import team.frosty.superpug.kitpvpbased.archetypes.Archetypes;
import team.frosty.superpug.kitpvpbased.archetypes.CheckSpawn;
import team.frosty.superpug.kitpvpbased.commands.DebugAddPD;
import team.frosty.superpug.kitpvpbased.commands.DebugGetPlayerPD;
import team.frosty.superpug.kitpvpbased.utils.ZoneUtils;
import team.frosty.superpug.kitpvpbased.zones.ZoneCheck;
import team.frosty.superpug.kitpvpbased.zones.DrawZones;


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
        getConfig().addDefault("arena.floor", 60);
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Registering event modules
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new TeleBow(), this);
        getServer().getPluginManager().registerEvents(new Archetypes(), this);
        //getServer().getPluginManager().registerEvents(new Levels(), this);

        // Creating original zones
        int x1 = getConfig().getInt("arena.x1");
        int x2 = getConfig().getInt("arena.x2");
        int z1 = getConfig().getInt("arena.z1");
        int z2 = getConfig().getInt("arena.z2");
        int floor = getConfig().getInt("arena.floor");
        ZoneCheck.circleLocations.put(ZoneUtils.genRandomCircleLoc(x1, x2, z1, z1, floor), 0d);
        ZoneCheck.circleLocations.put(ZoneUtils.genRandomCircleLoc(x1, x2, z1, z1, floor), 0d);

        // Adding in scheduler instances
        new CheckSpawn().runTaskTimer(this, 0, 1);
        new ZoneCheck().runTaskTimer(this,0,5L);
        new DrawZones().runTaskTimer(this,0,5L);

        // Registering commands (all for debug)
        this.getCommand("debug_add_pd").setExecutor(new DebugAddPD());
        this.getCommand("debug_get_player_pd").setExecutor(new DebugGetPlayerPD());
    }


    // Instance getter so we can use the plugin class methods in event classes.
    public static KitpvpBased getInstance() {
        return instance;
    }

}
