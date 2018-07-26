package src.ApProject.custom.Edit.Enemy;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import src.ApProject.constants.AI_BattlerBuilder;
import src.ApProject.constants.CreatCards;
import src.ApProject.thing.Cards.Card;

import java.util.ArrayList;

public class EditDeck {
    public void start(Scene scene, String path, int level){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        scene.setRoot(gridPane);
        int count = 0;
        Label info = new Label("All Cards");
        info.setMaxWidth(Double.MAX_VALUE);
        info.setAlignment(Pos.CENTER);
        gridPane.add(info, 0, count);
        Card[] allCards = CreatCards.getAllCards();
        for (Card card : allCards) {
            Label label = new Label(card.getName());
            gridPane.add(label,   count / 20 * 2, count %20 + 1);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            Button button = new Button("Add to Deck");
            button.setOnMouseClicked(event -> {
                AI_BattlerBuilder.add(level,card);
                EditDeck editDeck = new EditDeck();
                editDeck.start(scene, path, level);
            });
            gridPane.add(button,  1 + count / 20 * 2, count %20 + 1);
            count++;
        }
        int base = 2 + count / 20 * 2;
        count = 0;
        Label info2 = new Label("Cards In The Deck");
        gridPane.add(info2, base, count);
        info2.setMaxWidth(Double.MAX_VALUE);
        info2.setAlignment(Pos.CENTER);
        ArrayList<Card> enemyDeck = AI_BattlerBuilder.getCardArrayList(level);
        for (Card card : enemyDeck) {
            Label label = new Label(card.getName());
            gridPane.add(label,   base + count / 20 * 2, count %20 + 1);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            Button button = new Button("Remove From Deck");
            button.setOnMouseClicked(event -> {
                AI_BattlerBuilder.remove(level,card);
                EditDeck editDeck = new EditDeck();
                editDeck.start(scene, path, level);
            });
            gridPane.add(button,  base + 1 + count / 20 * 2, count %20 + 1);
            count++;
        }
        StackPane button  = src.ApProject.graphics.Button.buildButton("Back");
        gridPane.add(button,0,21);
        button.setOnMouseClicked(event -> {
            if(AI_BattlerBuilder.getCardArrayList(level).size() >= 25 && AI_BattlerBuilder.getCardArrayList(level).size() <= 30) {
                EditEnemyDeck editEnemyDeck = new EditEnemyDeck();
                editEnemyDeck.start(scene, path);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Number Of Cards In The Deck Must Be Between 25 and 30!");
                alert.showAndWait();
            }
        });
    }
}
