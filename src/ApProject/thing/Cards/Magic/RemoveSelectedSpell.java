package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.Spells.Spells;
import src.ToDoPackage.Battler;

public class RemoveSelectedSpell extends Magic{
    {
        this.magicType = MagicType.SELECTSPELL;
    }

    RemoveSelectedSpell(String magicDetails){
        this.magicDetails = magicDetails;
    }

    public void doMagic(Spells spells, Battler currentBattler, Battler enemyBattler) {
        enemyBattler.getSpellField().remove(spells);
    }
}
