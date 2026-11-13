/*
 * Author: Darsh & Keniel
 * Work Completed By: Darsh
 * Academic Integrity Pledge:
 * I have only used resources indicated that are allowable.
 * The work submitted is my/our, and only my/our work.
 * Signature: Darsh Pratap Singh & Keniel Benjamin Carrow
 * Date: November 13, 2026
 */

/*
 * AI Documentation:
 * - AI Agent used: Gemini
 * - How it was used: To create base java docs and some were later edited and getters and setters
 * - Why it was used: To save time
 * - Any changes made: told me to add the calculateEnemyTurnDamage so that i could be overridden in order to get good grade, and java docs, and getters and setters
 */

public class AIBattle extends Battle {
    private int playerWins;

    /**
     * Constructs an AIBattle with the player's current win count.
     * @param playerWins number of battles won by the player
     */
    public AIBattle(int playerWins) {
        super();
        setPlayerWins(playerWins);
    }

    /**
     * Subclass-specific method that rolls AI timer accuracy and scales damage by playerWins.
     * @param enemyDamage base enemy cannon damage
     * @return scaled AI attack damage
     */
    public double aiBattler(double enemyDamage) {
        double intensity = 1.0 + (playerWins * 0.2);
        double roll = Math.random();
        double totalDamage;

        if (roll < 0.20) {
            totalDamage = enemyDamage * 2.5 * intensity;
            System.out.println("The AI nailed the timer! It did " + totalDamage + " damage!");
        } else if (roll < 0.60) {
            totalDamage = enemyDamage * 1.5 * intensity;
            System.out.println("The AI was close to the timer! It did " + totalDamage + " damage!");
        } else {
            totalDamage = enemyDamage * intensity;
            System.out.println("The AI missed the timer and did " + totalDamage + " damage.");
        }

        return totalDamage;
    }

    /**
     * Overrides calculateEnemyTurnDamage from Battle to use adaptive AI scaling.
     * @param baseEnemyDamage base enemy cannon damage
     * @return AI scaled attack damage
     */
    @Override
    public double calculateEnemyTurnDamage(double baseEnemyDamage) {
        return aiBattler(baseEnemyDamage);
    }

    /**
     * Blanket JavaDoc for AIBattle Accessors (Getters) and Mutators (Setters).
     * Gets and sets playerWins with non-negative validation.
     */
    public int getPlayerWins() { return playerWins; }
    public void setPlayerWins(int playerWins) { if (playerWins >= 0) this.playerWins = playerWins; }
}
