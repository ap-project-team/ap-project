package src.Cards.Magic;

import src.Cards.Cards;
import src.ToDoPackage.Battler;

public class MoveFromHandToMF extends Magic{
    {
        this.magicType = MagicType.SELECTCARD;
    }

    public void doMagic(Cards cards, Battler currentBattler, Battler enemyBattler) {
        currentBattler.getHand().remove(cards);
        cards.play(currentBattler, enemyBattler,currentBattler.getMonsterField().getEmptySlotNumber());
    }
}
