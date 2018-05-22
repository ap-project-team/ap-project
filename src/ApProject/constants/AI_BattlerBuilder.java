package src.ApProject.constants;

import src.ApProject.battle.battler.AI_Battler;
import src.ApProject.exeptions.CardDoesNotExistExeption;
import src.ApProject.thing.Cards.Cards;

public class AI_BattlerBuilder {

    static public AI_Battler FirstAI(){
        Cards[] cards = new Cards[20];
        try {

            for (int i = 0; i < 10; i++)
                cards[i] = AllCards.buildCard("ElvenHunter");
            for (int i = 10; i < 15; i++)
                cards[i] = AllCards.buildCard("ElvenHunter");
            for (int i = 15; i < 20; i++)
                cards[i] = AllCards.buildCard("ElvenHunter");
        } catch (CardDoesNotExistExeption e){
            e.printStackTrace();
        }
        return new AI_Battler("Goblin Chieftain", cards);
    }
}
