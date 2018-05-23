package src.ApProject.battle.battler;


import src.ApProject.thing.Cards.Card;


public class AI_Battler extends Battler {
    public AI_Battler(String name, Card[] cards) {
        super(name, cards);
        type = "ENEMY";
    }


    @Override
    protected boolean turnOrders() {
        System.out.println("Enemy played!");
        //while (canPlay()){

        //}
        return false;
    }

}
