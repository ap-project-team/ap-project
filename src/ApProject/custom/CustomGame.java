package src.ApProject.custom;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.ApProject.graphics.Button;

import java.awt.event.MouseEvent;


public class CustomGame {
    private Stage stage;
    public CustomGame(Stage primaryStage){
        this.stage = primaryStage;
        stage.close();
    }

    public void start(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        VBox vBox = new VBox();
        StackPane newGame =  Button.buildButton("Start New Custom Game");
        newGame.setOnMouseClicked(event -> {
            NewCustomGame.start(stage);
        });
        StackPane savedButton =  Button.buildButton("Saved Games");
        vBox.getChildren().addAll(newGame, savedButton);
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
