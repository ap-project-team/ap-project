package src.ApProject.graphics;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import src.ApProject.battle.Battle;
import src.ApProject.battle.battler.Battler;

import java.util.ArrayList;
import java.util.TimerTask;


abstract public class AttackMove {
    public synchronized static void buildAttackMove(Pane root, Battler battler, Node attacker, Node defender) {
        ArrayList<Circle> balls = new ArrayList<>();

        Bounds attackerBoundsInScene = attacker.localToScene(attacker.getBoundsInLocal());
        Bounds defenderBoundsInScene = defender.localToScene(defender.getBoundsInLocal());

        double beginPointX = (attackerBoundsInScene.getMinX() + attackerBoundsInScene.getMaxX()) / 2;
        double beginPointY = (attackerBoundsInScene.getMinY() + attackerBoundsInScene.getMaxY()) / 2;

        double endPointX = (defenderBoundsInScene.getMinX() + defenderBoundsInScene.getMaxX()) / 2;
        double endPointY = (defenderBoundsInScene.getMinY() + defenderBoundsInScene.getMaxY()) / 2;

        Point2D point2D = new Point2D(beginPointX - endPointX, beginPointY - endPointY);

        double length = Math.sqrt(Math.pow(point2D.getX(), 2) + Math.pow(point2D.getY(), 2));

        balls.add(new Circle(beginPointX, beginPointY, 5, Color.RED));
        battler.getBattle().getRoot().getChildren().addAll(balls.get(0));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (0.2 < balls.get(balls.size() - 1).getRadius()) {
                    Circle circle = new Circle(beginPointX, beginPointY, (5 - balls.size() * 0.15),
                            Color.color((balls.size() * 0.15) / 5, 0, 0));
                    battler.getBattle().getRoot().getChildren().addAll(circle);
                    balls.add(circle);
                }

                if ((Math.abs(balls.get(0).getCenterX() - endPointX) <= 5) &&
                        (Math.abs(balls.get(0).getCenterY() - endPointY) <= 5)) {
                    battler.getBattle().getRoot().getChildren().remove(balls.get(0));
                    balls.remove(balls.get(0));
                }

                if (balls.size() == 0) {
                    //root.getChildren().remove(balls.get(0));
                    //battler.getBattle().newRoot();
                    battler.getBattle().update();
                    System.out.println("END");
                    this.stop();
                }

                for (Circle ball : balls) {
                    ball.setCenterX(ball.getCenterX() - 4 * point2D.getX() / length);
                    ball.setCenterY(ball.getCenterY() - 4 * point2D.getY() / length);
                }

                //battler.getBattle().update(balls, this);
            }
        };
        timer.start();
        battler.getBattle().update();
    }
}

