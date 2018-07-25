package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.Spells.Spells;

public class RemoveMonsterOrSpell extends Magic{
    {
        this.magicType = MagicType.MSorSpell;
    }

    public RemoveMonsterOrSpell(String magicDetails){
        this.magicDetails = magicDetails;
    }

    public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){
        if(monsterCardsInBattle == null){
            Spells spell = enemyBattler.getSpellField().getRandomSpell();
            CastSpellEffect.buildCastSpellEffect(enemyBattler, spell.getFullImage());
            enemyBattler.getSpellField().remove(spell, enemyBattler);
        }
        else{
            CastSpellEffect.buildCastSpellEffect(enemyBattler, monsterCardsInBattle.getFullImage());
            currentBattler.getMonsterField().remove(monsterCardsInBattle, currentBattler);
        }
    }
}
