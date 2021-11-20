package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;

public class ChangePlayerHP extends Magic {
    private int currentBattlerChangeAmount;
    private int enemyBattlerChangeAmount;

    public ChangePlayerHP(int currentBattlerChangeAmount, int enemyBattlerChangeAmount, String magicDetails){
        this.currentBattlerChangeAmount = currentBattlerChangeAmount;
        this.enemyBattlerChangeAmount = enemyBattlerChangeAmount;
        this.magicType = MagicType.WITHOUTTARGET;
        this.magicDetails = magicDetails;
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler) {
        currentBattler.changeHealthPoint(currentBattlerChangeAmount);
        enemyBattler.changeHealthPoint(enemyBattlerChangeAmount);
        if (currentBattlerChangeAmount != 0)
            CastSpellEffect.buildCastSpellEffect(currentBattler, currentBattler.getBattlerCard());
        if (enemyBattlerChangeAmount != 0)
            CastSpellEffect.buildCastSpellEffect(enemyBattler, enemyBattler.getBattlerCard());
    }
}
