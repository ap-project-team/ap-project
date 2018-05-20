package src.Cards.Magic;

import src.Cards.Cards;
import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class MoveMonsterCardToHand extends Magic{
    {
        this.magicType  = MagicType.WITHTARGET;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
        if(monsterCardsInBattle != null){
            Cards tempCard = monsterCardsInBattle.getCard();
            currentBattler.getMonsterField().remove(monsterCardsInBattle);
            currentBattler.getHand().add(tempCard);
        }
    }
}
