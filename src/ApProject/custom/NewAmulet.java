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
import src.ApProject.thing.Amulet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class NewAmulet {
    private String amuletName;
    private int amuletMPChange;
    private int amuletCost;
    private int amuletHPChange;
    private String path;
    public void start(Scene scene){
        GridPane gridPane = new GridPane();
        Label amuletNameLabel = new Label("Insert Amulet's Name : ");
        TextField amuletNameTextField = new TextField();
        gridPane.add(amuletNameLabel, 0, 0);
        gridPane.add(amuletNameTextField, 1,0);

        Label amuletPriceLabel = new Label("Insert Amulet's Price : ");
        TextField amuletPriceTextField = new TextField();
        gridPane.add(amuletPriceLabel, 0, 1);
        gridPane.add(amuletPriceTextField, 1,1);

        Label amuletHPLabel = new Label("Insert Player's HP Change : ");
        TextField amuletHPTextField = new TextField();
        gridPane.add(amuletHPLabel, 0, 2);
        gridPane.add(amuletHPTextField, 1,2);

        Label amuletMPLabel = new Label("Insert Player's MP Change : ");
        TextField amuletMPTextField = new TextField();
        gridPane.add(amuletMPLabel, 0, 3);
        gridPane.add(amuletMPTextField, 1,3);

        StackPane confirmButton = Button.buildButton("Confirm");
        confirmButton.setOnMouseClicked(event -> {
            gridPane.getChildren().clear();
            amuletName = amuletNameTextField.getText();
            amuletCost = Integer.parseInt(amuletPriceTextField.getText());
            amuletHPChange = Integer.parseInt(amuletHPTextField.getText());
            amuletMPChange = Integer.parseInt(amuletMPTextField.getText());

            Amulet amulet = new Amulet(amuletName, amuletCost, amuletHPChange, amuletMPChange, false);
            save(amulet);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your Amulet Was Successfully Added To The Amulets!");
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
        Label itemLabel = new Label("Amulet");
        itemLabel.setTextAlignment(TextAlignment.CENTER);
        itemLabel.setMaxWidth(Double.MAX_VALUE);
        itemLabel.setAlignment(Pos.CENTER);
        vBox.getChildren().add(itemLabel);
        vBox.getChildren().add(gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20,20,20,20));
        gridPane.setPadding(new Insets(20,20, 20,20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        scene.setRoot(vBox);
    }


    public void save(Amulet amulet){
        Amulet.addAmulet(amulet);
    }
}
