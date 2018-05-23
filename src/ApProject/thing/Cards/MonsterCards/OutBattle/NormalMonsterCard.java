package src.ApProject.thing.Cards.MonsterCards.OutBattle;


import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.MonsterCards.InBattle.NormalMonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.MonsterCards.Type;

public class NormalMonsterCard extends MonsterCard {

    public NormalMonsterCard(String cardName, int attackPoint, int healthPoint, int manaCost, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe){
        name = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.type = Type.Normal;
        this.price = manaCost * 300;
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum){
        if(currentBattler.getCurrentMana()>= manaCost  ) {
            if (currentBattler.getMonsterField().getSlot(slotNum) == null) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                currentBattler.getHand().remove(this);
                currentBattler.getMonsterField().add(new NormalMonsterCardsInBattle(name, this.basicAttackPoint, this.basicHealthPoint, this.monsterCardSpeciality, this.tribe, this, currentBattler, enemyBattler), slotNum);
            } else {
                System.out.println("That slot is full.");
            }
        }
        else {
            System.out.println("I don't have enough mana.");
        }
    }
    public String getInfo(){
        info = "Name : " + name + "\n" + "HP : " + basicHealthPoint + "\n" + "AP : " + basicAttackPoint + "\n"
                + "MP cost : " + manaCost + "\n" + "Card Type : " + type + "\n"+ "Card Tribe : " + tribe + "\n" + "Is Defensive"
                + (monsterCardSpeciality == MonsterCardSpeciality.Taunt) + "\n" + "Is Nimble" + (monsterCardSpeciality == MonsterCardSpeciality.Charge)
                + "\n";
        return info;
    }
}
