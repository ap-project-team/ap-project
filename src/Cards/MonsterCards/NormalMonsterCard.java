package src.Cards.MonsterCards;


import src.Cards.Cards;
import src.ToDoPackage.Battler;

public class NormalMonsterCard extends MonsterCards {

    NormalMonsterCard(String cardName,int attackPoint, int healthPoint, int manaCost, int price, MonsterCardSpeciality monsterCardSpeciality){
        this.cardName = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.price = price;
        this.monsterCardSpeciality = monsterCardSpeciality;
    }

    public void play(Battler battler, int slotNum){
        if(battler.getCurrentMana()>= manaCost && battler.getMonsterField().getSlot(slotNum).isEmpty()){
            battler.setCurrentMana(battler.getCurrentMana() - manaCost);
            battler.getHand().remove(this);
            battler.getMonsterField().add(new NormalMonsterCardsInBattle( this.cardName, this.basicAttackPoint, this.basicHealthPoint, this.monsterCardSpeciality), slotNum);
        }
        else {
            System.out.println("I can not do that.");
        }
    }
}
