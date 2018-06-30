package src.ApProject.controler;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import src.ApProject.Game;

import javax.print.attribute.standard.Fidelity;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class SinglePlayerGame {
    private Game game;
    Pane root = new Pane();
    Scene scene;

    SinglePlayerGame(String name, Scene scene) {
        game = new Game(name);
        this.scene = scene;

        Image img = new Image("./src//source//test1.png");
        ImageView gameMap = new ImageView(img);
        gameMap.setFitWidth(1000);
        gameMap.setFitHeight(1000);

        root.getChildren().addAll(gameMap);
        scene.setRoot(root);

        movePlayer();
    }

    void movePlayer(){
        final Point2D[] veracity = {new Point2D(0, 0)};

        ImageView player = new ImageView("./src//source//player//knight iso char_idle_0.png");
        player.setFitHeight(30);
        player.setFitWidth(30);
        final File[] currentMove = {new File("./src//source//player")};
        root.getChildren().addAll(player);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT){
                System.out.println("RIGHT");
                veracity[0] = new Point2D(1, 0);
                currentMove[0] = new File("./src//source//player//move(1,0)");
            }

            if (event.getCode() == KeyCode.LEFT){
                System.out.println("LEFT");
                veracity[0] = new Point2D(-1, 0);
                currentMove[0] = new File("./src//source//player//move(-1,0)");
            }

            if (event.getCode() == KeyCode.UP){
                System.out.println("UP");
                veracity[0] = new Point2D(0, -1);
                currentMove[0] = new File("./src//source//player//move(0,1)");
            }

            if (event.getCode() == KeyCode.DOWN){
                System.out.println("RIGHT");
                veracity[0] = new Point2D(0, 1);
                currentMove[0] = new File("./src//source//player//move(0,-1)");
            }

        });

        AnimationTimer timer = new AnimationTimer() {
            int i = 0, j =0;

            @Override
            public void handle(long now) {
                if (j++ % 5 == 0) player.setImage(new Image(currentMove[0].listFiles()[i++%6].getPath()));
                player.setTranslateX(player.getTranslateX()+ veracity[0].getX());
                player.setTranslateY(player.getTranslateY()+ veracity[0].getY());

            }
        };

        timer.start();
    }


}
