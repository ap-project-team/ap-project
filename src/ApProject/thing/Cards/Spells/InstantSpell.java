package src.ApProject.thing.Cards.Spells;

import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.InBattle.NormalMonsterCardsInBattle;
import src.ToDoPackage.Battler;

import java.util.ArrayList;

public class InstantSpell extends Spells{
    private MagicType magicType;
    public InstantSpell(String name, int manaCost, ArrayList<Magic> magics){
        this.name = name;
        this.manaCost = manaCost;
        this.magics.addAll(magics);
        this.spellType = SpellType.Instant;
        this.magicType = magics.get(0).getMagicType();
    }

    public void play(Battler currentBattler, Battler enemyBattler,MonsterCardsInBattle monsterCardsInBattle, Spells spells, Cards cards){
        if(currentBattler.getCurrentMana()>= manaCost  ) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                currentBattler.getHand().remove(this);
            try {
                for (Magic magic : magics) {
                    switch (magic.getMagicType()) {
                        case WITHTARGET:
                            magic.doMagic(monsterCardsInBattle, currentBattler, enemyBattler);
                            break;
                        case WITHOUTTARGET:
                            magic.doMagic(currentBattler, enemyBattler);
                            break;
                        case SELECTCARD:
                            magic.doMagic(cards, currentBattler, enemyBattler);
                            break;
                        case SELECTSPELL:
                            magic.doMagic(spells, currentBattler, enemyBattler);
                            break;
                        case BOTH:
                            magic.doMagic(monsterCardsInBattle, currentBattler, enemyBattler);
                            break;
                    }
                }
            }
            catch (Exception e) {
                System.out.println("That's not a valid Target");
                currentBattler.getHand().add(this);
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() + manaCost);
            }
        } else
            System.out.println("I don't have enough mana.");
    }

    public MagicType getMagicType() {
        return magicType;
    }

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum) {
        System.out.println("That's not how you play this card");
    }
}
