package src.ApProject.battle.battleField;

import src.ApProject.Game;
import src.ApProject.battle.Battle;
import src.ApProject.battle.battler.Battler;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.constants.CreatCards;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.Tribe;

import java.util.ArrayList;
import java.util.Random;
import src.ApProject.thing.Cards.MonsterCards.Type;

public class MonsterField {
    private MonsterCardsInBattle[] slots = new MonsterCardsInBattle[ConstantDatas.SIZE_OF_MONSTERFIELD];

    public void add(MonsterCardsInBattle card, int slotNum) {
        if (slots[slotNum] == null)
            slots[slotNum] = (card);
    }

    public void remove(MonsterCardsInBattle card, Battler cardOwner) {
        for (int i = 0; i < slots.length; i++)
            if (card.equals(slots[i])) {
                System.out.println(card.getCardName() + " has been kiled!");
                cardOwner.getGraveYard().add(slots[i].getCard());
                slots[i] = null;
                return;
            }
    }

    public void nextTurn() {
        for (int i = 0; i < slots.length; i++)
            if (slots[i] != null)
                slots[i].nextTurn();
    }

    public MonsterCardsInBattle getSlot(int index) {
        return slots[index];
    }

    public MonsterCardsInBattle getRandomMonsterCardInBattle() {
        Random random = new Random();
        int randomNum;
        while (true) {
            randomNum = random.nextInt(slots.length);
            if (slots[randomNum] != null)
                return slots[randomNum];
        }
    }

    public MonsterCardsInBattle[] getMonsterCardsInBattles() {
        return slots;
    }

    public MonsterCardsInBattle getRandomMonsterCardInBattleByType(Tribe tribe) {
        Random random = new Random();
        int randomNum;
        int count = 0;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null)
                if (slots[i].getTribe() == tribe)
                    count++;
        }
        while (count != 0) {
            randomNum = random.nextInt(ConstantDatas.SIZE_OF_MONSTERFIELD);
            if (slots[randomNum] != null)
                if (slots[randomNum].getTribe() == tribe)
                    return slots[randomNum];
        }
        return null;
    }

    public int getEmptySlotNumber() {
        int num = 0;
        for (MonsterCardsInBattle S : slots)
            if (S == null)
                num++;
        return num;
    }

    public boolean isEmpty() {
        return getEmptySlotNumber() == 0;
    }

    public int getSize() {
        return slots.length - getEmptySlotNumber();
    }

    public void viewMonsterField() {
        for (int i = 0; i < slots.length; i++)
            if (slots[i] == null)
                System.out.println((i + 1) + ".\tEmpety");
            else System.out.println((i + 1) + ".\t" + slots[i].getInfoInMonsterField());
    }

    public boolean useCardOrders(int i, Battler enemy) {
        //if (slots[i].getType() != Type.SpellCaster) {

        String order = Game.give();

        if (order.matches("Again\\s*")) {
            System.out.println(slots[i].getUseInfo());
            System.out.println("Your ");
        } else if (order.matches("Help\\s*")) {
            if (slots[i].getMagicType() == MagicType.NONE) {
                System.out.println(
                        "1. Attack #EnemyMonsterSlot / Player: To attack the card on that slot of enemy MonsterField\n" +
                                "2. Info: To get full information on card\n" +
                                "3. Exit: To go back to Play Menu");
            } else System.out.println(
                    "1. Attack #EnemyMonsterSlot / Player: To attack the card on that slot of enemy MonsterField or the enemy player\n" +
                            "2. Cast Spell: To cast the ’cards spell \n" +
                            "3. Info: To get full information on card\n" +
                            "4. Exit: To go back to Play Menu");
        } else if (order.matches("Attack (\\d*|Player)\\s*")) {
            String[] str = order.split("\\s");

            if (!slots[i].canAttack())
                System.out.println("This card can't attack.");
            else {
                String cardName = slots[i].getCardName();
                if (str[1].equals("Player")) {
                    System.out.println(cardName + " clashed with " + enemy.getName());
                    slots[i].attack();
                } else {
                    System.out.println(cardName + " clashed with " +
                            enemy.getMonsterField().getSlot(Integer.parseInt(str[1]) - 1).getCardName());
                    slots[i].attack(enemy.getMonsterField().getSlot(Integer.parseInt(str[1]) - 1));
                    return false;
                }
            }
        } else if (order.matches("Spell Casting\\s*") && slots[i].getMagicType() != MagicType.NONE) {

        } else if (order.matches("Info\\s*")) {
            System.out.println(slots[i].getCard().getInfo());
        } else if (order.matches("Exit\\s*")) return false;
        else System.out.println("Incurrect order!");
        return true;
    }
    //}
}
