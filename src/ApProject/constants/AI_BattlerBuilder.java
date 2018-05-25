package src.ApProject.constants;

import src.ApProject.battle.battler.AI_Battler;
import src.ApProject.exeptions.CardDoesNotExistExeption;
import src.ApProject.thing.Cards.Card;

public abstract class AI_BattlerBuilder {

    static public AI_Battler build(int level) {
        if (level == 1) {
            Card[] cards = new Card[20];

            for (int i = 0; i < 10; i++)
                cards[i] = CreatCards.getCard("GoblinSmuggler");
            for (int i = 10; i < 15; i++)
                cards[i] = CreatCards.getCard("GoblinShaman");
            for (int i = 15; i < 20; i++)
                cards[i] = CreatCards.getCard("ThrowingKnives");

            return new AI_Battler("Goblin Chieftain", cards);
        }

        if (level == 2) {

        }

        if (level == 3){

        }

        return null;
    }
}
