package src;

import src.ApProject.Game;
import src.ApProject.constants.AllCards;
import src.ApProject.constants.ConstantDatas;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AllCards.createAllCards();
        ConstantDatas.initData();
        Game game = new Game();
        while (game.mainMenuOrders()) ;
    }
}
