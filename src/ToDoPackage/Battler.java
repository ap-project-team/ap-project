package src.ToDoPackage;

public class Battler {
    private Hand hand;
    private Deck deck;
    private SpellField spellField;
    private MonsterField monsterField;
    private int currentMana;
    private int maxMana;
    private int currentHealth;
    private int maxHealth;
    private  GraveYard graveYard;

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
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

    public void setMonsterField(MonsterField monsterField) {
        this.monsterField = monsterField;
    }

    public void setCurrentMana(int currentMana){
        this.currentMana = currentMana;
    }

    public void changeHealthPoint(int amount){
        this.currentHealth += amount;
    }

    public SpellField getSpellField() {
        return spellField;
    }
}
