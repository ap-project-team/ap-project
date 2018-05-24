package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

public class RemoveMonsterOrSpell extends Magic{
    {
        this.magicType = MagicType.MSorSpell;
    }

    public RemoveMonsterOrSpell(String magicDetails){
        this.magicDetails = magicDetails;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        if(monsterCardsInBattle == null){
            enemyBattler.getSpellField().remove(enemyBattler.getSpellField().getRandomSpell(), enemyBattler);
        }
        else{
            currentBattler.getMonsterField().remove(monsterCardsInBattle, currentBattler);
        }
    }
}
