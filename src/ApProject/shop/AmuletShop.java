package src.ApProject.shop;

import src.ApProject.Game;
import src.ApProject.player.Player;
import src.ApProject.thing.Amulet;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AmuletShop {
    static protected ArrayList<String> shopAmulets = new ArrayList<>();

    public static void loadShopAmulets(String path) {
        shopAmulets = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(path + "\\Shop\\amuletShop.txt");
            Scanner scanner = new Scanner(fileIn);
            while (scanner.hasNext()) {
                String amuletName = scanner.next();
                shopAmulets.add(amuletName);
            }
            System.out.println("Finished Loading Amulets In The Shop");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveShopAmulets(String path){
        try {
            File file = new File(path + "\\Shop\\amuletShop.txt");
            FileWriter fileWriter = new FileWriter(file, false);
            for (String string : shopAmulets) {
                fileWriter.write(string + "\n");
            }
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Saving All Amulets In The Shop");
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
            p.sell(Integer.parseInt(str[3]), str[1], "AMULET", Amulet.getAmulet(str[1]).getPrice());
        } else if (order.matches("Buy \\w* - \\d*\\s*")) {
            p.buy(Integer.parseInt(str[3]), str[1], "AMULET", shopAmulets, Amulet.getAmulet(str[1]).getPrice());
        } else if (order.matches("info \\w*\\s*")) ;
            //toDo info
        else if (order.matches("Edit Amulets")) {

        }else if (order.matches("Again\\s*"))
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
            System.out.println(i+1+".\t"+shopAmulets.get(i)+"\t"+ Amulet.getAmulet(shopAmulets.get(i)).getPrice());
        }
        System.out.println(" Amulet Inventory");
        p.printInventory("AMULET");
    }

    public static ArrayList<String> getAllAmulets(){
        return shopAmulets;
    }

    public static void remove(String string){
        shopAmulets.remove(string);
    }
    public static void add(String string){
        shopAmulets.add(string);
    }

}
