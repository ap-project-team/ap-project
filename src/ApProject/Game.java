package src.ApProject;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import src.ApProject.battle.Battle;
import src.ApProject.constants.AI_BattlerBuilder;
import src.ApProject.graphics.ImageButton;
import src.ApProject.graphics.Button;
import src.ApProject.graphics.Map;
import src.ApProject.graphics.Message;
import src.ApProject.player.Player;
import src.ApProject.shop.Shop;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Game {
    public static String give() {
        Scanner sc = new Scanner(System.in);
        String order = sc.nextLine();
        return order;
    }


    Player p;
    Shop S = new Shop();
    Pane root = new Pane();
    Pane pastRoot;
    Scene scene;
    boolean paused = false;
    Map map;

    public Game(String name,  Scene scene, Pane pastRoot) {
        p = new Player(name);
        this.scene = scene;
        this.pastRoot = pastRoot;

        scene.setRoot(root);
        map = new Map(root, scene, this);
    }

    public boolean mainMenuOrders(String order) {
        try {
            System.out.println("1. Enter Shop: To enter shop and buy or sell cards and items \n" +
                    "2. Edit Inventory: To edit your amulet or deck\n" +
                    "3. Next: To go to deck and amulet customization");

            if (order.matches("Enter Shop\\s*")) {
                System.out.println("Welcome To Shop :");
                while (S.shopOrders(p)) ;
            } else if (order.matches("Edit Inventory\\s*")) {
                p.editInventory();
            } else if (order.matches("Next\\s*")) {
                if (p.isReadyForBattle()) {
                    Battle battle = new Battle(p.becomeBattler(), AI_BattlerBuilder.getAIBattler(p.getLevel()));
                    String result = battle.play();
                    if (result.equals("PLAYER")) p.win();
                    else if (result.equals("ENEMY"))
                        if (p.defeat()) {
                            System.out.println("YOU ARE OUT OF Mystic Hourglass.");
                            System.out.println("Good Game!\tWell Played!");
                            System.out.println("GAME OVER\nThe End");
                            return false;
                        }
                } else System.out.println("Your deck is not full.");
            } else if (order.matches("Help\\s*")) {
                System.out.println("1. Enter Shop: To enter shop and buy or sell cards and items \n" +
                        "2. Edit Inventory: To edit your amulet or deck\n" +
                        "3. Next: To go to deck and amulet customization");
            } else if (order.matches("Exit\\s*")) return false;
            else System.out.println("Incorrect order!");
            return true;
        } catch (Exception e) {

        }
        return true;
    }




    public boolean isPaused() {
        return paused;
    }

    public void pause(AnimationTimer timer) {
        paused = true;

        StackPane exitButton = Button.buildButton("Exit to MainMenu");
        exitButton.setOnMouseReleased(event1 -> {
            scene.setRoot(pastRoot);
        });

        StackPane backPackButton = Button.buildButton("BackPack");
        StackPane continueButton = Button.buildButton("Continue");

        VBox buttonList = Button.buildButtonList(new StackPane[]{continueButton, backPackButton, exitButton});

        backPackButton.setOnMouseReleased(event1 -> {
            root.getChildren().remove(buttonList);

            StackPane editDeck = ImageButton.buildButton("./src//source//CARD//card.png");
            editDeck.setOnMouseClicked(event -> p.editDeck(scene, root));

            StackPane items = ImageButton.buildButton("./src//source//ITEM//item.jpg");
            items.setOnMouseClicked(event -> p.viewInventory(scene, root, "ITEM"));

            StackPane amulets = ImageButton.buildButton("./src//source//AMULET//amulet.gif");
            amulets.setOnMouseClicked(event -> p.editAmulet(scene, root));

            StackPane back = ImageButton.buildButton("./src//source//back.png");

            HBox buttons = ImageButton.buildButtonList(new StackPane[]{editDeck, items, amulets, back});

            back.setOnMouseClicked(event -> {
                root.getChildren().addAll(buttonList);
                root.getChildren().remove(buttons);
            });

            root.getChildren().addAll(buttons);
        });

        continueButton.setOnMouseReleased(event1 -> {
            root.getChildren().remove(buttonList);
            paused = false;
            timer.start();
        });

        root.getChildren().addAll(buttonList);

    }

    public void play(AnimationTimer timer) {

        if (p.isReadyForBattle()) {
            Battle battle = new Battle(p.becomeBattler(), AI_BattlerBuilder.getAIBattler(p.getLevel()));
            battle.play(scene, root, p);
            timer.stop();
//                if (result.equals("PLAYER")) p.win();
//                else if (result.equals("ENEMY"))
//                    if (p.defeat()) {
//                        System.out.println("YOU ARE OUT OF Mystic Hourglass.");
//                        System.out.println("Good Game!\tWell Played!");
//                        System.out.println("GAME OVER\nThe End");
//                    }
        } else Message.buildMessage("Your deck is not full.", root);
    }
}
