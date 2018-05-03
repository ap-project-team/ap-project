package com.company.Cards.MonsterCards;


import com.company.Cards.MonsterCards.MonsterCardType;
import com.company.Cards.MonsterCards.MonsterCards;
import com.company.ToDoPackage.Battler;

public class NormalMonsterCard extends MonsterCards {

    NormalMonsterCard(int attackPoint, int healthPoint, int manaCost, MonsterCardType monsterCardType){
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.currentAttackPoint = attackPoint;
        this.currentHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.monsterCardType = monsterCardType;
    }

    public void play(Battler battler){
        if(battler.getCurrentMana()>= manaCost && battler.getMonsterField().getNumOfCurrentMonsters() < 5){
            battler.setCurrentMana(battler.getCurrentMana() - manaCost);
            battler.getHand().remove(this);
            battler.getMonsterField().add(this);
        }
    }

    public void attack(){

    }
}
