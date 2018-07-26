package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import src.ApProject.graphics.Button;
import src.ApProject.thing.Item;

import java.io.*;

public class NewItem {
    private String itemName;
    private int itemMPChange;
    private int itemCost;
    private int itemHPChange;
    private String path;

    public void start(Scene scene){
        GridPane gridPane = new GridPane();
        Label itemNameLabel = new Label("Insert Item's Name : ");
        TextField itemNameTextField = new TextField();
        gridPane.add(itemNameLabel, 0, 0);
        gridPane.add(itemNameTextField, 1,0);

        Label itemPriceLabel = new Label("Insert Item's Price : ");
        TextField itemPriceTextField = new TextField();
        gridPane.add(itemPriceLabel, 0, 1);
        gridPane.add(itemPriceTextField, 1,1);

        Label itemHPLabel = new Label("Insert Player's HP Change : ");
        TextField itemHPTextField = new TextField();
        gridPane.add(itemHPLabel, 0, 2);
        gridPane.add(itemHPTextField, 1,2);

        Label itemMPLabel = new Label("Insert Player's MP Change : ");
        TextField itemMPTextField = new TextField();
        gridPane.add(itemMPLabel, 0, 3);
        gridPane.add(itemMPTextField, 1,3);

        StackPane confirmButton = Button.buildButton("Confirm");
        confirmButton.setOnMouseClicked(event -> {
            gridPane.getChildren().clear();
            itemName = itemNameTextField.getText();
            itemCost = Integer.parseInt(itemPriceTextField.getText());
            itemHPChange = Integer.parseInt(itemHPTextField.getText());
            itemMPChange = Integer.parseInt(itemMPTextField.getText());

            Item item = new Item(itemName, itemCost, itemHPChange, itemMPChange);
            save(item);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your Item Was Successfully Added To The Items!");
            alert.showAndWait();
            NewCustomGame.start(scene, path);
        });
        StackPane backButton = Button.buildButton("Back");
        backButton.setOnMouseClicked(event -> {
            NewCustomGame.start(scene, path);
        });
        gridPane.add(confirmButton, 1, 4);
        gridPane.add(backButton,0,4);

        gridPane.setPrefSize(2000,1080);
        gridPane.setMaxSize(2000,1080);
        gridPane.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        Label itemLabel = new Label("Item");
        itemLabel.setTextAlignment(TextAlignment.CENTER);
        itemLabel.setMaxWidth(Double.MAX_VALUE);
        itemLabel.setAlignment(Pos.CENTER);
        vBox.getChildren().add(itemLabel);
        vBox.getChildren().add(gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.setSpacing(20);
        gridPane.setPadding(new Insets(20,20, 20,20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        scene.setRoot(vBox);
    }


    public void save(Item item){
        Item.addItem(item);
    }
}
