package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.ApProject.battle.battler.AI_Battler;
import src.ApProject.constants.AI_BattlerBuilder;
import src.ApProject.constants.CreatCards;
import src.ApProject.MainMenu;
import src.ApProject.custom.NewCustomGame;
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
        String path  = ".\\src\\Resource\\" + "0";
        CreatCards.loadAllCards(path);
        Item.loadAllItems(path);
        Amulet.loadAllAmulets(path);
        CardShop.loadShopCards(path);
        ItemShop.loadShopItems(path);
        AmuletShop.loadShopAmulets(path);
        AI_BattlerBuilder.loadAllEnemyDecks(path);
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

    public static void start(Scene scene, String path){
        CreatCards.loadAllCards(path);
        Item.loadAllItems(path);
        Amulet.loadAllAmulets(path);
        CardShop.loadShopCards(path);
        ItemShop.loadShopItems(path);
        AmuletShop.loadShopAmulets(path);
        AI_BattlerBuilder.loadAllEnemyDecks(path);
        new MainMenu().buildMainMenu(scene);
        System.out.println("'WELCOME TO THIS GAME'");
        System.out.println("Please Enter Your Name : ");
    }
}
