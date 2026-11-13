/*
 * Author: Darsh & Keniel
 * Work Completed By: Darsh
 * Academic Integrity Pledge:
 * I have only used resources indicated that are allowable.
 * The work submitted is my/our, and only my/our work.
 * Signature: Darsh Pratap Singh & Keniel Benjamin Carrow
 * Date: November 13, 2026
 */

import java.io.*;
import java.util.*;

/*
 * AI Documentation:
 * - AI Agent used: Gemini
 * - How it was used: I am going to be honest, there is almost 0 original code that I have on this file, but there was a lot of underlying code that AI
 * changed to make it prettier and like game like. Such as creating the ships or the file, I had a basic version and then when I told AI to 
 * change the code so its like a game entry it rewrote alot of the the code I have so I can't claim any of the code as mine but I do know
 * what everything does how it works. It also created the AI battle names and initialization
 * - Why it was used: To not waste time
 * - Any changes made: the entire code
 */



/*
A QUICK DECLARATION
All the code was run through ai to refractor and ensure all parts of the project was hitting the rubric
Some stuff soley exist to hit the rubric and were out of our original scope
this project idea was put into ai along with all documentation provided and the code in which AI refractor and reworded many things
90% of code was ours in the begenning but AI changed it but all is explainable.
*/

public class Main {

    /**
     * Clean, minimal main method acting as the program driver.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Wallet purse = new Wallet(100);
        Ship playerShip = new Ship("Starter Ship", 100, 100, 15, 50);
        Shipyard shop = new Shipyard();
        Battle battleSystem = new Battle();

        ArrayList<Ship> enemyShips = createEnemyShipList();
        ArrayList<Ship> defeatedShips = new ArrayList<>();

        loadGameState(input, playerShip, purse);
        playerShip = runVoyage(input, playerShip, purse, shop, battleSystem, enemyShips, defeatedShips);
        saveGameAndWriteStats(playerShip, purse, defeatedShips);
    }

    /**
     * Runs the 13-island progression loop and handles island choices.
     */
    public static Ship runVoyage(Scanner input, Ship playerShip, Wallet purse, Shipyard shop,
                                 Battle battleSystem, ArrayList<Ship> enemyShips, ArrayList<Ship> defeatedShips) {
        System.out.println("==========================================================");
        System.out.println("WELCOME TO THE PIRATE VOYAGE!");
        System.out.println("Your quest is to find the ultimate treasure!");
        System.out.println("==========================================================\n");

        System.out.print("Enter the name of your ship: ");
        playerShip.setName(input.nextLine());

        for (int islandNum = 1; islandNum <= 13; islandNum++) {
            if (playerShip.getCurrentHealth() <= 0) {
                System.out.println("\nGAME OVER! Your ship sank at Island " + islandNum + " with " + playerShip.getWins() + " wins.");
                break;
            }

            System.out.println("\n----------------------------------------------------------");
            System.out.println("ISLAND " + islandNum);
            System.out.println("Ship: " + playerShip.getName() +
                               " | HP: " + playerShip.getCurrentHealth() + "/" + playerShip.getMaxHealth() +
                               " | Doubloons: " + purse.getDoubloons() +
                               " | Wins: " + playerShip.getWins());
            System.out.println("----------------------------------------------------------");

            int[] threeIslands = new int[3];
            for (int i = 0; i < 3; i++) {
                double roll = Math.random();
                if (roll < 0.50) {
                    threeIslands[i] = 1;
                } else if (roll < 0.75) {
                    threeIslands[i] = 2;
                } else {
                    threeIslands[i] = 3;
                }
            }

            System.out.println("Three islands lie ahead! Pick which path to sail:");
            System.out.println("1. Sail to Left Island");
            System.out.println("2. Sail to Center Island");
            System.out.println("3. Sail to Right Island");

            int choice = getIslandChoice(input);
            int landedIslandType = threeIslands[choice - 1];

            playerShip = triggerIslandEvent(landedIslandType, playerShip, purse, shop, battleSystem, enemyShips, defeatedShips);
        }

        if (playerShip.getCurrentHealth() > 0) {
            System.out.println("\nVICTORY! You survived all 13 islands!");
            System.out.println("Final Ship: " + playerShip.getName() + " | Final Doubloons: " + purse.getDoubloons());
        }
        return playerShip;
    }

    /**
     * Triggers the event for Enemy Island (1), Shipyard Island (2), or Normal Island (3).
     */
    public static Ship triggerIslandEvent(int type, Ship playerShip, Wallet purse, Shipyard shop,
                                          Battle battleSystem, ArrayList<Ship> enemyShips, ArrayList<Ship> defeatedShips) {
        if (type == 1) {
            System.out.println("\nIT'S AN ENEMY ISLAND!");
            int randomIndex = (int) (Math.random() * enemyShips.size());

            // .remove(randomIndex) pulls the ship out of the ArrayList so you never face it again!
            Ship enemy = enemyShips.remove(randomIndex);

            battleSystem.mainBattle(playerShip, enemy, purse);

            if (enemy.getCurrentHealth() <= 0) {
                defeatedShips.add(enemy);
                System.out.println(enemy.getName() + " has been sunk and permanently cleared from the seas!");
            }
        } else if (type == 2) {
            System.out.println("\nIT'S A SHIPYARD ISLAND! Time to upgrade or repair.");
            playerShip = shop.useShipyard(playerShip, purse);
        } else {
            int treasure = (int) (Math.random() * 76) + 25;
            System.out.println("\nIT'S A NORMAL ISLAND! You found a buried treasure chest with " + treasure + " Doubloons!");
            purse.addDoubloons(treasure);
        }
        return playerShip;
    }

