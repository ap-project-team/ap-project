package src.ApProject.shop;

import src.ApProject.Game;
import src.ApProject.constants.CreatCards;
import src.ApProject.player.Player;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ItemShop {
    static protected ArrayList<String> shopItems = new ArrayList<>();

    public static void loadShopItems(String path) {
        shopItems = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(path + "\\Shop\\itemShop.txt");
            Scanner scanner = new Scanner(fileIn);
            while (scanner.hasNext()) {
                String itemName = scanner.nextLine();
                shopItems.add(itemName);
            }
            System.out.println("Finished Loading Items In The Shop");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveShopItems(String path){
        try {
            File file = new File(path + "\\Shop\\itemShop.txt");
            FileWriter fileWriter = new FileWriter(file, false);
            for (String string : shopItems) {
                fileWriter.write(string + "\n");
            }
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Saving All In The Shop");
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
            p.sell(Integer.parseInt(str[3]), str[1], "ITEM", Item.getItems(str[1]).getPrice());
        } else if (order.matches("Buy \\w* - \\d*\\s*")) {
            p.buy(Integer.parseInt(str[3]), str[1], "ITEM", shopItems, Item.getItems(str[1]).getPrice());
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
            System.out.println(i+1+".\t"+shopItems.get(i)+"\t"+ Item.getItems(shopItems.get(i)).getPrice());
        }
        System.out.println(" Item Inventory");
        p.printInventory("ITEM");
    }

    public static ArrayList<String> getAllItems(){
        return shopItems;
    }

    public static void remove(String string){
        shopItems.remove(string);
    }
    public static void add(String string){
        shopItems.add(string);
    }

    public static ArrayList<String> getShopItems() {
        return shopItems;
    }
}
