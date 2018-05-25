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

    public SpellType getSpellType() {
        return spellType;
    }
    public String getInfo() {
        return this.name +" Info" + "\n" + "Name : " + this.name + "\n" + "MP cost : " + manaCost + "\n" + "Card Type : " + spellType + "\n" +  "Spell Details : "
                + "\n" + magics.get(0).getmagicDetails();
    }
    public String getMagicDetails(){
        return magics.get(0).getmagicDetails();
    }
}
