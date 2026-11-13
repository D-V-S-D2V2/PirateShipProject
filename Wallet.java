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
 * - How it was used: It told me that I was missing stuff such as the if statements and the exception but I created it with the help of Darsh
 * - Why it was used: So that we can get a good grade on the project
 * - Any changes made: Getters and Setters and the if statements and the exception
 */

public class Wallet {
    private int startingBalance = 100;
    private int doubloons = 0;
    private int costPerPlay = 5;
    private int totalSpent = 0;
    private int lastRoundWinnings = 0;
    private int totalWon = 0;


    public Wallet() {
        this.startingBalance = 100;
        this.doubloons = 100;
    }

    /**
     * Parameterized constructor initializing the wallet with a custom balance.
     * @param doubloons starting doubloons
     */
    public Wallet(int doubloons) {
        this.startingBalance = doubloons;
        this.doubloons = doubloons;
    }

    /**
     * Adds doubloons won from battle or treasure and tracks per-round and cumulative winnings.
     * @param amount doubloons won
     */
    public void addDoubloons(int amount) {
        if (amount > 0) {
            this.doubloons += amount;
            this.lastRoundWinnings = amount;
            this.totalWon += amount;
        }
    }

    /**
     * Deducts doubloons for upgrades, repairs, or battle cost per play.
     * @param amount doubloons to spend
     * @throws IllegalStateException if the player does not have enough doubloons
     */
    public void spendDoubloons(int amount) {
        if (amount > this.doubloons) {
            throw new IllegalStateException("Insufficient doubloons! Needed " + amount + " but only have " + this.doubloons + ".");
        }
        if (amount > 0) {
            this.doubloons -= amount;
            this.totalSpent += amount;
        }
    }

    /**
     * Calculates net profit or loss across the game.
     * @return totalWon minus totalSpent
     */
    public int getNetResult() {
        return this.totalWon - this.totalSpent;
    }

    /**
     * Blanket JavaDoc for all Wallet Accessors (Getters) and Mutators (Setters).
     * Getters return the current value of each Wallet field, and Setters validate that values are non-negative.
     */
    public int getDoubloons() { 
        return this.doubloons; 
    }
    public void setDoubloons(int doubloons) { 
        if (doubloons >= 0) this.doubloons = doubloons; 
    }

    public int getStartingBalance() { 
        return startingBalance; 
    }
    public void setStartingBalance(int startingBalance) { 
        if (startingBalance >= 0) this.startingBalance = startingBalance; 
    }

    public int getCostPerPlay() { 
        return costPerPlay; 
    }
    public void setCostPerPlay(int costPerPlay) { 
        if (costPerPlay >= 0) this.costPerPlay = costPerPlay; 
    }

    public int getTotalSpent() { return totalSpent; }
    public void setTotalSpent(int totalSpent) { 
        if (totalSpent >= 0) this.totalSpent = totalSpent;
    }

    public int getLastRoundWinnings() { 
        return lastRoundWinnings; 
    }
    public void setLastRoundWinnings(int lastRoundWinnings) { 
        if (lastRoundWinnings >= 0) this.lastRoundWinnings = lastRoundWinnings; 
    }

    public int getTotalWon() { 
        return totalWon; 
    }
    public void setTotalWon(int totalWon) {
        if (totalWon >= 0) this.totalWon = totalWon; 
    }
}
