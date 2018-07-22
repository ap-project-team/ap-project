package src.ApProject.constants;

import src.ApProject.battle.battler.AI_Battler;
import src.ApProject.exeptions.CardDoesNotExistExeption;
import src.ApProject.thing.Cards.Card;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AI_BattlerBuilder {

    static public AI_Battler build(int level) {
        try {
                FileInputStream fileIn = new FileInputStream(".\\src\\Resource\\0\\EnemyDecks\\enemyCards" + level + ".txt");
                Scanner scanner = new Scanner(fileIn);
                ArrayList<Card> cardArrayList = new ArrayList<>();
                String enemyName = scanner.nextLine();
                while (scanner.hasNext()) {
                    String cardName = scanner.next();
                    int count = scanner.nextInt();
                    for (int i = 0; i < count; i++) {
                        cardArrayList.add(CreatCards.getCard(cardName));
                    }
                }
                return new AI_Battler(enemyName, cardArrayList.toArray(new Card[0]));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
