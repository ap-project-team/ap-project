package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class ChangeHPAndAP extends Magic {
    private int changeHPAmount;
    private int changeAPAmount;

    public ChangeHPAndAP(int changeAPAmount, int changeHPAmount){
        this.changeAPAmount = changeAPAmount;
        this.changeHPAmount = changeHPAmount;
        this.magicType = MagicType.WITHTARGET;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
            monsterCardsInBattle.changeAttackPoint(changeAPAmount);
            monsterCardsInBattle.changeHealthPoint(changeHPAmount);
            monsterCardsInBattle.checkDeath();
    }
}
