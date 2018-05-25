package src.ApProject.battle.battleField;

import src.ApProject.Game;
import src.ApProject.battle.battler.Battler;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.Spells.AuraSpell;
import src.ApProject.thing.Cards.Spells.SpellType;
import src.ApProject.thing.Cards.Spells.Spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    public void viewSpellField(){
        for (int i = 0; i < slots.length; i++)
            if (slots[i] == null)
                System.out.println((i + 1) + ".\tEmpety");
            else System.out.println((i + 1) + ".\t" + slots[i].getName());

    }

    public boolean spellCastingOrders(Battler currentBattler, Battler enemyBattler, MonsterCardsInBattle monsterCardsInBattle , Map<Integer, MonsterCardsInBattle> monsterMap,  Map<Integer, Card> cardsMap, Map<Integer, Spells> spellMap ) {
        if(monsterCardsInBattle.getMagicType() != MagicType.NONE) {
            String order = Game.give();

            if (order.matches("Help\\s*")) {
              System.out.println(
                        "1.\tTarget #TargetNum:To cast the spell on the specified target\n" +
                                "2.\tExit: To skip spell casting");
            } else if (order.matches("Target \\d*")) {
                String[] str = order.split("\\s");
                switch (monsterCardsInBattle.getMagicType()) {
                    case WITHOUTTARGET:
                        monsterCardsInBattle.doMagic(null, null, null);
                        break;
                    case SELECTCARD:
                        if (cardsMap.get(Integer.parseInt(str[1])) != null) {
                            monsterCardsInBattle.doMagic(null, null, cardsMap.get(Integer.parseInt(str[1])));
                            return false;
                        }else
                            System.out.println("That's not a valid Target");
                        break;
                    case SELECTSPELL:
                        if (spellMap.get(Integer.parseInt(str[1])) != null) {
                            monsterCardsInBattle.doMagic(null, spellMap.get(Integer.parseInt(str[1])), null);
                            return false;
                        }else
                            System.out.println("That's not a valid Target");
                        break;
                    default:
                        if (monsterMap.get(Integer.parseInt(str[1])) != null) {
                            monsterCardsInBattle.doMagic(monsterMap.get(Integer.parseInt(str[1])), null, null);
                            return false;
                        }
                        else
                            System.out.println("That's not a valid Target");
                        break;
                }
            } else if (order.matches("Exit\\s*")) return false;
            else System.out.println("Incorrect order!");
            return true;
        }
        return false;
    }

    public boolean battleCryOrders(Battler currentBattler, Battler enemyBattler, MonsterCardsInBattle monsterCardsInBattle){
        return false;
    }
}
