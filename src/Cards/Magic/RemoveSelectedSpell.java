package src.Cards.Magic;

import src.Cards.Spells.Spells;
import src.ToDoPackage.Battler;

public class RemoveSelectedSpell extends Magic{
    {
        this.magicType = MagicType.SELECTSPELL;
    }
    public void doMagic(Spells spells, Battler currentBattler, Battler enemyBattler) {
        enemyBattler.getSpellField().remove(spells);
    }
}
