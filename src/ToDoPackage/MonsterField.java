package src.ToDoPackage;

import src.Cards.Cards;
import src.Cards.MonsterCards.MonsterCards;
import src.Cards.MonsterCards.MonsterCardsInBattle;

import java.util.ArrayList;

public class MonsterField {
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
}
