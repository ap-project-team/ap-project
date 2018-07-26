package src.ApProject.graphics;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import src.ApProject.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    boolean[][] wall = new boolean[40][40];
    boolean[][] enemy = new boolean[40][40];
    ImageView map;
    Game game;

    public Map(Pane root, Scene scene, Game game){
        Scanner wallScanner = null;
        Scanner enemyScanner = null;
        this.game = game;

        try {
            wallScanner = new Scanner(new File("./src//source//Map//wall.txt"));
            enemyScanner = new Scanner(new File("./src//source//Map//enemy.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i<40; i++){
            String line = wallScanner.nextLine();
            String[] number = line.split(",");
            for (int j=0; j<40; j++){
                wall[j][i] = !number[j].equals("0");
            }
            wall[0][i] = true;
            wall[i][0] = true;
            wall[39][i] = true;
            wall[i][39] = true;
        }

        for (int i=0; i<40; i++){
            String line = enemyScanner.nextLine();
            String[] number = line.split(",");
            for (int j=0; j<40; j++){
                enemy[j][i] = !number[j].equals("0");
            }
        }

        map = new ImageView("./src//source//Map//map.png");



        map.setFitWidth(2200);
        map.setFitHeight(2200);

        map.setPreserveRatio(true);


        root.getChildren().addAll(map);

        movePlayer(root, scene);
    }

    void movePlayer(Pane root, Scene scene){
        final boolean[] stopped = {false};
        final Image[][] playerImages = {new Image[1]};

        playerImages[0][0] = new Image("./src//source//player//knight iso char_idle_0.png");

//        Image img = new Image("./src//source//test1.png");
//        ImageView gameMap = new ImageView(img);
//        gameMap.setFitWidth(1000);
//        gameMap.setFitHeight(1000);
//        root.getChildren().addAll(gameMap);


        System.out.println(root.getWidth());
        System.out.println(root.getHeight());


        final Point2D[] veracity = {new Point2D(0, 0)};
        ImageView player = new ImageView(playerImages[0][0]);
        player.setFitHeight(100);
        player.setFitWidth(100);

        player.setTranslateX(500);
        player.setTranslateY(500);


        root.getChildren().addAll(player);

        AnimationTimer timer = new AnimationTimer() {
            int time = 0, imageNum = 0;

            @Override
            public void handle(long now) {
                if (!stopped[0]) {
                    if (player.getTranslateX() + veracity[0].getX() < root.getWidth() * 2 / 3
                            && player.getTranslateX() + veracity[0].getX() > root.getWidth() * 1 / 3)
                        player.setTranslateX(player.getTranslateX() + veracity[0].getX());
                    else if (player.getTranslateX() > root.getWidth() * 2 /3 ||
                            player.getTranslateX() < root.getWidth() * 1 /3)
                        player.setTranslateX(player.getTranslateX() + veracity[0].getX());
                    else if (map.getTranslateX() - veracity[0].getX() < 0)
                        map.setTranslateX(map.getTranslateX() - veracity[0].getX());
                    else player.setTranslateX(player.getTranslateX() + veracity[0].getX());

                    if (player.getTranslateY() + veracity[0].getY() < root.getHeight() * 2 / 3
                            && player.getTranslateY() + veracity[0].getY() > root.getHeight() * 1 / 3)
                        player.setTranslateY(player.getTranslateY() + veracity[0].getY());
                    else if(player.getTranslateY() > root.getHeight() * 2 /3 ||
                            player.getTranslateY() < root.getHeight() * 1 /3)
                        player.setTranslateY(player.getTranslateY() + veracity[0].getY());
                    else if (map.getTranslateY() - veracity[0].getY() <0)
                        map.setTranslateY(map.getTranslateY() - veracity[0].getY());
                    else player.setTranslateY(player.getTranslateY() + veracity[0].getY());
//                    player.setTranslateY(player.getTranslateY() + veracity[0].getY());


                    time = (time+1)%5;

                    if (time == 0) {
                        imageNum = (imageNum + 1) % playerImages[0].length;
                        player.setImage(playerImages[0][imageNum]);
                    }

                    if (enemy[(int)((player.getTranslateX() - map.getTranslateX() + veracity[0].getX()
                            + player.getFitWidth()/2) / map.getFitWidth() * 40)]
                            [(int)((player.getTranslateY() - map.getTranslateY() + veracity[0].getY()
                            + player.getFitHeight()) / map.getFitHeight() * 40)]) {

                        player.setTranslateX(player.getTranslateX() - 5 * veracity[0].getX());
                        player.setTranslateY(player.getTranslateY() - 5 * veracity[0].getY());

                        stopPlayer(playerImages, veracity);

                        StackPane wantToPlay = new StackPane();

                        wantToPlay.setAlignment(Pos.CENTER);
                        Rectangle rectangle = new Rectangle(600, 300, Color.WHITE);
                        rectangle.setArcWidth(20);
                        rectangle.setArcHeight(20);

                        Text text = new Text("Are You Ready?");
                        text.setTranslateY(-100);
                        text.setFont(new Font(25));

                        StackPane yes = Button.buildButton("Yes");
                        StackPane no = Button.buildButton("no");

                        yes.setOnMouseClicked(event -> {
                            game.play(this);
                            scene.setOnKeyPressed(event1 -> {});
                            return;
                        });

                        no.setOnMouseClicked(event -> {
                            root.getChildren().remove(wantToPlay);
                        });

                        HBox options = new HBox(yes, no);
                        options.setSpacing(50);
                        options.setTranslateX(20);
                        wantToPlay.getChildren().addAll(rectangle, options, text);

                        wantToPlay.setTranslateX(root.getWidth()/2 - 225);
                        wantToPlay.setTranslateY(root.getHeight()/2 - 200);

                        root.getChildren().addAll(wantToPlay);
                    }

                }
                if (!canGo(player, veracity, root)) {
                    player.setTranslateX(player.getTranslateX() - 3 * veracity[0].getX());
                    player.setTranslateY(player.getTranslateY() - 3 * veracity[0].getY());

                    stopPlayer(playerImages, veracity);
                    stopped[0] = true;
                } else stopped[0] = false;

            }
        };

        scene.setOnKeyPressed(event -> {
            if (!game.isPaused()) {
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

                File[] files = new File("./src//source//player//move("+(int)veracity[0].getX()+","+(int)veracity[0].getY()+")").listFiles();;
                playerImages[0] = new Image[files.length];
                for (int i=0; i<files.length; i++)
                    playerImages[0][i] = new Image(files[i].getPath());

                veracity[0] = new Point2D(veracity[0].getX() * 3 , veracity[0].getY() * 3);
            }


            if (event.getCode() == KeyCode.P) {
                if (!game.isPaused())
                    game.pause(timer);
                timer.stop();
            }
        });

        timer.start();
    }

    private boolean canGo(ImageView player, Point2D[] veracity, Pane root) {
        double x = (player.getTranslateX() - map.getTranslateX() + veracity[0].getX() + player.getFitWidth()/2) / map.getFitWidth() * 40;
        double y = (player.getTranslateY() - map.getTranslateY() + veracity[0].getY() + player.getFitHeight()) / map.getFitHeight() * 40;

        System.out.println(x+","+(int)x+"    "+y+","+(int)y+"   "+wall[(int)x][(int)y]);

        return !wall[(int)x][(int)y] || (int)x > 40;
    }

    void stopPlayer(Image[][] playerImages, Point2D[] veracity) {
        veracity[0] = new Point2D(0, 0);
        playerImages[0] = new Image[1];
        playerImages[0][0] = new Image("./src//source//player//knight iso char_idle_0.png");
    }

}
