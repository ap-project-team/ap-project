package src.Cards.Magic;

import src.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class DrawCard extends Magic{
    private int cardsCount;
    public DrawCard(int cardsCount){
        this.cardsCount = cardsCount;
        this.magicType = MagicType.WITHOUTTARGET;
    }
    public void doMagic(Battler currentBattler, Battler enemyBattler){
        currentBattler.drawCard(cardsCount);
    }
}
