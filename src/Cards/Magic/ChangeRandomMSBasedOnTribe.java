package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.Cards.MonsterCards.Tribe;
import src.ToDoPackage.Battler;

public class ChangeRandomMSBasedOnTribe extends Magic {
    private Tribe tribe;
    private int changeAPAmount;
    private int changeHPAmount;

    ChangeRandomMSBasedOnTribe(int changeAPAmount, int changeHPAmount, Tribe tribe){
        this.tribe =  tribe;
        this.changeAPAmount = changeAPAmount;
        this.changeHPAmount = changeHPAmount;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
        if(monsterCardsInBattle == null) {
            MonsterCardsInBattle targetMonsterCard = currentBattler.getMonsterField().getRandomMonsterCardInBattleByType(tribe);
            if (targetMonsterCard != null) {
                targetMonsterCard.changeAttackPoint(changeAPAmount);
                targetMonsterCard.changeHealthPoint(changeHPAmount);
            }
        }
    }
}
