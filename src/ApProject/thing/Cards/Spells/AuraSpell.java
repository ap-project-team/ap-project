package src.ApProject.thing.Cards.Spells;

import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MonsterCards;
import src.ToDoPackage.Battler;

public class AuraSpell extends Spells{
    AuraSpell(String name){

    }
    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
        if(currentBattler.getCurrentMana()>= manaCost  ) {
            if (currentBattler.getMonsterField().getSlot(slotNum).isEmpty()) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                this.currentBattler = currentBattler;
                this.enemyBattler = enemyBattler;
                currentBattler.getHand().remove(this);
                currentBattler.getSpellField().add(this, slotNum);
                for(MonsterCardsInBattle monsterCardsInBattle: currentBattler.getMonsterField().getMonsterCardsInBattles()){
                    monsterCardsInBattle.addAuraEffect(this);
                }
            } else {
                System.out.println("That slot is full.");
            }
        }
        else {
            System.out.println("I don't have enough mana.");
        }
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle){
        try {
            for (Magic magic : magics) {
                        magic.doMagic(monsterCardsInBattle,currentBattler, enemyBattler);
            }
        }
        catch (Exception e) {
            System.out.println("Aura Spell Not Working");
        }
    }
}
