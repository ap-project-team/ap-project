package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.Cards.MonsterCards.Tribe;
import src.ToDoPackage.Battler;

public class DealDamageBasedOnTribe extends Magic{
    private int currentBattlerChangeHPAmount;
    private int enemyBattlerChangeHPAmount;
    private Tribe tribe;

   DealDamageBasedOnTribe(int currentBattlerChangeHPAmount, int enemyBattlerChangeHPAmount, Tribe tribe){
        this.currentBattlerChangeHPAmount = currentBattlerChangeHPAmount;
        this.enemyBattlerChangeHPAmount = enemyBattlerChangeHPAmount;
        this.tribe = tribe;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(MonsterCardsInBattle nullTarget, Battler currentBattler, Battler enemyBattler) {
        if(nullTarget == null) {
            for (MonsterCardsInBattle monsterCardsInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
                if(monsterCardsInBattle.getTribe() != tribe) {
                    monsterCardsInBattle.changeHealthPoint(enemyBattlerChangeHPAmount);
                    monsterCardsInBattle.checkDeath(enemyBattler);
                }
            }
            for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
                if(monsterCardsInBattle.getTribe() != tribe) {
                    monsterCardsInBattle.changeHealthPoint(currentBattlerChangeHPAmount);
                    monsterCardsInBattle.checkDeath(currentBattler);
                }
            }
        }
    }
}
