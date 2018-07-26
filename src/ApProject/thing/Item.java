package src.ApProject.thing;

import javafx.scene.image.ImageView;
import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Item extends Thing{
    int increaseHP;
    int increaseMP;
    private static Map<String, Item> allItems = new HashMap<>();
    public Item(String name, int price, int increaseHP, int increaseMP) {
        this.name = name;
        this.price = price;
        this.increaseHP = increaseHP;
        this.increaseMP = increaseMP;
        thingType = "ITEM";
    }
    static final long serialVersionUID = 10000;
    public static void buildItems(){
        allItems.put("SmallHPPotion",new Item("SmallHPPotion", 1000, 500, 0));
        allItems.put("MediumHPPotion", new Item("MediumHPPotion", 2000, 1000, 0));
        allItems.put("LargeHPPotion", new  Item("LargeHPPotion", 4000, 2000, 0));
        allItems.put("SmallMPPotion", new Item("SmallMPPotion", 1000, 0, 2));
        allItems.put("MediumMPPotion", new  Item("MediumMPPotion", 2000, 0, 4));
        allItems.put("LargeMPPotion", new Item("LargeMPPotion", 4000, 0, 8));
        allItems.put("LesserRestorative", new Item("LesserRestorative", 2000, 500, 2));
        allItems.put("GreaterRestorative", new Item("GreaterRestorative", 4000, 1000, 4));
    }

    public static void saveAllItems(String path){
        try {
            FileOutputStream fout = new FileOutputStream(path + "\\AllThings\\allItems.ser", false);
            fout.close();
            for (Item item : allItems.values()) {
                fout = new FileOutputStream(path + "\\AllThings\\allItems.ser", true);
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(item);
                oos.close();
                fout.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Saving All Items");
    }
    public static Item getItems(String name){
        return allItems.get(name);
    }

    public void useItem(Battler battler){
        battler.setCurrentMana(battler.getCurrentMana()+increaseMP);
        battler.changeHealthPoint(increaseHP);
        System.out.println("Item used.");
    }

    public String getInfo(){
        if (increaseMP == 0) return " Increase Player’s HP by " + increaseHP;
        else if (increaseHP == 0) return " Increase Player’s MP by "+ increaseMP;
        else return "Increase Player’s HP by " + increaseHP+" and MP by "+ increaseMP;
    }

    public int getIncreaseHP() {
        return increaseHP;
    }

    public int getIncreaseMP() {
        return increaseMP;
    }

    public static void addItem(Item item){
        allItems.put(item.getName(), item);
    }
    public static void loadAllItems(){
        try {
            FileInputStream fileIn = new FileInputStream(".\\src\\Resource\\0\\AllThings\\allItems.ser");
            Item item = null;
            boolean isExist = true;
            while (isExist) {
                if(fileIn.available() != 0) {
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    item = (Item) objectIn.readObject();
                    allItems.put(item.getName(), item);
                }
                else {
                    isExist = false;
                }
            }
            System.out.println("Finished Loading All Items");
            fileIn.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void remove(Item item){
        allItems.remove(item.getName());
    }
    public static Item[] getAllItems(){
        return allItems.values().toArray(new Item[0]);
    }
}
