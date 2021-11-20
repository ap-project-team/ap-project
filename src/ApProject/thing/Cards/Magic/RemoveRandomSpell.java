package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.Spells.Spells;

public class RemoveRandomSpell extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public RemoveRandomSpell(String magicDetails){
        this.magicDetails = magicDetails;
    }

    public void doMagic(MonsterCardsInBattle nullTarget, Battler currentBattler, Battler enemyBattler) {
        Spells spell = enemyBattler.getSpellField().getRandomSpell();
        CastSpellEffect.buildCastSpellEffect(enemyBattler, spell.getFullImage());
        enemyBattler.getSpellField().remove(spell, enemyBattler);
    }
}
