package src.ApProject.thing.Cards.MonsterCards.InBattle;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.Spells.AuraSpell;
import src.ApProject.thing.Cards.Spells.SpellType;
import src.ApProject.thing.Cards.Spells.Spells;

import java.util.ArrayList;
import java.util.Map;

public class GeneralMonsterCardsInBattle extends MonsterCardsInBattle {
    public GeneralMonsterCardsInBattle(String cardName, int attackPoint, int healthPoint, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, ArrayList<Magic> battleCry, ArrayList<Magic> will, Card card, Battler currentBattler, Battler enemyBattler){
        this.cardName = cardName;
        this.basicHealthPoint = healthPoint;
        this.currentAttackPoint = attackPoint;
        this.currentHealthPoint = healthPoint;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.card = card;
        this.battleCry.addAll(battleCry);
        this.will.addAll(will);
        this.currentBattler = currentBattler;
        this.enemyBattler = enemyBattler;
        this.isBattleCrier = true;
        this.battleCryType = battleCry.get(0).getMagicType();
        if(this.monsterCardSpeciality == MonsterCardSpeciality.Charge){
            canAttack = true;
            isSleep = false;
        }
        for(Spells spell : currentBattler.getSpellField().getSpells()){
            if(spell != null)
                if(spell.getSpellType() == SpellType.Aura)
                    this.addAuraEffect((AuraSpell) spell);
        }
        this.battleCryDetail = battleCry.get(0).getmagicDetails();
        this.willDetail = will.get(0).getmagicDetails();
        ArrayList<Map> map = currentBattler.getMonsterField().printingTargets( currentBattler, enemyBattler, battleCryType);
        while (currentBattler.getSpellField().battleCryOrders(this, map.get(0), map.get(1), map.get(2)));
    }

    public void doBattleCry(MonsterCardsInBattle monsterCardsInBattle, Spells spells, Card card){
            try {
                for (Magic magic : battleCry) {
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
            }
    }

    public void doWill(MonsterCardsInBattle monsterCardsInBattle, Spells spells, Card card){
        try {
            for (Magic magic : will) {
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
        }
    }

    public void checkDeath() {
        if(this.currentHealthPoint <= 0)
            currentBattler.getMonsterField().remove(this, currentBattler);
        doWill(null,null,null);
    }
}
