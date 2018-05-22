package src.ApProject.shop;

import src.ApProject.Game;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemShop {
    static protected List<String> shopItems = new ArrayList<>();

    {
        shopItems.add("c");
        shopItems.add("b");
        shopItems.add("b");
        shopItems.add("a");
        shopItems.add("c");
        shopItems.add("a");
        shopItems.add("b");
        shopItems.add("a");
        shopItems.add("a");
        shopItems.add("b");
    }

    protected boolean itemShopOrders(Player p) {
        String order = Game.give();
        String[] str = order.split("\\s");

        if (order.matches("Help\\s*")) {
            System.out.println(
                    "1. Buy \"Item Name\" - #NumberToBuy: To buy an item from the shop \n" +
                            "2. Sell \"Item Name\" - #NumberToSell: To sell an item from your item inventory \n" +
                            "3. Info \"Item Name\": To view the full information of the item\n" +
                            "4. Exit: To exit back to the shop menu");
        } else if (order.matches("Sell \\w* - \\d*\\s*")) {
            p.sell(Integer.parseInt(str[3]), str[1], "ITEM");
        } else if (order.matches("Buy \\w* - \\d*\\s*")) {
            p.buy(Integer.parseInt(str[3]), str[1], "ITEM", shopItems);
        } else if (order.matches("info \\w*\\s*")) ;
            //toDo info
        else if (order.matches("Again\\s*"))
            printEnteringText(p);
        else if (order.matches("Exit\\s*"))
            return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    public static void printEnteringText(Player p) {
        Collections.sort(shopItems);
        System.out.println("Remaining gil : "+p.getGil());
        System.out.println(" Shop List");
        for(int i=0; i<shopItems.size(); i++){
            System.out.println(i+1+".\t"+shopItems.get(i)+"\t"+ ConstantDatas.getPrice(shopItems.get(i),"ITEM"));
        }
        System.out.println(" Item Inventory");
        p.printInventory("ITEM");
    }
}
