package src.ToDoPackage;

import src.Cards.Cards;

import java.util.ArrayList;

public class MonsterField {
    private ArrayList<Slots> slots = new ArrayList<>(5);
    public void add(Cards cards){

    }
    public void remove(Cards cards){

    }
    public Slots getSlot(int index){
      return slots.get(index);
    }
}
