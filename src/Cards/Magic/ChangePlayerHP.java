package src.Cards.Magic;

import src.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class ChangePlayerHP extends Magic {
    private int currentBattlerChangeAmount;
    private int enemyBattlerChangeAmount;

    public ChangePlayerHP(int currentBattlerChangeAmount, int enemyBattlerChangeAmount){
        this.currentBattlerChangeAmount = currentBattlerChangeAmount;
        this.enemyBattlerChangeAmount = enemyBattlerChangeAmount;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic( Battler currentBattler, Battler enemyBattler) {
            currentBattler.changeHealthPoint(currentBattlerChangeAmount);
            enemyBattler.changeHealthPoint(enemyBattlerChangeAmount);
    }
}
