package src.Cards.MonsterCards.OutBattle;

import src.Cards.Magic.Magic;
import src.Cards.MonsterCards.InBattle.GeneralMonsterCardsInBattle;
import src.Cards.MonsterCards.InBattle.HeroMonsterCardsInBattle;
import src.Cards.MonsterCards.MonsterCardSpeciality;
import src.Cards.MonsterCards.Tribe;
import src.Cards.MonsterCards.Type;
import src.ToDoPackage.Battler;

import java.util.ArrayList;

public class HeroMonsterCards extends MonsterCards{
    public HeroMonsterCards(String cardName, int attackPoint, int healthPoint, int manaCost, int price, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, ArrayList<Magic> magics, ArrayList<Magic> battleCry, ArrayList<Magic> will){
        this.cardName = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.price = price;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.type = Type.SpellCaster;
        this.battleCry.addAll(battleCry);
        this.will.addAll(will);
        this.magics.addAll(magics);
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
        if(currentBattler.getCurrentMana()>= manaCost  ) {
            if (currentBattler.getMonsterField().getSlot(slotNum).isEmpty()) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                currentBattler.getHand().remove(this);
                currentBattler.getMonsterField().add(new HeroMonsterCardsInBattle(this.cardName, this.basicAttackPoint, this.basicHealthPoint, this.monsterCardSpeciality, this.tribe, this.magics,this.battleCry,this.will,this ,currentBattler, enemyBattler), slotNum);
            } else {
                System.out.println("That slot is full.");
            }
        }
        else {
            System.out.println("I don't have enough mana.");
        }
    }
}
