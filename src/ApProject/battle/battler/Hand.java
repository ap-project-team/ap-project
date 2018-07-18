package src.ApProject.battle.battler;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import src.ApProject.battle.Battle;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.graphics.Message;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

import java.util.ArrayList;
import java.util.Random;


public class Hand {
    HBox hBox;
    ArrayList<Card> hand = new ArrayList<>();
    Battler battler;

    Hand (Battler battler){
        this.battler = battler;
    }

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

        hBox = new HBox(10*5/hand.size());

        for (int i=0; i<hand.size(); i++) {
            //System.out.println(hand.get(i).getName());
            StackPane fullImage = new StackPane();

            ImageView image = new ImageView("./src//source//CARD//" + hand.get(i).getName() + ".png");
            int finalI = i;
            image.setOnMouseClicked(event -> {
                if (hand.get(finalI).getCardType().equals("MONSTERCARD")) {
                    if (battler.monsterField.getEmptySlotNumber() != 0) {
                        hand.get(finalI).play(battler, battler.enemy, battler.monsterField.getFirstEmptySlot());
                    } else root.getChildren().addAll(Message.buildMessage("Your MonsterField is full.", root));

                } else if (hand.get(finalI).getCardType().equals("SPELLCARD"))
                    if (battler.spellField.getEmptySlotNumber() != 0) {
                        hand.get(finalI).play(battler, battler.enemy, battler.spellField.getFirstEmptySlot());
                    } else root.getChildren().addAll(Message.buildMessage("Your MonsterField is full.", root));

                battler.battle.update();
            });

            image.setFitHeight(80*5/hand.size());
            image.setFitWidth(60*5/hand.size());

            Text text = new Text("MP: "+hand.get(i).getManaCost());

            text.setFont(new Font(15*ConstantDatas.SIZE_OF_MONSTERFIELD/hand.size()));
            fullImage.getChildren().addAll(image, text);
            hBox.getChildren().addAll(fullImage);
        }

        hBox.setAlignment(Pos.CENTER);
        root.getChildren().addAll(hBox);
    }
}
