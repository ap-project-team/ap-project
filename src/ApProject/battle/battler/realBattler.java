package src.ApProject.battle.battler;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;
import src.ApProject.Game;
import src.ApProject.battle.battleField.MonsterField;
import src.ApProject.constants.CreatCards;
import src.ApProject.thing.Amulet;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MonsterCard;
import src.ApProject.thing.Item;

import java.util.ArrayList;

public class realBattler extends Battler {

    protected ArrayList<Item> items;
    Circle itemButton;

    public realBattler(String name, Card[] realDeck, ArrayList<Item> realItems, Amulet realAmulet) {
        super(name, realDeck);
        this.amulet = realAmulet;
        this.items = realItems;
        type = "PLAYER";
        this.HP += amulet.getIncreaseHP();
        this.MAX_HP += amulet.getIncreaseHP();
        this.currentMP += amulet.getIncreaseMP();
        this.currentMaxMP += amulet.getIncreaseMP();
    }

    @Override
    protected boolean turnOrders() {
        String order = Game.give();

        if (order.matches("Help\\s*")) {
            System.out.println("1. Use #SlotNum: To use a specific card which is on the Monster Field \n" +
                    "2. Set HandIndex to #SlotNum: To set a card which is on the hand, in the field \n" +
                    "3. View Hand: To view the cards in your hand \n" +
                    "4. View Graveyard: To view the cards in your graveyard \n" +
                    "5. View SpellField: To view the cards in both ’players spell fields \n" +
                    "6. View MonsterField: To view the cards in both ’players monster fields \n" +
                    "7. Battler Info: To view your HP and Mana.\n" +
                    "8. Use Item #ItemName: To use some of your items." +
                    "9. Info \"Card Name\": To view full information about a card\n" +
                    "10. Done: To end your turn");
        } else if (order.matches("Use \\d*\\s*")) {
            String[] str = order.split("\\s");
            if (monsterField.getSlot(Integer.parseInt(str[1]) - 1) == null)
                System.out.println("This slot is empety.");
            else {
                int i = Integer.parseInt(str[1]) - 1;
                System.out.println(monsterField.getSlot(i).getUseInfo());
                while (monsterField.useCardOrders(this, enemy, i)) ;
            }
        } else if (order.matches("Set \\d* to \\d*\\s*")) {
            String[] str = order.split("\\s");
            int handIndex = Integer.parseInt(str[1]);
            int slotNum = Integer.parseInt(str[3]) - 1;

            if (handIndex > hand.size() || handIndex <= 0)
                System.out.println("HandIndex is not valid.");
            else {
                hand.get(handIndex - 1).play(this, enemy, slotNum);
            }
        } else if (order.matches("View Hand\\s*")) {
            System.out.println("Your Hand :");
            for (int i = 0; i < hand.size(); i++)
                System.out.println((i + 1) + ".\t" + hand.get(i).getName() + " - Mana : " + hand.get(i).getManaCost());
        } else if (order.matches("View Graveyard\\s*")) {
            System.out.println("Your Graveyard :");
            graveYard.viewGraveyard();
            System.out.println("Enemy Graveyard :");
            enemy.graveYard.viewGraveyard();
        } else if (order.matches("View SpellField\\s*")) {
            System.out.println("Your SpellField :");
            spellField.viewSpellField();
            System.out.println("Enemy's SpellField :");
            enemy.spellField.viewSpellField();
        } else if (order.matches("View MonsterField\\s*")) {
            System.out.println("Your MonsterField :");
            monsterField.viewMonsterField();
            System.out.println("Enemy's MonsterField :");
            enemy.monsterField.viewMonsterField();
        } else if (order.matches("Battler Info")) {
            System.out.println("Your Info :\nCurrentMana :\t" + getCurrentMana()
                    + "\nCurrentHP :\t" + getHealth() + "\nAmulet :\t" + this.amulet.getName());
            System.out.println("Enemy Info :\nCurrentHP :\t" + enemy.getHealth());
        } else if (order.matches("Use Item")) {
            while (useItemOrders()) ;
        } else if (order.matches("Info \\w*\\s*")) {
            System.out.println(CreatCards.getCard(order.split("\\s")[1]).getInfo());
        } else if (order.matches("Done\\s*")) {
            return false;
        } else System.out.println("Incorrect order!");
        return true;
    }

    boolean useItemOrders() {
        String order = Game.give().toLowerCase();

        if (order.matches("use \\w*\\s*")) {
            for (int i = 0; i < items.size(); i++) {
                String[] str = order.split("\\s");
                if (items.get(i).getName().toLowerCase().matches(str[1].toLowerCase())) {
                    items.get(i).useItem(this);
                    items.remove(i);
                    return true;
                }
            }
            System.out.println("You don't have this Item.");
        } else if (order.matches("help")) {
            System.out.println("1.\tUse \"Item Name\": To use item.\n" +
                    "2.\tView Items: To see your items.\n" +
                    "3.\tExit: To exit to previous menu");
        } else if (order.matches("view items")) {
            System.out.println("Your Items :");
            for (int i = 0; i < items.size(); i++)
                System.out.println((i + 1) + "\t" + items.get(i).getName() + ": " + items.get(i).getInfo());
        } else if (order.matches("exit\\s*")) return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    public void updatePlayField(VBox vBox, Pane root) {
        monsterField.update(vBox, root);
        spellField.update(vBox);
        hand.update(vBox);
    }


    @Override
    synchronized public void defeat() {
        battle.getGame().playerDefeat();
    }

    public Circle buildItemButton(Pane root){
        if (itemButton != null)
            root.getChildren().remove(itemButton);

        StackPane item = new StackPane();
        itemButton = new Circle(battle.getRoot().getWidth() - 100,
                battle.getRoot().getHeight()- 100, 30, Color.WHITE);
        root.getChildren().add(itemButton);

        itemButton.setOnMouseClicked(event -> {
            for (int i=0; i<items.size(); i++) {
                ImageView image = new ImageView("./src//source//ITEM//"+items.get(i).getName()+".png");
                image.setFitWidth(50);
                image.setFitHeight(50);

                image.setTranslateY(itemButton.getCenterY() - 50 - (image.getFitHeight()+10)*(i+1));
                item.setTranslateX(itemButton.getCenterX() - image.getFitWidth()/2);

                int finalI = i;
                image.setOnMouseClicked(event1 -> {
                    items.get(finalI).useItem(this);
                    items.remove(finalI);
                    root.getChildren().remove(item);
                    buildItemButton(root);
                    battle.update();
                });

                item.getChildren().add(image);
            }

            root.getChildren().add(item);

            itemButton.setOnMouseClicked(event1 -> {
                root.getChildren().removeAll(item);
                buildItemButton(root);
            });

        });

        return itemButton;
    }
}