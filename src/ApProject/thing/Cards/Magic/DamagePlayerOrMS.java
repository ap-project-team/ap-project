package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class DamagePlayerOrMS extends Magic{
    private int changeHPAmount;
    private int type;

    public DamagePlayerOrMS(int changeHPAmount, int type, String magicDetails){
        this.changeHPAmount = changeHPAmount;
        this.magicDetails = magicDetails;
        this.type = type;
        this.magicType = MagicType.BOTH;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        if(monsterCardsInBattle == null){
            if(type == 0)
                currentBattler.changeHealthPoint(changeHPAmount);
            else
                enemyBattler.changeHealthPoint(changeHPAmount);
        }
        else {
            monsterCardsInBattle.changeHealthPoint(changeHPAmount);
        }
    }
}
