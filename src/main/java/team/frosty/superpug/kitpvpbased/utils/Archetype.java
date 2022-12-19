package team.frosty.superpug.kitpvpbased.utils;

import org.bukkit.entity.Player;

abstract class Archetype {

    private int level;
    private Player plr;

    abstract public void applyLevel();

    abstract void level2();
    abstract void level3();
    abstract void level4();
    abstract void level5();
    abstract void level6();
    abstract void level7();
    abstract void level8();
    abstract void levelUber();


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        this.applyLevel();
    }

    public void levelUp() {
        this.level += 1;
        this.applyLevel();
    }
}
