package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

import java.util.Random;

public class DamageRandomMSOrPlayer extends Magic{
    private int changeHPAmount;
    private int type;

    public DamageRandomMSOrPlayer(int changeHPAmount, int type){
        this.changeHPAmount = changeHPAmount;
        this.type = type;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler){
        Random random = new Random();
        int randNum = random.nextInt(enemyBattler.getMonsterField().getSize() + 1) + 1;
        if(randNum == 1 || enemyBattler.getMonsterField().isEmpty()){
            if(type == 0)
                currentBattler.changeHealthPoint(changeHPAmount);
            else
                enemyBattler.changeHealthPoint(changeHPAmount);
        }
        else {
            enemyBattler.getMonsterField().getRandomMonsterCardInBattle().changeHealthPoint(changeHPAmount);
        }
    }
}
