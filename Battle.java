/*
 * Author: Darsh & Keniel
 * Work Completed By: Darsh
 * Academic Integrity Pledge:
 * I have only used resources indicated that are allowable.
 * The work submitted is my/our, and only my/our work.
 * Signature: Darsh Pratap Singh & Keniel Benjamin Carrow
 * Date: November 13, 2026
 */

import java.util.*;

/*
 * AI Documentation:
 * - AI Agent used: Gemini
 * - How it was used: I have marked the methods that were created by AI, everything else except the main 2 battle methods I wrote but Ai told me to write it
 * Additionally, Anything revoling the ManOfWar was with AI as I felt like was copying the existing code and did not do.
 * - Why it was used: To get a good grade and quicken the process
 * - Any changes made: getters and setters, java docs, marked methods, and stuff revolving manofwar
 */

public class Battle {
    private Ship playerShip;
    private Ship enemyShip;
    private int[] enemyWeakPoints = new int[5];
    private int[] playerShots = new int[5];
    private Scanner input = new Scanner(System.in);

    /**
     * Default constructor for Battle.
     */
    public Battle() {
        generateWeakPoints();
    }

    /**
     * Parameterized constructor for Battle.
     * @param playerShip player's ship
     * @param enemyShip enemy's ship
     */
    public Battle(Ship playerShip, Ship enemyShip) {
        this.playerShip = playerShip;
        this.enemyShip = enemyShip;
        generateWeakPoints();
    }

    /**
     * Populates the enemy's 5-element array with random target zones (1 to 3).
     */
    //Most of this method is AI as it told me i needed array matching
    public void generateWeakPoints() {
        for (int i = 0; i < enemyWeakPoints.length; i++) {
            enemyWeakPoints[i] = (int) (Math.random() * 3) + 1;
        }
    }

    /**
     * Compares the same index in playerShots and enemyWeakPoints and counts exact matches.
     * @param playerArray the player's 5 chosen zones
     * @return number of exact index matches (0 to 5)
     */
    //Most of this method is AI as it told me i needed array matching
    public int countPositionMatches(int[] playerArray) {
        int matches = 0;
        for (int i = 0; i < enemyWeakPoints.length && i < playerArray.length; i++) {
            if (playerArray[i] == enemyWeakPoints[i]) {
                matches++;
            }
        }
        return matches;
    }

    /**
     * Returns the rubric-required bonus multiplier based on exact array matches.
     * @param matches number of position matches (0 to 5)
     * @return multiplier (1.0, 1.2, 1.5, 2.0, 2.5, or 3.0)
     */

    public double getBonusMultiplier(int matches) {
        switch (matches) {
            case 1: return 1.2;
            case 2: return 1.5;
            case 3: return 2.0;
            case 4: return 2.5;
            case 5: return 3.0;
            default: return 1.0;
        }
    }

