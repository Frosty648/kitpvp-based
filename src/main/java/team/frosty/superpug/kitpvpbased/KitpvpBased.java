package team.frosty.superpug.kitpvpbased;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import team.frosty.superpug.kitpvpbased.archetypes.Archetypes;
import team.frosty.superpug.kitpvpbased.archetypes.CheckSpawn;
import team.frosty.superpug.kitpvpbased.commands.DebugAddPD;
import team.frosty.superpug.kitpvpbased.commands.DebugGetPlayerPD;
import team.frosty.superpug.kitpvpbased.zones.ZoneCheck;
import team.frosty.superpug.kitpvpbased.zones.DrawZones;


public final class KitpvpBased extends JavaPlugin {
    private static KitpvpBased instance;

    @Override
    public void onEnable() {
        instance = this;

        ZoneCheck.circleLocations.put(new Location(Bukkit.getWorlds().get(0),71, 65, -9 ), 0d);
        ZoneCheck.circleLocations.put(new Location(Bukkit.getWorlds().get(0),82, 65, -23 ),0d);
        new ZoneCheck().runTaskTimer(this,0,5L);
        new DrawZones().runTaskTimer(this,0,5L);

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
        getServer().getPluginManager().registerEvents(new TeleBow(), this);
        getServer().getPluginManager().registerEvents(new Archetypes(), this);
        //getServer().getPluginManager().registerEvents(new Levels(), this);

        // Adding in scheduler instances (SUBJECT TO CHANGE THIS IS NOT WELL DESIGNED AND IS NOT EXPANDABLE UPON)
        CheckSpawn checkY = new CheckSpawn();
        checkY.runTaskTimer(this, 0, 1);

        // Registering commands (all for debug)
        this.getCommand("debug_add_pd").setExecutor(new DebugAddPD());
        this.getCommand("debug_get_player_pd").setExecutor(new DebugGetPlayerPD());
    }


    // Instance getter so we can use the plugin class methods in event classes.
    public static KitpvpBased getInstance() {
        return instance;
    }

}
