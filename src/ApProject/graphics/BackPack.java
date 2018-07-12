package src.ApProject.graphics;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class BackPack {
    Pane root = new Pane();
    Pane pastRoot;
    Scene scene;

    BackPack(Scene scene, Pane root){
        this.scene = scene;
        this.pastRoot = root;
    }
}
