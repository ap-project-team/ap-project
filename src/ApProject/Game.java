package src.ApProject;

import src.ApProject.battle.Battle;
import src.ApProject.battle.battler.AI_Battler;
import src.ApProject.constants.AI_BattlerBuilder;
import src.ApProject.player.Player;
import src.ApProject.shop.Shop;

import java.util.Scanner;

public class Game {
    public static String give(){
        Scanner sc = new Scanner(System.in);
        String order = sc.nextLine();
        return order;
    }

    Player p = new Player();
    Shop S = new Shop();
    public Game(){
        System.out.println("WELCOME");
    }

    public boolean mainMenuOrders(){
        System.out.println("1. Enter Shop: To enter shop and buy or sell cards and items \n" +
                "2. Edit Inventory: To edit your amulet or deck\n" +
                "3. Next: To go to deck and amulet customization");
        String order = give();
        if (order.matches("Enter Shop\\s*")) {
            System.out.println("Welcom To Shop :");
            while (S.shopOrders(p)) ;
        } else if(order.matches("Edit Inventory\\s*")) {
            p.editInventory() ;
        } else if(order.matches("Next\\s*")) {
            if (p.isReadyForBattle()) {
                Battle battle1 = new Battle(p.becomeBattler(), AI_BattlerBuilder.FirstAI());
                String result = battle1.play();
                System.out.println(result);
            } else System.out.println("Your deck is not full.");
        } else if(order.matches("Help\\s*")) {
            System.out.println("1. Enter Shop: To enter shop and buy or sell cards and items \n" +
                    "2. Edit Inventory: To edit your amulet or deck\n" +
                    "3. Next: To go to deck and amulet customization");
        } else if(order.matches("Exit\\s*")) return false;
        else System.out.println("Incorrect order!");
        return true;
    }

}
