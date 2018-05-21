package src.Cards.Spells;

import src.Cards.Cards;
import src.Cards.Magic.Magic;
import src.ToDoPackage.Battler;

import java.util.ArrayList;

abstract public class Spells extends Cards{
    protected String spellName;
    protected int manaCost;
    protected SpellType spellType;
    protected Battler currentBattler;
    protected Battler enemyBattler;
    protected ArrayList<Magic> magics = new ArrayList<>();
    abstract public void play(Battler currentBattler, Battler enemyBattler, int slotNum);
}
