package src.Cards.Magic;

import src.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.Cards.MonsterCards.Tribe;
import src.ToDoPackage.Battler;

public class DealDamageBasedOnTribe extends Magic{
    private int currentBattlerChangeHPAmount;
    private int enemyBattlerChangeHPAmount;
    private Tribe tribe;

   public DealDamageBasedOnTribe(int currentBattlerChangeHPAmount, int enemyBattlerChangeHPAmount, Tribe tribe){
        this.currentBattlerChangeHPAmount = currentBattlerChangeHPAmount;
        this.enemyBattlerChangeHPAmount = enemyBattlerChangeHPAmount;
        this.tribe = tribe;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic( Battler currentBattler, Battler enemyBattler) {
            for (MonsterCardsInBattle monsterCardsInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
                if(monsterCardsInBattle.getTribe() != tribe) {
                    monsterCardsInBattle.changeHealthPoint(enemyBattlerChangeHPAmount);
                    monsterCardsInBattle.checkDeath();
                }
            }
            for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
                if(monsterCardsInBattle.getTribe() != tribe) {
                    monsterCardsInBattle.changeHealthPoint(currentBattlerChangeHPAmount);
                    monsterCardsInBattle.checkDeath();
                }
            }
    }
}
