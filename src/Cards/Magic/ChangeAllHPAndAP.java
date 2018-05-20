package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class ChangeAllHPAndAP extends Magic {
    private int currentBattlerChangeAPAmount;
    private int currentBattlerChangeHPAmount;
    private int enemyBattlerChangeAPAmount;
    private int enemyBattlerChangeHPAmount;

    ChangeAllHPAndAP(int currentBattlerChangeAPAmount, int currentBattlerChangeHPAmount, int enemyBattlerChangeAPAmount, int enemyBattlerChangeHPAmount){
        this.currentBattlerChangeAPAmount = currentBattlerChangeAPAmount;
        this.currentBattlerChangeHPAmount = currentBattlerChangeHPAmount;
        this.enemyBattlerChangeAPAmount = enemyBattlerChangeAPAmount;
        this.enemyBattlerChangeHPAmount = enemyBattlerChangeHPAmount;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(MonsterCardsInBattle nullTarget,Battler currentBattler, Battler enemyBattler) {
        if(nullTarget == null) {
            for (MonsterCardsInBattle monsterCardsInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
                monsterCardsInBattle.changeAttackPoint(enemyBattlerChangeAPAmount);
                monsterCardsInBattle.changeHealthPoint(enemyBattlerChangeHPAmount);
                monsterCardsInBattle.checkDeath(enemyBattler);
            }
            for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
                monsterCardsInBattle.changeAttackPoint(currentBattlerChangeAPAmount);
                monsterCardsInBattle.changeHealthPoint(currentBattlerChangeHPAmount);
                monsterCardsInBattle.checkDeath(currentBattler);
            }
        }
    }
}
