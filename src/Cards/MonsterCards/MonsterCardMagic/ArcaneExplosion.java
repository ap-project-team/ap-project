package src.Cards.MonsterCards.MonsterCardMagic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class ArcaneExplosion extends MonsterCardMagicWithOutTarget{
    public void doMagic(Battler currentBatteler, Battler enemyBattler){
        for (MonsterCardsInBattle monsterCardInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
            monsterCardInBattle.changeHealthPoint(-400);
            monsterCardInBattle.checkDeath(enemyBattler);
        }
        enemyBattler.getSpellField().remove(enemyBattler.getSpellField().getRandomSpell());
    }
}
