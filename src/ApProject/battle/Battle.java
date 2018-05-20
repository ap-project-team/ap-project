package src.ApProject.battle;

import src.ApProject.battle.battler.Battler;

import java.util.Random;


public class Battle {
    final int numberOfCardsInFirstHand = 5;
    int turnNum = 0;


    Battler[] battlers = new Battler[2];
    BattleGround battleGround = new BattleGround();


    public Battle(Battler battler, Battler enemy) {
        battlers[0] = battler;
        battlers[1] = enemy;

        battlers[0].setBattleGround(battleGround);
        battlers[1].setBattleGround(battleGround);
    }

    public String play(){
        int startNumber = Math.abs(new Random().nextInt())%2;
        System.out.println("Battle against "+battlers[1].getName()+" started!");
        System.out.println(battlers[startNumber].getName()+" starts the battle.\n");

        battlers[0].draw(numberOfCardsInFirstHand,"PLAYER");
        battlers[1].draw(numberOfCardsInFirstHand, "ENEMY");

        while (battlers[0].isAlive() && battlers[1].isAlive()){
            System.out.println("");
            battlers[(startNumber+(turnNum++))%2].playOneTurn(turnNum);
        }


        String winner;
        if (battlers[0].isAlive()) winner = "ENEMY";
        else if (battlers[1].isAlive()) winner = "PLAYER";
        else winner = "DRAW";

        return winner;
    }

    public void viewMonsterField() {
       // battleGround.viewMonsterField();
    }

    public void viewSpellField() {
       // battleGround.viewSpellField();
    }

    public void viewGraveyard() {
       // battleGround.viewGraveyard();
    }
}
