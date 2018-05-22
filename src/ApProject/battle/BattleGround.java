package src.ApProject.battle;

import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MonsterCards;

import java.util.ArrayList;


public class BattleGround {
    ArrayList<Cards> graveyard = new ArrayList<>();
    Slot[] monsterField = new Slot[ConstantDatas.SIZE_OF_MONSTERFIELD];
    Slot[] spellField = new Slot[ConstantDatas.SIZE_OF_SpellField];

    public BattleGround(){
        for (int i=0; i<monsterField.length; i++)
            monsterField[i] = new Slot<MonsterCards>("MONSTERCARD");

        for (int i=0; i<spellField.length; i++)
            spellField[i] = new Slot("SPELLCARD");
    }

    public boolean setMonsterCardInMonsterField(Cards card, int slotNum){
        if (monsterField[slotNum].isEmpety()){
            monsterField[slotNum].setThing(card);
            return true;
        }
        return false;

    }

    public void viewMonsterField() {
        System.out.println("Your MonsterField :");
    //    for (int i=0; i<monsterField.length; i++)
    //        System.out.println("Slot "+(i+1)+":\t"+monsterField[1]);
    }
}
