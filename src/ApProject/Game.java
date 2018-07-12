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
import src.ApProject.battle.Battle;
import src.ApProject.constants.AI_BattlerBuilder;
import src.ApProject.graphics.ImageButton;
import src.ApProject.graphics.Button;
import src.ApProject.player.Player;
import src.ApProject.shop.Shop;

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
    VBox buttons;

    public Game(String name,  Scene scene, Pane pastRoot) {
        p = new Player(name);
        this.scene = scene;
        this.pastRoot = pastRoot;

        scene.setRoot(root);
        movePlayer();
    }

    public boolean mainMenuOrders(String order) {
        try {
            System.out.println("1. Enter Shop: To enter shop and buy or sell cards and items \n" +
                    "2. Edit Inventory: To edit your amulet or deck\n" +
                    "3. Next: To go to deck and amulet customization");

            if (order.matches("Enter Shop\\s*")) {
                System.out.println("Welcom To Shop :");
                while (S.shopOrders(p)) ;
            } else if (order.matches("Edit Inventory\\s*")) {
                p.editInventory();
            } else if (order.matches("Next\\s*")) {
                if (p.isReadyForBattle()) {
                    Battle battle = new Battle(p.becomeBattler(), AI_BattlerBuilder.build(p.getLevel()));
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

    void movePlayer(){
        final boolean[] stoped = {true};
//        Image img = new Image("./src//source//test1.png");
//        ImageView gameMap = new ImageView(img);
//        gameMap.setFitWidth(1000);
//        gameMap.setFitHeight(1000);
//        root.getChildren().addAll(gameMap);


        final Point2D[] veracity = {new Point2D(0, 0)};
        ImageView player = new ImageView("./src//source//player//New folder//knight iso char_idle_0.png");
        player.setFitHeight(30);
        player.setFitWidth(30);
        final File[] currentMove = {new File("./src//source//player//New folder")};


        root.getChildren().addAll(player);

        AnimationTimer timer = new AnimationTimer() {
            int i = 0, j =0;

            @Override
            public void handle(long now) {
                if (!stoped[0]) {
                    if (j++ % 5 == 0) player.setImage(new Image(currentMove[0].listFiles()[i++ % currentMove[0].listFiles().length].getPath()));
                    player.setTranslateX(player.getTranslateX() + veracity[0].getX());
                    player.setTranslateY(player.getTranslateY() + veracity[0].getY());
                }
            }
        };

        scene.setOnKeyPressed(event -> {
            if (!paused) {
                if (event.getCode() == KeyCode.RIGHT) {
                    System.out.println("RIGHT");
                    veracity[0] = new Point2D(1, 0);
                }

                if (event.getCode() == KeyCode.LEFT) {
                    System.out.println("LEFT");
                    veracity[0] = new Point2D(-1, 0);
                }

                if (event.getCode() == KeyCode.UP) {
                    System.out.println("UP");
                    veracity[0] = new Point2D(0, -1);
                }

                if (event.getCode() == KeyCode.DOWN) {
                    System.out.println("DOWN");
                    veracity[0] = new Point2D(0, 1);
                }


                if (canGo(player.getX()+veracity[0].getX(), player.getY()+veracity[0].getY())) {
                    stoped[0] = false;
                    currentMove[0] = new File("./src//source//player//move("+(int)veracity[0].getX()+","+(int)veracity[0].getY()+")");
                    System.out.println("move("+(int)veracity[0].getX()+","+(int)veracity[0].getY()+")");
                }
            }


            if (event.getCode() == KeyCode.P) {
                if (!paused)
                    pause(timer);
                timer.stop();
            }

        });



        timer.start();
    }

    private boolean canGo(double v, double v1) {
        return true;
    }

    void pause(AnimationTimer timer) {
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
            items.setOnMouseClicked(event -> p.printInventory("ITEM"));

            StackPane amulets = ImageButton.buildButton("./src//source//AMULET//amulet.gif");
            amulets.setOnMouseClicked(event -> p.printInventory("AMULET"));

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
}
