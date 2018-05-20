package src.ApProject;

import src.ApProject.constants.ConstantDatas;

public class Main {

    public static void main(String[] args) {
        ConstantDatas.initData();
        Game game = new Game() ;
        while(game.mainMenuOrders());
    }
}
