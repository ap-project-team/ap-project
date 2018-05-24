package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

import java.util.SplittableRandom;

public class MoveRandomMSToHand extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }
    public MoveRandomMSToHand(String magicDetails){
        this.magicDetails = magicDetails;
    }
    public void doMagic(Battler currentBattler, Battler enemyBattler) {
            MonsterCardsInBattle monsterCardsInBattle = enemyBattler.getMonsterField().getRandomMonsterCardInBattle();
            enemyBattler.getMonsterField().remove(monsterCardsInBattle);
            currentBattler.getHand().add(monsterCardsInBattle.getCard());
    }
}
