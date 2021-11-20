package src.ApProject.graphics;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class BackButton {
    public static ImageView buildBackButton(Scene scene, Pane pastRoot){
        ImageView image = new ImageView("./src//source//back.png");
        image.setFitHeight(30);
        image.setFitWidth(30);
        image.setX(15);
        image.setY(15);

        image.setOnMouseClicked(event -> {
            scene.setRoot(pastRoot);
        });

        image.setOnMouseEntered(event -> {
            image.setOpacity(0.2);
        });

        image.setOnMouseExited(event -> {
            image.setOpacity(1);
        });

        return image;
    }
}