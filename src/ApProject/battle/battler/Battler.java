package src.ApProject.battle.battler;

import src.ApProject.battle.Battle;
import src.ApProject.battle.BattleGround;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

abstract public class Battler {
    private String name;
    String type;
    BattleGround battleGround = new BattleGround();


    int HP = 10000;

    int currentMP = 0;
    final int MAX_MP = 10;

    protected Battle battle;
    protected ArrayList<Cards> deck;
    protected ArrayList<Cards> hand = new ArrayList<>();

    public Battler(String name, Cards[] realDeck) {
        this.name = name;
        this.deck = new ArrayList<>(Arrays.asList(realDeck));
        shufelDeck();
    }

    public void setBattleGround(BattleGround battleGround){
        this.battleGround = battleGround;
    }

    private void shufelDeck(){
        ArrayList<Cards> newDeck = new ArrayList<>();
        int size = deck.size();
        for (int i=0; i<size; i++){
            int j = Math.abs(new Random().nextInt())%(deck.size());
            newDeck.add(deck.get(j));
            deck.remove(j);
        }
        deck = newDeck;
    }

    private boolean enemyTurnOrders() {
        //toDo AI
        return true;
    }

    protected String addToHand(int num){
        for (int i=0; i<num; i++){
            if (deck.size()>0) {
                int n = Math.abs(new Random().nextInt()) % deck.size();
                hand.add(deck.get(n));
                deck.remove(n);
            }
        }
        if (deck.size() > 0) return deck.get(deck.size()-1).getName();
        else return "Your deck is empety";
    }

    public void playOneTurn(int turnNum){
        if (currentMP < MAX_MP) currentMP++;

        String addedCard;
        if (turnNum != 1 && turnNum != 2)
            addedCard = addToHand(1);
        else addedCard = "Your cards has been drawn.";

        if (type.equals("PLAYER")) {
            System.out.println(
                    "Turn " + turnNum + " started! \n"
                    + name + "’s turn.\n"+
                    "[" + addedCard + "]\n" +
                    "[" + currentMP + " - " + MAX_MP + "]");
            while (turnOrders());
        } else if (type.equals("ENEMY"))
            turnOrders();
    }

    protected boolean turnOrders() {
        System.out.println("TURN ORDERS DID'NT OVER WRITED PROPERLY.");
        return true;
    }

    public String getName() {
        return name;
    }

    public void draw(int num, String battlerType) {
        addToHand(num);
        String text = "Player drew ";
        for(int i=0; i<num; i++) {
            text += hand.get(i).getName() + ", ";
        }
        text = text.substring(0,text.lastIndexOf(','));
        if (battlerType.equals("PLAYER"))
            System.out.println(text);
    }

    public boolean isAlive(){
        return HP>0;
    }
}