    /**
     * Runs the 5-position array matching salvage minigame to multiply battle winnings.
     * @param baseBounty base doubloon reward of the sunken ship
     * @return final multiplied doubloon reward
     */
    //Most of this method is AI as it told me i needed array matching
    public int runArrayMatchPlunder(int baseBounty) {
        generateWeakPoints();
        System.out.println("\n --- BROADSIDE SALVAGE BONUS (Position-Based Array Matching) --- ");
        System.out.println("Fire at 5 hull holds (enter 1, 2, or 3 for each slot) to multiply your " + baseBounty + " Doubloon bounty!");

        for (int i = 0; i < playerShots.length; i++) {
            while (true) {
                try {
                    System.out.print("Aim Cannon Slot #" + (i + 1) + " (1-3): ");
                    int val = Integer.parseInt(input.nextLine().trim());
                    if (val < 1 || val > 3) {
                        throw new IllegalArgumentException("Please enter 1, 2, or 3!");
                    }
                    playerShots[i] = val;
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Enter a number (1, 2, or 3).");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        int matches = countPositionMatches(playerShots);
        double multiplier = getBonusMultiplier(matches);
        int finalReward = (int) Math.round(baseBounty * multiplier);

        System.out.println("Enemy Hold Array:  " + Arrays.toString(enemyWeakPoints));
        System.out.println("Your Cannon Array: " + Arrays.toString(playerShots));
        System.out.println("Exact Position Matches: " + matches + "/5 -> Bonus Multiplier: x" + multiplier);
        System.out.println("Final Multiplied Plunder: " + finalReward + " Doubloons!");
        return finalReward;
    }

    /**
     * Runs the timer-based player attack.
     * @param x target seconds to wait
     * @param damage base cannon damage
     * @return calculated attack damage
     */
    public double battler(int x, double damage) {
        System.out.println("It is now your turn, type A when you think it is " + x + " seconds.");
        System.out.println("When ready type any key and press enter to begin the timer.");

        String begin = input.nextLine();
        long currentTime = System.currentTimeMillis();
        System.out.println("Timer started! Press enter at " + x + " seconds!");
        String response = input.nextLine();
        long afterTime = System.currentTimeMillis();

        double timeTaken = (double) (afterTime - currentTime) / 1000.0;
        double time = x;
        double diff = Math.abs(timeTaken - time);

        System.out.printf("You were %.3f seconds off\n", diff);
        if (diff <= 0.05) {
            System.out.println("CRITICAL! You did " + (damage * 2.5) + " much damage");
            return damage * 2.5;
        } else if (diff <= (time * 0.15)) {
            System.out.println("GREAT HIT! You did " + (damage * 1.5) + " much damage");
            return damage * 1.5;
        } else {
            System.out.println("You did " + damage + " much damage");
            return damage;
        }
    }

    /**
     * Calculates enemy turn damage (overridden in AIBattle subclass).
     * @param baseEnemyDamage base enemy cannon damage
     * @return enemy attack damage
     */
    public double calculateEnemyTurnDamage(double baseEnemyDamage) {
        return baseEnemyDamage;
    }

    /**
     * Runs the main combat loop between playerShip and enemyShip.
     * @param playerShip player's Ship
     * @param enemyShip enemy's Ship
     * @param purse player's Wallet
     */
    public void mainBattle(Ship playerShip, Ship enemyShip, Wallet purse) {
        this.playerShip = playerShip;
        this.enemyShip = enemyShip;
        playerShip.addBattlesPlayed();

        try {
            System.out.println("Spending " + purse.getCostPerPlay() + " Doubloons on gunpowder for battle...");
            purse.spendDoubloons(purse.getCostPerPlay());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage() + " Using reserve gunpowder for this fight!");
        }

        double enemyCanonDamage = (playerShip.getAura() >= 10) ? enemyShip.getCannonDamage() * 0.5
                                : (playerShip.getAura() >= 5) ? enemyShip.getCannonDamage() * 0.7
                                : (playerShip.getAura() >= 3) ? enemyShip.getCannonDamage() * 0.8
                                : enemyShip.getCannonDamage();
        double playerCanonDamage = playerShip.getCannonDamage();

        if (playerShip instanceof ManOfWar) {
            playerCanonDamage += ((ManOfWar) playerShip).getMortarsDamage();
        }
        if (enemyShip instanceof ManOfWar) {
            enemyCanonDamage += ((ManOfWar) enemyShip).getMortarsDamage();
        }

        System.out.println("You have encountered the " + enemyShip.getName() + "\n");

        while (playerShip.getCurrentHealth() > 0 && enemyShip.getCurrentHealth() > 0) {
            System.out.println("Your health: " + playerShip.getCurrentHealth() + " | Your damage: " + playerCanonDamage);
            System.out.println("Enemy health: " + enemyShip.getCurrentHealth() + " | Enemy base damage: " + enemyCanonDamage + "\n");

            System.out.print("Type A to attack, F to flee: ");
            String response = input.nextLine().trim();
            while (response.isEmpty() || (response.toUpperCase().charAt(0) != 'A' && response.toUpperCase().charAt(0) != 'F')) {
                System.out.print("Please enter either A or F: ");
                response = input.nextLine().trim();
            }

            if (response.toUpperCase().charAt(0) == 'A') {
                double playerDmgDealt = battler((int) (Math.random() * 6 + 1), playerCanonDamage);
                enemyShip.addCurrentHealth(-playerDmgDealt);

                if (enemyShip.getCurrentHealth() <= 0) {
                    System.out.println("\nYou sank the " + enemyShip.getName() + "!");
                    playerShip.addWins();
                    int totalReward = runArrayMatchPlunder(enemyShip.getPrice());
                    purse.addDoubloons(totalReward);
                    break;
                }

                System.out.println("\n--- ENEMY TURN ---");
                AIBattle ai = new AIBattle(playerShip.getWins());
                double aiDmgDealt = ai.calculateEnemyTurnDamage(enemyCanonDamage);
                playerShip.addCurrentHealth(-aiDmgDealt);
                System.out.println();

                if (playerShip.getCurrentHealth() <= 0) {
                    System.out.println("\nYour ship was sunk in battle!");
                    break;
                }
            } else {
                System.out.println("You fled the battle to save your ship!");
                break;
            }
        }
    }

    /**
     * These are all just getters and setters
     */
    public Ship getPlayerShip() {
        return playerShip;
        }
    public void setPlayerShip(Ship playerShip) { 
        this.playerShip = playerShip; 
    }

    public Ship getEnemyShip() { 
        return enemyShip; 
    }
    public void setEnemyShip(Ship enemyShip) { 
        this.enemyShip = enemyShip; 
    }

    public int[] getEnemyWeakPoints() { 
        return enemyWeakPoints; 
    }
    public void setEnemyWeakPoints(int[] enemyWeakPoints) {
        if (enemyWeakPoints != null && enemyWeakPoints.length == 5) this.enemyWeakPoints = enemyWeakPoints;
    }

    public int[] getPlayerShots() { 
        return playerShots; 
    }
    public void setPlayerShots(int[] playerShots) {
        if (playerShots != null && playerShots.length == 5) this.playerShots = playerShots;
    }
}
