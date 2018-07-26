package src.ApProject.custom;

import com.sun.deploy.security.ValidationState;
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
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.*;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.MonsterCards.Type;
import src.ApProject.thing.Cards.Spells.InstantSpell;

import java.io.*;
import java.util.ArrayList;


public class NewMonsterCard {
    private GridPane gridPane = new GridPane();
    private String cardName;
    private int cardMana;
    private int cardHP;
    private int cardAP;
    private MonsterCardSpeciality monsterCardSpeciality;
    private Tribe tribe;
    public GridPane getGridPane(Scene scene){
        Label cardNameLabel = new Label("Insert Monster Card's Name : ");
        TextField cardNameTextField = new TextField();
        gridPane.add(cardNameLabel, 0, 0);
        gridPane.add(cardNameTextField, 1,0);

        Label cardHPLabel = new Label("Insert Monster Card's Health Point : ");
        TextField cardHPTextField = new TextField();
        gridPane.add(cardHPLabel, 0, 1);
        gridPane.add(cardHPTextField, 1,1);

        Label cardAttackLabel = new Label("Insert Monster Card's Attack Point : ");
        TextField cardAttackTextField = new TextField();
        gridPane.add(cardAttackLabel, 0, 2);
        gridPane.add(cardAttackTextField, 1,2);

        Label cardMPlabel = new Label("Insert Monster Card's Mana Point : ");
        TextField cardMPTextField = new TextField();
        gridPane.add(cardMPlabel, 0, 3);
        gridPane.add(cardMPTextField, 1,3);

        Label monsterCardTypeInfoLabel = new Label("Choose Monster Card's Type : ");
        gridPane.add(monsterCardTypeInfoLabel,0,4);
        Label monsterCardTypeLabel = new Label();

        StackPane normalButton =  Button.buildButton("Normal");
        normalButton.setOnMouseClicked(event -> {
            monsterCardTypeLabel.setText("Normal");
        });
        gridPane.add(normalButton,0,5);

        StackPane spellCasterButton =  Button.buildButton("Spell Caster");
        spellCasterButton.setOnMouseClicked(event -> {
            monsterCardTypeLabel.setText("Spell Caster");
        });
        gridPane.add(spellCasterButton,1,5);

        StackPane generalButton =  Button.buildButton("General");
        generalButton.setOnMouseClicked(event -> {
            monsterCardTypeLabel.setText("General");
        });
        gridPane.add(generalButton,2,5);

        StackPane heroButton =  Button.buildButton("Hero");
        heroButton.setOnMouseClicked(event -> {
            monsterCardTypeLabel.setText("Hero");
        });
        gridPane.add(heroButton,3,5);
        gridPane.add(monsterCardTypeLabel, 4,5);

        Label cardSpecialtyInfoLabel = new Label("Choose Monster Card's Speciality : ");
        gridPane.add(cardSpecialtyInfoLabel,0,6);

        Label cardSpecialtyLabel = new Label("");
        StackPane normalButton1 =  Button.buildButton("Normal");
        normalButton1.setOnMouseClicked(event -> {
            cardSpecialtyLabel.setText("Normal");
            monsterCardSpeciality = MonsterCardSpeciality.Normal;
        });
        gridPane.add(normalButton1,0,7);

        StackPane nimbleButton =  Button.buildButton("Nimble");
        nimbleButton.setOnMouseClicked(event -> {
            cardSpecialtyLabel.setText("Nimble");
            monsterCardSpeciality = MonsterCardSpeciality.Charge;
        });
        gridPane.add(nimbleButton,1,7);

        StackPane tauntButton =  Button.buildButton("Defender");
        tauntButton.setOnMouseClicked(event -> {
            cardSpecialtyLabel.setText("Defender");
            monsterCardSpeciality = MonsterCardSpeciality.Taunt;
        });
        gridPane.add(tauntButton,2,7);
        gridPane.add(cardSpecialtyLabel, 3,7);

        Label monsterCardTribeInfoLabel = new Label("Choose Monster Card's Tribe : ");
        gridPane.add(monsterCardTribeInfoLabel,0,8);
        Label monsterCardTribeLabel = new Label();

        StackPane elvenButton =  Button.buildButton("Elven");
        elvenButton.setOnMouseClicked(event -> {
            monsterCardTribeLabel.setText("Elven");
            tribe = Tribe.Elven;
        });
        gridPane.add(elvenButton,0,9);

        StackPane dragonBreedButton =  Button.buildButton("Dragon Breed");
        dragonBreedButton.setOnMouseClicked(event -> {
            monsterCardTribeLabel.setText("Dragon Breed");
            tribe = Tribe.DragonBreed;
        });
        gridPane.add(dragonBreedButton,1,9);

        StackPane atlanteanButton =  Button.buildButton("Atlantean");
        atlanteanButton.setOnMouseClicked(event -> {
            monsterCardTribeLabel.setText("Atlantean");
            tribe = Tribe.Atlantean;
        });
        gridPane.add(atlanteanButton,2,9);

        StackPane demonicButton =  Button.buildButton("Demonic");
        demonicButton.setOnMouseClicked(event -> {
            monsterCardTribeLabel.setText("Demonic");
            tribe = Tribe.Demonic;
        });
        gridPane.add(demonicButton,3,9);
        gridPane.add(monsterCardTribeLabel, 4,9);

        StackPane confirmButton = Button.buildButton("Confirm");
        confirmButton.setOnMouseClicked(event -> {
            cardName = cardNameTextField.getText();
            cardMana = Integer.parseInt(cardMPTextField.getText());
            cardHP = Integer.parseInt(cardHPTextField.getText());
            cardAP = Integer.parseInt(cardAttackTextField.getText());
            gridPane.getChildren().clear();
           switch (monsterCardTypeLabel.getText()){
               case "Normal" :
                   MonsterCard normalMonsterCard = new NormalMonsterCard(cardName, cardAP, cardHP, cardMana, monsterCardSpeciality, tribe);
                   showEndMassage(scene, normalMonsterCard);
                   break;
               case "Spell Caster" :
                   MonsterCard magicMonsterCard = new MagicMonsterCard(cardName,cardAP, cardHP, cardMana, monsterCardSpeciality, tribe, null);
                   setInstantSpell(scene, "spell", magicMonsterCard);
                   break;
               case "General" :
                   MonsterCard generalMonsterCard = new GeneralMonsterCard(cardName, cardAP, cardHP, cardMana, monsterCardSpeciality, tribe, null,null);
                   setInstantSpell(scene, "battlecry", generalMonsterCard);
                   break;
               case "Hero" :
                   MonsterCard heroMonsterCard = new HeroMonsterCard(cardName, cardAP, cardHP, cardMana, monsterCardSpeciality, tribe, null, null, null);
                   System.out.println(heroMonsterCard.getType());
                   setInstantSpell(scene, "battlecry", heroMonsterCard);
                   break;
           }

        });
        StackPane backButton = Button.buildButton("Back");
        gridPane.add(backButton,1,10);
        gridPane.add(confirmButton,2,10);

        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        return gridPane;
    }

