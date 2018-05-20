package src.ApProject.player;

public class InventoryThing {
    @Override
    public boolean equals(Object obj) {
        return ((String)obj).equals(name);
    }

    int num;
    String name;

    InventoryThing(int num, String name){
        this.num = num;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    void add(int num){
        this.num+=num;
    }

    void remove(int num) {
        this.num-=num;
    }


}
