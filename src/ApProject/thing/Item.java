package src.ApProject.thing;

import src.ApProject.battle.battler.Battler;

public class Item extends Thing{
    int increaseHP;
    int increaseMP;

    public Item(String name, int price, int increaseHP, int increaseMP) {
        this.name = name;
        this.price = price;
        this.increaseHP = increaseHP;
        this.increaseMP = increaseMP;
        thingType = "ITEM";
    }

    public static Item buildItems(String name){
        if (name.equals("SmallHPPotion")) return new Item(name, 1000, 500, 0);
        if (name.equals("MediumHPPotion")) return new Item(name, 2000, 1000, 0);
        if (name.equals("LargeHPPotion")) return new Item(name, 4000, 2000, 0);
        if (name.equals("SmallMPPotion")) return new Item(name, 1000, 0, 2);
        if (name.equals("MediumMPPotion")) return new Item(name, 2000, 0, 4);
        if (name.equals("LargeMPPotion")) return new Item(name, 4000, 0, 8);
        if (name.equals("LesserRestorative")) return new Item(name, 2000, 500, 2);
        if (name.equals("GreaterRestorative")) return new Item(name, 4000, 1000, 4);
        return null;
    }

    public void useItem(Battler battler){
        battler.setCurrentMana(battler.getCurrentMana()+increaseMP);
        battler.changeHealthPoint(increaseHP);
        System.out.println("Item used.");
    }

    public String getInfo(){
        if (increaseMP == 0) return " Increase Player’s HP by "+increaseHP;
        else if (increaseHP == 0) return " Increase Player’s MP by "+increaseMP;
        else return "Increase Player’s HP by "+increaseHP+" and MP by "+increaseMP;
    }

}
