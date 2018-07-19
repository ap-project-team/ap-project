package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.ApProject.graphics.Button;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.ChangeHPAndAP;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.Spells.AuraSpell;

import java.io.*;
import java.util.ArrayList;

public class NewSpellCard {
    private String cardName;
    private int cardMana;
    private String cardDetails;
    private String cardType;
    private ArrayList<Magic> magics = new ArrayList<>();
    private ArrayList<Magic> inverseMagic = new ArrayList<>();
    private Stage stage;
    public GridPane getGridPane(Stage stage){
        GridPane gridPane = new GridPane();
        this.stage = stage;
        Label cardNameLabel = new Label("Insert Spell Card's Name : ");
        TextField cardNameTextField = new TextField();
        gridPane.add(cardNameLabel, 0, 0);
        gridPane.add(cardNameTextField, 1,0);

        Label cardMPlabel = new Label("Insert Spell Card's Mana Point : ");
        TextField cardMPTextField = new TextField();
        gridPane.add(cardMPlabel, 0, 1);
        gridPane.add(cardMPTextField, 1,1);

        Label cardInfoLabel = new Label("Insert Spell Card's Details : ");
        TextArea cardInfoTextField = new TextArea();
        cardInfoTextField.setPrefColumnCount(20);
        cardInfoTextField.setPrefRowCount(5);
        cardInfoTextField.setWrapText(true);
        gridPane.add(cardInfoLabel, 0, 2);
        gridPane.add(cardInfoTextField, 1,2);

        Label spellCardTypeInfoLabel = new Label("Choose Spell Card's Type : ");
        gridPane.add(spellCardTypeInfoLabel,0,3);
        Label spellCardTypeLabel = new Label();

        StackPane instantButton =  Button.buildButton("Instant");
        instantButton.setOnMouseClicked(event -> {
            spellCardTypeLabel.setText("Instant");
        });
        gridPane.add(instantButton,0,4);

        StackPane continuousButton =  Button.buildButton("Continuous");
        continuousButton.setOnMouseClicked(event -> {
            spellCardTypeLabel.setText("Continuous");
        });
        gridPane.add(continuousButton,1,4);

        StackPane auraButton =  Button.buildButton("Aura");
        auraButton.setOnMouseClicked(event -> {
            spellCardTypeLabel.setText("Aura");
        });
        gridPane.add(auraButton,2,4);
        gridPane.add(spellCardTypeLabel, 3,4);

        StackPane confirmButton = Button.buildButton("Confirm");
        confirmButton.setOnMouseClicked(event -> {
            cardName = cardNameTextField.getText();
            cardMana = Integer.parseInt(cardMPTextField.getText());
            cardDetails = cardInfoTextField.getText();
            cardType = spellCardTypeLabel.getText();
            gridPane.getChildren().clear();
            VBox vBox = new VBox(new Label(spellCardTypeLabel.getText()));
            vBox.setAlignment(Pos.CENTER);
            gridPane.add(vBox, 0 ,0);
            gridPane.add(createMagic(),0,1);
        });
        StackPane backButton = Button.buildButton("Back");
        gridPane.add(backButton,1,5);
        gridPane.add(confirmButton,2,5);

        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.TOP_CENTER);
        return gridPane;
    }
    public GridPane createMagic(){
        GridPane gridPane = new GridPane();
        switch (cardType){
            case "Instant" :

                break;
            case "Continuous":
                break;

            case "Aura":
                Label auraAttackLabel = new Label("Insert Friendly Attack Boost : ");
                TextField auraAttackTextField = new TextField();
                gridPane.add(auraAttackLabel, 0, 0);
                gridPane.add(auraAttackTextField, 1,0);

                Label auraHealthLabel = new Label("Insert Friendly Health Boost : ");
                TextField auraHealthTextField = new TextField();
                gridPane.add(auraHealthLabel, 0, 1);
                gridPane.add(auraHealthTextField, 1,1);

                StackPane confirmButton = Button.buildButton("Confirm");
                confirmButton.setOnMouseClicked(event -> {
                    gridPane.getChildren().clear();
                    magics.add(new ChangeHPAndAP(Integer.parseInt(auraAttackTextField.getText()),Integer.parseInt(auraHealthTextField.getText()), 0, cardName + " : " + cardDetails));
                    inverseMagic.add(new ChangeHPAndAP(Integer.parseInt(auraAttackTextField.getText()),Integer.parseInt(auraHealthTextField.getText()), 0, ""));
                    save(new AuraSpell(cardName, cardMana, magics,inverseMagic));
                    magics.clear();
                    NewCard.start(stage);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Aura Card Was Successfully Added To The Cards!");
                    alert.showAndWait();
                });
                StackPane backButton = Button.buildButton("Back");
                gridPane.add(backButton,1,2);
                gridPane.add(confirmButton,2,2);


                inverseMagic.clear();
                break;
        }
        return gridPane;
    }
    public void save(Card card){
        try {
            FileOutputStream fos = new FileOutputStream("cards.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            FileInputStream fis = new FileInputStream("cards.ser");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            Card result = (Card) ois.readObject();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
