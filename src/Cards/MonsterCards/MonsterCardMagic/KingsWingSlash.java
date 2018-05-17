package src.Cards.MonsterCards.MonsterCardMagic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class KingsWingSlash extends MonsterCardMagicWithOutTarget {
    public void doMagic(Battler currentBatteler, Battler enemyBattler){
        for (MonsterCardsInBattle monsterCardInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
            monsterCardInBattle.changeHealthPoint(-600);
            monsterCardInBattle.checkDeath(enemyBattler);
        }
        
    }
}
