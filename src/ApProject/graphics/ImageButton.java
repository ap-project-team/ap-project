package src.ApProject.graphics;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class ImageButton {
    public static StackPane buildButton(String URL){
        StackPane button = new StackPane();


        ImageView img = new ImageView(URL);
        img.setFitWidth(150);
        img.setFitHeight(150);
        button.setOpacity(0.9);

        button.setOnMouseEntered(e -> {
            img.setOpacity(0.3);
        });
        button.setOnMouseExited(e -> {
            img.setOpacity(0.9);
        });

        DropShadow shadow = new DropShadow(20,Color.WHITE);
        shadow.setInput(new Glow());
        button.setOnMousePressed(event -> button.setEffect(shadow));
        button.setOnMouseReleased(event -> button.setEffect(null));

        button.getChildren().addAll(img);
        button.setAlignment(Pos.CENTER);

        return button;
    }

    public static HBox buildButtonList(StackPane[] buttons){
        HBox buttonBox = new HBox(15);
        buttonBox.setTranslateX(200);
        buttonBox.setTranslateY(200);

        for (StackPane button : buttons)
            buttonBox.getChildren().addAll(button);

        return buttonBox;
    }


}
