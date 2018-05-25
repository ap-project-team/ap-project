package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.Tribe;

public class ChangeHPBasedOnTribe extends Magic{
    private int changeHPAmount;
    private int changeAPAmount;
    private Tribe tribe;

    public ChangeHPBasedOnTribe(int changeAPAmount, int changeHPAmount, Tribe tribe, String magicDetails){
        this.changeAPAmount = changeAPAmount;
        this.changeHPAmount = changeHPAmount;
        this.magicDetails = magicDetails;
        this.tribe = tribe;
        this.magicType = MagicType.FriendlyTarget;
        // TODO: 5/25/2018  
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
        if(monsterCardsInBattle.getTribe() == this.tribe) {
            monsterCardsInBattle.changeAttackPoint(changeAPAmount);
            monsterCardsInBattle.changeHealthPoint(changeHPAmount);
            monsterCardsInBattle.checkDeath();
        }
    }
}
