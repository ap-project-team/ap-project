package src.ApProject.battle.battleField;

import src.ApProject.battle.Slot;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Cards.Spells.Spells;

import java.util.ArrayList;

public class SpellField {
    //Don't change
    private Spells[] slots = new Spells[ConstantDatas.SIZE_OF_SpellField];

    public void add(Spells spells, int slotNum){
        if (slots[slotNum] == null)
            slots[slotNum] = (spells);
    }

    public void remove(Spells spells){
        for(int i=0; i<slots.length; i++)
            if (slots[i].equals(spells)) {
                slots[i] = null;
                return;
            }
    }

    public Spells getSlot(int index){
        return slots[index];
    }

    // TODO: 5/21/2018  
    public Spells getRandomSpell(){
        Spells spells = null;
        return spells;
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
}
