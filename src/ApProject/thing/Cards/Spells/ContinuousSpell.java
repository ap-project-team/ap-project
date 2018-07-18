package src.ApProject.thing.Cards.Spells;

import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.battle.battler.Battler;

import java.util.ArrayList;

public class ContinuousSpell extends Spells{
    public ContinuousSpell(String name, int manaCost, ArrayList<Magic> magics){
        this.name = name;
        this.manaCost = manaCost;
        this.magics.addAll(magics);
        this.spellType = SpellType.Continuous;
        this.price = 700 * this.manaCost;
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
        if(currentBattler.getCurrentMana()>= manaCost  ) {
            if (slotNum > -1 && slotNum < 3 && currentBattler.getSpellField().getSlot(slotNum) == null) {
                setImage();
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                this.currentBattler = currentBattler;
                this.enemyBattler = enemyBattler;
                currentBattler.getSpellField().add(this, slotNum);
                currentBattler.getHand().remove(this);
                System.out.println(this.name + " was moved from hand to number " + (slotNum + 1) + " slot in the spell field. " + this.manaCost + " MP was used.");
            } else {
                if(slotNum > -1 && slotNum < 3)
                    System.out.println("That slot is full.");
                else
                    System.out.println("Invalid Input");            }
        }
        else {
            System.out.println("I don't have enough mana.");
        }
    }

    public void doMagic(){
            try {
                System.out.println(this.name + " has cast a spell : \n" + magics.get(0).getmagicDetails() + "\n");
                for (Magic magic : magics) {
                    magic.doMagic(currentBattler, enemyBattler);
                }
            }
            catch (Exception e) {
                System.out.println("Continuous Magic Failed");
            } 
    }
}
