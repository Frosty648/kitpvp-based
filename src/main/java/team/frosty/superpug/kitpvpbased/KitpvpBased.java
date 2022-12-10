package team.frosty.superpug.kitpvpbased;

import org.bukkit.plugin.java.JavaPlugin;
import team.frosty.superpug.kitpvpbased.Commands.DebugAddPD;


public final class KitpvpBased extends JavaPlugin {

    private static KitpvpBased instance;

    @Override
    public void onEnable() {

        instance = this;

        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new Archetypes(), this);

        Scheduler checkY = new Scheduler(CheckType.CHECK_Y_LEVEL);
        checkY.runTaskTimer(this, 0, 1);

//        Scheduler checkArch = new Scheduler(CheckType.CHECK_ARCHETYPE);
//        checkArch.runTaskTimer(this, 0, 20);

        this.getCommand("debug_add_pd").setExecutor(new DebugAddPD());
    }

    @Override
    public void onDisable() {

    }

    public static KitpvpBased getInstance() {
        return instance;
    }

}