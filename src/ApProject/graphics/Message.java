package src.ApProject.graphics;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Message{
    public static Text buildMessage(String text, Pane root){
        Text t = new Text(text);
        t.setFont(new Font(20));


        t.setX(root.getWidth()/2 - t.maxWidth(10)/2);
        t.setY(root.getHeight() - 20);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                t.setOpacity(t.getOpacity()-0.01);
                if (t.getOpacity() == 0)
                    this.stop();
            }
        };

        timer.start();
        return t;
    }
}
