package src.Cards.MonsterCards;

import src.Cards.Cards;
import src.Cards.MonsterCards.MonsterCardMagic.MonsterCardMagic;
import src.ToDoPackage.Battler;

public class MonsterCardsInBattle {
    protected int basicHealthPoint;
    protected int basicAttackPoint;
    protected int currentHealthPoint;
    protected int currentAttackPoint;
    protected String cardName;
    protected boolean isSleep = true;
    protected boolean canAttack = false;
    protected MonsterCardMagic monsterCardMagic;
    protected MonsterCardBattleCry monsterCardBattleCry;
    protected MonsterCardDeathRattle monsterCardDeathRattle;
    protected  MonsterCardSpeciality monsterCardSpeciality;

    public void attack(Battler enemyBattler){
        if(canAttack)
            enemyBattler.changeHealthPoint(-this.currentAttackPoint);
        canAttack = false;
    }

    public void attack(MonsterCardsInBattle targetCard, Battler currentBattler, Battler enemyBattler){
        if(canAttack) {
            this.currentHealthPoint = -targetCard.currentAttackPoint;
            targetCard.currentHealthPoint = -this.currentAttackPoint;
            this.checkDeath(currentBattler);
            targetCard.checkDeath(enemyBattler);
        }
        canAttack = false;
    }

    public void checkDeath(Battler battler){
        if(this.currentHealthPoint <= 0)
            battler.getMonsterField().remove(this);
    }

    public void changeHealthPoint(int amount){
        this.currentHealthPoint += amount;
        this.basicHealthPoint += amount;
    }

    public void changeAttackPoint(int amount){
        this.currentAttackPoint += amount;
        this.basicAttackPoint += amount;
    }
}
