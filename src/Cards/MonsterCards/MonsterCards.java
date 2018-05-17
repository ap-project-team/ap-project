package src.Cards.MonsterCards;

import src.Cards.Cards;
import src.Cards.MonsterCards.MonsterCardMagic.MonsterCardMagic;
import src.ToDoPackage.Battler;

abstract public class MonsterCards extends Cards {
    protected int basicHealthPoint;
    protected int basicAttackPoint;
    protected int manaCost;
    protected int price;
    protected String cardName;
    protected MonsterCardMagic monsterCardMagic;
    protected MonsterCardBattleCry monsterCardBattleCry;
    protected MonsterCardDeathRattle monsterCardDeathRattle;
    protected  MonsterCardSpeciality monsterCardSpeciality;
}
