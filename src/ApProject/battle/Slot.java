package src.ApProject.battle;

import src.ApProject.thing.Cards.Card;

public class Slot<T extends Card> {
    String type;
    T thing;

    Slot(String type){
        this.type = type;
    }

    public void setThing(T thing) {
        this.thing = thing;
    }

    public boolean isEmpty() {
        return thing == null;
    }

}
