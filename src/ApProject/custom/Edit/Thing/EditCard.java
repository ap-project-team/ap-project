package src.ApProject.custom.Edit.Thing;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import src.ApProject.constants.CreatCards;
import src.ApProject.custom.Edit.Shop.EditCardShop;
import src.ApProject.custom.New.Card.NewMonsterCard;
import src.ApProject.custom.New.Card.NewSpellCard;
import src.ApProject.custom.NewCustomGame;
import src.ApProject.shop.CardShop;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MonsterCard;

public class EditCard {
    public void start(Scene scene, String path){
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
            Button button = new Button("Edit");
            button.setOnMouseClicked(event -> {
                CreatCards.remove(card);
                gridPane.getChildren().clear();
                if(card.getCardType().equals("SPELLCARD")){
                    NewSpellCard newSpellCard = new NewSpellCard();
                    gridPane.getChildren().add(newSpellCard.getEditGridPane(scene,path, card));
                }else {
                    NewMonsterCard newMonsterCard = new NewMonsterCard();
                    gridPane.getChildren().add(newMonsterCard.getEditGridPane(scene, path, (MonsterCard) card));
                }
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
