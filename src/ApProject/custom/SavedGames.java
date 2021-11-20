package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.apache.commons.io.FileUtils;
import src.ApProject.graphics.Button;
import src.Main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class SavedGames {
    public static void start(Scene scene){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        Label label = new Label("Saved Games");
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        gridPane.add(label,0,0);
        File file = new File(".\\src\\Resource");
        String[] directories = file.list(new FilenameFilter() {
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        int rowCount = 1;
        for (String fileName : directories){
            StackPane savedGamesButton =  Button.buildButton(fileName);
            String path =  ".\\src\\Resource\\" + fileName;
            savedGamesButton.setOnMouseClicked(event -> {
                Main.start(scene, path);
            });
            javafx.scene.control.Button button = new javafx.scene.control.Button("Remove");
            button.setTextFill(Color.RED);
            button.setOnMouseClicked(event -> {
                try {
                    FileUtils.deleteDirectory(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SavedGames.start(scene);
            });
            button.setMaxWidth(100);
            gridPane.add(savedGamesButton,0,rowCount);
            if(rowCount != 1)
                gridPane.add(button,1,rowCount);
            rowCount++;
        }


        StackPane backButton =  Button.buildButton("Back");
        backButton.setOnMouseClicked(event -> {
            CustomGame.start(scene);
        });
        gridPane.add(backButton,0,rowCount);

        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        scene.setRoot(gridPane);
    }
}
