package src.Cards.MonsterCards.OutBattle;

import src.Cards.Cards;
import src.Cards.Magic.Magic;
import src.Cards.MonsterCards.MonsterCardSpeciality;
import src.Cards.MonsterCards.Tribe;
import src.Cards.MonsterCards.Type;

import java.util.ArrayList;

abstract public class MonsterCards extends Cards {
    protected int basicHealthPoint;
    protected int basicAttackPoint;
    protected int manaCost;
    protected Tribe tribe;
    protected int price;
    protected String cardName;
    protected Type type;
    protected ArrayList<Magic> battleCry = new ArrayList<>();
    protected ArrayList<Magic> will = new ArrayList<>();
    protected ArrayList<Magic>  magics = new ArrayList<>();
    protected MonsterCardSpeciality monsterCardSpeciality;
    protected String info;
    public String getInfo(){
        info = "Name : " + cardName + "\n" + "HP : " + basicHealthPoint + "\n" + "AP : " + basicAttackPoint + "\n"
        + "MP cost : " + manaCost + "\n" + "Card Type : " + type + "\n"+ "Card Tribe : " + tribe + "\n" + "Is Defensive"
        + (monsterCardSpeciality == MonsterCardSpeciality.Taunt) + "\n" + "Is Nimble" + (monsterCardSpeciality == MonsterCardSpeciality.Charge)
        + "\n";
        return info;
    }
}
