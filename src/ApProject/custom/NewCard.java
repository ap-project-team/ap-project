package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import src.ApProject.graphics.Button;

public class NewCard {
    public static void start(Stage stage){
        stage.close();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        StackPane monsterCard =  Button.buildButton("Monster Card");
        StackPane spellCard =  Button.buildButton("Spell Card");
        Label cardTypeLabel = new Label("Choose Card Type");
        cardTypeLabel.setTextAlignment(TextAlignment.CENTER);
        cardTypeLabel.setMaxWidth(Double.MAX_VALUE);
        cardTypeLabel.setAlignment(Pos.CENTER);
        monsterCard.setOnMouseClicked(event -> {
            cardTypeLabel.setText("Monster Card");
            NewMonsterCard newMonsterCard = new NewMonsterCard();
            gridPane.getChildren().clear();
            VBox vBox = new VBox(new Label("Monster Card"));
            vBox.setAlignment(Pos.CENTER);
            gridPane.add(vBox, 0 ,0);
            gridPane.add(newMonsterCard.getGridPane(stage),0,1);
        });
        spellCard.setOnMouseClicked(event -> {
            cardTypeLabel.setText("Spell Card");
            NewSpellCard newSpellCard = new NewSpellCard();
            gridPane.getChildren().clear();
            VBox vBox = new VBox(new Label("Spell Card"));
            vBox.setAlignment(Pos.CENTER);
            gridPane.add(vBox, 0 ,0);
            gridPane.add(newSpellCard.getGridPane(stage),0,1);
        });
        StackPane backButton =  Button.buildButton("Back");
        backButton.setOnMouseClicked(event -> {
            NewCustomGame.start(stage);
        });
        gridPane.add(monsterCard,0,1);
        gridPane.add(spellCard,2,1);
        gridPane.add(cardTypeLabel,1,0);
        gridPane.add(backButton, 1 , 2);
        gridPane.setPrefSize(2000,1080);
        gridPane.setMaxSize(2000,1080);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20,20, 20,20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
