package src;

import src.ApProject.Game;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.constants.CreatCards;

public class Main {
    public static void main(String[] args) {
        CreatCards.createAllCards();
        ConstantDatas.initData();
        Game game = new Game();
        while (game.mainMenuOrders()) ;
    }
}
