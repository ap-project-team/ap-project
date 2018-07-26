package src.ApProject.custom.Edit.Shop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import src.ApProject.constants.CreatCards;
import src.ApProject.custom.NewCustomGame;
import src.ApProject.shop.CardShop;
import src.ApProject.thing.Cards.Card;

import java.util.ArrayList;

public class EditCardShop {
    public void start(Scene scene, String path) {
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
            Button button = new Button("Add to Shop");
            button.setOnMouseClicked(event -> {
                CardShop.add(card.getName());
                EditCardShop editCardShop = new EditCardShop();
                editCardShop.start(scene, path);
            });
            gridPane.add(button,  1 + count / 20 * 2, count %20 + 1);
            count++;
        }
        int base = 2 + count / 20 * 2;
        count = 0;
        Label info2 = new Label("Cards In The Shop");
        gridPane.add(info2, base, count);
        info2.setMaxWidth(Double.MAX_VALUE);
        info2.setAlignment(Pos.CENTER);
        ArrayList<String> shopCards = CardShop.getAllCards();
        for (String string : shopCards) {
            Label label = new Label(string);
            gridPane.add(label,   base + count / 20 * 2, count %20 + 1);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            Button button = new Button("Remove From Shop");
            button.setOnMouseClicked(event -> {
                CardShop.remove(string);
                EditCardShop editCardShop = new EditCardShop();
                editCardShop.start(scene, path);
            });
            gridPane.add(button,  base + 1 + count / 20 * 2, count %20 + 1);
            count++;
        }
        StackPane button  = src.ApProject.graphics.Button.buildButton("Back");
        gridPane.add(button,0,21);
        button.setOnMouseClicked(event -> {
            NewCustomGame.start(scene, path);
        });
    }
}
