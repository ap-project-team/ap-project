package src.Cards.MonsterCards;


import src.Cards.Cards;
import src.ToDoPackage.Battler;

public class NormalMonsterCard extends MonsterCards {

    NormalMonsterCard(String cardName,int attackPoint, int healthPoint, int manaCost, MonsterCardSpeciality monsterCardSpeciality){
        this.cardName = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.currentAttackPoint = attackPoint;
        this.currentHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.monsterCardSpeciality = monsterCardSpeciality;
    }

    public void play(Battler battler, int slotNum){
        if(battler.getCurrentMana()>= manaCost && battler.getMonsterField().getSlot(slotNum).isEmpty()){
            battler.setCurrentMana(battler.getCurrentMana() - manaCost);
            battler.getHand().remove(this);
            battler.getMonsterField().add(this);
        }
        else {
            System.out.println("I can not do that.");
        }
    }

    public void attack(Battler enemyBattler){
        enemyBattler.setCurrentHealth(enemyBattler.getCurrentHealth() - this.currentAttackPoint);
    }

    public void attack(Cards targetCard, Battler currentBattler, Battler enemyBattler){
        MonsterCards targetMonsterCard = (MonsterCards) targetCard;
        this.currentHealthPoint =- targetMonsterCard.currentAttackPoint;
        targetMonsterCard.currentHealthPoint =- this.currentAttackPoint;
        this.checkDeath(currentBattler);
        targetMonsterCard.checkDeath(enemyBattler);
    }

    public void checkDeath(Battler battler){
        if(this.currentHealthPoint <= 0)
            battler.getMonsterField().remove(this);
    }
}
