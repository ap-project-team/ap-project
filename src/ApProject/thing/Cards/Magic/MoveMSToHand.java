package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class MoveMSToHand extends Magic{
    {
        this.magicType  = MagicType.FriendlyTarget;
    }

    MoveMSToHand(String magicDetails){
        this.magicDetails = magicDetails;
    }
    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler) {
            Cards tempCard = monsterCardsInBattle.getCard();
            currentBattler.getMonsterField().remove(monsterCardsInBattle);
            currentBattler.getHand().add(tempCard);
    }
}
