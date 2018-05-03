package com.company.Cards.MonsterCards;

import com.company.Cards.Cards;
import com.company.ToDoPackage.Battler;
import com.company.ToDoPackage.Player;

abstract public class MonsterCards extends Cards {
    protected int basicHealthPoint;
    protected int basicAttackPoint;
    protected int currentHealthPoint;
    protected int currentAttackPoint;
    protected int manaCost;
    protected int price;
    protected String cardName;
    protected BattleCry battleCry;
    protected DeathRattle deathRattle;
    protected  MonsterCardSpeciality monsterCardSpeciality;

    abstract public void attack(Battler enemyBattler);
    abstract protected void attack(Cards targetCard, Battler currentBattler, Battler enemyBattler);
    abstract public void checkDeath(Battler battler);

}
