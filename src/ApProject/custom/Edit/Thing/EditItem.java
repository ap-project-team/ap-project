package src.ApProject.custom.Edit.Thing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import src.ApProject.custom.Edit.Shop.EditItemShop;
import src.ApProject.custom.New.NewItem;
import src.ApProject.custom.NewCustomGame;
import src.ApProject.shop.ItemShop;
import src.ApProject.thing.Item;

public class EditItem {
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
        info.setMaxWidth(Double.MAX_VALUE);
        info.setAlignment(Pos.CENTER);
        info1.setMaxWidth(Double.MAX_VALUE);
        info1.setAlignment(Pos.CENTER);
        gridPane.add(info,0,count);
        gridPane.add(info1,1,count);
        Item[] allItems = Item.getAllItems();
        for(Item item : allItems){
            Label label = new Label(item.getName());
            Label label1 = new Label(item.getInfo());
            gridPane.add(label,   count / 20 * 3, count %20 + 1);
            gridPane.add(label1,  1 + count / 20 * 3, count %20 + 1);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            label1.setMaxWidth(Double.MAX_VALUE);
            label1.setAlignment(Pos.CENTER);
            Button button = new Button("Edit");
            button.setOnMouseClicked(event -> {
                Item.remove(item);
                NewItem newItem = new NewItem();
                newItem.edit(scene, path, item);
            });
            gridPane.add(button,  2 + count / 20 * 3, count %20 + 1);
            count++;
        }
        StackPane button  = src.ApProject.graphics.Button.buildButton("Back");
        gridPane.add(button,0,21);
        button.setOnMouseClicked(event -> {
            NewCustomGame.start(scene, path);
        });
    }
}
