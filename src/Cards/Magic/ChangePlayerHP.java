package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class ChangePlayerHP extends Magic {
    private int currentBattlerChangeAmount;
    private int enemyBattlerChangeAmount;

    ChangePlayerHP(int currentBattlerChangeAmount, int enemyBattlerChangeAmount){
        this.currentBattlerChangeAmount = currentBattlerChangeAmount;
        this.enemyBattlerChangeAmount = enemyBattlerChangeAmount;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(MonsterCardsInBattle nullTarget, Battler currentBattler, Battler enemyBattler) {
        if(nullTarget == null) {
            currentBattler.changeHealthPoint(currentBattlerChangeAmount);
            enemyBattler.changeHealthPoint(enemyBattlerChangeAmount);
        }
    }
}
