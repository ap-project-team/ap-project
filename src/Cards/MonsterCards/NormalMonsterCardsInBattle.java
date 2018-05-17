package src.Cards.MonsterCards;

public class NormalMonsterCardsInBattle extends MonsterCardsInBattle {

    NormalMonsterCardsInBattle(String cardName,int attackPoint, int healthPoint, MonsterCardSpeciality monsterCardSpeciality){
        this.cardName = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.currentAttackPoint = attackPoint;
        this.currentHealthPoint = healthPoint;
        this.monsterCardSpeciality = monsterCardSpeciality;
        if(this.monsterCardSpeciality == MonsterCardSpeciality.Charge){
            canAttack = true;
            isSleep = false;
        }
    }
}
