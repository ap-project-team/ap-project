package src.ApProject.thing.Cards;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Thing;



abstract public class Card extends Thing{
    protected String cardType;
    protected int manaCost;
    protected String info;

    {
        thingType = "CARD";
    }


    public String getCardType() {
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
