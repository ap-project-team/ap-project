package src.ApProject.battle;

import src.ApProject.battle.battler.Battler;

import java.util.Random;


public class Battle {
    final int numberOfCardsInFirstHand = 5;
    int turnNum = 0;


    Battler[] battlers = new Battler[2];

    public Battle(Battler battler, Battler enemy) {
        battlers[0] = battler;
        battlers[0].setEnemy(enemy);
        battlers[1] = enemy;
        battlers[1].setEnemy(battler);
    }

    public String play(){
        int startNumber = Math.abs(new Random().nextInt())%2;
        System.out.println("Battle against "+ battlers[1].getName()+" started!");
        System.out.println(battlers[startNumber].getName()+" starts the battle.\n");

        battlers[0].drawCard(numberOfCardsInFirstHand);
        battlers[1].drawCard(numberOfCardsInFirstHand);

        while (battlers[0].isAlive() && battlers[1].isAlive()){
            System.out.println("");
            battlers[(startNumber+(turnNum++))%2].playOneTurn(turnNum);
        }

        System.out.println("GAME OVER");

        String winner = "Draw";
        if (battlers[0].isAlive()) winner = "ENEMY";
        else if (battlers[1].isAlive()) winner = "PLAYER";

        System.out.println(winner+"Wins");

        return winner;
    }
}
