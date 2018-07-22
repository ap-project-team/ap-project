package src.ApProject;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import src.ApProject.battle.battler.Battler;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.Spells.Spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Temp {
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

}
