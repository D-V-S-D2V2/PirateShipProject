/*
 * Author: [Your Name] & [Partner's Name]
 * Work Completed By: [Your Name] & [Partner's Name]
 * Academic Integrity Pledge:
 * I have only used resources indicated that are allowable.
 * The work submitted is my/our, and only my/our work.
 * Signature: [Your Name] & [Partner's Name]
 * Date: November 12, 2026
 */

import java.util.ArrayList;
import java.util.Scanner;

/*
 * AI Documentation:
 * - AI Agent used: Gemini
 * - How it was used: Structured the 13-island progression loop, 50/25/25 probability check, and enemy Ship ArrayList in Main.
 * - Why it was used: To handle island encounters directly in Main without needing extra Island classes.
 * - Any changes made: Connected directly to our existing Battle, Shipyard, Ship, and Wallet classes.
 */

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Wallet purse = new Wallet(100);
        Ship playerShip = new Ship("Starter Ship", 100, 100, 15, 50);
        Shipyard shop = new Shipyard();
        Battle battleSystem = new Battle();

        // ArrayList of different enemy ships you can face (Criterion 6)
        ArrayList<Ship> enemyShips = createEnemyShipList();

        System.out.println("==========================================================");
        System.out.println("🏴‍☠️ WELCOME TO THE PIRATE VOYAGE! 🏴‍☠️");
        System.out.println("Your quest is to find the ultimate treasure!");
        System.out.println("==========================================================");

        // Loop through all 13 islands
        for (int islandNum = 1; islandNum <= 13; islandNum++) {
            if (playerShip.getCurrentHealth() <= 0) {
                System.out.println("\n💀 GAME OVER! Your ship sank at Island " + islandNum + " of 13.");
                break;
            }

            System.out.println("\n----------------------------------------------------------");
            System.out.println("🌊 ISLAND " + islandNum + " OF 13");
            System.out.println("Ship: " + playerShip.getName() +
                               " | HP: " + playerShip.getCurrentHealth() + "/" + playerShip.getMaxHealth() +
                               " | Doubloons: " + purse.getDoubloons());
            System.out.println("----------------------------------------------------------");

            // Roll 3 separate islands (1 = Enemy 50%, 2 = Shipyard 25%, 3 = Normal Treasure 25%)
            int[] threeIslands = new int[3];
            for (int i = 0; i < 3; i++) {
                double roll = Math.random();
                if (roll < 0.50) {
                    threeIslands[i] = 1; // 50% Enemy Island
                } else if (roll < 0.75) {
                    threeIslands[i] = 2; // 25% Shipyard Island
                } else {
                    threeIslands[i] = 3; // 25% Normal Treasure Island
                }
            }

            System.out.println("Three islands lie ahead! Pick which path to sail:");
            System.out.println("1. Sail to Left Island");
            System.out.println("2. Sail to Center Island");
            System.out.println("3. Sail to Right Island");

            int choice = getIslandChoice(input);
            int landedIslandType = threeIslands[choice - 1];

            // Handle whichever island type the player landed on
            playerShip = triggerIslandEvent(landedIslandType, playerShip, purse, shop, battleSystem, enemyShips);
        }

        if (playerShip.getCurrentHealth() > 0) {
            System.out.println("\n🏆 VICTORY! You survived all 13 islands!");
            System.out.println("Final Ship: " + playerShip.getName() + " | Final Doubloons: " + purse.getDoubloons());
        }
    }

    // Runs the event for Enemy Island (1), Shipyard Island (2), or Normal Island (3)
    public static Ship triggerIslandEvent(int type, Ship playerShip, Wallet purse, Shipyard shop, Battle battleSystem, ArrayList<Ship> enemyShips) {
        if (type == 1) {
            // 50% ENEMY ISLAND
            System.out.println("\n⚔️ IT'S AN ENEMY ISLAND!");
            int randomIndex = (int) (Math.random() * enemyShips.size());
            Ship template = enemyShips.get(randomIndex);

            // Create a fresh copy of the chosen enemy ship so its health starts full
            Ship enemy = new Ship(template.getName(), template.getMaxHealth(),
                                  template.getMaxHealth(), template.getCannonDamage(), template.getPrice());

            battleSystem.mainBattle(playerShip, enemy, purse);

            if (enemy.getCurrentHealth() <= 0) {
                System.out.println("💰 You plundered " + enemy.getPrice() + " Doubloons from the enemy ship!");
                purse.addDoubloons(enemy.getPrice());
            }
        } else if (type == 2) {
            // 25% SHIPYARD ISLAND
            System.out.println("\n⚓ IT'S A SHIPYARD ISLAND! Time to upgrade or repair.");
            playerShip = shop.useShipyard(playerShip, purse);
        } else {
            // 25% NORMAL TREASURE ISLAND
            int treasure = (int) (Math.random() * 76) + 25; // Random treasure between 25 and 100
            System.out.println("\n🏝️ IT'S A NORMAL ISLAND! You found a buried treasure chest with " + treasure + " Doubloons!");
            purse.addDoubloons(treasure);
        }
        return playerShip;
    }

    // Creates the ArrayList of different ships you can face
    public static ArrayList<Ship> createEnemyShipList() {
        ArrayList<Ship> list = new ArrayList<>();
        list.add(new Ship("Merchant Gunboat", 60, 60, 8, 35));
        list.add(new Ship("Cutthroat Sloop", 85, 85, 12, 50));
        list.add(new Ship("Corsair Brigantine", 110, 110, 16, 70));
        list.add(new Ship("Royal Navy Frigate", 140, 140, 20, 95));
        list.add(new Ship("Spanish Galleon", 175, 175, 25, 130));
        list.add(new Ship("Imperial Dreadnought", 220, 220, 32, 175));
        return list;
    }

    // Validates that the player enters 1, 2, or 3 using try/catch (Criterion 9)
    public static int getIslandChoice(Scanner input) {
        while (true) {
            try {
                System.out.print("Enter island choice (1, 2, or 3): ");
                int pick = Integer.parseInt(input.nextLine().trim());
                if (pick < 1 || pick > 3) {
                    throw new IllegalArgumentException("Choice must be 1, 2, or 3!");
                }
                return pick;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number (1, 2, or 3).");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
