package src.ApProject.thing.Cards.Spells;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

import java.util.ArrayList;

public class AuraSpell extends Spells {
    int countOnTheBattleField = 0;
    private ArrayList<Magic> inverseMagics = new ArrayList<>();

    public AuraSpell(String name, int manaCost, ArrayList<Magic> magics, ArrayList<Magic> inverseMagics) {
        this.name = name;
        this.spellType = SpellType.Aura;
        this.magics.addAll(magics);
        this.manaCost = manaCost;
        this.inverseMagics.addAll(inverseMagics);
        this.price = 700 * this.manaCost;
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
        if (currentBattler.getCurrentMana() >= manaCost) {
            if (currentBattler.getSpellField().getSlot(slotNum) == null) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                this.currentBattler = currentBattler;
                this.enemyBattler = enemyBattler;
                currentBattler.getHand().remove(this);
                currentBattler.getSpellField().add(this, slotNum);
                countOnTheBattleField++;
                for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
                    if (monsterCardsInBattle != null)
                        monsterCardsInBattle.addAuraEffect(this);
                }
                System.out.println(this.name + " was moved from hand to number " + (slotNum + 1) + " slot in the spell field. " + this.manaCost + " MP was used.");
            } else {
                System.out.println("That slot is full.");
            }
        } else {
            System.out.println("I don't have enough mana.");
        }
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle) {
        try {
            for (Magic magic : magics) {
                magic.doMagic(monsterCardsInBattle, currentBattler, enemyBattler);
            }
        } catch (Exception e) {
            System.out.println("Aura Spell Not Working");
        }
    }

    public void doInverseMagic(MonsterCardsInBattle monsterCardsInBattle) {
        try {
            for (Magic magic : inverseMagics) {
                magic.doMagic(monsterCardsInBattle, currentBattler, enemyBattler);
            }
            monsterCardsInBattle.checkDeath();
        } catch (Exception e) {
            System.out.println("Aura Spell Not Working");
        }
    }

    public int getCountOnTheBattleField() {
        return countOnTheBattleField;
    }
}
