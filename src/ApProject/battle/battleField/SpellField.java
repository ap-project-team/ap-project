package src.ApProject.battle.battleField;

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

    public void nextTurne(){
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

    public boolean spellCastingOrders(Battler currentBattler,MonsterCardsInBattle monsterCardsInBattle , Map<Integer, MonsterCardsInBattle> monsterMap,  Map<Integer, Card> cardsMap, Map<Integer, Spells> spellMap ) {
        Random random = new Random();
        int randNum;
        if(monsterCardsInBattle.getMagicType() != MagicType.NONE) {
            if (monsterCardsInBattle.getMagicType() != WITHOUTTARGET) {
                if (currentBattler.getType().equals("PLAYER")) {
                    String order = Game.give();
                    if (order.matches("Help\\s*")) {
                        System.out.println(
                                "1.\tTarget #TargetNum:To cast the spell on the specified target\n" +
                                        "2.\tExit: To skip spell casting");
                    } else if (order.matches("Target \\d*")) {
                        String[] str = order.split("\\s");
                        switch (monsterCardsInBattle.getMagicType()) {
                            case SELECTCARD:
                                if (cardsMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doMagic(null, null, cardsMap.get(Integer.parseInt(str[1])));
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case SELECTSPELL:
                                if (spellMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doMagic(null, spellMap.get(Integer.parseInt(str[1])), null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case FriendlyPlayerOrMS:
                                if(Integer.parseInt(str[1]) == 1){
                                    monsterCardsInBattle.doMagic(null, null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                }else  if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doMagic(monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case EnemyPlayerOrMS:
                                if(Integer.parseInt(str[1]) == 1){
                                    monsterCardsInBattle.doMagic(null, null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                }else if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doMagic(monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            default:
                                if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doMagic(monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                        }
                    } else if (order.matches("Exit\\s*")) return false;
                    else System.out.println("Incorrect order!");
                    return true;
                }else {
                        switch (monsterCardsInBattle.getMagicType()) {
                            case SELECTCARD:
                                if(cardsMap.size() > 0) {
                                    Card card = cardsMap.get(random.nextInt(cardsMap.size()));
                                    if (card != null) {
                                        monsterCardsInBattle.doMagic(null, null, card);
                                        System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                        return false;
                                    }
                                }return false;
                            case SELECTSPELL:
                                if(spellMap.size() > 0) {
                                    Spells spell = spellMap.get(random.nextInt(spellMap.size()));
                                    if (spell != null) {
                                        monsterCardsInBattle.doMagic(null, spell, null);
                                        System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                        return false;
                                    }
                                }return false;
                            case FriendlyPlayerOrMS:
                                randNum = random.nextInt(monsterMap.size() + 1) + 1;
                                if(randNum == 1){
                                    monsterCardsInBattle.doMagic(null, null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                }else  if (monsterMap.get(randNum) != null) {
                                    monsterCardsInBattle.doMagic(monsterMap.get(randNum), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                }
                                break;
                            case EnemyPlayerOrMS:
                                randNum = random.nextInt(monsterMap.size() + 1) + 1;
                                if(randNum == 1){
                                    monsterCardsInBattle.doMagic(null, null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                }else  if (monsterMap.get(randNum) != null) {
                                    monsterCardsInBattle.doMagic(monsterMap.get(randNum), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                    return false;
                                }
                                break;
                            default:
                                if(monsterMap.size() > 0) {
                                    MonsterCardsInBattle monsterCard = monsterMap.get(random.nextInt(monsterMap.size()));
                                    if (monsterCard != null) {
                                        monsterCardsInBattle.doMagic(monsterCard, null, null);
                                        System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
                                        return false;
                                    }
                                }return false;
                        }
                    }
                }
             else{
                    monsterCardsInBattle.doMagic(null, null, null);
                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                    return false;
                }
            }
        return false;
    }

    public boolean battleCryOrders(Battler currentBattler,MonsterCardsInBattle monsterCardsInBattle , Map<Integer, MonsterCardsInBattle> monsterMap,  Map<Integer, Card> cardsMap, Map<Integer, Spells> spellMap){
       Random random = new Random();
       int randomNum;
        if(monsterCardsInBattle.getBattleCryType() != MagicType.NONE) {
            if (monsterCardsInBattle.getBattleCryType() != WITHOUTTARGET) {
                if (currentBattler.getType().equals("PLAYER")) {
                    String order = Game.give();
                    if (order.matches("Help\\s*")) {
                        System.out.println(
                                "1.\tTarget #TargetNum:To cast the spell on the specified target\n" +
                                        "2.\tExit: To skip spell casting");
                    } else if (order.matches("Target \\d*")) {
                        String[] str = order.split("\\s");
                        switch (monsterCardsInBattle.getBattleCryType()) {
                            case SELECTCARD:
                                if (cardsMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doBattleCry(null, null, cardsMap.get(Integer.parseInt(str[1])));
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case SELECTSPELL:
                                if (spellMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doBattleCry(null, spellMap.get(Integer.parseInt(str[1])), null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case FriendlyPlayerOrMS:
                                if (Integer.parseInt(str[1]) == 1) {
                                    monsterCardsInBattle.doBattleCry(null, null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                } else if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doBattleCry(monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case EnemyPlayerOrMS:
                                if (Integer.parseInt(str[1]) == 1) {
                                    monsterCardsInBattle.doBattleCry(null, null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                } else if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doBattleCry(monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            default:
                                if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    monsterCardsInBattle.doBattleCry(monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                        }
                    } else if (order.matches("Exit\\s*")) return false;
                    else System.out.println("Incorrect order!");
                    return true;
                }else {
                        switch (monsterCardsInBattle.getBattleCryType()) {
                            case SELECTCARD:
                                if(cardsMap.size() > 0) {
                                    Card card = cardsMap.get(random.nextInt(cardsMap.size())+ 1);
                                    if (card != null) {
                                        monsterCardsInBattle.doBattleCry(null, null, card);
                                        System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                        return false;
                                    }
                                }return false;
                            case SELECTSPELL:
                                if(spellMap.size() > 0) {
                                    Spells spell = spellMap.get(random.nextInt(spellMap.size()) + 1);
                                    if (spell != null) {
                                        monsterCardsInBattle.doBattleCry(null, spell, null);
                                        System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                        return false;
                                    }
                                }return false;
                            case FriendlyPlayerOrMS:
                                randomNum = random.nextInt(monsterMap.size() + 1) + 1;
                                if (randomNum == 1) {
                                    monsterCardsInBattle.doBattleCry(null, null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                } else if (monsterMap.get(randomNum) != null) {
                                    monsterCardsInBattle.doBattleCry(monsterMap.get(randomNum), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                }
                                break;
                            case EnemyPlayerOrMS:
                                randomNum = random.nextInt(monsterMap.size() + 1) + 1;
                                if (randomNum == 1) {
                                    monsterCardsInBattle.doBattleCry(null, null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                } else if (monsterMap.get(randomNum) != null) {
                                    monsterCardsInBattle.doBattleCry(monsterMap.get(randomNum), null, null);
                                    System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                    return false;
                                }
                                break;
                            default:
                                if(monsterMap.size() > 0) {
                                    MonsterCardsInBattle monsterCard = monsterMap.get(random.nextInt(monsterMap.size()) + 1);
                                    if (monsterCard != null) {
                                        monsterCardsInBattle.doBattleCry(monsterCard , null, null);
                                        System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                                        return false;
                                    }
                                }
                        }
                }
            }
            else {
                monsterCardsInBattle.doBattleCry(null, null, null);
                System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getBattleCryDetail() + "\n");
                return false;
            }
        }
        return false;
    }

    public boolean instantSpellOrders(Battler currentBattler,InstantSpell instantSpell, Map<Integer, MonsterCardsInBattle> monsterMap, Map<Integer, Card> cardsMap, Map<Integer, Spells> spellMap ) {
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
                                    instantSpell.doMagic(null, null, cardsMap.get(Integer.parseInt(str[1])));
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case SELECTSPELL:
                                if (spellMap.get(Integer.parseInt(str[1])) != null) {
                                    instantSpell.doMagic(null, spellMap.get(Integer.parseInt(str[1])), null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case FriendlyPlayerOrMS:
                                if(Integer.parseInt(str[1]) == 1) {
                                    instantSpell.doMagic(null, null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }else if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    instantSpell.doMagic(monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            case EnemyPlayerOrMS:
                                if(Integer.parseInt(str[1]) == 1) {
                                    instantSpell.doMagic(null, null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }else if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    instantSpell.doMagic(monsterMap.get(Integer.parseInt(str[1])), null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } else
                                    System.out.println("That's not a valid Target");
                                break;
                            default:
                                if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                                    instantSpell.doMagic(monsterMap.get(Integer.parseInt(str[1])), null, null);
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
                                    instantSpell.doMagic(null, null, card);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                } }return false;
                            case SELECTSPELL:
                                Spells spell = spellMap.get(random.nextInt(spellMap.size()) + 1);
                                if(spellMap.size()>0) {
                                    if (spell != null) {
                                        instantSpell.doMagic(null, spell, null);
                                        System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                        return false;
                                    }
                                }return false;
                            case FriendlyPlayerOrMS:
                                 randNum = random.nextInt(monsterMap.size() + 1) + 1;
                                if(randNum == 1) {
                                    instantSpell.doMagic(null, null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }else if (monsterMap.get(randNum) != null) {
                                    instantSpell.doMagic(monsterMap.get(randNum), null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }
                                break;
                            case EnemyPlayerOrMS:
                                randNum = random.nextInt(monsterMap.size() + 1) + 1;
                                if(randNum == 1) {
                                    instantSpell.doMagic(null, null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }else if (monsterMap.get(randNum) != null) {
                                    instantSpell.doMagic(monsterMap.get(randNum), null, null);
                                    System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                    return false;
                                }
                                break;
                            default:
                                if(monsterMap.size()>0) {
                                    MonsterCardsInBattle monsterCardsInBattle = monsterMap.get(random.nextInt(monsterMap.size()) + 1);
                                    if (monsterCardsInBattle != null) {
                                        instantSpell.doMagic(monsterCardsInBattle, null, null);
                                        System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                                        return false;
                                    }
                                }return false;
                        }
                }
            } else {
                instantSpell.doMagic(null, null, null);
                System.out.println(instantSpell.getName() + " has cast a spell : \n" + instantSpell.getMagicDetails() + "\n");
                return false;
            }
        }
        return false;
    }
}
