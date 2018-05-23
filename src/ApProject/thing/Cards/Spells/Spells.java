package src.ApProject.thing.Cards.Spells;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.Magic;

import java.util.ArrayList;

abstract public class Spells extends Card {
    protected SpellType spellType;
    protected Battler currentBattler;
    protected Battler enemyBattler;
    protected ArrayList<Magic> magics = new ArrayList<>();
    abstract public void play(Battler currentBattler, Battler enemyBattler, int slotNum);

    {
        cardType = "SPELLCARD";
    }
}
