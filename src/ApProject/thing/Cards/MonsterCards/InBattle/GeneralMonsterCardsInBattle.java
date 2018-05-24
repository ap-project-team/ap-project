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
    }

    // TODO: 5/21/2018 should be used in battle because it might have a target
    public void doBattleCry(MonsterCardsInBattle monsterCardsInBattle, Spells spells, Card card){
            try {
                for (Magic magic : battleCry) {
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
            }
            catch (Exception e) {
                System.out.println("That's not a valid Target");
            }
    }

    public void doWill(MonsterCardsInBattle monsterCardsInBattle, Spells spells, Card card){
        try {
            for (Magic magic : will) {
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
