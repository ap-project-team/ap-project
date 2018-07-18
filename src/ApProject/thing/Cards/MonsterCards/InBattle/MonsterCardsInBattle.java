package src.ApProject.thing.Cards.MonsterCards.InBattle;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.Magic;
import src.ApProject.thing.Cards.Magic.MagicType;
import src.ApProject.thing.Cards.MonsterCards.MonsterCardSpeciality;
import src.ApProject.thing.Cards.MonsterCards.Tribe;
import src.ApProject.thing.Cards.MonsterCards.Type;
import src.ApProject.thing.Cards.Spells.AuraSpell;
import src.ApProject.thing.Cards.Spells.InstantSpell;
import src.ApProject.thing.Cards.Spells.Spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MonsterCardsInBattle {
    protected int basicHealthPoint;
    protected int currentHealthPoint;
    protected int currentAttackPoint;
    protected Tribe tribe;
    protected String cardName;
    protected boolean isSleep = true;
    protected boolean canAttack = false;
    protected Card card;
    protected Type type;
    protected boolean isMagicUsed = false;
    protected InstantSpell battleCry;
    protected InstantSpell will;
    protected InstantSpell  magics;
    protected MonsterCardSpeciality monsterCardSpeciality;
    protected Battler currentBattler;
    protected Battler enemyBattler;
    protected Boolean isBattleCrier = false;
    protected MagicType battleCryType = MagicType.NONE;
    protected MagicType magicType = MagicType.NONE;
    protected String magicDetail;
    protected String battleCryDetail;
    protected String willDetail;
    protected ImageView image;


    public InstantSpell getMagics() { return magics; }

    public String getMagicDetail() {
        return magicDetail;
    }

    public String getBattleCryDetail() {
        return battleCryDetail;
    }

    public String getWillDetail() {
        return willDetail;
    }

    public boolean isMagicUsed() {
        return magics.isMagicUsed();
    }

    public boolean isSleep() {
        return isSleep;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public MonsterCardSpeciality getMonsterCardSpeciality() {
        return monsterCardSpeciality;
    }

    public int getCurrentHealthPoint() {
        return currentHealthPoint;
    }

    public int getCurrentAttackPoint() {
        return currentAttackPoint;
    }

    public MagicType getBattleCryType() {
        return battleCryType;
    }

    public MagicType getMagicType() {
        return magicType;
    }

    public void attack(){
        if(canAttack) {
            if(enemyBattler.getType().equals("PLAYER")){
                if(enemyBattler.getAmulet().canAttack()){
                    enemyBattler.changeHealthPoint(-(int)(this.currentAttackPoint * 0.8));
                }
            } else
            enemyBattler.changeHealthPoint(-this.currentAttackPoint);
        }
        canAttack = false;
    }

    public void attack(MonsterCardsInBattle targetCard){
        if(canAttack) {
            if(currentBattler.getType().equals("PLAYER")){
                if(currentBattler.getAmulet().canAttack()) {
                    this.currentHealthPoint -= (int)(targetCard.currentAttackPoint * 0.8);
                    targetCard.currentHealthPoint -= this.currentAttackPoint;
                }
                else {
                    this.currentHealthPoint -= targetCard.currentAttackPoint;
                    targetCard.currentHealthPoint -= this.currentAttackPoint;
                }
            }else {
                if(enemyBattler.getAmulet().canAttack()) {
                    this.currentHealthPoint -= targetCard.currentAttackPoint;
                    targetCard.currentHealthPoint -= (int)(this.currentAttackPoint * 0.8);
                } else {
                    this.currentHealthPoint -= targetCard.currentAttackPoint;
                    targetCard.currentHealthPoint -= this.currentAttackPoint;
                }
            }
            this.checkDeath();
            targetCard.checkDeath();
        }
        canAttack = false;
    }

    public String getCardName() {
        return cardName;
    }

    public void addAuraEffect(AuraSpell auraSpell){ auraSpell.doMagic(this); }

    public void removeAuraEffect(AuraSpell auraSpell){ auraSpell.doInverseMagic(this); }

    public Tribe getTribe() {
        return tribe;
    }

    public Type getType(){
        return this.type;
    }

    public Card getCard() { return card; }

    public void checkDeath(){
        if(this.currentHealthPoint <= 0) {
            currentBattler.getMonsterField().remove(this, currentBattler);
            System.out.println(this.getCardName() + " has been killed!");
        }
    }

    public void changeHealthPoint(int amount){
        this.currentHealthPoint += amount;
        this.basicHealthPoint += amount;
    }

    public void changeAttackPoint(int amount){
        this.currentAttackPoint += amount;
    }

    public void nextTurn(){
        this.isSleep = false;
        this.canAttack = true;
    }

    public String getInfoInMonsterField(){
        return this.cardName + " HP : " + this.currentHealthPoint + " AP : " + this.currentAttackPoint + " " + this.monsterCardSpeciality + " " + ((this.magicType == MagicType.NONE)?"No Spell":isMagicUsed?"UsedSpell":"HasSpell");
    }

    public String getUseInfo(){
        return "Using : " + this.cardName + "\n" + "HP : " + this.currentHealthPoint + " AP : " + this.currentAttackPoint + "\n" + "Is Sleeping : " + isSleep + "\nCan Attack : " + this.canAttack + ((this.magicType == MagicType.NONE)?"":"\nCan Cast : " + (isSleep?"False":isMagicUsed?"False":"True"));
    }

    public StackPane getImage() {
        StackPane fullImage = new StackPane();
        Text t = new Text("HP: "+getCurrentHealthPoint()+"\n"+"AP: "+getCurrentAttackPoint());

        fullImage.getChildren().addAll(image, t);
        return fullImage;
    }

    public void setImage() {
        this.image = new ImageView("./src//source//CARD//"+getCardName()+".png");
        image.setFitWidth(60);
        image.setFitHeight(80);
    }
}
