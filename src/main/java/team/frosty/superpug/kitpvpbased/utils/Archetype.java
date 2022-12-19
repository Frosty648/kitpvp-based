package team.frosty.superpug.kitpvpbased.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import team.frosty.superpug.kitpvpbased.KitpvpBased;

public abstract class Archetype {

    protected Player plr;

    abstract protected void applyLevel();

    abstract protected void level1();
    abstract protected void level2();
    abstract protected void level3();
    abstract protected void level4();
    abstract protected void level5();
    abstract protected void level6();
    abstract protected void level7();
    abstract protected void level8();
    abstract protected void levelUber();


    public int getLevel() {
        PersistentDataContainer data = this.plr.getPersistentDataContainer();
        NamespacedKey levelKey = new NamespacedKey(KitpvpBased.getInstance(), "kitLevel");
        return data.get(levelKey, PersistentDataType.INTEGER);
    }

    public void setLevel(int level) {
        PersistentDataContainer data = this.plr.getPersistentDataContainer();
        NamespacedKey levelKey = new NamespacedKey(KitpvpBased.getInstance(), "kitLevel");
        data.set(levelKey, PersistentDataType.INTEGER, level);
        this.applyLevel();
    }

    public void levelUp() {
        this.setLevel(this.getLevel() + 1);
        this.applyLevel();
    }
}
