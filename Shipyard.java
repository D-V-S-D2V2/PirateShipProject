/*
 * Author: Darsh & Keniel
 * Work Completed By: Darsh Keniel Gemini
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
 * - How it was used: Structured the upgrade menu, scaling cost formula, 5-level upgrade caps, and try/catch blocks.
 * - Why it was used: To organize the switch-case upgrade station and catch IllegalStateException when doubloons are low.
 * - Any changes made: Preserved player's wins and battlesPlayed when upgrading to a ManOfWar.
 */

/*
So everything you see here is created by ai including the ai documentation, I did not want to create this as you can see by the size but it was important
to the program so we gave it the underlying design of what should be in the code including the like upgrade caps and scalars and stuff like that
*/


public class Shipyard {
    private int repairCost = 15;
    private int manOfWarCost = 150;

    private int baseHealthCost = 30;
    private int baseCannonCost = 40;
    private int baseAuraCost = 50;
    private int baseMortarCost = 60;
    private int baseMortarDmgCost = 45;
    private int baseArmorCost = 50;

    private int maxUpgrades = 5;
    private int healthLevel = 0;
    private int cannonLevel = 0;
    private int auraLevel = 0;
    private int mortarDmgLevel = 0;
    private int armorLevel = 0;

    private Scanner input = new Scanner(System.in);

    /**
     * Scales the price of an upgrade up by 50% for each level already purchased.
     * @param baseCost base price of the upgrade
     * @param currentLevel number of times this stat has been upgraded (0 to 5)
     * @return scaled doubloon cost
     */
    public int getScaledCost(int baseCost, int currentLevel) {
        return (int) (baseCost * (1 + (currentLevel * 0.5)));
    }

