package team.frosty.superpug.kitpvpbased.archetypes;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import team.frosty.superpug.kitpvpbased.utils.Archetype;

public class Weaponsmith extends Archetype {
    public Weaponsmith(Player plr) {
        this.plr = plr;
    }

    private String[] levelMessages = {
            "You have been awarded with a chain helmet.",
            "You have been awarded with a bow.",
            "You have been awarded with a diamond sword.",
            "You have been awarded with a chain chest-plate and boots.",
            "You have been awarded with a chain helmet and leggings.",
            "You have been awarded with an iron chest-plate.",
            "You have been awarded with a diamond axe, a netherite sword, and your bow gained infinity.",
            "You were granted a powerful uberdrop for overleveling!"
    };

    @Override
    protected void applyLevel() {
        switch (this.getLevel()) {
            case 2 -> level2();
            case 3 -> level3();
            case 4 -> level4();
            case 5 -> level5();
            case 6 -> level6();
            case 7 -> level7();
            case 8 -> level8();
            case 9 -> levelUber();
        }
        this.plr.sendMessage(ChatColor.YELLOW + "[WEAPONSMITH LEVEL " + this.getLevel() + "] " + ChatColor.GREEN + levelMessages[this.getLevel()]);
        this.plr.playNote(this.plr.getLocation(), Instrument.BELL, Note.natural(1, Note.Tone.C));
    }

    @Override
    protected void level1() {
        
    }

    @Override
    protected void level2() {
        PlayerInventory newInv = this.plr.getInventory();
        newInv.clear();
//        newInv.addItem()
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
