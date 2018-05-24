package src.ApProject.constants;

import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.*;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.GeneralMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.HeroMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MagicMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.NormalMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.Spells.AuraSpell;
import src.ApProject.thing.Cards.Spells.ContinuousSpell;
import src.ApProject.thing.Cards.Spells.InstantSpell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class CreatCards {

    private static Map<String, Card> allCards = new HashMap<>();

    public static void createAllCards(){
        ArrayList<Magic> magics = new ArrayList<>();
        ArrayList<Magic> inverseMagic = new ArrayList<>();
        ArrayList<Magic> battleCry =  new ArrayList<>();
        ArrayList<Magic> will = new ArrayList<>();

        magics.add(new ChangeHPOfPlayerOrMS(-500, "ThrowingKnives : Deal 500 Damage to a selected enemy monster card on the field or to enemy Player"));
        allCards.put("ThrowingKnives",new InstantSpell("ThrowingKnives",3, magics));
        magics.clear();

        magics.add(new ChangeAllHPAndAP(0,0,0,-100, "Poisonous Cauldron : Deal 100 damage to all enemy monster cards and enemy player"));
        magics.add(new ChangePlayerHP(0, -100,""));
        allCards.put("PoisonousCauldron" , new ContinuousSpell("PoisonousCauldron ", 4,magics));
        magics.clear();

        magics.add(new ChangeHPOfPlayerOrMS(500, "First Aid Kit : Increase HP of a selected friendly monster or player by 500"));
        allCards.put("FirstAidKit", new InstantSpell("FirstAidKit", 3, magics));
        magics.clear();

        magics.add(new RemoveMonsterOrSpell("Reaper's Scythe : Send an enemy monster or spell card from field ro graveyard"));
        allCards.put("Reaper'sScythe", new InstantSpell("Reaper'sScythe", 4, magics));
        magics.clear();

        magics.add(new DamageRandomMSOrPlayer(-800,1, "Meteor Shower : Deal 800 to a random enemy monster card on the field or player"));
        allCards.put("MeteorShower", new ContinuousSpell("MeteorShower", 8, magics));
        magics.clear();

        magics.add(new ChangeHPBasedOnTribe(300, 300, 0, Tribe.Elven, "Lunar Blessing : Increase AP and HP of friendly Elven monster cards by 300"));
        magics.add(new ChangeHPBasedOnTribe(-300, -300, 0, Tribe.Elven, "Lunar Blessing : Increase AP and HP of friendly Elven monster cards by 300"));
        allCards.put("LunarBlessing", new AuraSpell("LunarBlessing",6, magics, inverseMagic));
        magics.clear();
        inverseMagic.clear();

        magics.add(new MoveMSToHand("Strategic Retreat : Select and move a friendly monster card from field to hand and draw one card from deck"));
        magics.add(new DrawCard(1,""));
        allCards.put("StrategicRetreat", new InstantSpell("StrategicRetreat", 6, magics));
        magics.clear();

        magics.add(new ChangeHPAndAP(300,0,0,"War Drum : Increase all friendly monster cards' AP by 300"));
        inverseMagic.add(new ChangeHPAndAP(-300,0,0,""));
        allCards.put("WarDrum", new AuraSpell("WarDrum", 6, magics,inverseMagic));
        magics.clear();
        inverseMagic.clear();

        magics.add(new ChangeAllHPAndAP(0,200, 0,0, "Healing Ward : Increase all friendly monster cards' HP by 200"));
        allCards.put("HealingWard", new ContinuousSpell("HealingWard", 5, magics));
        magics.clear();

        magics.add(new ChangePlayerHP(500,-500, "Blood Feast"));
        allCards.put("BloodFeast", new InstantSpell("BloodFeast", 4, magics));
        magics.clear();

        magics.add(new DamageBasedOnTribe(-500,-500, Tribe.Atlantean, "Tsunami : Deal 500 damage to all non-Atlantean monster cards on both sides of the field"));
        allCards.put("Tsunami", new InstantSpell("Tsunami", 6, magics));
        magics.clear();

        magics.add(new ChangeHPAndAP(400,400, 0, "Take All You Can : Increase all friendly normal monster cards' HP and AP by 400"));
        inverseMagic.add(new ChangeHPAndAP(-400,-400, 0, ""));
        allCards.put("TakeAllYouCan", new AuraSpell("TakeAllYouCan", 7, magics,inverseMagic));
        magics.clear();
        inverseMagic.clear();

        magics.add(new RemoveSelectedSpell("ArcaneBolt"));
        magics.add(new ChangePlayerHP(0,-500,""));
        allCards.put("ArcaneBolt", new InstantSpell("ArcaneBoly", 5,magics));
        magics.clear();

        magics.add(new RemoveAllSpellsBothSides("Greater Purge : Remove all spell cards on field from both sides and move them to hand"));
        allCards.put("GreaterPurge", new InstantSpell("GreaterPurge", 7, magics));
        magics.clear();

        magics.add(new RemoveAllSpells("Magic Seal : Remove all enemy spell cards from field and move them to graveyard"));
        allCards.put("MagicSeal", new ContinuousSpell("MagicSeal", 9, magics));
        magics.clear();

        allCards.put("ElvenRanger", new NormalMonsterCard("ElvenRanger", 400, 300,1, MonsterCardSpeciality.Normal, Tribe.Elven));

        allCards.put("ElvenHunter", new NormalMonsterCard("ElvenHunter",600,800,3, MonsterCardSpeciality.Normal, Tribe.Elven));

        allCards.put("ElvenGuardsman", new NormalMonsterCard("ElvenGuardsman", 500,1500,5, MonsterCardSpeciality.Taunt, Tribe.Elven));

        allCards.put("ElvenAssassin", new NormalMonsterCard("ElvenAssassin", 1200, 800, 5, MonsterCardSpeciality.Normal, Tribe.Elven));

        magics.add(new ChangeHPAndAP(300, 500, 0, "Rejuvenation : Increase a selected friendly monster card's HP by 500 and AP by 300"));
        allCards.put("ElvenDruid" , new MagicMonsterCard("ElvenDruid", 600, 1200, 5, MonsterCardSpeciality.Normal, Tribe.Elven, magics));
        magics.clear();

        magics.add(new ChangeAllHPAndAP(0,0,0,400, "Arcane Explosion : Deal 400 damage to all enemy monster cards and remove a random spell card form enemy field and move it to graveyard"));
        magics.add(new RemoveRandomSpell(""));
        allCards.put("ElvenSorceress", new MagicMonsterCard("ElvenSorceress", 1000, 1000, 7, MonsterCardSpeciality.Charge, Tribe.Elven,magics));
        magics.clear();

        battleCry.add(new RemoveAllSpells("Purge : Remove all enemy spell cards on the field and move them to hand"));
        will.add(new ChangeRandomMSBasedOnTribe(600, 800 , Tribe.Elven, "Increase a random friendly Elven monster card on the field's HP by 800 and AP by 600"));
        allCards.put("NobleElf", new GeneralMonsterCard("NobleElf", 2000, 2400, 9, MonsterCardSpeciality.Normal, Tribe.Elven, battleCry, will));
        battleCry.clear();
        will.clear();

        battleCry.add(new RemoveGraveYard(2, "ReviveAllies : move two random cards from your graveyard to hand"));
        magics.add(new ChangeHPOfPlayerOrMS(2500,"DivineBlessing : Increase HP of a friendly monster card or player by 2500"));
        will.add(new ChangeAllHPAndAP(200,500,0,0,"Burst of Light : Increase HP of all friendly monster cards and player by 500 and increase AP of all friendly monster cards by 200"));
        will.add(new ChangePlayerHP(500,0,""));
        allCards.put("Luthien,TheHighPriestess", new HeroMonsterCard("Luthien,TheHighPriestess", 2000, 2500, 9, MonsterCardSpeciality.Normal, Tribe.Elven, magics, battleCry,will));
        magics.clear();
        battleCry.clear();
        will.clear();

        allCards.put("LesserWhelp", new NormalMonsterCard("LesserWhelp", 300, 500,1, MonsterCardSpeciality.Normal, Tribe.DragonBreed));

        allCards.put("Dragonling", new NormalMonsterCard("Dragonling", 700,700, 3, MonsterCardSpeciality.Normal, Tribe.DragonBreed));

        allCards.put("ArmoredDragon", new NormalMonsterCard("ArmoredDragon", 400,2000, 5, MonsterCardSpeciality.Taunt, Tribe.DragonBreed));

        allCards.put("YellowDrake", new NormalMonsterCard("YellowDrake", 1000, 800, 5, MonsterCardSpeciality.Charge, Tribe.DragonBreed));

        allCards.put("GoblinSmuggler", new NormalMonsterCard("GoblinSmuggler", 400, 600,2, MonsterCardSpeciality.Normal, Tribe.Demonic));

        magics.add(new ChangeHPOfPlayerOrMS(400, "Mend : Increase a friendly monster card or player's HP by 400"));
        allCards.put("GoblinShaman", new MagicMonsterCard("GoblinShaman", 700,1000,4, MonsterCardSpeciality.Normal, Tribe.Demonic, magics));
        magics.clear();

    }

    public static Card getCard(String cardName){
        Card card = allCards.get(cardName);
        if(card == null) {
            System.out.println("No such a Card");
            return card;
        }
        else
            return card;
    }
}