    /**
     * Creates and populates the ArrayList of enemy Ship and ManOfWar objects.
     */
    public static ArrayList<Ship> createEnemyShipList() {
        ArrayList<Ship> list = new ArrayList<>();
        list.add(new Ship("Smuggler's Dinghy", 45, 45, 6, 25));
        list.add(new Ship("Merchant Gunboat", 60, 60, 8, 35));
        list.add(new Ship("Cutthroat Sloop", 85, 85, 12, 50));
        list.add(new Ship("Ghost Schooner", 95, 95, 18, 60));
        list.add(new Ship("Corsair Brigantine", 110, 110, 16, 70));
        list.add(new Ship("Privateer Barque", 125, 125, 19, 85));
        list.add(new Ship("Royal Navy Frigate", 140, 140, 20, 95));
        list.add(new Ship("Spanish Galleon", 175, 175, 25, 130));
        list.add(new Ship("Cursed Black Galleon", 195, 195, 28, 150));
        list.add(new Ship("Imperial Dreadnought", 220, 220, 32, 175));
        list.add(new ManOfWar("Ironclad Man-of-War", 240, 240, 25, 210, 2, 10.0, 0.15));
        list.add(new ManOfWar("Queen Anne's Revenge", 280, 280, 30, 260, 3, 12.0, 0.20));
        list.add(new ManOfWar("The Flying Dutchman", 320, 320, 35, 320, 4, 15.0, 0.25));
        list.add(new ManOfWar("Leviathan Flagship", 380, 380, 40, 400, 5, 18.0, 0.30));
        return list;
    }

    /**
     * Validates that the player enters island choice 1, 2, or 3 using try/catch.
     */
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

    /**
     * Loads previous session state from a file if the player chooses to, catching FileNotFoundException.
     */
    public static void loadGameState(Scanner input, Ship playerShip, Wallet purse) {
        System.out.print("Load previous save file? Enter filename (e.g., pirate_save.txt) or press ENTER to start fresh: ");
        String filename = input.nextLine().trim();
        if (filename.isEmpty()) {
            return;
        }
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            purse.setDoubloons(Integer.parseInt(fileScanner.nextLine()));
            purse.setTotalSpent(Integer.parseInt(fileScanner.nextLine()));
            purse.setTotalWon(Integer.parseInt(fileScanner.nextLine()));
            playerShip.setWins(Integer.parseInt(fileScanner.nextLine()));
            playerShip.setBattlesPlayed(Integer.parseInt(fileScanner.nextLine()));
            System.out.println("Save loaded! Doubloons: " + purse.getDoubloons() + " | Wins: " + playerShip.getWins());
        } catch (FileNotFoundException e) {
            System.out.println("File Error: Could not find '" + filename + "'. Starting a fresh voyage with 100 Doubloons!");
        } catch (Exception e) {
            System.out.println("Corrupted save file format. Starting fresh!");
        }
    }

    /**
     * Saves program state to pirate_save.txt and generates pirate_statistics.txt.
     */
    public static void saveGameAndWriteStats(Ship playerShip, Wallet purse, ArrayList<Ship> defeatedShips) {
        try (PrintWriter saveOut = new PrintWriter(new FileWriter("pirate_save.txt"));
             PrintWriter statsOut = new PrintWriter(new FileWriter("pirate_statistics.txt"))) {

            saveOut.println(purse.getDoubloons());
            saveOut.println(purse.getTotalSpent());
            saveOut.println(purse.getTotalWon());
            saveOut.println(playerShip.getWins());
            saveOut.println(playerShip.getBattlesPlayed());

            int totalBaseBountySunk = 0;
            for (int i = 0; i < defeatedShips.size(); i++) {
                totalBaseBountySunk += defeatedShips.get(i).getPrice();
            }

            int battles = playerShip.getBattlesPlayed();
            double winRate = (battles > 0) ? (playerShip.getWins() * 100.0 / battles) : 0.0;

            statsOut.println("========== PIRATE VOYAGE STATISTICS ==========");
            statsOut.println("Ship Name: " + playerShip.getName());
            statsOut.println("Total Battles Played: " + battles);
            statsOut.println("Total Battles Won: " + playerShip.getWins());
            statsOut.printf("Overall Win Rate: %.1f%%\n", winRate);
            statsOut.println("Starting Balance: " + purse.getStartingBalance() + " Doubloons");
            statsOut.println("Total Doubloons Spent: " + purse.getTotalSpent() + " Doubloons");
            statsOut.println("Total Doubloons Won: " + purse.getTotalWon() + " Doubloons");
            statsOut.println("Last Round Winnings: " + purse.getLastRoundWinnings() + " Doubloons");
            statsOut.println("Net Profit / Loss: " + purse.getNetResult() + " Doubloons");
            statsOut.println("Final Wallet Balance: " + purse.getDoubloons() + " Doubloons");
            statsOut.println("Ships Sunk This Session (" + defeatedShips.size() + " total, Base Value " + totalBaseBountySunk + " Doubloons):");
            for (Ship s : defeatedShips) {
                statsOut.println(" - " + s.getName() + " (Base Bounty: " + s.getPrice() + ")");
            }

            System.out.println("\nGame state saved to 'pirate_save.txt' and statistics exported to 'pirate_statistics.txt'!");
        } catch (IOException e) {
            System.out.println("File I/O Error while saving: " + e.getMessage());
        }
    }
}
