package src.ApProject.custom.Edit.Thing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import src.ApProject.custom.New.NewAmulet;
import src.ApProject.custom.NewCustomGame;
import src.ApProject.thing.Amulet;

public class EditAmulet {
    public void start(Scene scene, String path){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20, 20,20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        scene.setRoot(gridPane);
        int count = 0;
        Label info = new Label("All Amulets");
        info.setMaxWidth(Double.MAX_VALUE);
        info.setAlignment(Pos.CENTER);
        Label info1 = new Label("Info");
        info1.setMaxWidth(Double.MAX_VALUE);
        info1.setAlignment(Pos.CENTER);
        gridPane.add(info,0,count);
        gridPane.add(info1,1,count);
        Amulet[] allAmulets = Amulet.getAllAmulets();
        for(Amulet amulet : allAmulets){
            Label label = new Label(amulet.getName());
            Label label1 = new Label(amulet.getInfo());
            label.setMaxWidth(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            label1.setMaxWidth(Double.MAX_VALUE);
            label1.setAlignment(Pos.CENTER);
            gridPane.add(label,   count / 20 * 3, count %20 + 1);
            gridPane.add(label1,  1 + count / 20 * 3, count %20 + 1);
            Button button = new Button("Edit");
            button.setOnMouseClicked(event -> {
               gridPane.getChildren().clear();
               Amulet.remove(amulet);
                NewAmulet newAmulet = new NewAmulet();
                newAmulet.Edit(scene, path, amulet);
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
