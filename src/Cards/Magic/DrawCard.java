package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class DrawCard extends Magic{
    private int cardsCount;
    DrawCard(int cardsCount){
        this.cardsCount = cardsCount;
        this.magicType = MagicType.WITHOUTTARGET;
    }
    public void doMagic(MonsterCardsInBattle nullTarget, Battler currentBattler, Battler enemyBattler){
        currentBattler.drawCard(cardsCount);
    }
}
