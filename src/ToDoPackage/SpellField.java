package src.ToDoPackage;

import src.Cards.Spells.Spells;

import java.util.ArrayList;

public class SpellField {
    private ArrayList<Slots> slots = new ArrayList<>(5);
    private ArrayList<Spells> spells = new ArrayList<>();

    public void add(Spells spell, int slotNum){

    }
    public void remove(Spells spell){ }

    public Slots getSlot(int index){
        return slots.get(index);
    }

    public Spells getRandomSpell(){
        Spells spells = new Spells();
        return spells;
    }

    public Spells[] getSpells(){
        return spells.toArray(new Spells[1]);
    }
}
