package src.ApProject.battle;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.BackButton;
import src.ApProject.graphics.Message;
import src.ApProject.player.Player;

import java.awt.*;
import java.util.Random;


public class Battle {
    final int numberOfCardsInFirstHand = 5;
    int turnNum = 1;
    VBox battleGround = new VBox();
    Pane root = new Pane();

    Battler[] battlers = new Battler[2];

    public Battle(Battler battler, Battler enemy) {
        battlers[0] = battler;
        battlers[0].setBattle(this);
        battlers[0].setEnemy(enemy);
        battlers[1] = enemy;
        battlers[1].setEnemy(battler);
        battlers[1].setBattle(this);
    }

    public String play(){
        int startNumber = Math.abs(new Random().nextInt())%2;
        System.out.println("Battle against "+ battlers[1].getName()+" started!");
        System.out.println(battlers[startNumber].getName()+" starts the battle.\n");

        battlers[0].drawCard(numberOfCardsInFirstHand);
        battlers[1].drawCard(numberOfCardsInFirstHand);

        for (int turnNum = 0; battlers[0].isAlive() && battlers[1].isAlive(); turnNum++){
            battlers[(startNumber+(turnNum))%2].playOneTurn(turnNum);
        }

        System.out.println("GAME OVER");

        String winner = "Draw";
        if (battlers[0].isAlive()) winner = "PLAYER";
        else if (battlers[1].isAlive()) winner = "ENEMY";

        System.out.println(winner+" Wins");

        return winner;
    }

    public void play(Scene scene, Pane pastRoot, Player p){
        scene.setRoot(root);
        Circle nextTurnButton = new Circle(100,100,10, Color.BLUE);
        root.getChildren().addAll(BackButton.buildBackButton(scene, pastRoot), nextTurnButton);


        Rectangle line1 = new Rectangle(root.getWidth()/3, 0, 5, root.getHeight());
        Rectangle line2 = new Rectangle(root.getWidth()/3*2, 0, 5, root.getHeight());

        root.getChildren().addAll(line1, line2);

        int startNumber = Math.abs(new Random().nextInt())%2;
        Message.buildMessage("Battle against "+ battlers[1].getName()+" started!\n"+
                battlers[startNumber].getName()+" starts the battle.\n", root);

        battlers[0].drawCard(numberOfCardsInFirstHand);
        battlers[1].drawCard(numberOfCardsInFirstHand);


        if (startNumber == 0) {
            battlers[0].playOneTurn(turnNum, root);
            battlers[1].playOneTurn(turnNum);
            turnNum++;
            nextTurnButton.setOnMouseClicked(event -> {
                battlers[0].playOneTurn(turnNum, root);
                battlers[1].playOneTurn(turnNum);
                turnNum++;
            });
        } else {
            battlers[1].playOneTurn(turnNum);
            battlers[0].playOneTurn(turnNum, root);
            turnNum++;
            nextTurnButton.setOnMouseClicked(event -> {
                battlers[1].playOneTurn(turnNum);
                battlers[0].playOneTurn(turnNum, root);
                turnNum++;
            });
        }

        Circle endButton = new Circle(300,100,10, Color.RED);
        root.getChildren().addAll(endButton);

        endButton.setOnMouseClicked(event -> {
            System.out.println("GAME OVER");

            String winner = "Draw";
            if (battlers[0].isAlive()) winner = "PLAYER";
            else if (battlers[1].isAlive()) winner = "ENEMY";

            System.out.println(winner + " Wins");

        });

    }

    public void update(){
        root.getChildren().remove(battleGround);
        battleGround = new VBox(300);

        VBox vBox1 = new VBox(30);
        VBox vBox2= new VBox(30);

        battlers[1].updatePlayField(vBox1);
        battlers[0].updatePlayField(vBox2);
        vBox2.getChildren().addAll(new Text(battlers[0].getHealth()+""));

        battleGround.getChildren().addAll(vBox1, vBox2);

        vBox1.setAlignment(Pos.CENTER);
        vBox2.setAlignment(Pos.CENTER);


        battleGround.setTranslateX(root.getWidth()/2 - 150);
        battleGround.setTranslateY(200);
        battleGround.setAlignment(Pos.CENTER);

        root.getChildren().addAll(battleGround);
    }
}
