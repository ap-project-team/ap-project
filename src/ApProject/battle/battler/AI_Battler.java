package src.ApProject.battle.battler;

import src.ApProject.thing.Amulet;
import src.ApProject.thing.Card;
import src.ApProject.thing.Item;

import java.util.ArrayList;

public class AI_Battler extends Battler {
    public AI_Battler(String name, Card[] realDeck, ArrayList<Item> realItems, Amulet realAmulet) {
        super(name, realDeck, realItems, realAmulet);
        type = "ENEMY";
    }

}
