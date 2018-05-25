package src.ApProject.battle.battleField;

import src.ApProject.Game;
import src.ApProject.battle.battler.Battler;
import src.ApProject.constants.ConstantDatas;
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

    public boolean spellCastingOrders(Battler currentBattler, Battler enemyBattler, MonsterCardsInBattle monsterCardsInBattle) {
        if(monsterCardsInBattle.getMagicType() != MagicType.NONE) {
            System.out.println(monsterCardsInBattle.getCardName() + " has cast a spell : \n" + monsterCardsInBattle.getMagicDetail() + "\n");
            Integer count = 1;
            Map<Integer, MonsterCardsInBattle> map = new HashMap<>();
            switch (monsterCardsInBattle.getMagicType()) {
                case WITHOUTTARGET:
                    monsterCardsInBattle.doMagic(null, null, null);
                    return false;
                case FriendlyTarget:
                    System.out.println("List of Targets : \n");
                    System.out.println("MonsterField : \n");
                    for (int i = 0; i < 6; i++) {
                        MonsterCardsInBattle monsterCard = currentBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            System.out.println(count + ".\tSlot" + (i + 1) + ".\t" + monsterCard.getCardName());
                            map.put(count, monsterCard);
                        }
                    }
                    break;
                case EnemyTarget:
                    System.out.println("List of Targets : \n");
                    System.out.println("MonsterField : \n");
                    for (int i = 0; i < 6; i++) {
                        MonsterCardsInBattle monsterCard = enemyBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            System.out.println(count + ".\tSlot" + (i + 1) + ".\t" + monsterCard.getCardName());
                            map.put(count, monsterCard);
                        }
                    }
                    break;
                case FriendlyPlayerOrMS:
                    System.out.println("List of Targets : \n");

                    System.out.println("MonsterField : \n");
                    count++;
                    for (int i = 0; i < 6; i++) {
                        MonsterCardsInBattle monsterCard = enemyBattler.getMonsterField().getMonsterCardsInBattles()[i];
                        if (monsterCard != null) {
                            System.out.println(count + ".\tSlot" + (i + 1) + ".\t" + monsterCard.getCardName());
                            map.put(count, monsterCard);
                        }
                    }
                    break;
            }
        }
        return false;
    }

}
