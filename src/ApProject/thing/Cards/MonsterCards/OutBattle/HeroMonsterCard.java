package src.ApProject.thing.Cards.MonsterCards.OutBattle;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.InBattle.HeroMonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.MonsterCards.Type;

import java.util.ArrayList;

public class HeroMonsterCard extends MonsterCard {
    public HeroMonsterCard(String cardName, int attackPoint, int healthPoint, int manaCost, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, ArrayList<Magic> magics, ArrayList<Magic> battleCry, ArrayList<Magic> will){
        this.name = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.type = Type.SpellCaster;
        this.battleCry.addAll(battleCry);
        this.will.addAll(will);
        this.magics.addAll(magics);
        this.price = manaCost * 700;
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
        if(currentBattler.getCurrentMana()>= manaCost  ) {
            if (currentBattler.getMonsterField().getSlot(slotNum) == null) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                currentBattler.getHand().remove(this);
                currentBattler.getMonsterField().add(new HeroMonsterCardsInBattle(this.name, this.basicAttackPoint, this.basicHealthPoint, this.monsterCardSpeciality, this.tribe, this.magics,this.battleCry,this.will,this , currentBattler, enemyBattler), slotNum);
                System.out.println(this.name + " was moved from hand to number " + (slotNum + 1) + " slot in the monster field "+ this.manaCost + " MP was used.");
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
                + "MP cost : " + manaCost + "\n" + "Card Type : " + type + "\n"+ "Card Tribe : " + tribe + "\n" + "Is Defensive : "
                + (monsterCardSpeciality == MonsterCardSpeciality.Taunt) + "\n" + "Is Nimble : " + (monsterCardSpeciality == MonsterCardSpeciality.Charge)
                + "\n" + "Spell Details : " + "\n" + magics.get(0).getmagicDetails() + "\n" + "BattleCry Details : " + "\n" + battleCry.get(0).getmagicDetails()
                + "\n" + "Will Details : \n" + will.get(0).getmagicDetails();
        return info;
    }
}
