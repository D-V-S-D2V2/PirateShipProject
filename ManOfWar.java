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
 * - How it was used: It created javadocs and like all the getters and setters it also told us to add the if statment in add current health
 * - Why it was used: To not waste time
 * - Any changes made: getters and setters, java docs and if statment in add current health
 */

public class ManOfWar extends Ship {
    private int heavyMortars = 1;
    private double mortarDamage = 15.0;
    private double armorPlating = 0.10;

    /**
     * Parameterized constructor for ManOfWar.
     * @param name ship name
     * @param maxHealth max HP
     * @param currentHealth current HP
     * @param cannonDamage base cannon damage
     * @param price ship price/bounty
     * @param heavyMortars number of heavy mortars (0-6)
     * @param mortarDamage damage per heavy mortar
     * @param armorPlating damage reduction decimal (0.0 to 0.50)
     */
    public ManOfWar(String name, double maxHealth, double currentHealth, double cannonDamage, int price, int heavyMortars, double mortarDamage, double armorPlating) {
        super(name, maxHealth, currentHealth, cannonDamage, price);
        setHeavyMortars(heavyMortars);
        setMortarDamage(mortarDamage);
        setArmorPlating(armorPlating);
    }

    /**
     * Default constructor for ManOfWar.
     */
    public ManOfWar() {
        super("Man of War", 200, 200, 25, 150);
        this.heavyMortars = 1;
        this.mortarDamage = 15.0;
        this.armorPlating = 0.10;
    }

    /**
     * Calculates total bonus damage from all equipped heavy mortars.
     * @return heavyMortars multiplied by mortarDamage
     */
    public double getMortarsDamage() {
        return (heavyMortars * mortarDamage);
    }

    /**
     * Overrides addCurrentHealth so incoming damage (negative numbers) is reduced by armorPlating.
     * @param damage health change amount
     */
    @Override
    public void addCurrentHealth(double damage) {
        if (damage < 0) {
            double reducedDamage = damage * (1.0 - armorPlating);
            super.addCurrentHealth(reducedDamage);
        } else {
            super.addCurrentHealth(damage);
        }
    }

    /**
     * Blanket JavaDoc for all ManOfWar Accessors (Getters) and Mutators (Setters).
     * Getters return mortar and armor stats, and Setters validate caps (0-6 mortars, 0-50% armor).
     */
    public int getHeavyMortars() { return heavyMortars; }
    public void setHeavyMortars(int heavyMortars) { if (heavyMortars >= 0 && heavyMortars <= 6) this.heavyMortars = heavyMortars; }

    public double getMortarDamage() { return mortarDamage; }
    public void setMortarDamage(double mortarDamage) { if (mortarDamage >= 0) this.mortarDamage = mortarDamage; }

    public double getArmorPlating() { return armorPlating; }
    public void setArmorPlating(double armorPlating) { if (armorPlating >= 0.0 && armorPlating <= 0.50) this.armorPlating = armorPlating; }
}
