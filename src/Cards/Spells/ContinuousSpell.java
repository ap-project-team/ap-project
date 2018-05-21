package src.Cards.Spells;

import src.Cards.Magic.Magic;
import src.Cards.MonsterCards.InBattle.NormalMonsterCardsInBattle;
import src.ToDoPackage.Battler;

import java.util.ArrayList;

public class ContinuousSpell extends Spells{
    public ContinuousSpell(String name, int manaCost, ArrayList<Magic> magics){
        this.spellName = name;
        this.manaCost = manaCost;
        this.magics.addAll(magics);
        this.spellType = SpellType.Continuous;
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
        if(currentBattler.getCurrentMana()>= manaCost  ) {
            if (currentBattler.getMonsterField().getSlot(slotNum).isEmpty()) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                this.currentBattler = currentBattler;
                this.enemyBattler = enemyBattler;
                currentBattler.getSpellField().add(this, slotNum);
                currentBattler.getHand().remove(this);
            } else {
                System.out.println("That slot is full.");
            }
        }
        else {
            System.out.println("I don't have enough mana.");
        }
    }

    // TODO: 5/21/2018  should be called at start of a player's turn
    public void doMagic(){
            try {
                for (Magic magic : magics) {
                    magic.doMagic(currentBattler, enemyBattler);
                }
            }
            catch (Exception e) {
                System.out.println("Continuous Magic Failed");
            } 
    }
}
