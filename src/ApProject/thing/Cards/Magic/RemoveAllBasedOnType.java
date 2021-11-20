package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.graphics.CastSpellEffect;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.Type;

public class RemoveAllBasedOnType extends Magic{
    private Type type;
    public RemoveAllBasedOnType(Type type, String magicDetails){
        this.type = type;
        this.magicDetails = magicDetails;
        this.magicType = MagicType.WITHOUTTARGET;
    }

    public void doMagic(Battler currentBattler, Battler enemyBattler) {
            for (MonsterCardsInBattle monsterCardsInBattle : currentBattler.getMonsterField().getMonsterCardsInBattles()) {
                if (monsterCardsInBattle != null && monsterCardsInBattle.getType() != type) {
                    currentBattler.getMonsterField().remove(monsterCardsInBattle, currentBattler);
                    CastSpellEffect.buildCastSpellEffect(currentBattler, monsterCardsInBattle.getFullImage());
                }
            }
            for (MonsterCardsInBattle monsterCardsInBattle : enemyBattler.getMonsterField().getMonsterCardsInBattles()) {
                if (monsterCardsInBattle != null && monsterCardsInBattle.getType() != type) {
                    enemyBattler.getMonsterField().remove(monsterCardsInBattle, enemyBattler);
                    CastSpellEffect.buildCastSpellEffect(enemyBattler, monsterCardsInBattle.getFullImage());
                }
            }
    }
}
