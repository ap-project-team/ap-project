package src.ToDoPackage;

import src.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.Cards.MonsterCards.Tribe;

import java.util.ArrayList;

public class MonsterField {
    //Don't change
    private ArrayList<Slots> slots = new ArrayList<>(5);
    private ArrayList<MonsterCardsInBattle> monsterCardsInBattles = new ArrayList<>();

    public void add(MonsterCardsInBattle cards, int slotNum){

    }
    public void remove(MonsterCardsInBattle cards){

    }
    public Slots getSlot(int index){
      return slots.get(index);
    }

    public MonsterCardsInBattle getRandomMonsterCardInBattle(){
        MonsterCardsInBattle monsterCardsInBattle = new MonsterCardsInBattle();
        return monsterCardsInBattle;
    }

    public MonsterCardsInBattle[] getMonsterCardsInBattles(){
        return monsterCardsInBattles.toArray(new MonsterCardsInBattle[1]);
    }

    public MonsterCardsInBattle getRandomMonsterCardInBattleByType(Tribe tribe){
        MonsterCardsInBattle monsterCardsInBattle = new MonsterCardsInBattle();
        return monsterCardsInBattle;
    }

    public int getEmptySlotNumber(){
        return 0;
    }
    public boolean isEmpty(){
        return monsterCardsInBattles.isEmpty();
    }
    public int getSize(){
        return monsterCardsInBattles.size();
    }
}
