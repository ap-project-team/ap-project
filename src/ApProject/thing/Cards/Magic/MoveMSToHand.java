package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

public class MoveMSToHand extends Magic{
    {
        this.magicType  = MagicType.FriendlyTarget;
    }

    public MoveMSToHand(String magicDetails){
        this.magicDetails = magicDetails;
    }
    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
            Card tempCard = monsterCardsInBattle.getCard();
            currentBattler.getMonsterField().remove(monsterCardsInBattle);
            currentBattler.getHand().add(tempCard);
    }
}
