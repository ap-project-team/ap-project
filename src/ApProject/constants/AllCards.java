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
    static private Map<String, Card> allCards = new HashMap<>();

    public static void createAllCards(){
        ArrayList<Magic> magics = new ArrayList<>();
        ArrayList<Magic> battleCry =  new ArrayList<>();
        ArrayList<Magic> will = new ArrayList<>();

        magics.add(new ChangeHPOfPlayerOrMS(-500, "ThrowingKnives : Deal 500 Damage to a selected enemy monster card on the field or to enemy Player"));
        allCards.put("ThrowingKnives",new InstantSpell("ThrowingKnives",3, magics));
        magics.removeAll(magics);

        magics.add(new ChangeAllHPAndAP(0,0,0,-100, "Poisonous Cauldron : Deal 100 damage to all enemy monster cards and enemy player"));
        magics.add(new ChangePlayerHP(0, -100,""));
        allCards.put("PoisonousCauldron" , new ContinuousSpell("PoisonousCauldron ", 4,magics));
        magics.removeAll(magics);

        allCards.put("ElvenRanger", new NormalMonsterCard("ElvenRanger", 400, 300,1, MonsterCardSpeciality.Normal, Tribe.Elven));

        allCards.put("ElvenHunter", new NormalMonsterCard("ElvenHunter",600,800,3, MonsterCardSpeciality.Normal, Tribe.Elven));

        allCards.put("ElvenGuardsman", new NormalMonsterCard("ElvenGuardsman", 500,1500,5, MonsterCardSpeciality.Taunt, Tribe.Elven));

        allCards.put("ElvenAssassin", new NormalMonsterCard("ElvenAssassin", 1200, 800, 5, MonsterCardSpeciality.Normal, Tribe.Elven));

        magics.add(new ChangeHPAndAP(300, 500, 0, "Rejuvenation : Increase a selected friendly monster card's HP by 500 and AP by 300"));
        allCards.put("ElvenDruid" , new MagicMonsterCard("ElvenDruid", 600, 1200, 5, MonsterCardSpeciality.Normal, Tribe.Elven, magics));
        magics.removeAll(magics);

        battleCry.add(new RemoveAllSpells("Purge : Remove all enemy spell cards on the field and move them to hand"));
        will.add(new ChangeRandomMSBasedOnTribe(600, 800 , Tribe.Elven, "Increase a random friendly Elven monster card on the field's HP by 800 and AP by 600"));
        allCards.put("NobleElf", new GeneralMonsterCard("Noble Elf", 2000, 2400, 9, MonsterCardSpeciality.Normal, Tribe.Elven, battleCry, will));
        battleCry.removeAll(battleCry);
        will.removeAll(will);

        battleCry.add(new RemoveGraveYard(2, "ReviveAllies : move two random cards from your graveyard to hand"));
        magics.add(new ChangeHPOfPlayerOrMS(2500,"DivineBlessing : Increase HP of a friendly monster card or player by 2500"));
        will.add(new ChangeAllHPAndAP(200,500,0,0,"Burst of Light : Increase HP of all friendly monster cards and player by 500 and increase AP of all friendly monster cards by 200"));
        will.add(new ChangePlayerHP(500,0,""));
        allCards.put("Luthien,TheHighPriestess", new HeroMonsterCard("Luthien,TheHighPriestess", 2000, 2500, 9, MonsterCardSpeciality.Normal, Tribe.Elven, magics, battleCry,will));
        magics.removeAll(magics);
        battleCry.removeAll(battleCry);
        will.removeAll(will);
    }

    public static Card buildCard(String cardName){
        return allCards.get(cardName);
    }

    public static int getPrice(String name) {
        return buildCard(name).getPrice();
    }
}
