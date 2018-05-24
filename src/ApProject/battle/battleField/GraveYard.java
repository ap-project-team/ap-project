package src.ApProject.battle.battleField;

import src.ApProject.thing.Cards.Card;

import java.util.ArrayList;
import java.util.Random;

public class GraveYard {
    private ArrayList<Card> cardArrayList = new ArrayList<>();

    public void add(Card card){cardArrayList.add(card);}

    public void remove(Card card){cardArrayList.remove(card);}

    public Card getRandomCard(){
        Random random = new Random();
        Card card = cardArrayList.get(random.nextInt(cardArrayList.size()));
        cardArrayList.remove(card);
        return card;
    }
}
