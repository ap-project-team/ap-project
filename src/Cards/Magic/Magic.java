package src.Cards.Magic;

import src.Cards.Cards;
import src.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.Cards.Spells.Spells;
import src.ToDoPackage.Battler;

public class Magic {
        protected MagicType magicType;
        protected String magicName;
        protected String magicDetails;
        public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){}
        public void doMagic(Spells spells, Battler currentBattler, Battler enemyBattler){}
        public void doMagic(Cards cards, Battler currentBattler, Battler enemyBattler){}
        public void doMagic(Battler currentBattler, Battler enemyBattler){}
        public MagicType getMagicType() {
            return magicType;
        }
        public String getDetails(){
            return magicName + magicDetails;
        }
}

