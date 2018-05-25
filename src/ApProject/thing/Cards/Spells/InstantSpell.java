package src.ApProject.thing.Cards.Spells;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

import java.util.ArrayList;
import java.util.Map;

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

    public void play(Battler currentBattler, Battler enemyBattler, int slotNum){
        if(currentBattler.getCurrentMana()>= manaCost  ) {
                currentBattler.setCurrentMana(currentBattler.getCurrentMana() - manaCost);
                currentBattler.getHand().remove(this);
                this.currentBattler = currentBattler;
                this.enemyBattler = enemyBattler;
                ArrayList<Map> map = currentBattler.getMonsterField().printingTargets( currentBattler, enemyBattler, magicType);
                while (currentBattler.getSpellField().instantSpellOrders(currentBattler,this, map.get(0), map.get(1), map.get(2)));
        } else
            System.out.println("I don't have enough mana.");
    }
    
    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Spells spells, Card card){
        try {
            for (Magic magic : magics) {
                switch (magic.getMagicType()) {
                    case WITHOUTTARGET:
                        magic.doMagic(currentBattler, enemyBattler);
                        break;
                    case SELECTCARD:
                        magic.doMagic(card, currentBattler, enemyBattler);
                        break;
                    case SELECTSPELL:
                        magic.doMagic(spells, currentBattler, enemyBattler);
                        break;
                    default:
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
    }

    public MagicType getMagicType() {
        return magicType;
    }
    
}
