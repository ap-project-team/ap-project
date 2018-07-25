package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.ApProject.constants.CreatCards;
import src.ApProject.graphics.Button;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.*;
import src.ApProject.thing.Cards.Spells.AuraSpell;
import src.ApProject.thing.Cards.Spells.ContinuousSpell;
import src.ApProject.thing.Cards.Spells.InstantSpell;

import java.io.*;
import java.util.ArrayList;

public class NewSpellCard {
    private String cardName;
    private int cardMana;
    private String cardDetails;
    private String cardType;
    private ArrayList<Magic> magics = new ArrayList<>();
    private ArrayList<Magic> inverseMagic = new ArrayList<>();
    private Scene scene;
    public GridPane getGridPane(Scene scene){
        GridPane gridPane = new GridPane();
        this.scene = scene;
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
                Label instantFriendlyAttackLabel = new Label("Insert All Friendly Attack Change : ");
                TextField instantFriendlyAttackTextField = new TextField();
                gridPane.add(instantFriendlyAttackLabel, 0, 0);
                gridPane.add(instantFriendlyAttackTextField, 1,0);

                Label instantFriendlyHealthLabel = new Label("Insert All Friendly Health Change : ");
                TextField instantFriendlyHealthTextField = new TextField();
                gridPane.add(instantFriendlyHealthLabel, 2, 0);
                gridPane.add(instantFriendlyHealthTextField, 3,0);

                Label instantEnemyAttackLabel = new Label("Insert All Enemy Attack Change : ");
                TextField instantEnemyAttackTextField = new TextField();
                gridPane.add(instantEnemyAttackLabel, 0, 1);
                gridPane.add(instantEnemyAttackTextField, 1,1);

                Label instantEnemyHealthLabel = new Label("Insert All Enemy Health Change : ");
                TextField instantEnemyHealthTextField = new TextField();
                gridPane.add(instantEnemyHealthLabel, 2, 1);
                gridPane.add(instantEnemyHealthTextField, 3,1);

                StackPane addInstantMagicButton = Button.buildButton("Add Magic");
                addInstantMagicButton.setOnMouseClicked(event -> {
                    magics.add(new ChangeAllHPAndAP(Integer.parseInt(instantFriendlyAttackTextField.getText()),Integer.parseInt(instantFriendlyHealthTextField.getText()),Integer.parseInt(instantEnemyAttackTextField.getText()),Integer.parseInt(instantEnemyHealthTextField.getText()), cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton,4,1);

                Label instantFriendlyHeroHealthLabel = new Label("Insert Friendly Hero Health Change : ");
                TextField instantFriendlyHeroHealthTextField = new TextField();
                gridPane.add(instantFriendlyHeroHealthLabel, 0, 2);
                gridPane.add(instantFriendlyHeroHealthTextField, 1,2);

                Label instantEnemyHeroHealthLabel = new Label("Insert Enemy Hero Health Change : ");
                TextField instantEnemyHeroHealthTextField = new TextField();
                gridPane.add(instantEnemyHeroHealthLabel, 2, 2);
                gridPane.add(instantEnemyHeroHealthTextField, 3,2);

                StackPane addInstantMagicButton1 = Button.buildButton("Add Magic");
                addInstantMagicButton1.setOnMouseClicked(event -> {
                    magics.add(new ChangePlayerHP(Integer.parseInt(instantFriendlyHeroHealthTextField.getText()), Integer.parseInt(instantEnemyHeroHealthTextField.getText()),cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton1,4,2);

                Label instantDamageRandomMSOrPlayerHeroLabel = new Label("Insert Damage To A Random Enemy Card On The Field Or Player : ");
                TextField instantDamageRandomMSOrPlayerHeroTextField = new TextField();
                gridPane.add(instantDamageRandomMSOrPlayerHeroLabel, 0, 3);
                gridPane.add(instantDamageRandomMSOrPlayerHeroTextField, 1,3);

                StackPane addInstantMagicButton2 = Button.buildButton("Add Magic");
                addInstantMagicButton2.setOnMouseClicked(event -> {
                    magics.add(new DamageRandomMSOrPlayer(Integer.parseInt(instantDamageRandomMSOrPlayerHeroTextField.getText()),1, cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton2,4,3);

                Label instantRemoveAllSpellsLabel = new Label("Remove All Enemy Spell Cards : ");
                gridPane.add(instantRemoveAllSpellsLabel, 0, 4);

                StackPane addInstantMagicButton3 = Button.buildButton("Add Magic");
                addInstantMagicButton3.setOnMouseClicked(event -> {
                    magics.add(new RemoveAllSpells(cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton3,4,4);

                Label instantSelectedMonsterHealthLabel = new Label("Selected Monster Health Change : ");
                TextField instantSelectedMonsterHealthTextField = new TextField();
                gridPane.add(instantSelectedMonsterHealthLabel, 0, 5);
                gridPane.add(instantSelectedMonsterHealthTextField, 1,5);

                Label instantSelectedMonsterAttackLabel = new Label("Selected Monster Attack Change : ");
                TextField instantSelectedMonsterAttackTextField = new TextField();
                gridPane.add(instantSelectedMonsterAttackLabel, 2, 5);
                gridPane.add(instantSelectedMonsterAttackTextField, 3,5);

                Label instantSelectedMonsterTypeLabel = new Label("Insert Magic type : ");
                //0 friendly else enemy
                TextField instantSelectedMonsterTypeTextField = new TextField();
                gridPane.add(instantSelectedMonsterTypeLabel, 0, 6);
                gridPane.add(instantSelectedMonsterTypeTextField, 1,6);

                StackPane addInstantMagicButton4 = Button.buildButton("Add Magic");
                addInstantMagicButton4.setOnMouseClicked(event -> {
                    magics.add(new ChangeHPAndAP(Integer.parseInt(instantSelectedMonsterAttackTextField.getText()), Integer.parseInt(instantSelectedMonsterHealthTextField.getText()), Integer.parseInt(instantSelectedMonsterTypeTextField.getText()),cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton4,4,6);

                Label instantRemoveRandomSpellLabel = new Label("Remove a Random Spell : ");
                gridPane.add(instantRemoveRandomSpellLabel, 0, 7);

                StackPane addInstantMagicButton5 = Button.buildButton("Add Magic");
                addInstantMagicButton5.setOnMouseClicked(event -> {
                    magics.add(new RemoveRandomSpell(cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton5,4,7);

                Label instantSelectedMonsterOrPlayerTypeLabel = new Label("Change Selected Card's Or Player's HP : ");
                TextField instantSelectedMonsterOrPlayerTextField = new TextField();
                gridPane.add(instantSelectedMonsterOrPlayerTypeLabel, 0, 8);
                gridPane.add(instantSelectedMonsterOrPlayerTextField, 1,8);

                StackPane addInstantMagicButton6 = Button.buildButton("Add Magic");
                addInstantMagicButton6.setOnMouseClicked(event -> {
                    magics.add(new ChangeHPOfPlayerOrMS(Integer.parseInt(instantSelectedMonsterOrPlayerTextField.getText()),cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton6,4,8);

                Label instantDrawCardLabel = new Label("Draw Card Count : ");
                TextField instantDrawCardTextField = new TextField();
                gridPane.add(instantDrawCardLabel, 0, 9);
                gridPane.add(instantDrawCardTextField, 1,9);

                StackPane addInstantMagicButton7 = Button.buildButton("Add Magic");
                addInstantMagicButton7.setOnMouseClicked(event -> {
                    magics.add(new DrawCard(Integer.parseInt(instantDrawCardTextField.getText()),cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton7,4,9);

                Label instantRemoveMonsterLabel = new Label("Remove a Selected Monster : ");
                gridPane.add(instantRemoveMonsterLabel, 0, 10);

                StackPane addInstantMagicButton8 = Button.buildButton("Add Magic");
                addInstantMagicButton8.setOnMouseClicked(event -> {
                    magics.add(new RemoveMonster(cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton8,4,10);

                Label instantRemoveSpellLabel = new Label("Remove a Selected Spell : ");
                gridPane.add(instantRemoveSpellLabel, 0, 11);

                StackPane addInstantMagicButton9 = Button.buildButton("Add Magic");
                addInstantMagicButton9.setOnMouseClicked(event -> {
                    magics.add(new RemoveSelectedSpell(cardName + " : " + cardDetails));
                });
                gridPane.add(addInstantMagicButton9,4,11);

                StackPane confirmButton2 = Button.buildButton("Confirm");
                confirmButton2.setOnMouseClicked(event -> {
                    gridPane.getChildren().clear();
                    save(new InstantSpell(cardName, cardMana, magics));
                    NewCard.start(scene);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Instant Spell Card Was Successfully Added To The Cards!");
                    alert.showAndWait();
                });
                StackPane backButton2 = Button.buildButton("Back");
                gridPane.add(backButton2,1,12);
                gridPane.add(confirmButton2,2,12);

                break;
            case "Continuous":
                Label continuousFriendlyAttackLabel = new Label("Insert All Friendly Attack Change : ");
                TextField continuousFriendlyAttackTextField = new TextField();
                gridPane.add(continuousFriendlyAttackLabel, 0, 0);
                gridPane.add(continuousFriendlyAttackTextField, 1,0);

                Label continuousFriendlyHealthLabel = new Label("Insert All Friendly Health Change : ");
                TextField continuousFriendlyHealthTextField = new TextField();
                gridPane.add(continuousFriendlyHealthLabel, 2, 0);
                gridPane.add(continuousFriendlyHealthTextField, 3,0);

                Label continuousEnemyAttackLabel = new Label("Insert All Enemy Attack Change : ");
                TextField continuousEnemyAttackTextField = new TextField();
                gridPane.add(continuousEnemyAttackLabel, 0, 1);
                gridPane.add(continuousEnemyAttackTextField, 1,1);

                Label continuousEnemyHealthLabel = new Label("Insert All Enemy Health Change : ");
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
                    NewCard.start(scene);
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
                    NewCard.start(scene);
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
        CreatCards.addCard(card);
    }
}
