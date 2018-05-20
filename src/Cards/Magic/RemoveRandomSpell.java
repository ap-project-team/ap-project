package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class RemoveRandomSpell extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }
    public void doMagic(MonsterCardsInBattle nullTarget, Battler currentBattler, Battler enemyBattler) {
        if(nullTarget == null)
            enemyBattler.getSpellField().remove(enemyBattler.getSpellField().getRandomSpell());
    }
}
