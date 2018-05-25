package src.ApProject.battle.battler;

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
    protected Amulet amulet;

    public realBattler(String name, Card[] realDeck, ArrayList<Item> realItems, Amulet realAmulet) {
        super(name, realDeck);
        this.amulet = realAmulet;
        this.items = realItems;
        type = "PLAYER";
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
                    "7. Battler Info: To view your HP and Mana.\n"+
                    "8. Info \"Card Name\": To view full information about a card\n" +
                    "9. Done: To end your turn");
        } else if (order.matches("Use \\d*\\s*")) {
            String[] str = order.split("\\s");
            if (monsterField.getSlot(Integer.parseInt(str[1]) - 1) == null)
                System.out.println("This slot is empety.");
            else {
                int i = Integer.parseInt(str[1]) - 1;
                System.out.println(monsterField.getSlot(i).getUseInfo());
                while (monsterField.useCardOrders(this, enemy, i));
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
                System.out.println((i + 1) + ".\t" + hand.get(i).getName() +" - Mana : "+hand.get(i).getManaCost());
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
                    + "\nCurrentHP :\t" + getHealth());
            System.out.println("Enemy Info :\nCurrentHP :\t" + enemy.getHealth());
        } else if (order.matches("Info \\w*\\s*")) {
            System.out.println(CreatCards.getCard(order.split("\\s")[1]).getInfo());
        } else if (order.matches("Done\\s*")) {
            return false;
        } else System.out.println("Incorrect order!");
        return true;
    }
}
