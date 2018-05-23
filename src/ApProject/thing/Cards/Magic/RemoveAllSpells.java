package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.Spells.Spells;
import src.ToDoPackage.Battler;

public class RemoveAllSpells extends Magic{
    {
        this.magicType = MagicType.WITHOUTTARGET;
    }
    RemoveAllSpells(String magicDetails){
        this.magicDetails = magicDetails;
    }
    public void doMagic(Battler currentBattler, Battler enemyBattler) {
        for(Spells spell : enemyBattler.getSpellField().getSpells()){
            enemyBattler.getSpellField().remove(spell);
            currentBattler.getHand().add(spell);
        }
    }
}
