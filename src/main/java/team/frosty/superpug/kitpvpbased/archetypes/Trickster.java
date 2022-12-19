package team.frosty.superpug.kitpvpbased.archetypes;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import team.frosty.superpug.kitpvpbased.KitpvpBased;
import team.frosty.superpug.kitpvpbased.utils.Archetype;

public class Trickster extends Archetype {

    public Trickster(Player plr){
        this.plr = plr;
    }


    @Override
    public void applyLevel(){
        switch ((this.getLevel())){
            case 2 -> level2();
            case 3 -> level3();
            case 4 -> level4();
            case 5 -> level5();
            case 6 -> level6();
            case 7 -> level7();
            case 8 -> level8();
            case 9 -> levelUber();
        }
    }

    @Override
    protected void level1() {

    }

    @Override
    protected void level2() {

    }

    @Override
    protected void level3() {

    }

    @Override
    protected void level4() {

    }

    @Override
    protected void level5() {

    }

    @Override
    protected void level6() {

    }

    @Override
    protected void level7() {

    }

    @Override
    protected void level8() {

    }

    @Override
    protected void levelUber() {

    }

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
