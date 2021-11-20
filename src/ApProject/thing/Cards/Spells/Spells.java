package src.ApProject.thing.Cards.Spells;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import src.ApProject.battle.battler.Battler;
import src.ApProject.thing.Cards.Card;
import src.ApProject.thing.Cards.Magic.Magic;

import java.util.ArrayList;

abstract public class Spells extends Card {
    protected SpellType spellType;
    protected Battler currentBattler;
    protected Battler enemyBattler;
    protected ArrayList<Magic> magics = new ArrayList<>();
    abstract public void play(Battler currentBattler, Battler enemyBattler, int slotNum);

    {
        cardType = "SPELLCARD";
    }

    public SpellType getSpellType() {
        return spellType;
    }
    public String getInfo() {
        return this.name +" Info" + "\n" + "Name : " + this.name + "\n" + "MP cost : " + manaCost + "\n" + "Card Type : " + spellType + "\n" +  "Spell Details : "
                + "\n" + magics.get(0).getMagicDetails();
    }

    public String getMagicDetails(){
        return magics.get(0).getMagicDetails();
    }

    public StackPane getFullImage(){
        ImageView image = new ImageView("./src//source//CARD//"+getSpellName()+".png");
        image.setFitWidth(60);
        image.setFitHeight(80);

        StackPane fullImage = new StackPane();
        Text t = new Text(this.getName());

        fullImage.getChildren().addAll(image, t);
        return fullImage;
    }

    public String getSpellName() {
        return name;
    }

    public void changeBasics(String name, int manaCost){
        this.name = name;
        this.manaCost =manaCost;
    }

}
