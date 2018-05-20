package src.ApProject.battle;

import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Card;

import java.util.ArrayList;

public class BattleGround {
    ArrayList<Card> graveyard = new ArrayList<>();
    //MonsterCard[] monsterField = new MonsterCard[ConstantDatas.SIZE_OF_MONSTERFIELD];
    //SpellCard[] spellField = new SpellCard[ConstantDatas.SIZE_OF_MONSTERFIELD];

    BattleGround(){

    }

    public void viewMonsterField() {
        System.out.println("Your MonsterField :");
    //    for (int i=0; i<monsterField.length; i++)
    //        System.out.println("Slot "+(i+1)+":\t"+monsterField[1]);
    }
}
