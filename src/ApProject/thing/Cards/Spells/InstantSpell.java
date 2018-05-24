package src.ApProject.thing.Cards.Spells;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

import java.util.ArrayList;

public class InstantSpell extends Spells{
    private MagicType magicType;
    public InstantSpell(String name, int manaCost, ArrayList<Magic> magics){
        this.name = name;
        this.manaCost = manaCost;
        this.magics.addAll(magics);
        this.spellType = SpellType.Instant;
        this.magicType = magics.get(0).getMagicType();
        this.price = 700 * this.manaCost;
    }

    public void play(Battler currentBattler, Battler enemyBattler, MonsterCardsInBattle monsterCardsInBattle, Spells spells, Card card){
        if(currentBattler.getCurrentMana()>= manaCost  ) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                currentBattler.getHand().remove(this);
            try {
                for (Magic magic : magics) {
                    switch (magic.getMagicType()) {
                        case FriendlyTarget:
                            magic.doMagic(monsterCardsInBattle, currentBattler, enemyBattler);
                            break;
                        case EnemyTarget:
                            magic.doMagic(monsterCardsInBattle, currentBattler, enemyBattler);
                            break;
                        case WITHOUTTARGET:
                            magic.doMagic(currentBattler, enemyBattler);
                            break;
                        case SELECTCARD:
                            magic.doMagic(card, currentBattler, enemyBattler);
                            break;
                        case SELECTSPELL:
                            magic.doMagic(spells, currentBattler, enemyBattler);
                            break;
                        case FriendlyPlayerOrMS:
                            magic.doMagic(monsterCardsInBattle, currentBattler, enemyBattler);
                            break;
                        case EnemyPlayerOrMS:
                            magic.doMagic(monsterCardsInBattle, currentBattler, enemyBattler);
                            break;
                        case MSorSpell:
                            magic.doMagic(monsterCardsInBattle, currentBattler, enemyBattler);
                            break;
                    }
                }
                System.out.println(this.name + " was casted : \n" + magics.get(0).getmagicDetails());
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
