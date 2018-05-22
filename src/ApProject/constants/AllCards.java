package src.ApProject.constants;

import src.ApProject.exeptions.CardDoesNotExistExeption;
import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.NormalMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.Tribe;

import static src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality.Normal;

public abstract class AllCards {
    public static Cards buildCard(String name) throws CardDoesNotExistExeption {
        if (name.equals("ElvenRanjer"))
            return (new NormalMonsterCard("ElvenRanjer", 400,
                    300, 1, Normal, Tribe.Elven));

        if (name.equals("ElvenHunter"))
            return (new NormalMonsterCard("ElvenHunter",600,
                    800, 3, Normal, Tribe.Elven));

        if (name.equals("YellowDrake"))
            return (new NormalMonsterCard("YellowDrake",1000,
                    800, 5, Normal, Tribe.DargonBreed));

        if (name.equals("BlueDragon"))
            return (new NormalMonsterCard("BlueDragon",1200,
                    800, 5, Normal, Tribe.Elven));



        //toDo more card
        throw new CardDoesNotExistExeption();
    }

    public static int getPrice(String name){
        try {
            return buildCard(name).getPrice();
        } catch (CardDoesNotExistExeption cardDoesNotExistExeption) {
            cardDoesNotExistExeption.printStackTrace();
        }   return -1;
    }
}
