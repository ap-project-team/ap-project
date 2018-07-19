package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.Spells.Spells;

import java.io.Serializable;

public class Magic implements Serializable {
        protected MagicType magicType;
        protected String magicDetails;
        public void doMagic(MonsterCardsInBattle monsterCardsInBattle, Battler currentBattler, Battler enemyBattler){}
        public void doMagic(Spells spells, Battler currentBattler, Battler enemyBattler){}
        public void doMagic(Card card, Battler currentBattler, Battler enemyBattler){}
        public void doMagic(Battler currentBattler, Battler enemyBattler){}
        public MagicType getMagicType() {
            return magicType;
        }
        public String getMagicDetails(){
            return magicDetails;
        }
}

