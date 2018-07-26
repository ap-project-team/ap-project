package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import src.ApProject.shop.AmuletShop;
import src.ApProject.shop.ItemShop;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Item;

import java.util.ArrayList;

public class EditItemShop {
    public void start(Scene scene, String path){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20, 20,20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        scene.setRoot(gridPane);
        int count = 0;
        Label info = new Label("All Items");
        Label info1 = new Label("Info");
        gridPane.add(info,0,count);
        gridPane.add(info1,1,count);
        Item[] allItems = Item.getAllItems();
        for(Item item : allItems){
            Label label = new Label(item.getName());
            Label label1 = new Label(item.getInfo());
            gridPane.add(label,   count / 20 * 3, count %20 + 1);
            gridPane.add(label1,  1 + count / 20 * 3, count %20 + 1);
            Button button = new Button("Add to Shop");
            button.setOnMouseClicked(event -> {
                ItemShop.add(item.getName());
                EditItemShop editItemShop = new EditItemShop();
                editItemShop.start(scene, path);
            });
            gridPane.add(button,  2 + count / 20 * 3, count %20 + 1);
            count++;
        }
        count = 0;
        int base = 3 + count / 20 * 2;
        Label info2 = new Label("Items In The Shop");
        Label info3 = new Label("Info");
        gridPane.add(info2,base,count);
        gridPane.add(info3,base + 1,count);
        ArrayList<String> shopItems = ItemShop.getAllItems();
        for(String string: shopItems){
            Label label = new Label(string);
            Label label1 = new Label(Item.getItems(string).getInfo());
            gridPane.add(label,   base + count / 20 * 3, count %20 + 1);
            gridPane.add(label1,   base + 1 + count / 20 * 3, count %20 + 1);
            Button button = new Button("Remove From Shop");
            button.setOnMouseClicked(event -> {
                ItemShop.remove(string);
                EditItemShop editItemShop = new EditItemShop();
                editItemShop.start(scene, path);
            });
            gridPane.add(button,   base + 2 + count / 20 * 3, count %20 + 1);
            count++;
        }
        StackPane button  = src.ApProject.graphics.Button.buildButton("Back");
        gridPane.add(button,0,21);
        button.setOnMouseClicked(event -> {
            NewCustomGame.start(scene, path);
        });
    }
}
