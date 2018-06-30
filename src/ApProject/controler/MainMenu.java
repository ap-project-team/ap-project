package src.ApProject.controler;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu extends Parent{
    public Scene buildMainMenu(Scene scene){
        scene = new Scene (this,1000,700);

        getChildren().addAll(buildButtons(scene));
        return scene;
    }

    VBox buildButtons(Scene scene){

        VBox buttons = new VBox(15);
        buttons.setTranslateX(200);
        buttons.setTranslateY(200);

        Pane singlePlayerButton = buildMenuButton("SinglePlayer");
        singlePlayerButton.setOnMouseClicked(event -> {
            SinglePlayerGame singlePlayerGame = new SinglePlayerGame("YOU", scene);
        });


        Pane multiPlayerButton = buildMenuButton("MultiPlayer");

        Pane exitButton = buildMenuButton("Exit");

        Pane optionsButton = buildMenuButton("Options");

        buttons.getChildren().addAll(singlePlayerButton, multiPlayerButton, optionsButton, exitButton);

        return buttons;
    }

    StackPane buildMenuButton(String name){
        StackPane button = new StackPane();

        Text text = new Text(name);
        text.setFont(new Font(20));
        text.setFill(Color.WHITE);

        Rectangle bg = new Rectangle(200,300,250,35);
        bg.setFill(Color.BLACK);
        bg.setOpacity(0.7);
        bg.setEffect(new GaussianBlur(3));

        button.setOnMouseEntered(e -> {
            bg.setOpacity(0.3);
            bg.setTranslateX(10);
            text.setFill(Color.BLACK);
            text.setTranslateX(10);
        });
        button.setOnMouseExited(e -> {
            bg.setOpacity(0.7);
            bg.setTranslateX(0);
            text.setFill(Color.WHITE);
            text.setTranslateX(0);
        });

        DropShadow shadow = new DropShadow(20,Color.WHITE);
        shadow.setInput(new Glow());
        button.setOnMousePressed(event -> button.setEffect(shadow));
        button.setOnMouseReleased(event -> button.setEffect(null));

        button.setAlignment(Pos.CENTER);
        button.getChildren().addAll(bg,text);

        return button;
    }
}

