package src.ApProject.graphic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import src.ApProject.constants.ConstantData;



public class BattleGraphic extends Application {
    private SlotsGraphic[] playerMonsterField = new SlotsGraphic[5];
    private SlotsGraphic[] enemyMonsterField = new SlotsGraphic[5];
    public void start(Stage primaryStage){
//        Group root = new Group();
//        Line line = new Line(ConstantData.SCENE_LENGTH/3, 0, ConstantData.SCENE_LENGTH/3, ConstantData.SCENE_HEIGHT);
//        Line line1 = new Line(ConstantData.SCENE_LENGTH * 2 /3, 0, ConstantData.SCENE_LENGTH * 2/3, ConstantData.SCENE_HEIGHT);
//        root.getChildren().add(line);
//        root.getChildren().add(line1);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(addMiddleGridPane());
        borderPane.setLeft(addLeftGridPane());
        borderPane.setRight(addRightGridPane());
        borderPane.setStyle("-fx-background-color: #996633");
        Scene scene = new Scene(borderPane, ConstantData.SCENE_LENGTH , ConstantData.SCENE_HEIGHT);
        scene.setFill(Color.rgb(255, 255, 153));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane addRightGridPane(){
        GridPane gridPane = new GridPane();
        VBox handBox = new VBox();
        TextField[] hand = new TextField[10];
        for (int i = 0; i < 10; i++) {
            hand[i] = new TextField();
            hand[i].setEditable(false);
//            hand[i].setStyle("-fx-background-color: #996633");
            hand[i].setPrefSize(500,10);
            handBox.getChildren().add(hand[i]);
        }
        handBox.setAlignment(Pos.CENTER_LEFT);
        handBox.setSpacing(20);
        Button playCardButton = new Button("Play Card");
        Button showHandButton = new Button("Show Hand");
        Button showItemsButton = new Button("Show Items");
        VBox buttonsBox = new VBox(20);
        buttonsBox.setPrefWidth(100);
        playCardButton.setMaxWidth(100);
        showHandButton.setMaxWidth(100);
        showItemsButton.setMaxWidth(100);
        buttonsBox.getChildren().addAll(playCardButton, showHandButton, showItemsButton);
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setVgap(300);
        gridPane.add(handBox,0,0);
        gridPane.add(buttonsBox,0,1);
        return gridPane;
    }

    public GridPane addMiddleGridPane(){
        GridPane gridPane = new GridPane();
        Image emptyImage  = new Image("file:EmptySlot.png");
        for (int i = 0; i < 5; i++) {
            playerMonsterField[i] = new SlotsGraphic();
            enemyMonsterField[i] = new SlotsGraphic();
        }
        ImageView[] enemy = new ImageView[3];
        HBox enemyBox = new HBox();
        for (int i = 0; i <3 ; i++) {
            enemy[i] = new ImageView(emptyImage);
            enemyBox.getChildren().add(enemy[i]);
        }
        enemyBox.setPadding(new Insets(20,20,20,20));
        enemyBox.setSpacing(100);
        enemyBox.setAlignment(Pos.CENTER);

        ImageView[] enemySpellField = new ImageView[3];
        HBox enemySpellFieldBox = new HBox();
        for (int i = 0; i < 3; i++) {
            enemySpellField[i] = new ImageView(emptyImage);
            enemySpellFieldBox.getChildren().add(enemySpellField[i]);
        }
        enemySpellFieldBox.setPadding(new Insets(20,20,20,20));
        enemySpellFieldBox.setSpacing(20);
        enemySpellFieldBox.setAlignment(Pos.CENTER);

        HBox enemyMonsterFieldBox = new HBox();
        for (int i = 0; i < 5; i++) {
            enemyMonsterFieldBox.getChildren().add(enemyMonsterField[i].getVBox());
        }
        enemyMonsterFieldBox.setPadding(new Insets(20,20,20,20));
        enemyMonsterFieldBox.setSpacing(20);

        ImageView[] playerSpellField = new ImageView[3];
        HBox playerSpellFieldBox = new HBox();
        for (int i = 0; i < 3; i++) {
            playerSpellField[i] = new ImageView(emptyImage);
            playerSpellFieldBox.getChildren().add(playerSpellField[i]);
        }
        playerSpellFieldBox.setPadding(new Insets(20,20,20,20));
        playerSpellFieldBox.setSpacing(20);
        playerSpellFieldBox.setAlignment(Pos.CENTER);

        HBox playerMonsterFieldBox = new HBox();
        for (int i = 0; i < 5; i++) {
            playerMonsterFieldBox.getChildren().add(playerMonsterField[i].getVBox());
        }
        playerMonsterFieldBox.setPadding(new Insets(20,20,20,20));
        playerMonsterFieldBox.setSpacing(20);

        ImageView[] player = new ImageView[3];
        HBox playerBox = new HBox();
        for (int i = 0; i <3 ; i++) {
            player[i] = new ImageView(emptyImage);
            playerBox.getChildren().add(player[i]);
        }
        playerBox.setPadding(new Insets(20,20,20,20));
        playerBox.setSpacing(100);
        playerBox.setAlignment(Pos.CENTER);

        gridPane.add(enemyMonsterFieldBox,0,2);
        gridPane.add(enemySpellFieldBox,0,1);
        gridPane.add(enemyBox,0,0);
        gridPane.add(playerMonsterFieldBox,0,3);
        gridPane.add(playerSpellFieldBox,0,4);
        gridPane.add(playerBox,0,5);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    public GridPane addLeftGridPane(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        TextField infoTextField = new TextField();
        infoTextField.setPrefSize(ConstantData.SCENE_LENGTH/3 - 50, ConstantData.SCENE_HEIGHT * 6/ 10);
        infoTextField.setAlignment(Pos.TOP_LEFT);
        infoTextField.setEditable(false);
        TextField eventsTextField = new TextField();
        eventsTextField.setPrefSize(ConstantData.SCENE_LENGTH/3 - 50, ConstantData.SCENE_HEIGHT * 4/ 10 - 50 );
        eventsTextField.setAlignment(Pos.TOP_LEFT);
        eventsTextField.setEditable(false);
        gridPane.add(infoTextField, 0, 0);
        gridPane.add(eventsTextField, 0, 1);
        return gridPane;
    }
    public void startLaunch(){
        launch();
    }
}
