package src.ApProject.thing.Cards.MonsterCards.InBattle;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.Spells.AuraSpell;
import src.ApProject.thing.Cards.Spells.InstantSpell;
import src.ApProject.thing.Cards.Spells.SpellType;
import src.ApProject.thing.Cards.Spells.Spells;

import java.util.ArrayList;
import java.util.Map;

public class GeneralMonsterCardsInBattle extends MonsterCardsInBattle {
    public GeneralMonsterCardsInBattle(String cardName, int attackPoint, int healthPoint, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, InstantSpell battleCry, InstantSpell will, Card card, Battler currentBattler, Battler enemyBattler){
        this.cardName = cardName;
        this.basicHealthPoint = healthPoint;
        this.currentAttackPoint = attackPoint;
        this.currentHealthPoint = healthPoint;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.card = card;
        this.battleCry = battleCry;
        this.will = will;
        this.currentBattler = currentBattler;
        this.enemyBattler = enemyBattler;
        this.isBattleCrier = true;
        this.battleCryType = battleCry.getMagicType();
        if(this.monsterCardSpeciality == MonsterCardSpeciality.Charge){
            canAttack = true;
            isSleep = false;
        }
        for(Spells spell : currentBattler.getSpellField().getSpells()){
            if(spell != null)
                if(spell.getSpellType() == SpellType.Aura)
                    this.addAuraEffect((AuraSpell) spell);
        }
        this.battleCryDetail = battleCry.getMagicDetails();
        this.willDetail = will.getMagicDetails();
        ArrayList<Map> map = currentBattler.getMonsterField().printingTargets(this.battleCry, currentBattler, enemyBattler, battleCryType);
        setImage();
//        while (currentBattler.getSpellField().instantSpellOrders(currentBattler, enemyBattler, battleCry, map.get(0), map.get(1), map.get(2)));
    }

    public void checkDeath() {
        if(this.currentHealthPoint <= 0) {
            currentBattler.getMonsterField().remove(this, currentBattler);
            currentBattler.getBattle().updateEvent(this.getCardName() + " has been killed!");
            ArrayList<Map> map = currentBattler.getMonsterField().printingTargets
                    (will, currentBattler, currentBattler.getEnemy(), getMagicType());
//            currentBattler.getSpellField().instantSpellOrders(currentBattler, enemyBattler,this.will, null, null, null);
        }
    }
}
