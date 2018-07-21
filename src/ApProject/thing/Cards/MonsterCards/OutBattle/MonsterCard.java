package src.ApProject.thing.Cards.MonsterCards.OutBattle;

import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.MonsterCards.Type;
import src.ApProject.thing.Cards.Spells.InstantSpell;

import java.io.Serializable;
import java.util.ArrayList;

abstract public class MonsterCard extends Card {
    protected int basicHealthPoint;
    protected int basicAttackPoint;
    protected Tribe tribe;
    protected Type type;
    protected InstantSpell battleCry;
    protected InstantSpell will;
    protected InstantSpell magics;
    protected MonsterCardSpeciality monsterCardSpeciality;

    {
        cardType = "MONSTERCARD";
    }

    public void setBattleCry(InstantSpell battleCry) {
        this.battleCry = battleCry;
    }

    public void setWill(InstantSpell will) {
        this.will = will;
    }

    public void setMagics(InstantSpell magics) {
        this.magics = magics;
    }

    public Type getType() {
        return type;
    }
}
