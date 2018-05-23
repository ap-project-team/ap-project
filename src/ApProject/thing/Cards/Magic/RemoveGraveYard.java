package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;

public class RemoveGraveYard extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }

    RemoveGraveYard(String magicDetails){
        this.magicDetails = magicDetails;
    }
    public void doMagic(Battler currentBattler, Battler enemyBattler) {
            Card randomCard = currentBattler.getGraveYard().getRandomCard();
            currentBattler.getGraveYard().remove(randomCard);
            currentBattler.getHand().add(randomCard);
    }
}
