package src.ApProject.constants;

import src.ApProject.thing.Amulet;
import src.ApProject.thing.Item;
import src.ApProject.thing.Thing;

import java.util.ArrayList;


public abstract class ConstantDatas {
    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Amulet> amulets = new ArrayList<>();

    public static final int STARTING_GIL = 10000;
    public static final int MAX_CARD_IN_HAND = 10;
    public final static int SIZE_OF_DECK = 30;
    public final static int SIZE_OF_MONSTERFIELD = 5;
    public final static int SIZE_OF_SpellField = 3;
    public final static int SIZE_OF_HAND = 10;
}
