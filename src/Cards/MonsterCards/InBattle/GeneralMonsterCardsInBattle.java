package src.Cards.MonsterCards.InBattle;

import src.Cards.Cards;
import src.Cards.Magic.Magic;
import src.Cards.MonsterCards.MonsterCardSpeciality;
import src.Cards.MonsterCards.Tribe;
import src.Cards.Spells.Spells;
import src.ToDoPackage.Battler;

import java.util.ArrayList;

public class GeneralMonsterCardsInBattle extends MonsterCardsInBattle {
    public GeneralMonsterCardsInBattle(String cardName, int attackPoint, int healthPoint, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, ArrayList<Magic> battleCry, ArrayList<Magic> will, Cards card, Battler currentBattler, Battler enemyBattler){
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
    }

    // TODO: 5/21/2018 should be used in battle because it might have a target
    public void doBattleCry(MonsterCardsInBattle monsterCardsInBattle, Spells spells, Cards cards){
            try {
                for (Magic magic : battleCry) {
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
            }
    }

    public void doWill(MonsterCardsInBattle monsterCardsInBattle, Spells spells, Cards cards){
        try {
            for (Magic magic : will) {
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
        }
    }

    public void checkDeath() {
        if(this.currentHealthPoint <= 0)
            currentBattler.getMonsterField().remove(this);
        doWill(null,null,null);
    }
}
