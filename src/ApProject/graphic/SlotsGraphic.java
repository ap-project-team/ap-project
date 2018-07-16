package src.ApProject.graphic;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SlotsGraphic {
    private ImageView imageView = new ImageView();
    private Image image  = new Image("file:EmptySlot.png");
    private TextField health = new TextField();
    private TextField attack = new TextField();
    {
        imageView.setImage(image);
        imageView.setFitHeight(112 - 32);
        health.setEditable(false);
        attack.setEditable(false);
        health.setFont(new Font(8));
        attack.setFont(new Font(8));
        health.setPrefSize(40,8);
        attack.setPrefSize(40,8);
    }

    private VBox vBox = new VBox();
    public VBox getVBox(){
        HBox hBox = new HBox();
        hBox.getChildren().addAll(attack, health);
        vBox.getChildren().addAll(imageView, hBox);
        return vBox;
    }
}
