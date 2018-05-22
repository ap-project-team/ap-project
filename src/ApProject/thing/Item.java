package src.ApProject.thing;

public class Item extends Thing{
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        thingType = "ITEM";
    }
}
