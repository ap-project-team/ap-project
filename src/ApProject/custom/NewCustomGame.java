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
import src.ApProject.constants.AI_BattlerBuilder;
import src.ApProject.constants.CreatCards;
import src.ApProject.custom.Edit.Enemy.EditEnemyDeck;
import src.ApProject.custom.Edit.Shop.EditCardShop;
import src.ApProject.custom.Edit.Shop.EditItemShop;
import src.ApProject.custom.Edit.Thing.EditAmulet;
import src.ApProject.custom.Edit.Shop.EditAmuletShop;
import src.ApProject.custom.Edit.Thing.EditCard;
import src.ApProject.custom.Edit.Thing.EditItem;
import src.ApProject.custom.New.NewAmulet;
import src.ApProject.custom.New.NewCard;
import src.ApProject.custom.New.NewItem;
import src.ApProject.graphics.Button;
import src.ApProject.shop.AmuletShop;
import src.ApProject.shop.CardShop;
import src.ApProject.shop.ItemShop;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Item;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class NewCustomGame {
    private static String sourcePath;
    private static String destinationPath;
    public static void start(Scene scene, String path){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        StackPane newCardButton =  Button.buildButton("Create New Card");
        newCardButton.setOnMouseClicked(event -> {
            NewCard.start(scene,path);
        });
        gridPane.add(newCardButton,0,0);

        StackPane newItemButton =  Button.buildButton("Create New Item");
        newItemButton.setOnMouseClicked(event -> {
            NewItem newItem = new NewItem();
            newItem.start(scene,path);
        });
        gridPane.add(newItemButton,0,1);

        StackPane newAmuletButton =  Button.buildButton("Create New Amulet");
        newAmuletButton.setOnMouseClicked(event -> {
            NewAmulet newAmulet = new NewAmulet();
            newAmulet.start(scene,path);
        });
        gridPane.add(newAmuletButton,0,2);

        StackPane editCardsButton =  Button.buildButton("Edit Cards");
        editCardsButton.setOnMouseClicked(event -> {
            EditCard editCard = new EditCard();
            editCard.start(scene, path);
        });
        gridPane.add(editCardsButton,1,0);

        StackPane editItemsButton =  Button.buildButton("Edit Items");
        editItemsButton.setOnMouseClicked(event -> {
            EditItem editItem = new EditItem();
            editItem.start(scene, path);
        });
        gridPane.add(editItemsButton,1,1);
        
        StackPane editAmuletsButton =  Button.buildButton("Edit Amulets");
        editAmuletsButton.setOnMouseClicked(event -> {
            EditAmulet editAmulet = new EditAmulet();
            editAmulet.start(scene, path);
        });
        gridPane.add(editAmuletsButton,1,2);

        StackPane editCardShopButton =  Button.buildButton("Edit Card Shop");
        editCardShopButton.setOnMouseClicked(event -> {
            EditCardShop editCardShop = new EditCardShop();
            editCardShop.start(scene, path);
        });
        gridPane.add(editCardShopButton,2,0);

        StackPane editItemShopButton =  Button.buildButton("Edit Item Shop");
        editItemShopButton.setOnMouseClicked(event -> {
            EditItemShop editItemShop = new EditItemShop();
            editItemShop.start(scene, path);
        });
        gridPane.add(editItemShopButton,2,1);

        StackPane editAmuletShopButton =  Button.buildButton("Edit Amulet Shop");
        editAmuletShopButton.setOnMouseClicked(event -> {
            EditAmuletShop editAmuletShop = new EditAmuletShop();
            editAmuletShop.start(scene, path);
        });
        gridPane.add(editAmuletShopButton,2,2);

        StackPane backButton =  Button.buildButton("Back");
        backButton.setOnMouseClicked(event -> {
            showMenu(scene);
        });
        gridPane.add(backButton,0,3);

        StackPane confirmButton =  Button.buildButton("Confirm");
        confirmButton.setOnMouseClicked(event -> {
            CreatCards.saveAllCards(path);
            Item.saveAllItems(path);
            Amulet.saveAllAmulets(path);
            CardShop.saveShopCards(path);
            ItemShop.saveShopItems(path);
            AmuletShop.saveShopAmulets(path);
            AI_BattlerBuilder.saveAllEnemyDeck(path);
            CustomGame.start(scene);
        });
        gridPane.add(confirmButton,3,3);

        StackPane editEnemyDeckButton =  Button.buildButton("Edit Enemy's Deck");
        editEnemyDeckButton.setOnMouseClicked(event -> {
            EditEnemyDeck editEnemyDeck = new EditEnemyDeck();
            editEnemyDeck.start(scene,path);
        });
        gridPane.add(editEnemyDeckButton,3,0);

        gridPane.setHgap(20);
        gridPane.setVgap(20);
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
            CreatCards.loadAllCards(destinationPath);
            Item.loadAllItems(destinationPath);
            Amulet.loadAllAmulets(destinationPath);
            CardShop.loadShopCards(destinationPath);
            ItemShop.loadShopItems(destinationPath);
            AmuletShop.loadShopAmulets(destinationPath);
            AI_BattlerBuilder.loadAllEnemyDecks(destinationPath);
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
