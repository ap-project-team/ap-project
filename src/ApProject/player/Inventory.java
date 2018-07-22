package src.ApProject.player;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import src.ApProject.Game;
import src.ApProject.constants.CreatCards;
import src.ApProject.graphics.BackButton;
import src.ApProject.graphics.Message;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Item;

import java.util.ArrayList;

public class Inventory {
    protected ArrayList<InventoryThing> cardInventory = new ArrayList<>();
    protected ArrayList<InventoryThing> itemInventory = new ArrayList<>();
    protected ArrayList<InventoryThing> amuletInventory = new ArrayList<>();
    protected InventoryDeck deck = new InventoryDeck();


    Inventory(){
        cardInventory.add(new InventoryThing(5,"ThrowingKnives"));
        cardInventory.add(new InventoryThing(5,"PoisonousCauldron"));
        cardInventory.add(new InventoryThing(5,"LunarBlessing"));
        cardInventory.add(new InventoryThing(5,"ElvenRanger"));
        cardInventory.add(new InventoryThing(10,"OgreWarchief"));

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

    public void printInventory(ArrayList<InventoryThing> list) {
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
            while (deck.editDeckOrders(this)) ;
        } else if (order.matches("Edit Amulets\\s*")) {
            deck.printAmuletEnteringText(amuletInventory);
            while (deck.editAmuletOerders(this)) ;
        } else if (order.matches("Exit")) return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    private boolean inventoryListOrders(String type) {
        String order = Game.give();
        if (order.matches("Info \\w*\\s*")) {
            if (type.equals("Card"))
                CreatCards.getCard(order.split("\\s")[1]).getInfo();
        } else if (order.matches("Exit\\s*"))
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

    public void editDeck(Scene scene, Pane pastRoot){
        Pane root = new Pane();
        scene.setRoot(root);
        root.getChildren().addAll(BackButton.buildBackButton(scene, pastRoot));

        deck.viewDeck(root);
        viewInventory(root,"CARD", cardInventory);

        Rectangle rectangle = new Rectangle(root.getWidth()-400,0,10,root.getHeight());
        root.getChildren().addAll(rectangle);
    }

    public void editAmulet(Pane root){
        StackPane stackPane = new StackPane();
        stackPane.setTranslateX(root.getWidth()-600);
        stackPane.setTranslateY(200);
        stackPane.setAlignment(Pos.CENTER);

        Rectangle rectangle = new Rectangle(400,500, Color.LIGHTGRAY);
        Text text;

        stackPane.getChildren().addAll(rectangle);

        if (deck.getEquippedAmulet().equals("null")) {
            text = new Text("You don't have an equipped Amulet.");
        } else {
            text = new Text("Equipped Amulet : "+deck.getEquippedAmulet());
            text.setTranslateY(-150);
            ImageView image = new ImageView("./src//source//AMULET//"+deck.getEquippedAmulet()+".png");
            image.setFitWidth(200);
            image.setFitHeight(200);
            stackPane.getChildren().addAll(image);

            image.setOnMouseClicked(event -> {
                deck.removeEquippedAmulet();
                root.getChildren().removeAll(stackPane);
                editAmulet(root);
            });
        }

        text.setFont(new Font(15));
        stackPane.getChildren().addAll(text);
        root.getChildren().addAll(stackPane);
    }

    public void viewInventory(Pane root, String type, ArrayList<InventoryThing> list) {
        VBox inventoryVBox = new VBox(5);
        inventoryVBox.setTranslateX(100);
        inventoryVBox.setTranslateY(60);
        Text text = new Text(100, 40 , "Your Inventory :");
        text.setFont(new Font(25));
        text.setFill(Color.SLATEGRAY);
        for (int i=0; i<list.size(); i++) {
            InventoryThing thing = list.get(i);
            ImageView img = new ImageView("./src//source//"+type+"//" + thing.getName() + ".png");
            img.setFitHeight(20);
            img.setFitWidth(350);
            img.setEffect(new Glow(0.2));

            StackPane stackPane = new StackPane();
            StackPane info = new StackPane();


            Text t = new Text("");
            if (type.equals("CARD")) {
                t = new Text(CreatCards.getCard(thing.getName()).getInfo());

                img.setOnMouseClicked(event -> {
                    int numberInInventory = thing.getNum();
                    int numberInDeck = deck.getNumberOfCardsInDeck(thing.getName(), "CARD");
                    if (deckIsFull()) {
                        root.getChildren().addAll(Message.buildMessage("Your Deck is full.",  root));
                    } else if (numberInDeck >= numberInInventory) {
                        root.getChildren().addAll(Message.buildMessage("You don't have enough " + thing.getName() + ".",  root));
                    } else if (numberInDeck < numberInInventory) {
                        deck.addCardToDeck(thing.name);
                        deck.sort();
                    }
                });

            } else if (type.equals("ITEM")) {
                t=new Text("Name : "+thing.getName()+"\n"+Item.getItems(thing.getName()).getInfo());
            } else if (type.equals("AMULET")) {
                t = new Text("Name : "+thing.getName()+"\n"+Amulet.getAmulet(thing.getName()).getInfo());
                img.setOnMouseClicked(event -> {
                    deck.setEquippedAmulet(thing.getName());
                    editAmulet(root);
                });
            }

            t.setFont(new Font(10));
            t.setFill(Color.SLATEGRAY);

            Rectangle rectangle = new Rectangle(t.maxWidth(100)+10, t.maxHeight(100)+10);
            rectangle.setFill(Color.LIGHTYELLOW);
            rectangle.setArcHeight(15);
            rectangle.setArcWidth(15);
            info.getChildren().addAll(rectangle, t);
            info.setAlignment(Pos.CENTER);

            img.setOnMouseEntered(event -> {
                img.setOpacity(0.6);
                root.getChildren().addAll(info);
                info.setTranslateX(event.getSceneX()-5);
                info.setTranslateY(event.getSceneY()+5);
            });
            img.setOnMouseExited(event -> {
                img.setOpacity(1);
                root.getChildren().remove(info);
            });

            Text numText = new Text(thing.getNum()+"×");
            numText.setFill(Color.ORANGE);
            numText.setFont(new Font(20));
            inventoryVBox.getChildren().addAll(numText);

            numText.setTranslateX(-200);
            stackPane.getChildren().addAll(numText, img);
            inventoryVBox.getChildren().addAll(stackPane);
        }
        root.getChildren().addAll(inventoryVBox, text);
    }

}

