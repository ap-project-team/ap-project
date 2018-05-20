package src.ApProject.player;

import src.ApProject.battle.battler.Battler;
import src.ApProject.battle.battler.realBattler;
import src.ApProject.constants.*;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Card;
import src.ApProject.thing.Item;

import java.util.ArrayList;

public class Player {
    private String name;
    private int gil = 1000;
    private Inventory inventory = new Inventory();

    {
        name = "MAZ";

        inventory.cardInventory.add(new InventoryThing(5, "a"));
        inventory.cardInventory.add(new InventoryThing(2, "b"));

        inventory.itemInventory.add(new InventoryThing(2, "a"));
        inventory.itemInventory.add(new InventoryThing(2, "b"));

        inventory.amuletInventory.add(new InventoryThing(1, "c"));
        inventory.amuletInventory.add(new InventoryThing(2, "d"));
    }

    public int getGil() {
        return gil;
    }

    public void spend(int amount) {
        gil -= amount;
    }

    public void reserve(int amount) {
        gil += amount;
    }

    public void buy(int number, String name, String type, ArrayList<String> list) {
        int numberOfThingsInShop = 0;
        int price = ConstantDatas.getPrice(name, type);
        for (String s : list)
            if (s.equals(name))
                numberOfThingsInShop++;
        if (number <= 0) System.out.println("You should buy more than one thing");
        else if (number <= numberOfThingsInShop) {
            if (getGil() >= number * price) {
                spend(number * price);
                inventory.addToInventory(number, name, inventory.getList(type));
                for (int i = 0; i < number; i++)
                    list.remove(name);
                System.out.println("Successfully bought " + number + " of " + name + "!");
            } else System.out.println("Not enough Gil!");
        } else System.out.println("Shop don't have enough thing");
    }

    public void sell(int number, String name, String type) {
        int numberOfAvailableThingsInInventory = inventory.numberOfThingsInInventory(name, type) - inventory.numberOfThingsInDeck(name, type);
        int price = ConstantDatas.getSellPrice(name, type);
        if (number <= 0) System.out.println("You should sell more than one thing!");
        else if (number <= numberOfAvailableThingsInInventory) {
            inventory.removeFromInventory(number, name, inventory.getList(type));
            reserve(number * price);
            System.out.println("Successfully sold " + number + " of " + name + "!");
        } else System.out.println("Not enough cards!");
    }

    public void printInventory(String type) {
        inventory.printInventoy(inventory.getList(type));
    }

    public void editInventory() {
        System.out.println("You Are In Edit Inventory Section :");
        while (inventory.editInventoryOrders()) ;
    }

    public boolean isReadyForBattle(){
        return inventory.deckIsFull();
    }

    public Battler becomeBattler() {
        Card[] realDeck = new Card[ConstantDatas.SIZE_OF_DECK];
        ArrayList<Item> realItems = new ArrayList<>();
        Amulet realAmulet = ConstantDatas.<Amulet>buildThing(inventory.deck.getEquippedAmulet(), "AMULET");

        for (int i = 0; i < realDeck.length; i++)
            realDeck[i] = ConstantDatas.<Card>buildThing(inventory.deck.getSlots()[i], "CARD");
        for (int i = 0; i < inventory.itemInventory.size(); i++) {
            InventoryThing inventoryThing = inventory.itemInventory.get(i);
            for (int j = 0; j < inventoryThing.getNum(); j++)
                realItems.add(ConstantDatas.<Item>buildThing(inventoryThing.getName(), "ITEM"));
        }

        Battler battler = new realBattler(name, realDeck, realItems, realAmulet);

        return battler;
    }
}
