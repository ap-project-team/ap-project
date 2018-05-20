package src.ApProject.shop;

import src.ApProject.Game;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class AmuletShop {
    static protected ArrayList<String> shopAmulets = new ArrayList<>();

    {
        shopAmulets.add("c");
        shopAmulets.add("b");
        shopAmulets.add("b");
        shopAmulets.add("a");
        shopAmulets.add("c");
        shopAmulets.add("a");
        shopAmulets.add("b");
        shopAmulets.add("a");
        shopAmulets.add("a");
        shopAmulets.add("b");
    }

    protected boolean amuletShopOrders(Player p) {
        String order = Game.give();
        String[] str = order.split("\\s");

        if (order.matches("Help\\s*")) {
            System.out.println(
                    "1. Buy \"Amulet Name\" - #NumberToBuy: To buy a number of an amulet from the shop \n" +
                    "2. Sell \"Amulet Name\" - #NumberToSell: To sell a number of an amulet from amulet inventory\n" +
                    "3. Info \"Amulet Name\": To get full info on an amulet \n" +
                    "4. Edit Amulets: To equip or remove your ’heros amulet\n" +
                    "5. Exit: To exit to the shop menu");
        } else if (order.matches("Sell \\w* - \\d*\\s*")) {
            p.sell(Integer.parseInt(str[3]), str[1], "AMULET");
        } else if (order.matches("Buy \\w* - \\d*\\s*")) {
            p.buy(Integer.parseInt(str[3]), str[1], "AMULET", shopAmulets);
        } else if (order.matches("info \\w*\\s*")) ;
            //toDo info
        else if (order.matches("Edit Amulets")) ;
            //toDo Edit Amulet
        else if (order.matches("Again\\s*"))
            printEnteringText(p);
        else if (order.matches("Exit\\s*"))
            return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    public static void printEnteringText(Player p) {
        Collections.sort(shopAmulets);
        System.out.println("Remaining gil : "+p.getGil());
        System.out.println(" Shop List");
        for(int i=0; i<shopAmulets.size(); i++){
            System.out.println(i+1+".\t"+shopAmulets.get(i)+"\t"+ ConstantDatas.getPrice(shopAmulets.get(i),"AMULET"));
        }
        System.out.println(" Amulet Inventory");
        p.printInventory("AMULET");
    }
}
