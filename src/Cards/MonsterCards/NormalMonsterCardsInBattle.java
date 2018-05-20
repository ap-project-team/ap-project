package src.Cards.MonsterCards;

import src.Cards.Cards;

public class NormalMonsterCardsInBattle extends MonsterCardsInBattle {

    NormalMonsterCardsInBattle(String cardName,int attackPoint, int healthPoint, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe,Cards card){
        this.cardName = cardName;
        this.basicHealthPoint = healthPoint;
        this.currentAttackPoint = attackPoint;
        this.currentHealthPoint = healthPoint;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.card = card;
        if(this.monsterCardSpeciality == MonsterCardSpeciality.Charge){
            canAttack = true;
            isSleep = false;
        }
    }
}
