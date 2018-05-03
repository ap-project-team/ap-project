package com.company.Cards.MonsterCards;

import com.company.Cards.Cards;
import com.company.ToDoPackage.Battler;

abstract public class MonsterCards extends Cards {
    int basicHealthPoint;
    int basicAttackPoint;
    int currentHealthPoint;
    int currentAttackPoint;
    int manaCost;
    int price;
    BattleCry battleCry;
    DeathRattle deathRattle;
    MonsterCardType monsterCardType;

    abstract public void attack();
}
