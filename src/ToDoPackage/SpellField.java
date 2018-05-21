package src.ToDoPackage;

import src.Cards.Spells.Spells;

import java.util.ArrayList;

public class SpellField {
    //Don't change
    private ArrayList<Slots> slots = new ArrayList<>(5);
    private ArrayList<Spells> spells = new ArrayList<>();

    public void add(Spells spell, int slotNum){

    }
    public void remove(Spells spell){ }

    public Slots getSlot(int index){
        return slots.get(index);
    }

    // TODO: 5/21/2018  
    public Spells getRandomSpell(){
        Spells spells = null;
        return spells;
    }

    public Spells[] getSpells(){
        return spells.toArray(new Spells[1]);
    }
}
