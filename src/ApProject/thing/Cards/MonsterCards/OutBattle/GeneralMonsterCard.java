package src.ApProject.thing.Cards.MonsterCards.OutBattle;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.InBattle.GeneralMonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.MonsterCards.Type;

import java.util.ArrayList;

public class GeneralMonsterCard extends MonsterCard {
    public GeneralMonsterCard(String cardName, int attackPoint, int healthPoint, int manaCost, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, ArrayList<Magic> battleCry, ArrayList<Magic> will){
        this.name = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.type = Type.SpellCaster;
        this.battleCry.addAll(battleCry);
        this.will.addAll(will);
        this.price = manaCost * 1000;
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
        if(currentBattler.getCurrentMana()>= manaCost  ) {
            if (currentBattler.getMonsterField().getSlot(slotNum) == null) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                currentBattler.getHand().remove(this);
                currentBattler.getMonsterField().add(new GeneralMonsterCardsInBattle(this.name, this.basicAttackPoint, this.basicHealthPoint, this.monsterCardSpeciality, this.tribe, this.battleCry,this.will,this , currentBattler, enemyBattler), slotNum);
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
                + "\n"  + "BattleCry Details : " + "\n" + battleCry.get(0).getmagicDetails() + "\n" + "Will Details : " + will.get(0).getmagicDetails();
        return info;
    }
}
