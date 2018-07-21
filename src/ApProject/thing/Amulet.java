package src.ApProject.thing;

import javafx.scene.image.*;
import src.ApProject.battle.battler.Battler;

public class Amulet extends Thing{
    int increaseHP;
    int increaseMP;
    boolean attack;
    public Amulet(String name, int price, int increaseHP, int increaseMP, boolean attack) {
        this.name = name;
        this.price = price;
        this.increaseHP = increaseHP;
        this.increaseMP = increaseMP;
        this.attack = attack;
        thingType = "ITEM";
    }

    public static Amulet buildAmulet(String name){
        if (name.equals("IronPendant")) return new Amulet(name, 2000, 500, 0, false);
        if (name.equals("GoldPendant")) return new Amulet(name, 4000, 1000, 0, false);
        if (name.equals("DiamondPendant")) return new Amulet(name, 8000, 2000, 0, false);
        if (name.equals("IronRing")) return new Amulet(name, 2000, 0, 1, false);
        if (name.equals("GoldRing")) return new Amulet(name, 4000, 0, 2, false);
        if (name.equals("DiamondRing")) return new Amulet(name, 8000, 0, 3, false);
        if (name.equals("DemonKing’sCrown")) return new Amulet(name, 0, 0, 0, true);
        return null;
    }

    public String getInfo(){
        if (attack) return ("Decrease All Incoming Damages by 20%");
        else if (increaseMP == 0) return " Increase Player’s Max HP by "+increaseHP;
        else if (increaseHP == 0) return " Increase Player’s Max MP by "+increaseMP;
        else return " Increase Player’s Max HP by " + increaseHP + " Increase Player’s Max MP by " + increaseMP;
    }

    public int getIncreaseHP() {
        return increaseHP;
    }

    public int getIncreaseMP() {
        return increaseMP;
    }

    public boolean canAttack() {
        return attack;
    }
}
