package src.ApProject.battle.battler;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

import java.util.ArrayList;
import java.util.Random;


public class Hand {
    HBox hBox;
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

    public void update(Pane root) {
        if (hBox != null)
            root.getChildren().remove(hBox);

        hBox = new HBox(10);

        for (int i=0; i<hand.size(); i++) {
            System.out.println(hand.get(i).getName());
            ImageView image = new ImageView("./src//source//CARD//" + hand.get(i).getName() + ".png");
            image.setFitHeight(60);
            image.setFitWidth(45);
            hBox.getChildren().addAll(image);
        }

        hBox.setAlignment(Pos.CENTER);
        root.getChildren().addAll(hBox);
    }
}
