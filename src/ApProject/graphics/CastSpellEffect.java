package src.ApProject.graphics;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import src.ApProject.battle.battler.Battler;

import java.util.ArrayList;

public abstract class CastSpellEffect {
    public static void buildCastSpellEffect(Battler battler, Node defender) {
        battler.getBattle().addEffect();
        System.out.println("CastSpell Added : "+battler.getBattle().getActiveEffects());
        final boolean[] back = {false};
        final double[] i = {0};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (back[0]) i[0] -=0.5;
                else i[0] +=0.5;

                if (i[0] == 10) back[0] = true;

                if (i[0] <= 0) {
                    this.stop();
                    battler.getBattle().deleteEffect();
                    battler.getBattle().update();
                    System.out.println("CastSpell Removed : "+battler.getBattle().getActiveEffects());
                }
                defender.setEffect(new DropShadow(i[0], Color.BLUE));
            }
        };
        timer.start();
    }

}
