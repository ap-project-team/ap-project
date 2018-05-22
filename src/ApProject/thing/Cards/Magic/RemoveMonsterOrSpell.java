package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ToDoPackage.Battler;

public class RemoveMonsterOrSpell extends Magic{
    {
        this.magicType = MagicType.BOTH;
    }
    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        if(monsterCardsInBattle == null){
            enemyBattler.getSpellField().remove(enemyBattler.getSpellField().getRandomSpell());
        }
        else{
            currentBattler.getMonsterField().remove(monsterCardsInBattle);
        }
    }
}
