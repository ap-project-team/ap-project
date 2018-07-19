package src.ApProject.thing;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.Serializable;

abstract public class Thing implements Serializable{
    public static String thingType;
    protected String name;
    protected int price;
    protected ImageView image;

    {
        try {
            image = new ImageView("./src//source//" + thingType + "//" + name + ".png");
        } catch (Exception e) {} //{ image = new ImageView("./src//source//a.jpeg");}
    }
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
