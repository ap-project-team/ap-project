package src.ApProject.thing.Cards.MonsterCards.OutBattle;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MagicMonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.MonsterCards.Type;
import src.ApProject.thing.Cards.Spells.InstantSpell;

import java.util.ArrayList;

public class MagicMonsterCard extends MonsterCard {

    public MagicMonsterCard(String cardName, int attackPoint, int healthPoint, int manaCost, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, InstantSpell magics){
        this.name = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.type = Type.SpellCaster;
        this.magics = magics;
        this.price = manaCost * 500;
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
            if(currentBattler.getCurrentMana()>= manaCost  ) {
                if (slotNum > -1 &&  slotNum < 5 && currentBattler.getMonsterField().getSlot(slotNum) == null) {
                    currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                    currentBattler.getHand().remove(this);
                    currentBattler.getMonsterField().add(new MagicMonsterCardsInBattle(this.name, this.basicAttackPoint, this.basicHealthPoint, this.monsterCardSpeciality, this.tribe, this.magics,this, currentBattler, enemyBattler), slotNum);
                    System.out.println(this.name + " was moved from hand to number " + (slotNum + 1) + " slot in the monster field "+ this.manaCost + " MP was used.");
                } else {
                    if(slotNum > -1 &&  slotNum < 5)
                        System.out.println("That slot is full.");
                    else
                        System.out.println("Invalid Input");
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
                + "\n" + "Spell Details : " + "\n" + magics.getMagicDetails();
        return info;
    }
}

