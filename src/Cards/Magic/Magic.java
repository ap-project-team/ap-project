package src.Cards.Magic;

import src.Cards.MonsterCards.MonsterCardsInBattle;
import src.Cards.Spells.Spells;
import src.ToDoPackage.Battler;

public class Magic {
        protected MagicType magicType;
        public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){};
        public void doMagic(Spells spells, Battler currentBattler, Battler enemyBattler){}
        public MagicType getMagicType() {
            return magicType;
        }
}