    private String spellDetails ="";

    public void setInstantSpell(Scene scene, String type, MonsterCard monsterCard){
        ArrayList<Magic> magics = new ArrayList<>();
        GridPane gridPane = new GridPane();
        GridPane gridPane1 = new GridPane();
        Label cardInfoLabel = new Label("Insert Spell's Details : ");
        TextArea spellInfoTextField = new TextArea();
        spellInfoTextField.setPrefColumnCount(20);
        spellInfoTextField.setPrefRowCount(5);
        spellInfoTextField.setWrapText(true);
        gridPane1.add(cardInfoLabel, 0, 0);
        gridPane1.add(spellInfoTextField, 1,0);
        gridPane1.setAlignment(Pos.CENTER);
        gridPane1.setPadding(new Insets(20,20,20,20));

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
            spellDetails = spellInfoTextField.getText();
            magics.add(new ChangeAllHPAndAP(Integer.parseInt(instantFriendlyAttackTextField.getText()),Integer.parseInt(instantFriendlyHealthTextField.getText()),Integer.parseInt(instantEnemyAttackTextField.getText()),Integer.parseInt(instantEnemyHealthTextField.getText()),  spellDetails));
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
            spellDetails = spellInfoTextField.getText();
            magics.add(new ChangePlayerHP(Integer.parseInt(instantFriendlyHeroHealthTextField.getText()), Integer.parseInt(instantEnemyHeroHealthTextField.getText()), spellDetails));
        });
        gridPane.add(addInstantMagicButton1,4,2);

        Label instantDamageRandomMSOrPlayerHeroLabel = new Label("Insert Damage To A Random Enemy Card On The Field Or Player : ");
        TextField instantDamageRandomMSOrPlayerHeroTextField = new TextField();
        gridPane.add(instantDamageRandomMSOrPlayerHeroLabel, 0, 3);
        gridPane.add(instantDamageRandomMSOrPlayerHeroTextField, 1,3);

        StackPane addInstantMagicButton2 = Button.buildButton("Add Magic");
        addInstantMagicButton2.setOnMouseClicked(event -> {
            spellDetails = spellInfoTextField.getText();
            magics.add(new DamageRandomMSOrPlayer(Integer.parseInt(instantDamageRandomMSOrPlayerHeroTextField.getText()),1, spellDetails));
        });
        gridPane.add(addInstantMagicButton2,4,3);

        Label instantRemoveAllSpellsLabel = new Label("Remove All Enemy Spell Cards : ");
        gridPane.add(instantRemoveAllSpellsLabel, 0, 4);

        StackPane addInstantMagicButton3 = Button.buildButton("Add Magic");
        addInstantMagicButton3.setOnMouseClicked(event -> {
            spellDetails = spellInfoTextField.getText();
            magics.add(new RemoveAllSpells( spellDetails));
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
            spellDetails = spellInfoTextField.getText();
            magics.add(new ChangeHPAndAP(Integer.parseInt(instantSelectedMonsterAttackTextField.getText()), Integer.parseInt(instantSelectedMonsterHealthTextField.getText()), Integer.parseInt(instantSelectedMonsterTypeTextField.getText()), spellDetails));
        });
        gridPane.add(addInstantMagicButton4,4,6);

        Label instantRemoveRandomSpellLabel = new Label("Remove a Random Spell : ");
        gridPane.add(instantRemoveRandomSpellLabel, 0, 7);

        StackPane addInstantMagicButton5 = Button.buildButton("Add Magic");
        addInstantMagicButton5.setOnMouseClicked(event -> {
            spellDetails = spellInfoTextField.getText();
            magics.add(new RemoveRandomSpell( spellDetails));
        });
        gridPane.add(addInstantMagicButton5,4,7);

        Label instantSelectedMonsterOrPlayerTypeLabel = new Label("Change Selected Card's Or Player's HP : ");
        TextField instantSelectedMonsterOrPlayerTextField = new TextField();
        gridPane.add(instantSelectedMonsterOrPlayerTypeLabel, 0, 8);
        gridPane.add(instantSelectedMonsterOrPlayerTextField, 1,8);

        StackPane addInstantMagicButton6 = Button.buildButton("Add Magic");
        addInstantMagicButton6.setOnMouseClicked(event -> {
            spellDetails = spellInfoTextField.getText();
            magics.add(new ChangeHPOfPlayerOrMS(Integer.parseInt(instantSelectedMonsterOrPlayerTextField.getText()), spellDetails));
        });
        gridPane.add(addInstantMagicButton6,4,8);

        Label instantDrawCardLabel = new Label("Draw Card Count : ");
        TextField instantDrawCardTextField = new TextField();
        gridPane.add(instantDrawCardLabel, 0, 9);
        gridPane.add(instantDrawCardTextField, 1,9);

        StackPane addInstantMagicButton7 = Button.buildButton("Add Magic");
        addInstantMagicButton7.setOnMouseClicked(event -> {
            spellDetails = spellInfoTextField.getText();
            magics.add(new DrawCard(Integer.parseInt(instantDrawCardTextField.getText()), spellDetails));
        });
        gridPane.add(addInstantMagicButton7,4,9);

        Label instantRemoveMonsterLabel = new Label("Remove a Selected Monster : ");
        gridPane.add(instantRemoveMonsterLabel, 0, 10);

        StackPane addInstantMagicButton8 = Button.buildButton("Add Magic");
        addInstantMagicButton8.setOnMouseClicked(event -> {
            spellDetails = spellInfoTextField.getText();
            magics.add(new RemoveMonster(spellDetails));
        });
        gridPane.add(addInstantMagicButton8,4,10);

        Label instantRemoveSpellLabel = new Label("Remove a Selected Spell : ");
        gridPane.add(instantRemoveSpellLabel, 0, 11);

        StackPane addInstantMagicButton9 = Button.buildButton("Add Magic");
        addInstantMagicButton9.setOnMouseClicked(event -> {
            spellDetails = spellInfoTextField.getText();
            magics.add(new RemoveSelectedSpell(spellDetails));
        });
        gridPane.add(addInstantMagicButton9,4,11);

        StackPane confirmButton2 = Button.buildButton("Confirm");
        confirmButton2.setOnMouseClicked(event -> {
            gridPane.getChildren().clear();
            switch (type){
                case "battlecry" :
                    monsterCard.setBattleCry(new InstantSpell(cardName, 0, magics));
                    setInstantSpell(scene, "will", monsterCard);
                    break;
                case "will" :
                    monsterCard.setWill(new InstantSpell(cardName, 0, magics));
                    if(monsterCard.getType().equals(Type.Hero))
                        setInstantSpell(scene, "spell", monsterCard);
                    else
                        showEndMassage(scene, monsterCard);
                    break;
                case "spell" :
                    monsterCard.setMagics(new InstantSpell(cardName, 0, magics));
                    showEndMassage(scene, monsterCard);
                    break;
            }
        });
        StackPane backButton2 = Button.buildButton("Back");
        gridPane.add(backButton2,1,12);
        gridPane.add(confirmButton2,2,12);
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);
        magics.clear();
        VBox vBox = new VBox();
        Label label = new Label(type);
        vBox.getChildren().add(label);
        vBox.getChildren().add(gridPane1);
        vBox.getChildren().add(gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        scene.setRoot(vBox);
    }

    public void showEndMassage(Scene scene, MonsterCard monsterCard){
        save(monsterCard);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your Monster Card Was Successfully Added To The Cards!");
        alert.showAndWait();
        NewCard.start(scene);
    }

    public void save(Card card){
        CreatCards.addCard(card);
    }
}
