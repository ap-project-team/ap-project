package src.ApProject.battle.battler;



import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import src.ApProject.battle.Battle;
import src.ApProject.battle.battleField.GraveYard;
import src.ApProject.battle.battleField.MonsterField;
import src.ApProject.battle.battleField.SpellField;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.graphics.Message;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

abstract public class Battler {
    private String name;
    protected String type;
    protected Amulet amulet;

    MonsterField monsterField = new MonsterField(this);
    SpellField spellField = new SpellField();
    GraveYard graveYard = new GraveYard();
    StackPane battlerCard;

    int HP = 10000;

    int currentMaxMP = 0;
    int currentMP = 0;
    int MAX_HP = 10000;
    final int MAX_MP = 10;

    protected Battle battle;
    protected Battler enemy;


    protected ArrayList<Card> deck;
    protected Hand hand = new Hand(this);

    public String getType() {
        return type;
    }

    public void setEnemy(Battler enemy) {
        this.enemy = enemy;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public int getHealth() {
        return HP;
    }

    public Hand getHand() {
        return hand;
    }

    public int getCurrentMana() {
        return currentMP;
    }

    public void setCurrentMaxMP(int currentMaxMP) {
        this.currentMaxMP = currentMaxMP;
    }

    public Battler(String name, Card[] realDeck) {
        this.name = name;
        this.deck = new ArrayList<>(Arrays.asList(realDeck));
        shuffleDeck();
    }

    private void shuffleDeck(){
        ArrayList<Card> newDeck = new ArrayList<>();
        int size = deck.size();
        for (int i=0; i<size; i++){
            int j = Math.abs(new Random().nextInt())%(deck.size());
            newDeck.add(deck.get(j));
            deck.remove(j);
        }
        deck = newDeck;
    }

    protected String addToHand(int num){
        for (int i=0; i<num; i++){
            if (deck.size()>0) {
                int n = (new Random().nextInt(deck.size()));
                hand.add(deck.get(n));
                deck.remove(n);
            }
        }
        return hand.get(hand.size()-1).getName();
    }

    public void playOneTurn(int turnNum){
        if (currentMaxMP < MAX_MP) currentMaxMP++;
        currentMP = currentMaxMP;

        spellField.nextTurn();
        monsterField.nextTurn();

        String addedCard = "Your hand is full.";

        if (turnNum != 1) {
            if (deck.size() != 0) {
                if (hand.size() != ConstantDatas.MAX_CARD_IN_HAND)
                    addedCard = addToHand(1);
                else {
                    int i = (new Random().nextInt(deck.size()));
                    graveYard.add(deck.get(i));
                    deck.remove(i);
                }
            } else addedCard = "Your deck is empty.";
        } else addedCard = "Your cards has been drawn.";

        if (type.equals("PLAYER")) {
            System.out.println(
                    "Turn " + turnNum + " started! \n"
                    + name + "’s turn.\n"+
                    "[" + addedCard + "]\n" +
                    "[" + currentMaxMP + " - " + MAX_MP + "]");
            while (turnOrders());
        } else if (type.equals("ENEMY")) {
            turnOrders();
        }
    }

    public void playOneTurn(int turnNum, Pane root){
        if (currentMaxMP < MAX_MP) currentMaxMP++;
        currentMP = currentMaxMP;

        spellField.nextTurn();
        monsterField.nextTurn();

        String addedCard = "Your hand is full.";
        if (turnNum != 1) {
            if (deck.size() != 0) {
                if (hand.size() != ConstantDatas.MAX_CARD_IN_HAND)
                    addedCard = addToHand(1);
                else {
                    int i = (new Random().nextInt(deck.size()));
                    graveYard.add(deck.get(i));
                    deck.remove(i);
                }
            } else addedCard = "Your deck is empty.";
        } else addedCard = "Your cards has been drawn.";

        battle.update();
        root.getChildren().addAll(Message.buildMessage(addedCard, root));

        if (type.equals("PLAYER")) {
            System.out.println(
                    "Turn " + turnNum + " started! \n"
                            + name + "’s turn.\n"+
                            "[" + addedCard + "]\n" +
                            "[" + currentMaxMP + " - " + MAX_MP + "]");
            //turnOrders();
            // while (turnOrders());
        } else if (type.equals("ENEMY")) {
            turnOrders();
        }

    }

    public void updatePlayField(VBox vBox) {
        //Override
    }

    protected boolean turnOrders() {
        System.out.println("TURN ORDERS DID'NT OVERWRITE PROPERLY.");
        return false;
    }

    public String getName() {
        return name;
    }

    public void drawCard(int num) {
        addToHand(num);
        String text = "Player drew ";
        for(int i=0; i<num; i++) {
            text += hand.getName(i) + ", ";
        }
        text = text.substring(0,text.lastIndexOf(','));
        if (type.equals("PLAYER"))
            System.out.println(text);
    }

    public boolean isAlive(){
        return HP>0;
    }

    public MonsterField getMonsterField() {
        return monsterField;
    }

    public void setCurrentMana(int currentMana){
        this.currentMP = currentMana;
    }

    public void changeHealthPoint(int amount){
        this.HP += amount;
        if(this.HP >= MAX_HP)
            this.HP = MAX_HP;
    }

    public GraveYard getGraveYard() {
        return graveYard;
    }

    public SpellField getSpellField() {
        return spellField;
    }

    public Amulet getAmulet(){return amulet;}

    public Battler getEnemy() {
        return enemy;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattlerCard(StackPane battlerCard) {
        this.battlerCard = battlerCard;
    }

    public StackPane getBattlerCard() {
        return battlerCard;
    }
}
