package src.ApProject.battle.battleField;

import src.ApProject.battle.Slot;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MonsterCard;
import src.ApProject.thing.Cards.MonsterCards.Tribe;

import java.util.ArrayList;
import java.util.Random;

public class MonsterField {
    //Don't change
    private MonsterCardsInBattle[] slots = new MonsterCardsInBattle[ConstantDatas.SIZE_OF_MONSTERFIELD];

    public void add(MonsterCardsInBattle card, int slotNum){
        if (slots[slotNum] == null)
            slots[slotNum] = (card);
    }

    public void remove(MonsterCardsInBattle card){
        for(int i=0; i<slots.length; i++)
            if (slots[i].equals(card)) {
                slots[i] = null;
                return;
            }
    }

    public MonsterCardsInBattle getSlot(int index){
      return slots[index];
    }

    public MonsterCardsInBattle getRandomMonsterCardInBattle(){
        Random random = new Random();
        int randomNum;
        while (true){
            randomNum = random.nextInt(slots.length);
            if(slots[randomNum] != null)
                return slots[randomNum];
        }
    }

    public MonsterCardsInBattle[] getMonsterCardsInBattles(){
        return slots;
    }

    public MonsterCardsInBattle getRandomMonsterCardInBattleByType(Tribe tribe){
        Random random = new Random();
        int randomNum;
        int count = 0;
        for (int i = 0; i < slots.length; i++) {
            if(slots[i] != null)
                if (slots[i].getTribe() == tribe)
                    count++;
        }
        while (count != 0){
            randomNum = random.nextInt(ConstantDatas.SIZE_OF_MONSTERFIELD);
            if(slots[randomNum] != null)
                if(slots[randomNum].getTribe() == tribe)
                    return slots[randomNum];
        }
        return null;
    }

    public int getEmptySlotNumber(){
        int num = 0;
        for (MonsterCardsInBattle S : slots)
            if (S == null)
                num ++;
        return num;
    }

    public boolean isEmpty(){
        return getEmptySlotNumber() == 0;
    }

    public int getSize(){
        return slots.length - getEmptySlotNumber();
    }

    public void viewMonsterField() {
        for (int i = 0; i < slots.length; i++)
            if (slots[i] == null)
                System.out.println((i + 1) + ".\tEmpty");
            else System.out.println((i + 1) + ".\t" + slots[i].getCardName());
    }
}
