package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

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
            if (monsterCardsInBattle != null) {
                monsterCardsInBattle.changeAttackPoint(enemyBattlerChangeAPAmount);
                monsterCardsInBattle.changeHealthPoint(enemyBattlerChangeHPAmount);
                monsterCardsInBattle.checkDeath();
                if (enemyBattlerChangeAPAmount != 0 || enemyBattlerChangeHPAmount != 0)
                    CastSpellEffect.buildCastSpellEffect(enemyBattler, monsterCardsInBattle.getFullImage());
            }
        }
        for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
            if (monsterCardsInBattle != null) {
                monsterCardsInBattle.changeAttackPoint(currentBattlerChangeAPAmount);
                monsterCardsInBattle.changeHealthPoint(currentBattlerChangeHPAmount);
                monsterCardsInBattle.checkDeath();
                if (currentBattlerChangeAPAmount != 0 || currentBattlerChangeHPAmount != 0)
                    CastSpellEffect.buildCastSpellEffect(currentBattler, monsterCardsInBattle.getFullImage());
            }
        }
    }
}
