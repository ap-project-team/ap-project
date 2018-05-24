package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

public class RemoveMonster extends Magic {
    {
        this.magicType = MagicType.EnemyTarget;
    }

    public RemoveMonster(String magicDetails){
        this.magicDetails = magicDetails;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        enemyBattler.getMonsterField().remove(monsterCardsInBattle);
    }
}
