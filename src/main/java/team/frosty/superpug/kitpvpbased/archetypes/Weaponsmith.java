package team.frosty.superpug.kitpvpbased.archetypes;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import team.frosty.superpug.kitpvpbased.utils.Archetype;

public class Weaponsmith extends Archetype {
    public Weaponsmith(Player plr) {
        this.plr = plr;
        this.level = 1;
    }

    @Override
    protected void applyLevel() {
        if (this.level > 1) level2();
        if (this.level > 2) level3();
        if (this.level > 3) level4();
        if (this.level > 4) level5();
        if (this.level > 5) level6();
        if (this.level > 6) level7();
        if (this.level > 7) level8();
        if (this.level > 8) levelUber();
        this.plr.sendMessage(ChatColor.YELLOW + "[WEAPONSMITH LEVEL " + this.level + "] " + ChatColor.GREEN + "");
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

}
