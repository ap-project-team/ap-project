package src.ApProject.battle.battler;

import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

import java.util.ArrayList;
import java.util.Random;


public class Hand {

    ArrayList<Card> hand = new ArrayList<>();

    public void add(Card card){
        hand.add(card);
    }

    public void remove(Card card){
        hand.remove(card);
    }

    public void remove(int num){
        hand.remove(num);
    }

    public String getName(int i){
        return hand.get(i).getName();
    }

    public int size(){
        return hand.size();
    }

    public Card get(int i){
        return hand.get(i);
    }

    public void shuffleHand(){
        ArrayList<Card> newHand = new ArrayList<>();
        Random R = new Random();
        while (hand.size() != 0){
            int i = Math.abs(R.nextInt()) % hand.size();
            newHand.add(hand.get(i));
            hand.remove(i);
        }
        hand = newHand;
    }

}
