package src.ApProject.shop;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import src.ApProject.Game;
import src.ApProject.graphics.Button;
import src.ApProject.player.Player;

public class Shop {
    Pane root = new Pane();
    CardShop cardShop = new CardShop();
    ItemShop itemShop = new ItemShop();
    AmuletShop amuletShop = new AmuletShop();

    private void printEnteringText(Player p){
        System.out.println("Remaining gil : " + p.getGil());
        System.out.println("1. Card Shop \n" +
                "2. Item Shop \n" +
                "3. Amulet Shop\n" +
                "4. Exit");
    }

    public void shopControler(Player p, Scene scene, Pane pastRoot){
        scene.setRoot(root);

        StackPane cardShopButtom = Button.buildButton("Card Shop");
        cardShopButtom.setOnMouseClicked(event -> {
            cardShop.cardShopOrders(p);
        });

        StackPane itemShopButtom = Button.buildButton("Item Shop");
        itemShopButtom.setOnMouseClicked(event -> {
            itemShop.itemShopOrders(p);
        });

        StackPane amuletShopButtom = Button.buildButton("Amulet Shop");
        amuletShopButtom.setOnMouseClicked(event -> {
            amuletShop.amuletShopOrders(p);
        });


        root.getChildren().addAll(Button.buildButtonList(new StackPane[]{cardShopButtom, itemShopButtom, amuletShopButtom}));
    }

    public boolean shopOrders(Player p) {
        printEnteringText(p);
        String order = Game.give();
        String[] str = order.split("\\s");
        /*if (order.matches("Help\\s*")) {
            System.out.println("Remaining gil : " + p.getGil());
            System.out.println("1. Card Shop \n" +
                    "2. Item Shop \n" +
                    "3. Amulet Shop\n" +
                    "4. Exit");
        } else*/ if (order.matches("Card Shop\\s*")) {
            CardShop.printEnteringText(p);
            while (cardShop.cardShopOrders(p)) ;
        } else if (order.matches("Item Shop\\s*")) {
            ItemShop.printEnteringText(p);
            while (itemShop.itemShopOrders(p)) ;
        } else if (order.matches("Amulet Shop\\s*")) {
            AmuletShop.printEnteringText(p);
            while (amuletShop.amuletShopOrders(p)) ;
        } else if (order.matches("Again\\s*"));
        else if (order.matches("Exit\\s*"))
            return false;
        else System.out.println("Incorrect order!");
        return true;
    }


}
