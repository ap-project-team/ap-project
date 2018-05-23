package src.ApProject.thing.Cards.MonsterCards.OutBattle;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MagicMonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.MonsterCards.Type;

import java.util.ArrayList;

public class MagicMonsterCard extends MonsterCard {

    public MagicMonsterCard(String cardName, int attackPoint, int healthPoint, int manaCost, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, ArrayList<Magic> magics){
        name = cardName;
        this.basicAttackPoint = attackPoint;
        this.basicHealthPoint = healthPoint;
        this.manaCost = manaCost;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.type = Type.SpellCaster;
        this.magics.addAll(magics);
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
            if(currentBattler.getCurrentMana()>= manaCost  ) {
                if (currentBattler.getMonsterField().getSlot(slotNum) == null) {
                    currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                    currentBattler.getHand().remove(this);
                    currentBattler.getMonsterField().add(new MagicMonsterCardsInBattle(this.name, this.basicAttackPoint, this.basicHealthPoint, this.monsterCardSpeciality, this.tribe, this.magics,this, currentBattler, enemyBattler), slotNum);
                } else {
                    System.out.println("That slot is full.");
                }
            }
            else {
                System.out.println("I don't have enough mana.");
            }
    }
}