    /**
     * Runs the interactive Shipyard menu and returns the upgraded Ship or newly bought ManOfWar.
     * @param playerShip the player's active Ship or ManOfWar
     * @param purse the player's Wallet
     * @return the updated Ship or ManOfWar object
     */
    public Ship useShipyard(Ship playerShip, Wallet purse) {
        boolean inShop = true;

        while (inShop) {
            int currentHealthCost = getScaledCost(baseHealthCost, healthLevel);
            int currentCannonCost = getScaledCost(baseCannonCost, cannonLevel);
            int currentAuraCost = getScaledCost(baseAuraCost, auraLevel);
            int currentMortarDmgCost = getScaledCost(baseMortarDmgCost, mortarDmgLevel);
            int currentArmorCost = getScaledCost(baseArmorCost, armorLevel);

            System.out.println("\n================ SHIPYARD UPGRADE STATION ================");
            System.out.println("Current Ship: " + playerShip.getName() +
                               " | HP: " + playerShip.getCurrentHealth() + "/" + playerShip.getMaxHealth() +
                               " | Cannon Dmg: " + playerShip.getCannonDamage() +
                               " | Aura: " + playerShip.getAura());

            if (playerShip instanceof ManOfWar) {
                ManOfWar mow = (ManOfWar) playerShip;
                System.out.println("Man of War Stats -> Mortars: " + mow.getHeavyMortars() + "/6" +
                                   " | Total Mortar Dmg: " + mow.getMortarsDamage() +
                                   " | Armor: " + (int) Math.round(mow.getArmorPlating() * 100) + "%");
            }

            System.out.println("Your Doubloons: " + purse.getDoubloons());
            System.out.println("----------------------------------------------------------");
            System.out.println("1. Repair Ship to Full Health (" + repairCost + " Doubloons)");
            System.out.println("2. Upgrade Max Health +25 [Lvl " + healthLevel + "/" + maxUpgrades + "] (" +
                               (healthLevel >= maxUpgrades ? "MAXED" : currentHealthCost + " Doubloons") + ")");
            System.out.println("3. Upgrade Cannon Damage +5 [Lvl " + cannonLevel + "/" + maxUpgrades + "] (" +
                               (cannonLevel >= maxUpgrades ? "MAXED" : currentCannonCost + " Doubloons") + ")");
            System.out.println("4. Upgrade Aura +2 [Lvl " + auraLevel + "/" + maxUpgrades + "] (" +
                               (auraLevel >= maxUpgrades ? "MAXED" : currentAuraCost + " Doubloons") + ")");

            if (!(playerShip instanceof ManOfWar)) {
                System.out.println("5. BUY MAN OF WAR CLASS SHIP (" + manOfWarCost + " Doubloons)");
            } else {
                ManOfWar mow = (ManOfWar) playerShip;
                int mortarUpgradesBought = mow.getHeavyMortars() - 1;
                int currentMortarCost = getScaledCost(baseMortarCost, mortarUpgradesBought);

                System.out.println("5. Add Heavy Mortar [Total: " + mow.getHeavyMortars() + "/6] (" +
                                   (mow.getHeavyMortars() >= 6 ? "MAXED" : currentMortarCost + " Doubloons") + ")");
                System.out.println("6. Upgrade Heavy Mortar Damage +10 [Lvl " + mortarDmgLevel + "/" + maxUpgrades + "] (" +
                                   (mortarDmgLevel >= maxUpgrades ? "MAXED" : currentMortarDmgCost + " Doubloons") + ")");
                System.out.println("7. Upgrade Armor Plating +5% [Lvl " + armorLevel + "/" + maxUpgrades + "] (" +
                                   (armorLevel >= maxUpgrades ? "MAXED" : currentArmorCost + " Doubloons") + ")");
            }
            System.out.println("0. Exit Shipyard");
            System.out.print("Enter your choice: ");

            String choice = input.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        if (playerShip.getCurrentHealth() >= playerShip.getMaxHealth()) {
                            System.out.println("Your ship is already at full health!");
                        } else {
                            purse.spendDoubloons(repairCost);
                            playerShip.setCurrentHealth(playerShip.getMaxHealth());
                            System.out.println("Ship repaired to " + playerShip.getMaxHealth() + " HP!");
                        }
                        break;

                    case "2":
                        if (healthLevel >= maxUpgrades) {
                            System.out.println("Max Health is already at the maximum upgrade level (5/5)!");
                        } else {
                            purse.spendDoubloons(currentHealthCost);
                            healthLevel++;
                            playerShip.setMaxHealth(playerShip.getMaxHealth() + 25);
                            playerShip.setCurrentHealth(playerShip.getCurrentHealth() + 25);
                            System.out.println("Max Health upgraded to " + playerShip.getMaxHealth() + " (Level " + healthLevel + "/5)!");
                        }
                        break;

                    case "3":
                        if (cannonLevel >= maxUpgrades) {
                            System.out.println("Cannon Damage is already at the maximum upgrade level (5/5)!");
                        } else {
                            purse.spendDoubloons(currentCannonCost);
                            cannonLevel++;
                            playerShip.setCannonDamage(playerShip.getCannonDamage() + 5);
                            System.out.println("Base Cannon Damage upgraded to " + playerShip.getCannonDamage() + " (Level " + cannonLevel + "/5)!");
                        }
                        break;

                    case "4":
                        if (auraLevel >= maxUpgrades) {
                            System.out.println("Aura is already at the maximum upgrade level (5/5)!");
                        } else {
                            purse.spendDoubloons(currentAuraCost);
                            auraLevel++;
                            playerShip.setAura(playerShip.getAura() + 2);
                            System.out.println("Ship Aura upgraded to " + playerShip.getAura() + " (Level " + auraLevel + "/5)!");
                        }
                        break;

                    case "5":
                        if (!(playerShip instanceof ManOfWar)) {
                            purse.spendDoubloons(manOfWarCost);
                            int savedAura = playerShip.getAura();
                            int savedWins = playerShip.getWins();
                            int savedBattles = playerShip.getBattlesPlayed();
                            playerShip = new ManOfWar("Man of War",
                                                      playerShip.getMaxHealth() + 75,
                                                      playerShip.getMaxHealth() + 75,
                                                      playerShip.getCannonDamage() + 10,
                                                      manOfWarCost, 1, 15.0, 0.10);
                            playerShip.setAura(savedAura);
                            playerShip.setWins(savedWins);
                            playerShip.setBattlesPlayed(savedBattles);
                            System.out.println("You upgraded your vessel to a MAN OF WAR!");
                        } else {
                            ManOfWar mow = (ManOfWar) playerShip;
                            int mortarUpgradesBought = mow.getHeavyMortars() - 1;
                            int currentMortarCost = getScaledCost(baseMortarCost, mortarUpgradesBought);

                            if (mow.getHeavyMortars() >= 6) {
                                System.out.println("You already have the maximum of 6 Heavy Mortars!");
                            } else {
                                purse.spendDoubloons(currentMortarCost);
                                mow.setHeavyMortars(mow.getHeavyMortars() + 1);
                                System.out.println("Added a Heavy Mortar! Total: " + mow.getHeavyMortars() + "/6.");
                            }
                        }
                        break;

                    case "6":
                        if (playerShip instanceof ManOfWar) {
                            ManOfWar mow = (ManOfWar) playerShip;
                            if (mortarDmgLevel >= maxUpgrades) {
                                System.out.println("Heavy Mortar Damage is already maxed out (5/5)!");
                            } else {
                                purse.spendDoubloons(currentMortarDmgCost);
                                mortarDmgLevel++;
                                mow.setMortarDamage(mow.getMortarDamage() + 10);
                                System.out.println("Heavy Mortar Damage upgraded to " + mow.getMortarDamage() +
                                                   " (Total Mortar Dmg: " + mow.getMortarsDamage() + ")!");
                            }
                        } else {
                            System.out.println("You must buy the Man of War first!");
                        }
                        break;

                    case "7":
                        if (playerShip instanceof ManOfWar) {
                            ManOfWar mow = (ManOfWar) playerShip;
                            if (armorLevel >= maxUpgrades || mow.getArmorPlating() >= 0.50) {
                                System.out.println("Armor Plating is already maxed out!");
                            } else {
                                purse.spendDoubloons(currentArmorCost);
                                armorLevel++;
                                mow.setArmorPlating(Math.round((mow.getArmorPlating() + 0.05) * 100.0) / 100.0);
                                System.out.println("Armor Plating upgraded to " + (int) Math.round(mow.getArmorPlating() * 100) + "% (Level " + armorLevel + "/5)!");
                            }
                        } else {
                            System.out.println("You must buy the Man of War first!");
                        }
                        break;

                    case "0":
                        System.out.println("Setting sail from the Shipyard...");
                        inShop = false;
                        break;

                    default:
                        System.out.println("Invalid option, please enter a valid number.");
                }
            } catch (IllegalStateException e) {
                System.out.println("Purchase failed: " + e.getMessage());
            }
        }
        return playerShip;
    }

    /**
     * Blanket JavaDoc for all Shipyard Accessors (Getters) and Mutators (Setters).
     * Getters return base costs and upgrade levels, and Setters validate that costs are non-negative and levels are within maxUpgrades.
     */
    public int getRepairCost() { return repairCost; }
    public void setRepairCost(int repairCost) { if (repairCost >= 0) this.repairCost = repairCost; }

    public int getManOfWarCost() { return manOfWarCost; }
    public void setManOfWarCost(int manOfWarCost) { if (manOfWarCost >= 0) this.manOfWarCost = manOfWarCost; }

    public int getBaseHealthCost() { return baseHealthCost; }
    public void setBaseHealthCost(int baseHealthCost) { if (baseHealthCost >= 0) this.baseHealthCost = baseHealthCost; }

    public int getBaseCannonCost() { return baseCannonCost; }
    public void setBaseCannonCost(int baseCannonCost) { if (baseCannonCost >= 0) this.baseCannonCost = baseCannonCost; }

    public int getBaseAuraCost() { return baseAuraCost; }
    public void setBaseAuraCost(int baseAuraCost) { if (baseAuraCost >= 0) this.baseAuraCost = baseAuraCost; }

    public int getBaseMortarCost() { return baseMortarCost; }
    public void setBaseMortarCost(int baseMortarCost) { if (baseMortarCost >= 0) this.baseMortarCost = baseMortarCost; }

    public int getBaseMortarDmgCost() { return baseMortarDmgCost; }
    public void setBaseMortarDmgCost(int baseMortarDmgCost) { if (baseMortarDmgCost >= 0) this.baseMortarDmgCost = baseMortarDmgCost; }

    public int getBaseArmorCost() { return baseArmorCost; }
    public void setBaseArmorCost(int baseArmorCost) { if (baseArmorCost >= 0) this.baseArmorCost = baseArmorCost; }

    public int getMaxUpgrades() { return maxUpgrades; }
    public void setMaxUpgrades(int maxUpgrades) { if (maxUpgrades > 0) this.maxUpgrades = maxUpgrades; }

    public int getHealthLevel() { return healthLevel; }
    public void setHealthLevel(int healthLevel) { if (healthLevel >= 0 && healthLevel <= maxUpgrades) this.healthLevel = healthLevel; }

    public int getCannonLevel() { return cannonLevel; }
    public void setCannonLevel(int cannonLevel) { if (cannonLevel >= 0 && cannonLevel <= maxUpgrades) this.cannonLevel = cannonLevel; }

    public int getAuraLevel() { return auraLevel; }
    public void setAuraLevel(int auraLevel) { if (auraLevel >= 0 && auraLevel <= maxUpgrades) this.auraLevel = auraLevel; }

    public int getMortarDmgLevel() { return mortarDmgLevel; }
    public void setMortarDmgLevel(int mortarDmgLevel) { if (mortarDmgLevel >= 0 && mortarDmgLevel <= maxUpgrades) this.mortarDmgLevel = mortarDmgLevel; }

    public int getArmorLevel() { return armorLevel; }
    public void setArmorLevel(int armorLevel) { if (armorLevel >= 0 && armorLevel <= maxUpgrades) this.armorLevel = armorLevel; }
}
