package src.ApProject.thing.Cards.MonsterCards.OutBattle;

import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.MonsterCards.Type;

import java.util.ArrayList;

abstract public class MonsterCard extends Card {
    protected int basicHealthPoint;
    protected int basicAttackPoint;
    protected Tribe tribe;
    protected Type type;
    protected ArrayList<Magic> battleCry = new ArrayList<>();
    protected ArrayList<Magic> will = new ArrayList<>();
    protected ArrayList<Magic>  magics = new ArrayList<>();
    protected MonsterCardSpeciality monsterCardSpeciality;
    protected String info;

    {
        cardType = "MONSTERCARD";
    }


    public String getInfo(){
        info = "Name : " + name + "\n" + "HP : " + basicHealthPoint + "\n" + "AP : " + basicAttackPoint + "\n"
        + "MP cost : " + manaCost + "\n" + "Card Type : " + type + "\n"+ "Card Tribe : " + tribe + "\n" + "Is Defensive"
        + (monsterCardSpeciality == MonsterCardSpeciality.Taunt) + "\n" + "Is Nimble" + (monsterCardSpeciality == MonsterCardSpeciality.Charge)
        + "\n";
        return info;
    }
}
