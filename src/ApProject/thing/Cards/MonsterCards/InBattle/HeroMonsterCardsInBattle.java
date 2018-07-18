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

public class HeroMonsterCardsInBattle extends MonsterCardsInBattle{
    public HeroMonsterCardsInBattle(String cardName, int attackPoint, int healthPoint, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, InstantSpell magics, InstantSpell battleCry, InstantSpell will, Card card, Battler currentBattler, Battler enemyBattler){
        this.cardName = cardName;
        this.basicHealthPoint = healthPoint;
        this.currentAttackPoint = attackPoint;
        this.currentHealthPoint = healthPoint;
        this.monsterCardSpeciality = monsterCardSpeciality;
        this.tribe = tribe;
        this.card = card;
        this.magics = magics;
        this.battleCry = battleCry;
        this.will = will;
        this.currentBattler = currentBattler;
        this.enemyBattler = enemyBattler;
        this.isBattleCrier = true;
        this.magicType = magics.getMagicType();
        this.battleCryType = battleCry.getMagicType();
        this.magicDetail = magics.getMagicDetails();
        this.battleCryDetail = battleCry.getMagicDetails();
        this.willDetail = will.getMagicDetails();
        if(this.monsterCardSpeciality == MonsterCardSpeciality.Charge){
            canAttack = true;
            isSleep = false;
        }
        for(Spells spell : currentBattler.getSpellField().getSpells()){
            if(spell != null)
                if(spell.getSpellType() == SpellType.Aura)
                    this.addAuraEffect((AuraSpell) spell);
        }
        ArrayList<Map> map = currentBattler.getMonsterField().printingTargets( currentBattler, enemyBattler, battleCryType);
        while (currentBattler.getSpellField().instantSpellOrders(currentBattler, enemyBattler, battleCry, map.get(0), map.get(1), map.get(2)));
        setImage();

    }
    public void checkDeath() {
        if(this.currentHealthPoint <= 0) {
            currentBattler.getMonsterField().remove(this, currentBattler);
            System.out.println(this.getCardName() + " has been killed!");
            while (currentBattler.getSpellField().instantSpellOrders(currentBattler, enemyBattler,this.will, null, null, null));
        }
    }

}
