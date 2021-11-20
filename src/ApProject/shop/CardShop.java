package src.ApProject.shop;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import src.ApProject.Game;
import src.ApProject.battle.battler.Hand;
import src.ApProject.constants.CreatCards;
import src.ApProject.graphics.Message;
import src.ApProject.player.InventoryThing;
import src.ApProject.player.Player;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Item;
import src.ApProject.thing.Thing;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CardShop{
    static protected ArrayList<String> shopCards = new ArrayList<>();

    public static void loadShopCards(String path) {
        shopCards = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(path + "\\Shop\\cardShop.txt");
            Scanner scanner = new Scanner(fileIn);
            while (scanner.hasNext()) {
                String cardName = scanner.next();
                shopCards.add(cardName);
            }
            System.out.println("Finished Loading Cards In The Shop");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveShopCards(String path){
        try {
            File file = new File(path + "\\Shop\\cardShop.txt");
            FileWriter fileWriter = new FileWriter(file, false);
            for (String string : shopCards) {
                fileWriter.write(string + "\n");
            }
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Saving All Cards In The Shop");
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
            p.sell(Integer.parseInt(str[3]), str[1], "CARD", CreatCards.getCard(str[1]).getPrice());
        } else if (order.matches("Buy \\w* - \\d*\\s*")) {
            p.buy(Integer.parseInt(str[3]), str[1], "CARD", shopCards, CreatCards.getCard(str[1]).getPrice());
        } else if (order.matches("info \\D*\\s*")) {
            System.out.println(CreatCards.getCard(str[1]).getInfo());
        } else if (order.matches("Edit InventoryDeck\\s*")) {
            p.editDeck();
        } else if (order.matches("Again\\s*"))
            printEnteringText(p);
        else if (order.matches("Exit\\s*"))
            return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    public static void printEnteringText(Player p) {
        System.out.println("Remaining gil : "+p.getGil());
        System.out.println(" Shop List");
        for(int i=0; i<shopCards.size(); i++){
            System.out.println(i+1+".\t"+shopCards.get(i)+"\t"+ CreatCards.getCard(shopCards.get(i)).getPrice());
        }
        System.out.println(" Card Inventory");
        p.printInventory("CARD");
    }

    public static ArrayList<String> getAllCards(){
        return shopCards;
    }

    public static void remove(String string){
        shopCards.remove(string);
    }

    public static void add(String string){
        shopCards.add(string);
    }

    public static ArrayList<String> getShopCards() {
        return shopCards;
    }
}
