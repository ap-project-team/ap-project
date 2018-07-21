package src.ApProject.graphics;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import src.ApProject.battle.Battle;

abstract public class DieingEffect {
    public static void buildEffect(Node node, Battle battle){
        battle.addEffect();
        AnimationTimer timer = new AnimationTimer() {
            public double i=0;

            @Override
            public void handle(long now) {
                i+=1;

                if (i > 19.0) {
                    this.stop();
                    battle.deleteEffect();
                    battle.update();
                }

                node.setEffect(new DropShadow(i, Color.color(1-i/20,0,0)));
                node.setOpacity(1-i/20);
            }
        };
        timer.start();
    }
}
