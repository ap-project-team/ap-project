package src.Cards.Magic;

import src.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class RemoveRandomSpell extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }
    public void doMagic(MonsterCardsInBattle nullTarget, Battler currentBattler, Battler enemyBattler) {
        enemyBattler.getSpellField().remove(enemyBattler.getSpellField().getRandomSpell());
    }
}
