package src.ApProject.shop;

import src.ApProject.Game;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class CardShop{
    static protected ArrayList<String> shopCards = new ArrayList<>();

    {
        shopCards.add("c");
        shopCards.add("b");
        shopCards.add("b");
        shopCards.add("a");
        shopCards.add("c");
        shopCards.add("a");
        shopCards.add("b");
        shopCards.add("a");
        shopCards.add("a");
        shopCards.add("b");
    }

    protected boolean cardShopOrders(Player p) {
        String order = Game.give();
        String[] str = order.split("\\s");

        if (order.matches("Help\\s*")) {
            System.out.println(
                    "1. Buy \"Card Name\" - #NumberToBuy: To buy a certain number of a card from shop \n" +
                            "2. Sell \"Card Name\" - #NumberToSell: To sell a certain number of a card from inventory\n" +
                            "3. Info \"Card Name\": To get more information about a card \n" +
                            "4. Edit InventoryDeck: To edit InventoryDeck and remove and add cards to it\n" +
                            "5. Exit: To return to shop menu");
        } else if (order.matches("Sell \\w* - \\d*\\s*")) {
            p.sell(Integer.parseInt(str[3]), str[1], "CARD");
        } else if (order.matches("Buy \\w* - \\d*\\s*")) {
            p.buy(Integer.parseInt(str[3]), str[1], "CARD", shopCards);
        } else if (order.matches("info \\w*\\s*")) ;
            //toDo info
        else if (order.matches("Edit InventoryDeck")) ;
            //toDo Edit InventoryDeck
        else if (order.matches("Again\\s*"))
            printEnteringText(p);
        else if (order.matches("Exit\\s*"))
            return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    public static void printEnteringText(Player p) {
        Collections.sort(shopCards);
        System.out.println("Remaining gil : "+p.getGil());
        System.out.println(" Shop List");
        for(int i=0; i<shopCards.size(); i++){
            System.out.println(i+1+".\t"+shopCards.get(i)+"\t"+ ConstantDatas.getPrice(shopCards.get(i),"CARD"));
        }
        System.out.println(" Card Inventory");
        p.printInventory("CARD");
    }
}
