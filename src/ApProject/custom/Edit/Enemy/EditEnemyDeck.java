package src.ApProject.custom.Edit.Enemy;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import src.ApProject.constants.AI_BattlerBuilder;
import src.ApProject.custom.NewCustomGame;

public class EditEnemyDeck {
    private int level;
    public void start(Scene scene, String path){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        scene.setRoot(gridPane);
        int count = 0;
        Label info = new Label("All Enemy Decks");
        info.setMaxWidth(Double.MAX_VALUE);
        info.setAlignment(Pos.CENTER);
        gridPane.add(info, 0, count);
        for (int i =  1; i < 3 ; i++) {
            level = i;
            Label label = new Label(AI_BattlerBuilder.getEnemyName(level));
            gridPane.add(label,   count / 20 * 2, count %20 + 1);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            Button button = new Button("Edit Deck");
            button.setOnMouseClicked(event -> {
                EditDeck editDeck = new EditDeck();
                editDeck.start(scene, path, level);
            });
            gridPane.add(button,  1 + count / 20 * 2, count %20 + 1);
            count++;
        }
        StackPane button  = src.ApProject.graphics.Button.buildButton("Back");
        gridPane.add(button,0,21);
        button.setOnMouseClicked(event -> {
            NewCustomGame.start(scene, path);
        });
    }
}
