package src.ApProject.battle;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.BackButton;
import src.ApProject.graphics.Message;
import src.ApProject.player.Player;

import java.util.ArrayList;
import java.util.Random;



public class Battle {
    final int numberOfCardsInFirstHand = 5;
    int turnNum = 1;
    VBox battleGround = new VBox();
    Pane root = new Pane();
    Pane pastRoot = new Pane();
    int startNumber;
    Battler[] battlers = new Battler[2];
    StackPane eventView;
    String history = "";
    private int activeEffects = 0;

    public Battle(Battler battler, Battler enemy) {
        battlers[0] = battler;
        battlers[0].setBattle(this);
        battlers[0].setEnemy(enemy);
        battlers[1] = enemy;
        battlers[1].setEnemy(battler);
        battlers[1].setBattle(this);

        root.setBackground(new Background(new BackgroundFill(Color.color(0.9, 1, 1), CornerRadii.EMPTY, Insets.EMPTY)));
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
        Circle nextTurnButton = new Circle(root.getWidth()-100,root.getHeight()/2,30, Color.BLUE);
        root.getChildren().addAll(BackButton.buildBackButton(scene, pastRoot), nextTurnButton);

        Rectangle line1 = new Rectangle(root.getWidth()/3, 0, 5, root.getHeight());
        Rectangle line2 = new Rectangle(root.getWidth()/3*2, 0, 5, root.getHeight());

        root.getChildren().addAll(line1, line2);

        int startNumber = Math.abs(new Random().nextInt())%2;

        updateEvent("Battle against "+ battlers[1].getName()+" started!\n"+
                battlers[startNumber].getName()+" starts the battle.\n");

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
    }

    public void updateEvent(String newStr){
        if (eventView != null) root.getChildren().remove(eventView);
        eventView = new StackPane();

        history += newStr + "\n";

        Text topic = new Text("Events: \n");
        topic.setFont(new Font(20));
        topic.setTranslateX(30);
        topic.setTranslateY(30);

        Text eventHistory = new Text(history);
        eventHistory.setTranslateX(30);
        eventHistory.setTranslateY(65);

        Rectangle eventBox = new Rectangle(300,700,Color.LIGHTPINK);
        eventBox.setArcWidth(10);
        eventBox.setArcHeight(10);

        eventView.setAlignment(Pos.TOP_LEFT);
        eventView.setTranslateX(100);
        eventView.setTranslateY(100);

        if (history.split("\\n").length > 36) {
            String str = new String();
            String[] tempStr = history.split("\\n");

            for (int i = tempStr.length - 35; i<tempStr.length; i++)
                str += tempStr[i] +"\n";

            eventHistory.setText(str);
            history = eventHistory.getText();
        }

        eventView.getChildren().addAll(eventBox, eventHistory, topic);
        root.getChildren().addAll(eventView);
    }

    synchronized public void update() {
        //root.getChildren().remove(battleGround);
        System.out.println(activeEffects);

        if (!battlers[0].isAlive())
            battlers[0].defeat();
        if (!battlers[1].isAlive())
            battlers[1].defeat();

        if (activeEffects == 0) {
            if (root.getChildren().contains(battleGround))
                root.getChildren().remove(battleGround);

            battleGround = new VBox(50);
            StackPane[] battlersView = new StackPane[2];

            VBox vBox1 = new VBox(30);
            VBox vBox2 = new VBox(30);

            Circle circle = new Circle(50, Color.LIGHTSKYBLUE);
            Text text = new Text("Health: " + battlers[1].getHealth());
            battlersView[1] = new StackPane(circle, text);
            battlers[1].setBattlerCard(battlersView[1]);

            Circle circle1 = new Circle(50, Color.INDIANRED);
            Text text1 = new Text("Health: " + battlers[0].getHealth() + "\nMana: " + battlers[0].getCurrentMana());
            battlersView[0] = new StackPane(circle1, text1);
            battlers[0].setBattlerCard(battlersView[0]);

            battleGround.getChildren().addAll(vBox1, vBox2);

            vBox1.setAlignment(Pos.CENTER);
            vBox2.setAlignment(Pos.CENTER);

            battleGround.setTranslateX(root.getWidth() / 2 - 170);
            battleGround.setTranslateY(50);
            battleGround.setAlignment(Pos.CENTER);

            vBox1.getChildren().addAll(battlersView[1]);
            battlers[0].updatePlayField(vBox2);
            vBox2.getChildren().addAll(battlersView[0]);
            battlers[1].updatePlayField(vBox1);

            root.getChildren().addAll(battleGround);
        }
    }

    public Pane getRoot() {
        return root;
    }

    synchronized public void addEffect() {
        activeEffects++;
    }

    synchronized public void deleteEffect() {
        activeEffects--;
    }

    public int getActiveEffects() {
        return activeEffects;
    }
}
