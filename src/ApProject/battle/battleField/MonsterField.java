package src.ApProject.battle.battleField;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import src.ApProject.Game;
import src.ApProject.battle.battler.Battler;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.Tribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import src.ApProject.thing.Cards.Spells.Spells;

import static src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality.Taunt;

public class MonsterField {

    private HBox hBox;
    private MonsterCardsInBattle[] slots = new MonsterCardsInBattle[ConstantDatas.SIZE_OF_MONSTERFIELD];


    public void add(MonsterCardsInBattle card, int slotNum) {
        if (slots[slotNum] == null)
            slots[slotNum] = (card);
    }

    public void remove(MonsterCardsInBattle card, Battler cardOwner) {
        for (int i = 0; i < slots.length; i++)
            if (card.equals(slots[i])) {
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
                System.out.println((i + 1) + ".\tEmpty");
            else System.out.println((i + 1) + ".\t" + slots[i].getInfoInMonsterField());
    }

    public boolean useCardOrders(Battler player, Battler enemy, int slotNum) {
        //if (slots[i].getType() != Type.SpellCaster) {

        String order = Game.give();

        if (order.matches("Again\\s*")) {
            System.out.println(slots[slotNum].getUseInfo());
            System.out.println("Your ");
        } else if (order.matches("Help\\s*")) {
            if (slots[slotNum].getMagicType() == MagicType.NONE) {
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

            if (!slots[slotNum].canAttack())
                System.out.println("This card can't attack.");
            else if (enemy.getMonsterField().numberOfTaunts() != 0) {
                System.out.println("Player have Taunt in his field!");
                MonsterCardsInBattle card = enemy.getMonsterField().getRandomTaunt();
                System.out.println(slots[slotNum].getCardName() + " clashed with " + card.getCardName());
                slots[slotNum].attack(card);
                return false;
            } else {
                String cardName = slots[slotNum].getCardName();
                if (str[1].equals("Player")) {
                    System.out.println(cardName + " clashed with " + enemy.getName());
                    slots[slotNum].attack();
                    return false;
                } else {
                    System.out.println(cardName + " clashed with " +
                            enemy.getMonsterField().getSlot(Integer.parseInt(str[1]) - 1).getCardName());
                    slots[slotNum].attack(enemy.getMonsterField().getSlot(Integer.parseInt(str[1]) - 1));
                    return false;
                }
            }
        } else if (order.matches("Cast Spell\\s*") && slots[slotNum].getMagicType() != MagicType.NONE && !slots[slotNum].isMagicUsed() && !slots[slotNum].isSleep()) {
            ArrayList<Map> map= player.getMonsterField().printingTargets(player,enemy, slots[slotNum].getMagicType());
            while (player.getSpellField().instantSpellOrders( player, enemy,slots[slotNum].getMagics(), map.get(0), map.get(1), map.get(2))){
            }
        } else if (order.matches("Info\\s*")) {
            System.out.println(slots[slotNum].getCard().getInfo());
        } else if (order.matches("Exit\\s*")) return false;
        else System.out.println("Incorrect order!");
        return true;
    }

    public int numberOfTaunts() {
        int num = 0;
        for (int i=0; i<slots.length; i++)
            if (slots[i] != null)
                if (slots[i].getMonsterCardSpeciality() == Taunt)
                    num ++;
        return num;
    }

    public MonsterCardsInBattle getRandomTaunt(){
        int i = (new Random().nextInt(numberOfTaunts()));
        for (int j = 0; j<slots.length; j++)
            if (slots[j] != null && slots[j].getMonsterCardSpeciality() == Taunt && (i--)==0)
                return slots[i+1];
        throw new Error();
    }

    public ArrayList<Map> printingTargets(Battler currentBattler, Battler enemyBattler, MagicType magicType){
        Integer count = 1;
        ArrayList<Map> outputMap= new ArrayList<>();
        Map<Integer, MonsterCardsInBattle> monsterMap = new HashMap<>();
        Map<Integer, Card> cardsMap = new HashMap<>();
        Map<Integer, Spells> spellMap = new HashMap<>();
        outputMap.add(monsterMap);
        outputMap.add(cardsMap);
        outputMap.add(spellMap);
        if(currentBattler.getType().equals("PLAYER")) {
            switch (magicType) {
                case FriendlyTarget:
                    System.out.println("List of Targets : \n");
                    System.out.println("MonsterField : \n");
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = currentBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            System.out.println(count + ".\tSlot" + (i + 1) + ".\t" + monsterCard.getCardName());
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
                case EnemyTarget:
                    System.out.println("List of Targets : \n");
                    System.out.println("MonsterField : \n");
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = enemyBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            System.out.println(count + ".\tSlot" + (i + 1) + ".\t" + monsterCard.getCardName());
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
                case FriendlyPlayerOrMS:
                    System.out.println("List of Targets : \n");
                    System.out.println(count + ". \t Your Player\n");
                    count++;
                    System.out.println("MonsterField : \n");
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = currentBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            System.out.println(count + ".\tSlot" + (i + 1) + ".\t" + monsterCard.getCardName());
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
                case EnemyPlayerOrMS:
                    System.out.println("List of Targets : \n");
                    System.out.println(count + ". \t Enemy Player\n");
                    count++;
                    System.out.println("MonsterField : \n");
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = enemyBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            System.out.println(count + ".\tSlot" + (i + 1) + ".\t" + monsterCard.getCardName());
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
                case SELECTCARD:
                    System.out.println("List of Targets : \n");
                    System.out.println("Hand : \n");
                    for (int i = 0; i < ConstantDatas.SIZE_OF_HAND; i++) {
                        Card card = currentBattler.getHand().get(i);
                        if (card != null) {
                            System.out.println(count + ".\t" + card.getName());
                            cardsMap.put(count, card);
                            count++;
                        }
                    }
                    break;
                case SELECTSPELL:
                    System.out.println("List of Targets : \n");
                    System.out.println("SpellField : \n");
                    for (int i = 0; i < ConstantDatas.SIZE_OF_SpellField; i++) {
                        Spells spell = enemyBattler.getSpellField().getSlot(i);
                        if (spell != null) {
                            System.out.println(count + ".\t" + spell.getName());
                            spellMap.put(count, spell);
                            count++;
                        }
                    }
                    break;
                case MSorSpell:
                    System.out.println("List of Targets : \n");
                    System.out.println("MonsterField : \n");
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = enemyBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            System.out.println(count + ".\tSlot" + (i + 1) + ".\t" + monsterCard.getCardName());
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
            }
        }else {
            switch (magicType) {
                case FriendlyTarget:
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = currentBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
                case EnemyTarget:
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = enemyBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
                case FriendlyPlayerOrMS:
                    count++;
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = currentBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
                case EnemyPlayerOrMS:
                    count++;
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = enemyBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
                case SELECTCARD:
                    for (int i = 0; i < ConstantDatas.SIZE_OF_HAND; i++) {
                        Card card = currentBattler.getHand().get(i);
                        if (card != null) {
                            cardsMap.put(count, card);
                            count++;
                        }
                    }
                    break;
                case SELECTSPELL:
                    for (int i = 0; i < ConstantDatas.SIZE_OF_SpellField; i++) {
                        Spells spell = enemyBattler.getSpellField().getSlot(i);
                        if (spell != null) {
                            spellMap.put(count, spell);
                            count++;
                        }
                    }
                    break;
                case MSorSpell:
                    for (int i = 0; i < ConstantDatas.SIZE_OF_MONSTERFIELD; i++) {
                        MonsterCardsInBattle monsterCard = enemyBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            monsterMap.put(count, monsterCard);
                            count++;
                        }
                    }
                    break;
            }
        }
        return outputMap;
    }

    public void update(Pane root){
        if (hBox != null)
            root.getChildren().remove(hBox);

        hBox = new HBox(10);

        for (int i=0; i<slots.length; i++) {
            if (slots[i] == null)
                hBox.getChildren().addAll(new Rectangle(60,80));
            else {
                hBox.getChildren().addAll(slots[i].getImage());
            }
        }

        hBox.setAlignment(Pos.CENTER);
        root.getChildren().addAll(hBox);
    }
}
