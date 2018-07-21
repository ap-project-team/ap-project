package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.ApProject.graphics.Button;

public class NewCustomGame {
    public static void start(Stage stage){
        stage.close();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        VBox vBox = new VBox();
        StackPane newCardButton =  Button.buildButton("Create New Card");
        newCardButton.setOnMouseClicked(event -> {
            NewCard.start(stage);
        });
        StackPane newItemButton =  Button.buildButton("Create New Items");
        newItemButton.setOnMouseClicked(event -> {
            NewItem newItem = new NewItem();
            newItem.start(stage);
        });
        vBox.getChildren().addAll(newCardButton, newItemButton);
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20,20,20,20));
        gridPane.add(vBox,0,0);
        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
