package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class ChangeHPAndAP extends Magic {
    private int changeHPAmount;
    private int changeAPAmount;

    ChangeHPAndAP(int changeAPAmount, int changeHPAmount){
        this.changeAPAmount = changeAPAmount;
        this.changeHPAmount = changeHPAmount;
        this.magicType = MagicType.WITHTARGET;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
        if(monsterCardsInBattle != null) {
            monsterCardsInBattle.changeAttackPoint(changeAPAmount);
            monsterCardsInBattle.changeHealthPoint(changeHPAmount);
            monsterCardsInBattle.checkDeath(enemyBattler);
        }
    }
}
