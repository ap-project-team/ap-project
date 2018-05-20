package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.Cards.MonsterCards.Type;
import src.ToDoPackage.Battler;

public class RemoveAllBasedOnType extends Magic{
    private Type type;
    RemoveAllBasedOnType(Type type){
        this.type = type;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(MonsterCardsInBattle nullTarget, Battler currentBattler, Battler enemyBattler) {
        if(nullTarget == null) {
            for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
                if (monsterCardsInBattle.getType() != type)
                    currentBattler.getMonsterField().remove(monsterCardsInBattle);
            }
            for (MonsterCardsInBattle monsterCardsInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
                if (monsterCardsInBattle.getType() != type)
                    currentBattler.getMonsterField().remove(monsterCardsInBattle);
            }
        }
    }
}
