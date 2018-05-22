package src.ApProject.thing.Cards.MonsterCards.InBattle;

import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.Spells.Spells;
import src.ToDoPackage.Battler;

import java.util.ArrayList;

public class MagicMonsterCardsInBattle extends MonsterCardsInBattle {
    private boolean isMagicUsed = false;
    public MagicMonsterCardsInBattle(String cardName, int attackPoint, int healthPoint, MonsterCardSpeciality monsterCardSpeciality, Tribe tribe, ArrayList<Magic> magics, Cards card, Battler currentBattler, Battler enemyBattler){
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
        if(this.monsterCardSpeciality == MonsterCardSpeciality.Charge){
            canAttack = true;
            isSleep = false;
        }
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Spells spells, Cards cards){
        if(!isMagicUsed) {
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
                isMagicUsed = true;
            }
            catch (Exception e) {
                System.out.println("That's not a valid Target");
                isMagicUsed = false;
            }
        }
    }
}

