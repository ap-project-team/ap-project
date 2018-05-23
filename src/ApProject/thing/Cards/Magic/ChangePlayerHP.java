package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class ChangePlayerHP extends Magic {
    private int currentBattlerChangeAmount;
    private int enemyBattlerChangeAmount;

    public ChangePlayerHP(int currentBattlerChangeAmount, int enemyBattlerChangeAmount, String magicDetails){
        this.currentBattlerChangeAmount = currentBattlerChangeAmount;
        this.enemyBattlerChangeAmount = enemyBattlerChangeAmount;
        this.magicType = MagicType.WITHOUTTARGET;
        this.magicDetails = magicDetails;
    }

    public void doMagic( Battler currentBattler, Battler enemyBattler) {
            currentBattler.changeHealthPoint(currentBattlerChangeAmount);
            enemyBattler.changeHealthPoint(enemyBattlerChangeAmount);
    }
}
