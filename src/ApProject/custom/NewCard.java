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
import javafx.stage.Stage;
import src.ApProject.graphics.Button;

public class NewCard {
    public static void start(Stage stage){
        stage.close();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        HBox hBox = new HBox();
        StackPane monsterCard =  Button.buildButton("Monster Card");
        StackPane spellCard =  Button.buildButton("Spell Card");
        Label cardTypeLabel = new Label("Chose Card Type");
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
        hBox.getChildren().addAll(monsterCard, spellCard, cardTypeLabel);
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20,20,20,20));
        gridPane.add(hBox,0,0);
        gridPane.setPrefSize(2000,1080);
        gridPane.setMaxSize(2000,1080);
        gridPane.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
