package src.ApProject.shop;

import src.ApProject.Game;
import src.ApProject.player.Player;

public class Shop {
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
