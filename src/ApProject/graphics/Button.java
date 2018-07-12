package src.ApProject.graphics;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Button {
    public static StackPane buildButton(String name){
        StackPane button = new StackPane();

        Text text = new Text(name);
        text.setFont(new Font(20));
        text.setFill(Color.WHITE);

        Rectangle rectangle = new Rectangle(200,300,250,35);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(0.7);
        rectangle.setEffect(new GaussianBlur(3));

        button.setOnMouseEntered(e -> {
            rectangle.setOpacity(0.3);
            rectangle.setTranslateX(10);
            text.setFill(Color.BLACK);
            text.setTranslateX(10);
        });
        button.setOnMouseExited(e -> {
            rectangle.setOpacity(0.7);
            rectangle.setTranslateX(0);
            text.setFill(Color.WHITE);
            text.setTranslateX(0);
        });

        DropShadow shadow = new DropShadow(20,Color.WHITE);
        shadow.setInput(new Glow());
        button.setOnMousePressed(event -> button.setEffect(shadow));
        button.setOnMouseReleased(event -> button.setEffect(null));

        button.setAlignment(Pos.CENTER);
        button.getChildren().addAll(rectangle,text);

        return button;
    }

    public static VBox buildButtonList(StackPane[] buttuns){
        VBox buttons = new VBox(15);
        buttons.setTranslateX(200);
        buttons.setTranslateY(200);

        for (StackPane button : buttuns)
            buttons.getChildren().addAll(button);

        return buttons;
    }
}

