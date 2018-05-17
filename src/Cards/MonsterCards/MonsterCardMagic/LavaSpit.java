package src.Cards.MonsterCards.MonsterCardMagic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class LavaSpit extends MonsterCardMagicWithTarget {
    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        if(!isUsed) {
            monsterCardsInBattle.changeHealthPoint(-500);
            monsterCardsInBattle.checkDeath(enemyBattler);
            monsterCardsInBattle.changeAttackPoint(-500);
        }
        isUsed = true;
    }
}
