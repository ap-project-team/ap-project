package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;
import src.ApProject.thing.Cards.Spells.Spells;

public class RemoveSelectedSpell extends Magic{
    {
        this.magicType = MagicType.SELECTSPELL;
    }

    public RemoveSelectedSpell(String magicDetails){
        this.magicDetails = magicDetails;
    }

    public void doMagic(Spells spells, Battler currentBattler, Battler enemyBattler) {
        CastSpellEffect.buildCastSpellEffect(enemyBattler, spells.getFullImage());
        enemyBattler.getSpellField().remove(spells, enemyBattler);
    }
}
