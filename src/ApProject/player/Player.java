package src.ApProject.player;

import src.ApProject.battle.battler.Battler;
import src.ApProject.battle.battler.realBattler;
import src.ApProject.constants.*;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Item;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int level = 1;
    private String name;
    private int MysticHourglassNum = 3;
    private int gil = ConstantDatas.STARTING_GIL;
    private Inventory inventory = new Inventory();
    private State lastState = new State(gil, inventory);

    {
        inventory.itemInventory.add(new InventoryThing(2, "SmallHPPotion"));
        inventory.itemInventory.add(new InventoryThing(2, "GreaterRestorative"));

        inventory.amuletInventory.add(new InventoryThing(1, "GoldRing"));
        inventory.amuletInventory.add(new InventoryThing(2, "DemonKing’sCrown"));
    }

    public Player(String name){
        this.name = name;
    }

    public int getLevel() {
        return level;
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

    public void buy(int number, String name, String type, List<String> list, int price) {
        int numberOfThingsInShop = 0;
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

    public void sell(int number, String name, String type, int price) {
        int numberOfAvailableThingsInInventory = inventory.numberOfThingsInInventory(name, type) - inventory.numberOfThingsInDeck(name, type);
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
        Amulet realAmulet = Amulet.buildAmulet(inventory.deck.getEquippedAmulet());

        for (int i = 0; i < realDeck.length; i++) {
            realDeck[i] = CreatCards.getCard(inventory.deck.getSlots()[i]);
        }
        for (int i = 0; i < inventory.itemInventory.size(); i++) {
            InventoryThing inventoryThing = inventory.itemInventory.get(i);
            for (int j = 0; j < inventoryThing.getNum(); j++)
                realItems.add(Item.buildItems(inventoryThing.getName()));
        }

        Battler battler = new realBattler(name, realDeck, realItems, realAmulet);

        return battler;
    }

    public boolean defeat() {
        if (MysticHourglassNum > 0) {
            gil = lastState.getGil();
            inventory = lastState.getInventory();

            System.out.println("\nYou Used one Mystic Hourglass.");
            System.out.println("You have " + (--MysticHourglassNum) + " Mystic Hourglass left.\n");
            return false;
        }
        return true;
    }

    public void win(){
        gil += 10000*level;
        lastState = new State(gil, inventory);
        level++;
    }

    public void editDeck(){
        inventory.deck.printDeckEnteringText(inventory.cardInventory);
        while (inventory.deck.editDeckOrders(inventory));
    }
}
