package src.ApProject.player;

import src.ApProject.Game;
import src.ApProject.constants.ConstantDatas;

import java.util.ArrayList;

public class InventoryDeck {
    private String[] slots = new String[ConstantDatas.SIZE_OF_DECK];
    private String equippedAmulet = "null";


    InventoryDeck(){
        for (int i=0; i<slots.length; i++)
            slots[i] = new String("null");
        for (int i=0; i<5; i++)
            slots[i] = "a";
        for (int i=5; i<10; i++)
            slots[i] = "b";
        for (int i=10; i<15; i++)
            slots[i] = "c";
    }

    protected int getNumberOfCardsInDeck(String name, String type){
        if (type != "CARD") return 0;
        int num = 0;
        for (String s : slots)
            if (name.equals(s)) num++;
        return num;
    }

    protected void printDeckEnteringText(ArrayList<InventoryThing> list){
        System.out.println("InventoryDeck :");
        for (int i=0; i<slots.length; i++)
            System.out.println("Slot "+(i+1)+" :\t"+slots[i]);
        System.out.println("Other Cards :");
        for (int i=0; i<list.size(); i++)
            System.out.println(i+1+".\t"+list.get(i).getNum()+ "\t"+list.get(i).getName()
                    + "\t/\t"+getNumberOfCardsInDeck(list.get(i).getName(),"CARD")+" on deck");
    }

    protected void printAmuletEnteringText(ArrayList<InventoryThing> list){
        System.out.println("Amulets :");
        for (int i=0; i<list.size(); i++)
            System.out.println((i+1)+".\t"+list.get(i).getName());
        if (!equippedAmulet.equals("null"))
            System.out.println("Player is equipped with "+equippedAmulet);
    }

    protected boolean editDeckOerders(Inventory inventory){
        String order = Game.give();

        if (order.matches("Help\\s*"))
            System.out.println(
                    "1. Add \"Card Name\" #CardSlotNum: To add cards to your deck \n" +
                    "2. Remove \"Card Name\" #CardSlotNum: To remove cards from your deck \n" +
                    "3. Info \"Card Name\": To get more information about a specific card\n" +
                    "4. Exit: To return to the previous section");
        else if (order.matches("Again\\s*"))
            printDeckEnteringText(inventory.getList("CARD"));
        else if (order.matches("Add \\w* \\d*\\s*")){
            String[] str = order.split("\\s");
            int numberInInventory = inventory.numberOfThingsInInventory(str[1], "CARD");
            int numberInDeck = getNumberOfCardsInDeck(str[1], "CARD");
            int slotNumber = Integer.parseInt(str[2]);
            if (slotNumber <=0 || slotNumber>slots.length)
                System.out.println("There is Only "+slots.length+"slot.");
            else if (slots[slotNumber-1].equals(str[1]))
                System.out.println("Yeou already have "+str[1]+" in this slot.");
            else if (numberInDeck < numberInInventory) {
                slots[slotNumber-1] = str[1];
                System.out.println(str[1] + " was added to slot " + slotNumber + ".");
            } else System.out.println("You don't have enough card.");
        } else if (order.matches("Remove \\w* \\d*\\s*")){
            String[] str = order.split("\\s");
            int slotNumber = Integer.parseInt(str[2]);
            if (slotNumber <=0 || slotNumber>slots.length)
                System.out.println("There is Only "+slots.length+"slot.");
            else if (slots[slotNumber-1].equals("null"))
                System.out.println("This slot is empty");
            else if (!slots[slotNumber-1].equals(str[1]))
                System.out.println("This slot have a diffrent card.");
            else {
                slots[slotNumber - 1] = "null";
                System.out.println(str[1] + " was removed from slot " + slotNumber + ".");
            }
        } else if (order.equals("Info \\w*\\s*")){
            //toDo Info
        } else if (order.equals("Exit")) return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    protected boolean editAmuletOerders(Inventory inventory){
        String order = Game.give();

        if (order.matches("Help\\s*"))
            System.out.println(
                    "1. Equip \"Amulet Name\": To equip the player with an amulet \n" +
                            "2. Remove Amulet: To remove the amulet equipped with the player (if the player is equipped with one)\n" +
                            "3. Info \"Amulet Name\": To get more information about a specific amulet\n" +
                            "4. Exit: To return to the previous section");
        else if (order.matches("Again\\s*"))
            printAmuletEnteringText(inventory.getList("AMULET"));
        else if (order.matches("Equip \\w*\\s*")){
            String[] str = order.split(" ");
            if (inventory.numberOfThingsInInventory(str[1],"AMULET") > 0){
                equippedAmulet = str[1];
                System.out.println(str[1]+" was equipped on the player.");
            } else System.out.println("You don't have any "+str[1]+".");
        } else if (order.matches("Remove Amulet\\s*")){
            if (equippedAmulet.equals("null")) System.out.println("You don't have any equipped amulet.");
            else {
                System.out.println(equippedAmulet+" was removed!");
                equippedAmulet = "null";
            }
        } else if (order.equals("Info \\w*\\s*")){
            //toDo Info
        } else if (order.equals("Exit")) return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    protected String[] getSlots() {
        return slots;
    }

    protected String getEquippedAmulet() {
        return equippedAmulet;
    }

    public boolean deckIsFull() {
        for (int i=0; i<slots.length; i++)
            if (slots[i].equals("null"))
                return false;
        return true;
    }
}