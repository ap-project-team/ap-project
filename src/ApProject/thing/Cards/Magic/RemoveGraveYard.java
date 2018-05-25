package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;

public class RemoveGraveYard extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }
    int count;
    public RemoveGraveYard( int count, String magicDetails){
        this.magicDetails = magicDetails;
        this.count = count;
    }
    public void doMagic(Battler currentBattler, Battler enemyBattler) {
        for (int i = 0; i < count; i++) {
            Card randomCard = currentBattler.getGraveYard().getRandomCard();
            if(randomCard != null) {
                currentBattler.getGraveYard().remove(randomCard);
                currentBattler.getHand().add(randomCard);
            }
        }
    }
}
