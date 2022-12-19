package team.frosty.superpug.kitpvpbased.utils;

import org.bukkit.entity.Player;

public abstract class Archetype {

    protected int level;
    protected Player plr;

    abstract protected void applyLevel();

    abstract protected void level2();
    abstract protected void level3();
    abstract protected void level4();
    abstract protected void level5();
    abstract protected void level6();
    abstract protected void level7();
    abstract protected void level8();
    abstract protected void levelUber();


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
