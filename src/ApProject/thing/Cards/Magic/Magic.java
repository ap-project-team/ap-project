package src.ApProject.thing.Cards.Magic;

import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.Spells.Spells;
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

