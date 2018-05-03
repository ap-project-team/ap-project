package com.company.ToDoPackage;

public class Battler {
    Hand hand;
    Deck deck;

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    SpellField spellField;
    MonsterField monsterField;
    int currentMana;
    int maxMana;

    public int getCurrentMana() {
        return currentMana;
    }

    public MonsterField getMonsterField() {
        return monsterField;
    }

    public void setMonsterField(MonsterField monsterField) {
        this.monsterField = monsterField;
    }

    public void setCurrentMana(int currentMana){
        this.currentMana = currentMana;
    }
}
