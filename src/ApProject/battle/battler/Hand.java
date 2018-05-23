package src.ApProject.battle.battler;

import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

import java.util.ArrayList;


public class Hand {
    //Don't change
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

}
