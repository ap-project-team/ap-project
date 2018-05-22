package src.ApProject.thing.Cards;

import src.ApProject.thing.Thing;
import src.ToDoPackage.Battler;

abstract public class Cards extends Thing{
    protected static String cardType;
    protected int manaCost;

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
}
