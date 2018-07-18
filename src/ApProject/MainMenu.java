package src.ApProject;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import src.ApProject.Game;
import src.ApProject.graphics.Button;

public class MainMenu extends Pane{
    public Scene buildMainMenu(Scene scene){
        scene = new Scene (this,1000,700);

        getChildren().addAll(buildButtons(scene));
        return scene;
    }

    VBox buildButtons(Scene scene){

        StackPane singlePlayerButton = Button.buildButton("SinglePlayer");
        singlePlayerButton.setOnMouseClicked(event -> {
            Game singlePlayerGame = new Game("YOU", scene, this);
        });


        StackPane multiPlayerButton = Button.buildButton("MultiPlayer");

        StackPane optionsButton = Button.buildButton("Options");

        StackPane exitButton = Button.buildButton("Exit");
        exitButton.setOnMouseClicked(event -> Platform.exit());
        return Button.buildButtonList(new StackPane[]{singlePlayerButton, multiPlayerButton, optionsButton, exitButton});
    }
}
