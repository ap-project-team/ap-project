package src.ApProject.thing.Cards.Spells;

import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ToDoPackage.Battler;

import java.util.ArrayList;

abstract public class Spells extends Cards{
    protected SpellType spellType;
    protected Battler currentBattler;
    protected Battler enemyBattler;
    protected ArrayList<Magic> magics = new ArrayList<>();
    abstract public void play(Battler currentBattler, Battler enemyBattler, int slotNum);

    {
        cardType = "SPELLCARD";
    }
}
