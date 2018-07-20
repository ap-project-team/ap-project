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
import src.ApProject.thing.Cards.Magic.*;
import src.ApProject.thing.Cards.Spells.AuraSpell;
import src.ApProject.thing.Cards.Spells.ContinuousSpell;

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
                Label continuousFriendlyAttackLabel = new Label("Insert Friendly Attack Change : ");
                TextField continuousFriendlyAttackTextField = new TextField();
                gridPane.add(continuousFriendlyAttackLabel, 0, 0);
                gridPane.add(continuousFriendlyAttackTextField, 1,0);

                Label continuousFriendlyHealthLabel = new Label("Insert Friendly Health Change : ");
                TextField continuousFriendlyHealthTextField = new TextField();
                gridPane.add(continuousFriendlyHealthLabel, 2, 0);
                gridPane.add(continuousFriendlyHealthTextField, 3,0);

                Label continuousEnemyAttackLabel = new Label("Insert Enemy Attack Change : ");
                TextField continuousEnemyAttackTextField = new TextField();
                gridPane.add(continuousEnemyAttackLabel, 0, 1);
                gridPane.add(continuousEnemyAttackTextField, 1,1);

                Label continuousEnemyHealthLabel = new Label("Insert Enemy Health Change : ");
                TextField continuousEnemyHealthTextField = new TextField();
                gridPane.add(continuousEnemyHealthLabel, 2, 1);
                gridPane.add(continuousEnemyHealthTextField, 3,1);

                StackPane addMagicButton = Button.buildButton("Add Magic");
                addMagicButton.setOnMouseClicked(event -> {
                    magics.add(new ChangeAllHPAndAP(Integer.parseInt(continuousFriendlyAttackTextField.getText()),Integer.parseInt(continuousFriendlyHealthTextField.getText()),Integer.parseInt(continuousEnemyAttackTextField.getText()),Integer.parseInt(continuousEnemyHealthTextField.getText()), cardName + " : " + cardDetails));
                });
                gridPane.add(addMagicButton,4,1);

                Label continuousFriendlyHeroHealthLabel = new Label("Insert Friendly Hero Health Change : ");
                TextField continuousFriendlyHeroHealthTextField = new TextField();
                gridPane.add(continuousFriendlyHeroHealthLabel, 0, 2);
                gridPane.add(continuousFriendlyHeroHealthTextField, 1,2);

                Label continuousEnemyHeroHealthLabel = new Label("Insert Enemy Hero Health Change : ");
                TextField continuousEnemyHeroHealthTextField = new TextField();
                gridPane.add(continuousEnemyHeroHealthLabel, 2, 2);
                gridPane.add(continuousEnemyHeroHealthTextField, 3,2);

                StackPane addMagicButton1 = Button.buildButton("Add Magic");
                addMagicButton1.setOnMouseClicked(event -> {
                    magics.add(new ChangePlayerHP(Integer.parseInt(continuousFriendlyHeroHealthTextField.getText()), Integer.parseInt(continuousEnemyHeroHealthTextField.getText()),cardName + " : " + cardDetails));
                });
                gridPane.add(addMagicButton1,4,2);

                Label continuousDamageRandomMSOrPlayerHeroLabel = new Label("Insert Damage To A Random Enemy Card On The Field Or Player : ");
                TextField continuousDamageRandomMSOrPlayerHeroTextField = new TextField();
                gridPane.add(continuousDamageRandomMSOrPlayerHeroLabel, 0, 3);
                gridPane.add(continuousDamageRandomMSOrPlayerHeroTextField, 1,3);

                StackPane addMagicButton2 = Button.buildButton("Add Magic");
                addMagicButton2.setOnMouseClicked(event -> {
                    magics.add(new DamageRandomMSOrPlayer(Integer.parseInt(continuousDamageRandomMSOrPlayerHeroTextField.getText()),1, cardName + " : " + cardDetails));
                });
                gridPane.add(addMagicButton2,4,3);

                Label continuousRemoveAllSpellsLabel = new Label("Remove All Enemy Spell Cards : ");
                gridPane.add(continuousRemoveAllSpellsLabel, 0, 4);

                StackPane addMagicButton3 = Button.buildButton("Add Magic");
                addMagicButton3.setOnMouseClicked(event -> {
                    magics.add(new RemoveAllSpells(cardName + " : " + cardDetails));
                });
                gridPane.add(addMagicButton3,4,4);
                StackPane confirmButton1 = Button.buildButton("Confirm");
                confirmButton1.setOnMouseClicked(event -> {
                    gridPane.getChildren().clear();
                    save(new ContinuousSpell(cardName, cardMana, magics));
                    NewCard.start(stage);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Continuous Spell Card Was Successfully Added To The Cards!");
                    alert.showAndWait();
                });
                StackPane backButton1 = Button.buildButton("Back");
                gridPane.add(backButton1,1,5);
                gridPane.add(confirmButton1,2,5);
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
                    NewCard.start(stage);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Aura Spell Card Was Successfully Added To The Cards!");
                    alert.showAndWait();
                });
                StackPane backButton = Button.buildButton("Back");
                gridPane.add(backButton,1,2);
                gridPane.add(confirmButton,2,2);
                break;
        }
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.TOP_CENTER);
        magics.clear();
        inverseMagic.clear();
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
