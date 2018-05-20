package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.Cards.Spells.Spells;
import src.ToDoPackage.Battler;

public class RemoveAllSpells extends Magic{
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
        for(Spells spell : enemyBattler.getSpellField().getSpells()){
            enemyBattler.getSpellField().remove(spell);
            currentBattler.getHand().add(spell);
        }
    }
}
