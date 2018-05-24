package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;

public class RemoveRandomSpell extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public RemoveRandomSpell(String magicDetails){
        this.magicDetails = magicDetails;
    }

    public void doMagic(MonsterCardsInBattle nullTarget, Battler currentBattler, Battler enemyBattler) {
        enemyBattler.getSpellField().remove(enemyBattler.getSpellField().getRandomSpell(), enemyBattler);
    }
}
