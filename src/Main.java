package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.ApProject.constants.CreatCards;
import src.ApProject.Game;
import src.ApProject.MainMenu;

import java.util.ArrayList;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ArrayList<String> addAll = new ArrayList<>();
        CreatCards.createAllCards();

        Scene scene = new Scene(new Pane());
        stage.setScene(new MainMenu().buildMainMenu(scene));

        stage.setFullScreen(true);
        stage.show();

        System.out.println("'WELCOME TO THIS GAME'");
        System.out.println("Please Enter Your Name : ");


//        Game game = new Game("name", scene, new Pane());
//
//        while (game.mainMenuOrders(Game.give())) ;
    }
}
