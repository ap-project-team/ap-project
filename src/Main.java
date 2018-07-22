package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.ApProject.constants.CreatCards;
import src.ApProject.MainMenu;
import src.ApProject.shop.AmuletShop;
import src.ApProject.shop.CardShop;
import src.ApProject.shop.ItemShop;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Item;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        CreatCards.loadAllCards();
        Item.loadAllItems();
        Amulet.loadAllAmulets();
        CardShop.loadShopCards();
        ItemShop.loadShopItems();
        AmuletShop.loadShopAmulets();
        Scene scene = new Scene(new Pane());
        stage.setScene(new MainMenu().buildMainMenu(scene));

        stage.setFullScreen(true);
       // stage.setMaxWidth(1368);
        //stage.setMaxHeight(912);

        stage.show();



        System.out.println("'WELCOME TO THIS GAME'");
        System.out.println("Please Enter Your Name : ");


//        Game game = new Game("name", scene, new Pane());
//
//        while (game.mainMenuOrders(Game.give())) ;
    }
}
