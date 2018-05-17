package src.Cards.MonsterCards.MonsterCardMagic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class Rejuvenation extends MonsterCardMagicWithTarget {

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
        if(!isUsed) {
            monsterCardsInBattle.increaseAttackPoint(300);
            monsterCardsInBattle.increaseHealthPoint(500);
        }
        isUsed = true;
    }
}
