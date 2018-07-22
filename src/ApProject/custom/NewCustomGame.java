package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.apache.commons.io.FileUtils;
import src.ApProject.graphics.Button;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class NewCustomGame {
    private static String sourcePath;
    private static String destinationPath;
    public static void start(Scene scene, String path){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        VBox vBox = new VBox();
        StackPane newCardButton =  Button.buildButton("Create New Card");
        newCardButton.setOnMouseClicked(event -> {
            NewCard.start(scene);
        });
        StackPane newItemButton =  Button.buildButton("Create New Item");
        newItemButton.setOnMouseClicked(event -> {
            NewItem newItem = new NewItem();
            newItem.start(scene);
        });
        StackPane newAmuletButton =  Button.buildButton("Create New Amulet");
        newAmuletButton.setOnMouseClicked(event -> {
            NewAmulet newAmulet = new NewAmulet();
            newAmulet.start(scene);
        });
        vBox.getChildren().addAll(newCardButton, newItemButton, newAmuletButton);
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20,20,20,20));
        gridPane.add(vBox,0,0);
        gridPane.setAlignment(Pos.CENTER);
        scene.setRoot(gridPane);
    }

    public static void showMenu(Scene scene){
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
                    sourcePath =  path;
            });
            javafx.scene.control.Button button = new javafx.scene.control.Button("Remove");
            button.setTextFill(Color.RED);
            button.setOnMouseClicked(event -> {
                try {
                    FileUtils.deleteDirectory(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showMenu(scene);
            });
            button.setMaxWidth(100);
            gridPane.add(savedGamesButton,0,rowCount);
            if(rowCount != 1)
                gridPane.add(button,1,rowCount);
            rowCount++;
        }
        TextField textField = new TextField("Input Custom Game's Name");
        textField.setOnMouseClicked(event -> {
            textField.setText("");
        });
        textField.setAlignment(Pos.CENTER);
        StackPane savedGamesButton =  Button.buildButton("Continue");
        savedGamesButton.setOnMouseClicked(event -> {
            destinationPath = ".\\src\\Resource\\" + textField.getText();
            new File(destinationPath).mkdirs();
            try {
                FileUtils.copyDirectory(new File(sourcePath), new File(destinationPath));
            } catch (IOException e) {

            }
            start(scene, destinationPath);
        });
        gridPane.add(textField, 0, rowCount++);
        gridPane.add(savedGamesButton,0,rowCount++);
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
