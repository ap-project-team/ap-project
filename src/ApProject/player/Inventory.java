package src.ApProject.player;

import src.ApProject.Game;
import src.ApProject.constants.CreatCards;

import java.util.ArrayList;

public class Inventory {
    protected ArrayList<InventoryThing> cardInventory = new ArrayList<>();
    protected ArrayList<InventoryThing> itemInventory = new ArrayList<>();
    protected ArrayList<InventoryThing> amuletInventory = new ArrayList<>();
    protected InventoryDeck deck = new InventoryDeck();

    Inventory() {

    }

    Inventory(ArrayList<InventoryThing> cards, ArrayList<InventoryThing> items, ArrayList<InventoryThing> amulets, InventoryDeck deck) {
        this.cardInventory = cards;
        this.itemInventory = items;
        this.amuletInventory = amulets;
        this.deck = deck;
    }

    ArrayList<InventoryThing> getList(String type) {
        if (type.equals("CARD")) return cardInventory;
        if (type.equals("ITEM")) return itemInventory;
        if (type.equals("AMULET")) return amuletInventory;
        return null;
    }

    protected void addToInventory(int num, String name, ArrayList<InventoryThing> list) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals(name)) {
                list.get(i).add(num);
                return;
            }
        list.add(new InventoryThing(num, name));
    }

    protected void removeFromInventory(int num, String name, ArrayList<InventoryThing> list) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals(name)) {
                list.get(i).remove(num);
                if (list.get(i).getNum() == 0)
                    list.remove(i);
                return;
            }
    }

    protected int numberOfThingsInInventory(String name, String type) {
        ArrayList<InventoryThing> list = getList(type);
        for (InventoryThing i : list)
            if (i.name.equals(name))
                return i.num;
        return 0;
    }

    protected int numberOfThingsInDeck(String name, String type) {
        return deck.getNumberOfCardsInDeck(name, type);
    }

    public void printInventoy(ArrayList<InventoryThing> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).getName() + " / " + list.get(i).getNum());
        }
    }

    private void printEnteringText() {
        System.out.println("1. Card Inventory: To view your cards \n" +
                "2. Item Inventory: To view your items \n" +
                "3. Amulet Inventory: To view your amulets \n" +
                "4. Edit InventoryDeck: To edit your card deck \n" +
                "5. Edit Amulets: To equip or remove your amulets\n" +
                "6. Exit: To exit to previous menu");
    }

    protected boolean editInventoryOrders() {
        printEnteringText();
        String order = Game.give();
        String[] str = order.split("\\s");

        /*if (order.matches("Help\\s*")) {
            System.out.println(
                    "1. Card Inventory: To view your cards \n" +
                            "2. Item Inventory: To view your items \n" +
                            "3. Amulet Inventory: To view your amulets \n" +
                            "4. Edit InventoryDeck: To edit your card deck \n" +
                            "5. Edit Amulets: To equip or remove your amulets\n" +
                            "6. Exit: To exit to previous menu");
        } else*/
        if (order.matches("Card Inventory\\s*")) {
            System.out.println("Card Inventory:");
            for (int i = 0; i < cardInventory.size(); i++) {
                InventoryThing t = cardInventory.get(i);
                System.out.println(i + 1 + ".\t" + t.getNum()
                        + " " + t.getName() + "\t/\t" + deck.getNumberOfCardsInDeck(t.getName(), "CARD") + " on deck");
            }
            while (inventoryListOrders("Card")) ;
        } else if (order.matches("Item Inventory\\s*")) {
            System.out.println("Item Inventory:");
            for (int i = 0; i < itemInventory.size(); i++) {
                InventoryThing t = itemInventory.get(i);
                System.out.println(i + 1 + ".  " + t.getNum() + " " + t.getName());
            }
            while (inventoryListOrders("Item")) ;
        } else if (order.matches("Amulet Inventory\\s*")) {
            System.out.println("Amulet Inventory:");
            for (int i = 0; i < amuletInventory.size(); i++) {
                InventoryThing t = amuletInventory.get(i);
                System.out.println(i + 1 + ".  " + t.getNum() + " " + t.getName());
            }
            while (inventoryListOrders("Amulet")) ;
        } else if (order.matches("Edit Deck\\s*")) {
            deck.printDeckEnteringText(cardInventory);
            while (deck.editDeckOerders(this)) ;
        } else if (order.matches("Edit Amulets\\s*")) {
            deck.printAmuletEnteringText(amuletInventory);
            while (deck.editAmuletOerders(this)) ;
        } else if (order.matches("Exit")) return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    private boolean inventoryListOrders(String type) {
        String order = Game.give();
        if (order.matches("info \\w*\\s*")) ;
            if (type.equals("Card"))
                CreatCards.getCard(order.split("\\s")[1]).getInfo();
        else if (order.matches("Exit\\s*"))
            return false;
        else if (order.matches("Help\\s*"))
            System.out.println(
                    "1. Info \"" + type + " Name\": To get more information about a " + type + " \n" +
                            "2. Exit: To exit to previous menu");
        else System.out.println("Incorrect order!");
        return true;
    }

    public boolean deckIsFull() {
        return deck.deckIsFull();
    }

    Inventory copy() {
        ArrayList<InventoryThing> cards = new ArrayList<>();
        ArrayList<InventoryThing> items = new ArrayList<>();
        ArrayList<InventoryThing> amulets = new ArrayList<>();
        InventoryDeck deck = this.deck.copy();

        for (int i=0; i<cardInventory.size(); i++)
            cards.add(cardInventory.get(i).copy());
        for (int i=0; i<itemInventory.size(); i++)
            items.add(itemInventory.get(i).copy());
        for (int i=0; i<amuletInventory.size(); i++)
            amulets.add(amuletInventory.get(i).copy());

        Inventory inventory = new Inventory(cards,items,amulets,deck);
        return null;
    }
}

