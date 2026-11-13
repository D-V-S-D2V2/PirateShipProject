/*
 * Author: Darsh & Keniel
 * Work Completed By: Keniel
 * Academic Integrity Pledge:
 * I have only used resources indicated that are allowable.
 * The work submitted is my/our, and only my/our work.
 * Signature: Darsh Pratap Singh & Keniel Benjamin Carrow
 * Date: November 13, 2026
 */

/*
 * AI Documentation:
 * - AI Agent used: Gemini
 * - How it was used: It created javadocs and like all the getters and setters
 * - Why it was used: To not waste time
 * - Any changes made: getters and setters and java docs
 */

public class Ship {
    private double maxHealth = 0;
    private double currentHealth = 0;
    private double cannonDamage = 0;
    private int price = 0;
    private int aura = 0;
    private String name = "";
    private int wins = 0;
    private int battlesPlayed = 0;

    /**
     * Default constructor initializing a standard Ship.
     */
    public Ship() {
        this.maxHealth = 100;
        this.currentHealth = 100;
        this.cannonDamage = 10;
        this.price = 50;
        this.name = "Ship";
    }

    /**
     * Parameterized constructor initializing a Ship with custom stats.
     * @param name ship name
     * @param maxHealth maximum hull HP
     * @param currentHealth current hull HP
     * @param cannonDamage base cannon damage
     * @param price ship cost or bounty reward
     */
    public Ship(String name, double maxHealth, double currentHealth, double cannonDamage, int price) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.cannonDamage = cannonDamage;
        this.price = price;
    }

    /**
     * Adds or subtracts health from currentHealth and prevents negative HP.
     * @param damage health change (negative for damage, positive for repair)
     */
    public void addCurrentHealth(double damage) {
        this.currentHealth += damage;
        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }
    }

    /**
     * Increments the player's win count by 1.
     */
    public void addWins() {
        this.wins += 1;
    }

    /**
     * Increments the total number of battles played by 1.
     */
    public void addBattlesPlayed() {
        this.battlesPlayed += 1;
    }

    /**
     * Blanket JavaDoc for all Ship Accessors (Getters) and Mutators (Setters).
     * Getters return the ship's private fields, and Setters validate input ranges before updating.
     */
    public String getName() { return name; }
    public double getMaxHealth() { return maxHealth; }
    public double getCurrentHealth() { return currentHealth; }
    public double getCannonDamage() { return cannonDamage; }
    public int getPrice() { return price; }
    public int getAura() { return aura; }
    public int getWins() { return wins; }
    public int getBattlesPlayed() { return battlesPlayed; }

    public void setName(String name) { if (name != null && !name.trim().isEmpty()) this.name = name; }
    public void setMaxHealth(double maxHealth) { if (maxHealth > 0) this.maxHealth = maxHealth; }
    public void setCurrentHealth(double currentHealth) {
        if (currentHealth < 0) this.currentHealth = 0;
        else if (currentHealth > this.maxHealth) this.currentHealth = this.maxHealth;
        else this.currentHealth = currentHealth;
    }
    public void setCannonDamage(double cannonDamage) { if (cannonDamage >= 0) this.cannonDamage = cannonDamage; }
    public void setPrice(int price) { if (price >= 0) this.price = price; }
    public void setAura(int aura) { if (aura >= 0) this.aura = aura; }
    public void setWins(int wins) { if (wins >= 0) this.wins = wins; }
    public void setBattlesPlayed(int battlesPlayed) { if (battlesPlayed >= 0) this.battlesPlayed = battlesPlayed; }
}
