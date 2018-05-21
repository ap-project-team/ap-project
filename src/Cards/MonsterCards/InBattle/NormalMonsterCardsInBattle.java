package src.Cards.MonsterCards.InBattle;

import src.Cards.Cards;
import src.Cards.MonsterCards.MonsterCardSpeciality;
import src.Cards.MonsterCards.Tribe;
import src.ToDoPackage.Battler;

public class NormalMonsterCardsInBattle extends MonsterCardsInBattle {

    public NormalMonsterCardsInBattle(String cardName, int attackPoint, int healthPoint, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, Cards card, Battler currentBattler, Battler enemyBattler){
        this.cardName = cardName;
        this.basicHealthPoint = healthPoint;
        this.currentAttackPoint = attackPoint;
        this.currentHealthPoint = healthPoint;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.card = card;
        this.currentBattler = currentBattler;
        this.enemyBattler = enemyBattler;
        if(this.monsterCardSpeciality == MonsterCardSpeciality.Charge){
            canAttack = true;
            isSleep = false;
        }
    }
}
