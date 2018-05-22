package src.ApProject.thing;

abstract public class Thing {
    public static String thingType;
    protected String name;
    protected int price;

   /* public Thing(String name, int price) {
        this.name = name;
        this.price = price;
    }*/


    public static String getThingType() {
        return thingType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
