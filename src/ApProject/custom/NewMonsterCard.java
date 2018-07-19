package src.ApProject.custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import src.ApProject.graphics.Button;


public class NewMonsterCard {
    private GridPane gridPane = new GridPane();
    private String cardName;
    public GridPane getGridPane(Stage stage){
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

        StackPane heroButton =  Button.buildButton("Heo");
        heroButton.setOnMouseClicked(event -> {
            monsterCardTypeLabel.setText("Hero");
        });
        gridPane.add(heroButton,3,5);
        gridPane.add(monsterCardTypeLabel, 4,5);

        Label cardSpecialtyInfoLabel = new Label("Choose Monster Card's Speciality : ");
        gridPane.add(cardSpecialtyInfoLabel,0,6);

        Label cardSpecialtyLabel = new Label("");
        addButton("Normal", cardSpecialtyLabel, gridPane, 0,7);
        addButton("Defender", cardSpecialtyLabel, gridPane, 1,7);
        addButton("Nimble", cardSpecialtyLabel, gridPane, 2,7);
        gridPane.add(cardSpecialtyLabel, 3,7);

        StackPane confirmButton = Button.buildButton("Confirm");
        StackPane backButton = Button.buildButton("Back");
        gridPane.add(backButton,1,8);
        gridPane.add(confirmButton,2,8);

        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        return gridPane;
    }

    public void addButton(String string, Label label, GridPane gridPane, int columnNum, int rowNum){
        StackPane button =  Button.buildButton(string);
        button.setOnMouseClicked(event -> {
            label.setText(string);
        });
        gridPane.add(button,columnNum, rowNum);
    }
}
