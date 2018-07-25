package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import src.ApProject.shop.AmuletShop;
import src.ApProject.thing.Amulet;

import java.io.BufferedReader;
import java.util.ArrayList;


public class EditAmuletShop {
    public void start(Scene scene, String path){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20, 20,20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        scene.setRoot(gridPane);
        int count = 0;
        Label info = new Label("All Amulets");
        Label info1 = new Label("Info");
        gridPane.add(info,0,count);
        gridPane.add(info1,1,count);
        Amulet[] allAmulets = Amulet.getAllAmulets();
        for(Amulet amulet : allAmulets){
            Label label = new Label(amulet.getName());
            Label label1 = new Label(amulet.getInfo());
            gridPane.add(label,   count / 20 * 3, count %20 + 1);
            gridPane.add(label1,  1 + count / 20 * 3, count %20 + 1);
            Button button = new Button("Add to Shop");
            button.setOnMouseClicked(event -> {
                AmuletShop.add(amulet.getName());
                EditAmuletShop editAmuletShop = new EditAmuletShop();
                editAmuletShop.start(scene, path);
            });
            gridPane.add(button,  2 + count / 20 * 3, count %20 + 1);
            count++;
        }
        count = 0;
        int base = 3 + count / 20 * 2;
        Label info2 = new Label("Amulets In The Shop");
        Label info3 = new Label("Info");
        gridPane.add(info2,base,count);
        gridPane.add(info3,base + 1,count);
        ArrayList<String> shopAmulets = AmuletShop.getAllAmulets();
        for(String string: shopAmulets){
            Label label = new Label(string);
            Label label1 = new Label(Amulet.getAmulet(string).getInfo());
            gridPane.add(label,   base + count / 20 * 3, count %20 + 1);
            gridPane.add(label1,   base + 1 + count / 20 * 3, count %20 + 1);
            Button button = new Button("Remove From Shop");
            button.setOnMouseClicked(event -> {
                AmuletShop.remove(string);
                EditAmuletShop editAmuletShop = new EditAmuletShop();
                editAmuletShop.start(scene, path);
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
