package src.Cards.MonsterCards.InBattle;

import src.Cards.Cards;
import src.Cards.Magic.Magic;
import src.Cards.Magic.MagicType;
import src.Cards.MonsterCards.MonsterCardSpeciality;
import src.Cards.MonsterCards.Tribe;
import src.Cards.MonsterCards.Type;
import src.ToDoPackage.Battler;
import java.util.ArrayList;

public class MonsterCardsInBattle {
    protected int basicHealthPoint;
    protected int currentHealthPoint;
    protected int currentAttackPoint;
    protected Tribe tribe;
    protected String cardName;
    protected boolean isSleep = true;
    protected boolean canAttack = false;
    protected Cards card;
    protected Type type;
    protected ArrayList<Magic> battleCry = new ArrayList<>();
    protected ArrayList<Magic> will = new ArrayList<>();
    protected ArrayList<Magic>  magics = new ArrayList<>();
    protected MonsterCardSpeciality monsterCardSpeciality;
    protected Battler currentBattler;
    protected Battler enemyBattler;
    protected Boolean isBattleCrier = false;
    protected MagicType battleCryType = MagicType.NONE;
    protected MagicType magicType = MagicType.NONE;

    public MagicType getBattleCryType() {
        return battleCryType;
    }

    public MagicType getMagicType() {
        return magicType;
    }

    public Boolean getBattleCrier() {
        return isBattleCrier;
    }

    public void attack(){
        if(canAttack)
            enemyBattler.changeHealthPoint(-this.currentAttackPoint);
        canAttack = false;
    }

    public void attack(MonsterCardsInBattle targetCard){
        if(canAttack) {
            this.currentHealthPoint = -targetCard.currentAttackPoint;
            targetCard.currentHealthPoint = -this.currentAttackPoint;
            this.checkDeath();
            targetCard.checkDeath();
        }
        canAttack = false;
    }

    public Tribe getTribe() {
        return tribe;
    }

    public Type getType(){
        return this.type;
    }

    public Cards getCard() { return card; }

    public void checkDeath(){
        if(this.currentHealthPoint <= 0)
            currentBattler.getMonsterField().remove(this);
    }

    public void changeHealthPoint(int amount){
        this.currentHealthPoint += amount;
        this.basicHealthPoint += amount;
    }

    public void changeAttackPoint(int amount){
        this.currentAttackPoint += amount;
    }
}
