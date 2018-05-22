package src.ApProject.battle;

import src.ApProject.thing.Cards.Cards;

public class Slot<T extends Cards> {
    String type;
    T thing;

    Slot(String type){
        this.type = type;
    }

    public void setThing(T thing) {
        this.thing = thing;
    }

    boolean isEmpety(){
        return thing == null;
    }

}
