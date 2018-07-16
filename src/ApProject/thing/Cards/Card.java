package src.ApProject.thing.Cards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Thing;


abstract public class Card extends Thing{
    protected static String cardType;
    protected int manaCost;
    protected String info;

    {
        thingType = "CARD";
    }


    public static String getCardType() {
        return cardType;
    }

    abstract public void play(Battler currentBattler, Battler enemyBattler, int slotNum);

    public int getManaCost() {
        return manaCost;
    }

    public String getInfo() {
        return info;
    }
}
