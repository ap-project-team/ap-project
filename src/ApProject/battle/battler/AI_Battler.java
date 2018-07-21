package src.ApProject.battle.battler;


import javafx.scene.layout.VBox;
import src.ApProject.constants.ConstantDatas;
import src.ApProject.graphics.AttackMove;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.InBattle.MonsterCardsInBattle;
import src.ApProject.thing.Cards.MonsterCards.OutBattle.MonsterCard;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class AI_Battler extends Battler {
    public AI_Battler(String name, Card[] cards) {
        super(name, cards);
        type = "ENEMY";
    }


    @Override
    protected boolean turnOrders() {
        System.out.println("Enemy Moves :");
        useMonsterCard();
        setCard();
        battle.update();
        return false;
    }

    void setCard() {
        hand.shuffleHand();
        for (int i = 0; i < hand.size(); i++)
            if (hand.get(i).getManaCost() <= getCurrentMana()) {
                if (hand.get(i).getCardType().equals("MONSTERCARD")) {
                    for (int j = 0; j < ConstantDatas.SIZE_OF_MONSTERFIELD; j++) {
                        if (monsterField.getSlot(j) == null) {
                            hand.get(i).play(this, enemy, j);
                            break;
                        }
                    }
                }
            }
        if (monsterField.numberOfTaunts() != 0)
            System.out.println(monsterField.getRandomTaunt().getUseInfo());
    }

    void useMonsterCard() {
        ArrayList<MonsterCardsInBattle> enemyMonsterField = new ArrayList<>();
        ArrayList<MonsterCardsInBattle> AI_BattlerMonsterField = new ArrayList<>();

        for (int i = 0; i<ConstantDatas.SIZE_OF_MONSTERFIELD; i++)
            if (enemy.monsterField.getSlot(i) != null)
                enemyMonsterField.add(enemy.monsterField.getSlot(i));

        for (int i = 0; i<ConstantDatas.SIZE_OF_MONSTERFIELD; i++){
            if (monsterField.getSlot(i) != null) {
                if (monsterField.getSlot(i).canAttack()) {
                    int rand = new Random().nextInt(enemyMonsterField.size()+1);
                    if (rand == enemyMonsterField.size()) {
                        System.out.println(monsterField.getSlot(i).getCardName() + " clashed with " + enemy.getName());
                        monsterField.getSlot(i).attack();
                    } else {
                        System.out.println(monsterField.getSlot(i).getCardName() + " clashed with "
                                + enemyMonsterField.get(rand).getCardName());
                        AttackMove.buildAttackMove(battle.getRoot(), this, monsterField.getSlot(i).getFullImage(),
                                enemyMonsterField.get(rand).getFullImage());
                        monsterField.getSlot(i).attack(enemyMonsterField.get(rand));
                    }
                }
            }
            enemyMonsterField.clear();
        }
        for (int i = 0; i<monsterField.getNumberOfFullSlots(); i++) {
            if (monsterField.getSlot(i) != null && monsterField.getSlot(i).getMagicType() != MagicType.NONE  && !monsterField.getSlot(i).isMagicUsed() && !monsterField.getSlot(i).isSleep()) {
                ArrayList<Map> map = this.getMonsterField().printingTargets(this, enemy, monsterField.getSlot(i).getMagicType());
                while (this.getSpellField().instantSpellOrders(this, enemy, monsterField.getSlot(i).getMagics(), map.get(0), map.get(1), map.get(2))) ;
            }
        }
    }

    public void updatePlayField(VBox vBox) {
        spellField.update(vBox);
        monsterField.update(vBox);
    }
}
