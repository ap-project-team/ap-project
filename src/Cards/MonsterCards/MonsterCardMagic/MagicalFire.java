package src.Cards.MonsterCards.MonsterCardMagic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class MagicalFire {
    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        enemyBattler.getMonsterField().remove(monsterCardsInBattle);
    }
}
