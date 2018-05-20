package src.Cards.Magic;

import src.Cards.Cards;
import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class RemoveGraveYard extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }
    public void doMagic(MonsterCardsInBattle nullTarget, Battler currentBattler, Battler enemyBattler) {
        if(nullTarget == null){
            Cards randomCard = currentBattler.getGraveYard().getRandomCard();
            currentBattler.getGraveYard().remove(randomCard);
            currentBattler.getHand().add(randomCard);
        }
    }
}
