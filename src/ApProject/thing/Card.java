package src.ApProject.thing;

public class Card extends Thing{
    public Card(String name, int price) {
        super(name, price);
        type = "CARD";
    }

    public String getName() {
        return name;
    }
}
