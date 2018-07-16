package src.ApProject.battle;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.BackButton;
import src.ApProject.graphics.Message;
import src.ApProject.player.Player;

import java.awt.*;
import java.util.Random;


public class Battle {
    final int numberOfCardsInFirstHand = 5;
    int turnNum = 1;


    Battler[] battlers = new Battler[2];

    public Battle(Battler battler, Battler enemy) {
        battlers[0] = battler;
        battlers[0].setEnemy(enemy);
        battlers[1] = enemy;
        battlers[1].setEnemy(battler);
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
        Pane root = new Pane();
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


        battlers[(startNumber+(turnNum))%2].playOneTurn(turnNum, root);
        nextTurnButton.setOnMouseClicked(event -> {
            battlers[(startNumber+(turnNum))%2].playOneTurn(turnNum, root);
        });

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
}
