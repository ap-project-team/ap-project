package src;

import src.ApProject.Game;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.GeneralMonsterCards;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MonsterCards;
import src.ToDoPackage.Battler;

public class Main {

    public static void main(String[] args) {
        //
        ConstantDatas.initData();
        Game game = new Game();
        while (game.mainMenuOrders()) ;
    }
}
