package src.Cards.MonsterCards.OutBattle;


import src.Cards.MonsterCards.InBattle.NormalMonsterCardsInBattle;
import src.Cards.MonsterCards.MonsterCardSpeciality;
import src.Cards.MonsterCards.Tribe;
import src.Cards.MonsterCards.Type;
import src.ToDoPackage.Battler;
import src.ToDoPackage.Battler;

public class NormalMonsterCard extends MonsterCards {

    public NormalMonsterCard(String cardName, int attackPoint, int healthPoint, int manaCost, int price, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe){
        this.cardName = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.price = price;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.type = Type.Normal;
    }

    public void play(Battler currentBattler, Battler enemyBattler,int slotNum){
        if(currentBattler.getCurrentMana()>= manaCost  ) {
            if (currentBattler.getMonsterField().getSlot(slotNum).isEmpty()) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                currentBattler.getHand().remove(this);
                currentBattler.getMonsterField().add(new NormalMonsterCardsInBattle(this.cardName, this.basicAttackPoint, this.basicHealthPoint, this.monsterCardSpeciality, this.tribe, this, currentBattler, enemyBattler), slotNum);
            } else {
                System.out.println("That slot is full.");
            }
        }
        else {
            System.out.println("I don't have enough mana.");
        }
    }
}
