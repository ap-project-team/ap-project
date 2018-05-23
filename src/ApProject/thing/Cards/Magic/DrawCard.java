package src.ApProject.thing.Cards.Magic;

import src.ApProject.battle.battler.Battler;

public class DrawCard extends Magic{
    private int cardsCount;
    public DrawCard(int cardsCount){
        this.cardsCount = cardsCount;
        this.magicType = MagicType.WITHOUTTARGET;
    }
    public void doMagic(Battler currentBattler, Battler enemyBattler){
        currentBattler.drawCard(cardsCount);
    }
}
