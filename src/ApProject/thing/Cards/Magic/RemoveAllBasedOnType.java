package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.Type;

public class RemoveAllBasedOnType extends Magic{
    private Type type;
    RemoveAllBasedOnType(Type type){
        this.type = type;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler) {
            for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
                if (monsterCardsInBattle != null && monsterCardsInBattle.getType() != type)
                    currentBattler.getMonsterField().remove(monsterCardsInBattle);
            }
            for (MonsterCardsInBattle monsterCardsInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
                if (monsterCardsInBattle != null && monsterCardsInBattle.getType() != type)
                    currentBattler.getMonsterField().remove(monsterCardsInBattle);
            }
    }
}
