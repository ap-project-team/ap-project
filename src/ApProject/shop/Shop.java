package src.ApProject.shop;

import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import src.ApProject.constants.CreatCards;
import src.ApProject.graphics.BackButton;
import src.ApProject.graphics.Button;
import src.ApProject.graphics.Map;
import src.ApProject.player.Player;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Item;

import java.util.ArrayList;
import java.util.Optional;

public class Shop {
    Pane root;
    Scene scene;
    CardShop cardShop = new CardShop();
    ItemShop itemShop = new ItemShop();
    AmuletShop amuletShop = new AmuletShop();
    Pane allShopsRoot;

    private void printEnteringText(Player p){
        System.out.println("Remaining gil : " + p.getGil());
        System.out.println("1. Card Shop \n" +
                "2. Item Shop \n" +
                "3. Amulet Shop\n" +
                "4. Exit");
    }

    public void shopView(Player p, Scene scene, Pane mapRoot, Map map){
        root = new Pane();
        scene.setRoot(root);
        this.scene = scene;
        this.allShopsRoot = root;

        StackPane cardShopButton = Button.buildButton("Card Shop");
        cardShopButton.setOnMouseClicked(event -> {
            viewShopThings("CARD", p);
        });

        StackPane itemShopButton = Button.buildButton("Item Shop");
        itemShopButton.setOnMouseClicked(event -> {
            viewShopThings("ITEM", p);
        });

        StackPane amuletShopButton = Button.buildButton("Amulet Shop");
        amuletShopButton.setOnMouseClicked(event -> {
            viewShopThings("AMULET", p);
        });

        ImageView back = BackButton.buildBackButton(scene, mapRoot);
        back.setOnMouseClicked(event -> {
            scene.setRoot(mapRoot);
            map.movePlayer(mapRoot, scene);
        });
        root.getChildren().addAll(back);
        root.getChildren().addAll(Button.buildButtonList(new StackPane[]{cardShopButton, itemShopButton, amuletShopButton}));
    }

    public boolean shopOrders(Player p) {
        printEnteringText(p);
        String order = Game.give();
        String[] str = order.split("\\s");
        /*if (order.matches("Help\\s*")) {
            System.out.println("Remaining gil : " + p.getGil());
            System.out.println("1. Card Shop \n" +
                    "2. Item Shop \n" +
                    "3. Amulet Shop\n" +
                    "4. Exit");
        } else*/ if (order.matches("Card Shop\\s*")) {
            CardShop.printEnteringText(p);
            while (cardShop.cardShopOrders(p)) ;
        } else if (order.matches("Item Shop\\s*")) {
            ItemShop.printEnteringText(p);
            while (itemShop.itemShopOrders(p)) ;
        } else if (order.matches("Amulet Shop\\s*")) {
            AmuletShop.printEnteringText(p);
            while (amuletShop.amuletShopOrders(p)) ;
        } else if (order.matches("Again\\s*"));
        else if (order.matches("Exit\\s*"))
            return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    public void viewShopThings(String type, Player player) {
        ArrayList<String> list;
        root = new Pane();
        root.getChildren().add(BackButton.buildBackButton(scene, allShopsRoot));
        scene.setRoot(root);

        if (type.equals("CARD"))
            list = cardShop.getShopCards();
        else if (type.equals("ITEM"))
            list = itemShop.getShopItems();
        else list = amuletShop.getShopAmulets();

        player.viewInventoryInShop(root.getScene(), root, type, player, this, allShopsRoot);

        VBox shopVBox = new VBox(5);
        HBox[] shopHBoxes = new HBox[list.size()/6 + 1];
        shopHBoxes[list.size()/6] = new HBox();

        shopVBox.setTranslateX(root.getWidth() - 500);
        shopVBox.setTranslateY(60);
        Text text = new Text(root.getWidth() - 500, 40 , "Shop:");
        text.setFont(new Font(25));
        text.setFill(Color.SLATEGRAY);
        for (int i=0; i<list.size(); i++) {
            String name = list.get(i);
            ImageView img = new ImageView("./src//source//" + type + "//" + name + ".png");
            img.setFitHeight(80);
            img.setFitWidth(60);
            img.setEffect(new Glow(0.2));

            StackPane stackPane = new StackPane();
            StackPane info = new StackPane();


            Text t = new Text("");

            img.setOnMouseClicked(event -> {
                TextInputDialog alert = new TextInputDialog("");
                alert.setTitle("Buy");
                alert.setContentText("How much "+name+" do you want ?");
                alert.setHeaderText(type+" SHOP");
                Optional<String> result = alert.showAndWait();

                if (result.isPresent()){
                    if (type.equals("CARD"))
                        player.buy(Integer.parseInt(result.get()), name, type, list, CreatCards.getCard(name).getPrice());
                    else if (type.equals("ITEM"))
                        player.buy(Integer.parseInt(result.get()), name, type, list, Item.getItems(name).getPrice());
                    else player.buy(Integer.parseInt(result.get()), name, type, list, Amulet.getAmulet(name).getPrice());

                    viewShopThings(type, player);
                }
            });

            if (type.equals("CARD")) {
                t = new Text(CreatCards.getCard(name).getInfo());
            } else if (type.equals("ITEM")) {
                t=new Text("Name : "+name+"\n"+ Item.getItems(name).getInfo());
            } else if (type.equals("AMULET")) {
                t = new Text("Name : "+name+"\n"+ Amulet.getAmulet(name).getInfo());
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


            stackPane.getChildren().addAll(img);

            if (i % 5 == 0)
                shopHBoxes[i/5] = new HBox(10);
            shopHBoxes[i/5].getChildren().addAll(stackPane);
        }
        for (int i=0; i<shopHBoxes.length; i++){
            shopVBox.getChildren().add(shopHBoxes[i]);
        }

        root.getChildren().addAll(shopVBox, text);
    }

    public Scene getScene() {
        return scene;
    }
}
