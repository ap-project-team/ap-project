package src.ApProject.battle.battler;


import src.ApProject.constants.ConstantDatas;
import src.ApProject.thing.Cards.Card;


public class AI_Battler extends Battler {
    public AI_Battler(String name, Card[] cards) {
        super(name, cards);
        type = "ENEMY";
    }


    @Override
    protected boolean turnOrders() {
        System.out.println("Enemy Moves :");
        setCard();
        return false;
    }

    void setCard() {
        hand.shuffleHand();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getManaCost() <= getCurrentMana()) {
                for (int j = 0; j < ConstantDatas.SIZE_OF_MONSTERFIELD; j++) {
                    if (monsterField.getSlot(j) == null) {
                        hand.get(i).play(this, enemy, j);
                        break;
                    }
                }
            }
        }
    }
}
