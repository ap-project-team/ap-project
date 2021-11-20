package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

public class ChangeHPAndAP extends Magic {
    private int changeHPAmount;
    private int changeAPAmount;

    public ChangeHPAndAP(int changeAPAmount, int changeHPAmount, int type, String magicDetails){
        this.changeAPAmount = changeAPAmount;
        this.changeHPAmount = changeHPAmount;
        this.magicDetails = magicDetails;
        if(type == 0)
            this.magicType = MagicType.FriendlyTarget;
        else
            this.magicType = MagicType.EnemyTarget;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
        monsterCardsInBattle.changeAttackPoint(changeAPAmount);
        monsterCardsInBattle.changeHealthPoint(changeHPAmount);
        monsterCardsInBattle.checkDeath();
        CastSpellEffect.buildCastSpellEffect(currentBattler, monsterCardsInBattle.getFullImage());
    }
}
