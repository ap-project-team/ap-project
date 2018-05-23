package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class RemoveMonster extends Magic {
    {
        this.magicType = MagicType.EnemyTarget;
    }

    RemoveMonster(String magicDetails){
        this.magicDetails = magicDetails;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        enemyBattler.getMonsterField().remove(monsterCardsInBattle);
    }
}
