package src.ApProject.battle.battleField;

import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import src.ApProject.Game;
import src.ApProject.battle.battler.Battler;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MonsterCard;
import src.ApProject.thing.Cards.MonsterCards.Type;
import src.ApProject.thing.Cards.Spells.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static src.ApProject.thing.Cards.Magic.MagicType.WITHOUTTARGET;

public class SpellField {

    private Spells[] slots = new Spells[ConstantDatas.SIZE_OF_SpellField];
    HBox hBox;


    public void add(Spells spells, int slotNum){
        if (slots[slotNum] == null)
            slots[slotNum] = (spells);
    }

    public void remove(Spells spell, Battler spellOwner){
        for(int i=0; i<slots.length; i++)
            if (slots[i].equals(spell)) {
                if(slots[i].getSpellType() == SpellType.Aura){
                    for(MonsterCardsInBattle monsterCardsInBattle : spellOwner.getMonsterField().getMonsterCardsInBattles()){
                        if(monsterCardsInBattle != null){
                            monsterCardsInBattle.removeAuraEffect((AuraSpell) spell);
                        }
                    }
                }
                spellOwner.getGraveYard().add(spell);
                slots[i] = null;
                return;
            }
    }

    public Spells getSlot(int index){
        return slots[index];
    }

    public Spells getRandomSpell(){
        Random random = new Random();
        int count = 0;
        int randNum;
        for (int i = 0; i < slots.length; i++) {
            if(slots[i] != null)
                count++;
        }
        while (count != 0){
            randNum = random.nextInt(slots.length);
            if(slots[randNum] != null)
                return slots[randNum];
        }
        return null;
    }

    public Spells[] getSpells(){
        return slots;
    }

    public void nextTurn(){
        for (int i=0; i<slots.length; i++){
            if (slots[i] != null)
                if (slots[i].getSpellType() == SpellType.Continuous)
                    ((ContinuousSpell)slots[i]).doMagic();
        }

    }

    public void viewSpellField(){
        for (int i = 0; i < slots.length; i++)
            if (slots[i] == null)
                System.out.println((i + 1) + ".\tEmpty");
            else System.out.println((i + 1) + ".\t" + slots[i].getName() + " \n" + slots[i].getMagicDetails());

    }

    public boolean instantSpellOrders(Battler currentBattler, Battler enemyBattler,InstantSpell instantSpell, Map<Integer, MonsterCardsInBattle> monsterMap, Map<Integer, Card> cardsMap, Map<Integer, Spells> spellMap ) {
        Random random =  new Random();
        int randNum;
        if(instantSpell.getMagicType() != MagicType.NONE) {
            if (instantSpell.getMagicType() != WITHOUTTARGET) {
                if(currentBattler.getType().equals("PLAYER")) {
                    String order = Game.give();
                    if (order.matches("Help\\s*")) {
                        System.out.println(
                                "1.\tTarget #TargetNum:To cast the spell on the specified target\n" +
                                        "2.\tExit: To skip spell casting");
                    } else if (order.matches("Target \\d*")) {
                        String[] str = order.split("\\s");
                        switch (instantSpell.getMagicType()) {
                            case SELECTCARD:
                                if (cardsMap.get(Integer.parseInt(str[1])) != null) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, null, null, cardsMap.get(Integer.parseInt(str[1])));
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case SELECTSPELL:
                                if (spellMap.get(Integer.parseInt(str[1])) != null) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, null, spellMap.get(Integer.parseInt(str[1])), null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case FriendlyPlayerOrMS:
                                if(Integer.parseInt(str[1]) == 1) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, null, null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }else if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case EnemyPlayerOrMS:
                                if(Integer.parseInt(str[1]) == 1) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, null, null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }else if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            default:
                                if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                        }
                    } else if (order.matches("Exit\\s*")) return false;
                    else System.out.println("Incorrect order!");
                    return true;
                } else {
                        switch (instantSpell.getMagicType()) {
                            case SELECTCARD:
                                if(cardsMap.size()>0){
                                Card card = cardsMap.get(random.nextInt(cardsMap.size()) + 1);
                                if (card != null) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, null, null, card);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } }return false;
                            case SELECTSPELL:
                                Spells spell = spellMap.get(random.nextInt(spellMap.size()) + 1);
                                if(spellMap.size()>0) {
                                    if (spell != null) {
                                        instantSpell.doMagic(currentBattler, enemyBattler, null, spell, null);
                                        System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                        return false;
                                    }
                                }return false;
                            case FriendlyPlayerOrMS:
                                 randNum = random.nextInt(monsterMap.size() + 1) + 1;
                                if(randNum == 1) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, null, null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }else if (monsterMap.get(randNum) != null) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, monsterMap.get(randNum), null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }
                                break;
                            case EnemyPlayerOrMS:
                                randNum = random.nextInt(monsterMap.size() + 1) + 1;
                                if(randNum == 1) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, null, null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }else if (monsterMap.get(randNum) != null) {
                                    instantSpell.doMagic(currentBattler, enemyBattler, monsterMap.get(randNum), null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }
                                break;
                            default:
                                if(monsterMap.size()>0) {
                                    MonsterCardsInBattle monsterCardsInBattle = monsterMap.get(random.nextInt(monsterMap.size()) + 1);
                                    if (monsterCardsInBattle != null) {
                                        instantSpell.doMagic(currentBattler, enemyBattler, monsterCardsInBattle, null, null);
                                        System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                        return false;
                                    }
                                }return false;
                        }
                }
            } else {
                instantSpell.doMagic(currentBattler, enemyBattler, null, null, null);
                System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                return false;
            }
        }
        return false;
    }

    public int getSize(){
        return slots.length;
    }

    public int getEmptySlotNumber() {
        int num = 0;
        for (int i=0; i<slots.length; i++)
            if (slots[i] == null)
                num++;
        return num;
    }


    public int getFirstEmptySlot() {
        for (int i=0; i<slots.length; i++)
            if (slots[i] == null)
                return i;
        return -1;
    }

    public void update(Pane root){
        if (hBox != null)
            root.getChildren().remove(hBox);

        hBox = new HBox(10);

        for (int i=0; i<slots.length; i++) {
            if (slots[i] == null)
                hBox.getChildren().addAll(new Rectangle(60,80));
            else {
                hBox.getChildren().addAll(slots[i].getFullImage());
            }
        }

        hBox.setAlignment(Pos.CENTER);
        root.getChildren().addAll(hBox);
    }
}
