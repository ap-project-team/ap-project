package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class RemoveGraveYard extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }
    public void doMagic(Battler currentBattler, Battler enemyBattler) {
            Cards randomCard = currentBattler.getGraveYard().getRandomCard();
            currentBattler.getGraveYard().remove(randomCard);
            currentBattler.getHand().add(randomCard);
    }
}
