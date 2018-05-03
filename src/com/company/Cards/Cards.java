package com.company.Cards;

import com.company.Cards.MonsterCards.BattleCry;
import com.company.Cards.MonsterCards.DeathRattle;
import com.company.Cards.MonsterCards.MonsterCardSpeciality;
import com.company.ToDoPackage.Battler;

abstract public class Cards {
    abstract public void play(Battler battler, int slotNum);
}
