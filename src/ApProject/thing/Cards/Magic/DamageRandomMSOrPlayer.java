package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

import java.util.Random;

public class DamageRandomMSOrPlayer extends Magic{
    private int changeHPAmount;
    private int type;

    public DamageRandomMSOrPlayer(int changeHPAmount, int type, String magicDetails){
        this.changeHPAmount = changeHPAmount;
        this.magicDetails = magicDetails;
        this.type = type;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler){
        Random random = new Random();
        int randNum = random.nextInt(enemyBattler.getMonsterField().getNumberOfFullSlots() + 1) + 1;
        if(randNum == 1 || enemyBattler.getMonsterField().isEmpty()) {
            if (type == 0) {
                currentBattler.changeHealthPoint(changeHPAmount);
                CastSpellEffect.buildCastSpellEffect(currentBattler, currentBattler.getBattlerCard());
            } else {
                enemyBattler.changeHealthPoint(changeHPAmount);
                CastSpellEffect.buildCastSpellEffect(enemyBattler, enemyBattler.getBattlerCard());
            }
        }
        else {
            MonsterCardsInBattle monsterCardsInBattle = enemyBattler.getMonsterField().getRandomMonsterCardInBattle();
            monsterCardsInBattle.changeHealthPoint(changeHPAmount);
            CastSpellEffect.buildCastSpellEffect(enemyBattler, monsterCardsInBattle.getFullImage());
        }
    }
}
