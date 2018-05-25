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

public class MagicMonsterCardsInBattle extends MonsterCardsInBattle {
    public MagicMonsterCardsInBattle(String cardName, int attackPoint, int healthPoint, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, ArrayList<Magic> magics, Card card, Battler currentBattler, Battler enemyBattler){
        this.cardName = cardName;
        this.basicHealthPoint = healthPoint;
        this.currentAttackPoint = attackPoint;
        this.currentHealthPoint = healthPoint;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.card = card;
        this.magics.addAll(magics);
        this.currentBattler = currentBattler;
        this.enemyBattler = enemyBattler;
        this.magicType = magics.get(0).getMagicType();
        this.magicDetail = magics.get(0).getmagicDetails();
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

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Spells spells, Card card){
        if(!isMagicUsed) {
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
                isMagicUsed = true;
            }
            catch (Exception e) {
                System.out.println("That's not a valid Target");
                isMagicUsed = false;
            }
        }
    }
}

