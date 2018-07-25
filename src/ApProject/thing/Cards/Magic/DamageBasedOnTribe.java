package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.Tribe;

public class DamageBasedOnTribe extends Magic{
    private int currentBattlerChangeHPAmount;
    private int enemyBattlerChangeHPAmount;
    private Tribe tribe;

   public DamageBasedOnTribe(int currentBattlerChangeHPAmount, int enemyBattlerChangeHPAmount, Tribe tribe, String magicDetails){
        this.currentBattlerChangeHPAmount = currentBattlerChangeHPAmount;
        this.enemyBattlerChangeHPAmount = enemyBattlerChangeHPAmount;
        this.tribe = tribe;
        this.magicDetails = magicDetails;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler) {

            for (MonsterCardsInBattle monsterCardsInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
                if(monsterCardsInBattle != null && monsterCardsInBattle.getTribe() != tribe) {
                    monsterCardsInBattle.changeHealthPoint(enemyBattlerChangeHPAmount);
                    CastSpellEffect.buildCastSpellEffect(enemyBattler, monsterCardsInBattle.getFullImage());
                    monsterCardsInBattle.checkDeath();
                }
            }
            for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
                if(monsterCardsInBattle != null && monsterCardsInBattle.getTribe() != tribe) {
                    monsterCardsInBattle.changeHealthPoint(currentBattlerChangeHPAmount);
                    CastSpellEffect.buildCastSpellEffect(enemyBattler, monsterCardsInBattle.getFullImage());
                    monsterCardsInBattle.checkDeath();
                }
            }
    }
}
