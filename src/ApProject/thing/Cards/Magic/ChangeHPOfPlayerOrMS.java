package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

public class ChangeHPOfPlayerOrMS extends Magic{
    private int changeHPAmount;

    public ChangeHPOfPlayerOrMS(int changeHPAmount, String magicDetails){
        this.changeHPAmount = changeHPAmount;
        this.magicDetails = magicDetails;
        if(changeHPAmount > 0)
            this.magicType = MagicType.FriendlyPlayerOrMS;
        else
            this.magicType = MagicType.EnemeyPlayerOrMS;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        if(monsterCardsInBattle == null){
            if(magicType == MagicType.FriendlyPlayerOrMS)
                currentBattler.changeHealthPoint(changeHPAmount);
            else
                enemyBattler.changeHealthPoint(changeHPAmount);
        }
        else {
            monsterCardsInBattle.changeHealthPoint(changeHPAmount);
        }
    }
}
