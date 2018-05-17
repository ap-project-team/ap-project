package src.Cards.MonsterCards.MonsterCardMagic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class DivineBlessing extends MonsterCardMagicWithTarget {
    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        monsterCardsInBattle.changeHealthPoint(2500);
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler){
        currentBattler.changeHealthPoint(2500);
    }
}
