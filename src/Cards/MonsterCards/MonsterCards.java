package src.Cards.MonsterCards;

import src.Cards.Cards;
import src.Cards.Magic.Magic;

abstract public class MonsterCards extends Cards {
    protected int basicHealthPoint;
    protected int basicAttackPoint;
    protected int manaCost;
    protected Tribe tribe;
    protected int price;
    protected String cardName;
    protected Magic magic;
    protected Type type;
    protected MonsterCardBattleCry monsterCardBattleCry;
    protected MonsterCardDeathRattle monsterCardDeathRattle;
    protected  MonsterCardSpeciality monsterCardSpeciality;
}
