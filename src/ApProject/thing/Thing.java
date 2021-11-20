package src.ApProject.thing;



import javafx.scene.image.*;

import java.io.Serializable;

abstract public class Thing implements Serializable{
    public static String thingType;
    protected String name;
    protected int price;
    protected String imagePath;
    protected static final long serialVersionUID = 100010;
    {
        try {
            imagePath = "./src//source//" + thingType + "//" + name + ".png";
            ImageView image = new ImageView(imagePath);
        } catch (Exception e) {
            imagePath = "./src//source//NoImage.jpg";
        } //{ image = new ImageView("./src//source//a.jpeg");}
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
