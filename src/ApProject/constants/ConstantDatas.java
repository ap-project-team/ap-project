package src.ApProject.constants;

import src.ApProject.thing.Amulet;
import src.ApProject.thing.Cards.Cards;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.NormalMonsterCard;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Item;
import src.ApProject.thing.Thing;

import java.util.ArrayList;

import static src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality.*;

public abstract class ConstantDatas {
    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Amulet> amulets = new ArrayList<>();

    public final static int SIZE_OF_DECK = 15;
    public final static int SIZE_OF_MONSTERFIELD = 5;
    public final static int SIZE_OF_SpellField = 5;

    public static void initData(){
        items.add(new Item("a",100));
        items.add(new Item("b",200));
        items.add(new Item("c",300));
        items.add(new Item("d",400));

        amulets.add(new Amulet("a",100));
        amulets.add(new Amulet("b",200));
        amulets.add(new Amulet("c",300));
        amulets.add(new Amulet("d",400));
    }

    public static ArrayList getList(String type){
        if (type.equals("CARD")) throw new NullPointerException();
        if (type.equals("ITEM")) return items;
        if (type.equals("AMULET")) return amulets;
        return null;
    }

    public static int getPrice(String name, String type){
        if (type.equals("CARD"))
            return AllCards.getPrice(name);
        ArrayList<Thing> list = getList(type);
        for (Thing t : list){
            if (t.getName().equals(name))
                return t.getPrice();
        }
        throw new NullPointerException();
    }

    public static int getSellPrice(String name, String type){
        return getPrice(name,type)/2;
    }

    public static <T extends Thing> T buildThing(String name, String type){
        ArrayList<T> list = ConstantDatas.getList(type);
        if (name.equals("null"))
            return null;
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getName().equals(name))
                return list.get(i);
        }
        throw new NullPointerException();
    }
}
