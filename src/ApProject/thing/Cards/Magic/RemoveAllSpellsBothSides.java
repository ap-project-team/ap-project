package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;
import src.ApProject.thing.Cards.Spells.Spells;

public class RemoveAllSpellsBothSides extends Magic {
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public RemoveAllSpellsBothSides(String magicDetails){
        this.magicDetails = magicDetails;
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler) {
        for(Spells spell : enemyBattler.getSpellField().getSpells()) {
            if (spell != null) {
                CastSpellEffect.buildCastSpellEffect(currentBattler, spell.getFullImage());
                currentBattler.getSpellField().remove(spell, enemyBattler);
                currentBattler.getHand().add(spell);
            }
        }
        for(Spells spell : enemyBattler.getSpellField().getSpells()) {
            if (spell != null) {
                CastSpellEffect.buildCastSpellEffect(enemyBattler, spell.getFullImage());
                enemyBattler.getSpellField().remove(spell, enemyBattler);
                currentBattler.getHand().add(spell);
            }
        }
    }
}
