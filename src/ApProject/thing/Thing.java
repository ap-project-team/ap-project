package src.ApProject.thing;

abstract public class Thing {
    String name;
    static String type;
    int price;

    Thing(String name, int price){
        this.name = name;
        this.price = price;
    }

    public static String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
