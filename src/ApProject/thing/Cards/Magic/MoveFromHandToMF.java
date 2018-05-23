package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;

public class MoveFromHandToMF extends Magic{
    {
        this.magicType = MagicType.SELECTCARD;
    }

    public void doMagic(Card card, Battler currentBattler, Battler enemyBattler) {
        currentBattler.getHand().remove(card);
        card.play(currentBattler, enemyBattler, currentBattler.getMonsterField().getEmptySlotNumber());
    }
}
