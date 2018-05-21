package src.Cards.Magic;

import src.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.Cards.Spells.Spells;
import src.ToDoPackage.Battler;

public class RemoveAllSpells extends Magic{
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler) {
        for(Spells spell : enemyBattler.getSpellField().getSpells()){
            enemyBattler.getSpellField().remove(spell);
            currentBattler.getHand().add(spell);
        }
    }
}
