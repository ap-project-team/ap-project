package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class RemoveMonster extends Magic {
    {
        this.magicType = MagicType.WITHTARGET;
    }
    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        if(monsterCardsInBattle != null)
            enemyBattler.getMonsterField().remove(monsterCardsInBattle);
    }
}
