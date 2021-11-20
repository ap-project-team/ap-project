package src.ApProject.graphics;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public abstract class EndGameMessage {
    public static void buildMessage(Pane root, String state){
        StackPane message = new StackPane();

        ImageView imageView = new ImageView("./src//source//"+state+".png");
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);

        message.getChildren().addAll(imageView);
        root.getChildren().addAll(message);

        Text text;

        if (state.equals("lose"))
            text = new Text("You Lost !!\nMystic Hour Glass used !!");
        else text = new Text("You Won !!");

        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(new Font(40));
        text.setTranslateY(250);
        message.getChildren().addAll(text);

        message.setAlignment(Pos.CENTER);
        message.setTranslateX(root.getWidth()/2 - imageView.getFitWidth()/2);
        message.setTranslateY(root.getHeight()/2 - imageView.getFitHeight()/2);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (message.getOpacity() - 0.005 < 0) {
                    root.getChildren().remove(message);
                    this.stop();
                }
                message.setOpacity(message.getOpacity() - 0.005);
            }
        };

        timer.start();
    }
}
