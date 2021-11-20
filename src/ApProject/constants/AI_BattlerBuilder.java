package src.ApProject.constants;

import src.ApProject.battle.battler.AI_Battler;
import src.ApProject.exeptions.CardDoesNotExistExeption;
import src.ApProject.thing.Cards.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AI_BattlerBuilder {
    private static ArrayList<Card> cardArrayList1 = new ArrayList<>();
    private static ArrayList<Card> cardArrayList2 = new ArrayList<>();
    private static String enemyName1;
    private static String enemyName2;
    public static AI_Battler getAIBattler(int level) {
        ArrayList<Card> arrayList = new ArrayList<>();
        String enemyName = "";
        if(level == 1) {
            arrayList = cardArrayList1;
            enemyName = enemyName1;
        }
        if(level == 2) {
            arrayList = cardArrayList2;
            enemyName = enemyName2;
        }
        return new AI_Battler(enemyName, arrayList.toArray(new Card[0]));
    }

    public static void loadAllEnemyDecks(String path){
        cardArrayList1 = new ArrayList<>();
        cardArrayList2 = new ArrayList<>();
        ArrayList<Card> arrayList = new ArrayList<>();
        String enemyName = "";
        for (int level = 1; level < 3; level++) {
            if(level == 1)
                arrayList = cardArrayList1;

            if(level == 2)
                arrayList = cardArrayList2;

            try {
                FileInputStream fileIn = new FileInputStream(path + "\\EnemyDecks\\enemyCards" + level + ".txt");
                Scanner scanner = new Scanner(fileIn);
                enemyName = scanner.nextLine();
                while (scanner.hasNext()) {
                    String cardName = scanner.next();
                    arrayList.add(CreatCards.getCard(cardName));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(level == 1)
                enemyName1 = enemyName;

            if(level == 2)
                enemyName2 = enemyName;
        }
        System.out.println("Finished Loading Enemy Decks");
    }

    public static void saveAllEnemyDeck(String path){
        ArrayList<Card> arrayList = new ArrayList<>();
        String enemyName = "";
        for (int level = 1; level < 3; level++) {
            if(level == 1) {
                arrayList = cardArrayList1;
                enemyName = enemyName1;
            }
            if(level == 2) {
                arrayList = cardArrayList2;
                enemyName = enemyName2;
            }

            try {
                File fileOut = new File(path + "\\EnemyDecks\\enemyCards" + level + ".txt");
                FileWriter fileWriter = new FileWriter(fileOut, false);
                fileWriter.write(enemyName + "\n");
                for(Card card : arrayList){
                    fileWriter.write(card.getName() + "\n");
                }
                fileWriter.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(level == 1)
                enemyName1 = enemyName;
            if(level == 2)
                enemyName2 = enemyName;
        }
        System.out.println("Finished Saving Enemy Decks");
    }
    public static ArrayList<Card> getCardArrayList (int level){
        if(level == 1){
            return cardArrayList1;
        }
        if(level == 2){
            return cardArrayList2;
        }
        return null;
    }

    public static String getEnemyName(int level){
        if(level == 1){
            return enemyName1;
        }
        if(level == 2){
            return enemyName2;
        }
        return null;
    }

    public static void setEnemeyName(int level, String string){
        if(level == 1)
            enemyName1 = string;
        if(level == 2)
            enemyName2 = string;
    }
    public static void remove(int level, Card card){
        if(level == 1)
            cardArrayList1.remove(card);
        if(level == 2)
            cardArrayList2.remove(card);
    }

    public static void add(int level, Card card){
        if(level == 1)
            cardArrayList1.add(card);
        if(level == 2)
            cardArrayList2.add(card);
    }
}
