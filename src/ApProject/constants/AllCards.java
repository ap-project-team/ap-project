package src.ApProject.constants;

import src.ApProject.exeptions.CardDoesNotExistExeption;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.*;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.GeneralMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.HeroMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MagicMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.NormalMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.Spells.ContinuousSpell;
import src.ApProject.thing.Cards.Spells.InstantSpell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality.Normal;

public abstract class AllCards {
    public static Card buildCard(String cardName){
        return CreatCards.getCard(cardName);
    }

    public static int getPrice(String name) {
        return buildCard(name).getPrice();
    }
}
