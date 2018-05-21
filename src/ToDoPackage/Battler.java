package src.ToDoPackage;

public class Battler {
    //Don't change
    private Hand hand;
    private Deck deck;
    private SpellField spellField;
    private MonsterField monsterField;
    private int currentMana;
    private int maxMana;
    private int currentHealth;
    private int maxHealth;
    private  GraveYard graveYard;

    public void drawCard(int amount){
        // TODO: 5/20/2018
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public MonsterField getMonsterField() {
        return monsterField;
    }

    public void setCurrentMana(int currentMana){
        this.currentMana = currentMana;
    }

    public void changeHealthPoint(int amount){
        this.currentHealth += amount;
        if(this.currentHealth >= maxHealth)
            this.currentHealth = maxHealth;
    }

    public GraveYard getGraveYard() {
        return graveYard;
    }

    public SpellField getSpellField() {
        return spellField;
    }
}
