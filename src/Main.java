package src;

import src.ApProject.Game;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.constants.CreatCards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        ArrayList<String> addAll = new ArrayList<>();
        CreatCards.createAllCards();

        System.out.println("'WELCOME TO THIS GAME'");
        System.out.print("Please Enter Your Name : ");

        Game game = new Game(Game.give());

        while (game.mainMenuOrders()) ;
    }
}
