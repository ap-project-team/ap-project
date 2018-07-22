package src.ApProject.thing;

import javafx.scene.image.*;
import src.ApProject.battle.battler.Battler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Amulet extends Thing{
    private int increaseHP;
    private int increaseMP;
    private boolean attack;
    private static Map<String, Amulet> allAmulets = new HashMap<>();
    public Amulet(String name, int price, int increaseHP, int increaseMP, boolean attack) {
        this.name = name;
        this.price = price;
        this.increaseHP = increaseHP;
        this.increaseMP = increaseMP;
        this.attack = attack;
        thingType = "ITEM";
    }
    static final long serialVersionUID = 10001;

//    public static void buildAllAmulets(){
//        allAmulets.put("IronPendant", new Amulet("IronPendant", 2000, 500, 0, false));
//        allAmulets.put("GoldPendant", new Amulet("GoldPendant", 4000, 1000, 0, false));
//        allAmulets.put("DiamondPendant", new Amulet("DiamondPendant", 8000, 2000, 0, false));
//        allAmulets.put("IronRing", new Amulet("IronRing", 2000, 0, 1, false));
//        allAmulets.put("GoldRing", new Amulet("GoldRing", 4000, 0, 2, false));
//        allAmulets.put("DiamondRing", new Amulet("DiamondRing", 8000, 0, 3, false));
//        allAmulets.put("DemonKing’sCrown", new Amulet("DemonKing’sCrown", 0, 0, 0, true));
//        saveAllAmulets();
//    }
//
//    public static void saveAllAmulets(){
//        for(Amulet amulet : allAmulets.values()){
//            System.out.println(amulet.getInfo());
//            try {
//                FileOutputStream fout = new FileOutputStream(".\\src\\Resource\\0\\allAmulets.ser",true);
//                ObjectOutputStream oos = new ObjectOutputStream(fout);
//                oos.writeObject(amulet);
//                oos.close();
//                fout.close();
//            } catch (FileNotFoundException e) {
//                System.out.println(e.getMessage());
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
    public static Amulet getAmulet(String name){
      return allAmulets.get(name);
    }

    public String getInfo(){
        if (attack) return ("Decrease All Incoming Damages by 20%");
        else if (increaseMP == 0) return " Increase Player’s Max HP by "+increaseHP;
        else if (increaseHP == 0) return " Increase Player’s Max MP by "+increaseMP;
        else return " Increase Player’s Max HP by " + increaseHP + " Increase Player’s Max MP by " + increaseMP;
    }

    public int getIncreaseHP() {
        return increaseHP;
    }

    public int getIncreaseMP() {
        return increaseMP;
    }

    public boolean canAttack() {
        return attack;
    }


    public static void loadAllAmulets(){
        try {
            FileInputStream fileIn = new FileInputStream(".\\src\\Resource\\0\\AllThings\\allAmulets.ser");
            Amulet amulet = null;
            boolean isExist = true;
            while (isExist) {
                if(fileIn.available() != 0) {
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    amulet = (Amulet) objectIn.readObject();
                    allAmulets.put(amulet.getName(), amulet);
                }
                else {
                    isExist = false;
                }
            }
            System.out.println("Finished Loading All Amulets");
            fileIn.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
