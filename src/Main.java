package src;

import src.ApProject.Game;
import src.ApProject.constants.CreatCards;
import src.ApProject.graphic.BattleGraphic;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ArrayList<String> addAll = new ArrayList<>();
        CreatCards.createAllCards();

//        BattleGraphic battleGraphic = new BattleGraphic();
//        battleGraphic.startLaunch();

        System.out.println("'WELCOME TO THIS GAME'");
        System.out.print("Please Enter Your Name : ");

        Game game = new Game(Game.give());

        while (game.mainMenuOrders()) ;
    }
}
