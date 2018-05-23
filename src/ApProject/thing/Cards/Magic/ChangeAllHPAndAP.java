package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class ChangeAllHPAndAP extends Magic {
    private int currentBattlerChangeAPAmount;
    private int currentBattlerChangeHPAmount;
    private int enemyBattlerChangeAPAmount;
    private int enemyBattlerChangeHPAmount;

    public ChangeAllHPAndAP(int currentBattlerChangeAPAmount, int currentBattlerChangeHPAmount, int enemyBattlerChangeAPAmount, int enemyBattlerChangeHPAmount, String magicDetails){
        this.currentBattlerChangeAPAmount = currentBattlerChangeAPAmount;
        this.currentBattlerChangeHPAmount = currentBattlerChangeHPAmount;
        this.enemyBattlerChangeAPAmount = enemyBattlerChangeAPAmount;
        this.enemyBattlerChangeHPAmount = enemyBattlerChangeHPAmount;
        this.magicDetails = magicDetails;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler) {
            for (MonsterCardsInBattle monsterCardsInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
                monsterCardsInBattle.changeAttackPoint(enemyBattlerChangeAPAmount);
                monsterCardsInBattle.changeHealthPoint(enemyBattlerChangeHPAmount);
                monsterCardsInBattle.checkDeath();
            }
            for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
                monsterCardsInBattle.changeAttackPoint(currentBattlerChangeAPAmount);
                monsterCardsInBattle.changeHealthPoint(currentBattlerChangeHPAmount);
                monsterCardsInBattle.checkDeath();
            }
    }
}
