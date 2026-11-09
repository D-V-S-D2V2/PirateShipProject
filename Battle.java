import java.util.*;

public class Battle {
    Ship playerShip;
    Ship enemyShip;
    Scanner input = new Scanner(System.in);

    public double battler(int x, double damage) {
        
        System.out.println("It is now your turn, type A when you think it is " + x + " seconds.");
        System.out.println("When ready type any key and press enter to begin the timer.");

        String begin = input.nextLine();
        long currentTime = System.currentTimeMillis();
        String response = input.nextLine();
        long afterTime = System.currentTimeMillis();

        double timeTaken = (double) (afterTime - currentTime) / 1000.0;      

        double time = x;
        System.out.println("You were " + Math.abs(timeTaken-time) + " off");
        if (timeTaken == x) {
            System.out.println("You did " + (damage*2.5) + " much damage");
            return damage*2.5;
        } else if(Math.abs(timeTaken-time) <= (time*.05)) {
            System.out.println("You did " + (damage*1.5) + " much damage");
            return damage*1.5;
        } else {
            System.out.println("You did " + (damage) + " much damage");
            return damage;
        }
    }
    public void mainBattle(Ship playerShip, Ship enemyShip, Wallet purse) {

        double enemyCanonDamage = (playerShip.getAura()>=10) ? enemyShip.getCannonDamage()*.5 : (playerShip.getAura()>=5) ? enemyShip.getCannonDamage()*.3 : (playerShip.getAura()>=3) ? enemyShip.getCannonDamage()*.2 : enemyShip.getCannonDamage();
        double playerCanonDamage = playerShip.getCannonDamage();

        System.out.println("You have encourtered the " + enemyShip.getName());
        System.out.println("");

        while (playerShip.getCurrentHealth() > 0 && enemyShip.getCurrentHealth() > 0) {
            System.out.println("Your health: " + playerShip.getCurrentHealth() + " Your damage: " + playerCanonDamage);
            System.out.println("Enemy health: " + enemyShip.getCurrentHealth() + " Enemy damage: " + enemyCanonDamage);
            System.out.println();
            System.out.println();

            System.out.print("Type A to attack, F to flee");
            String response = input.nextLine();
            while (response.toUpperCase().charAt(0) != 'A' && response.toUpperCase().charAt(0) != 'F') {
                System.out.println("Please enter either A or F");
            }

            if (response.toUpperCase().charAt(0) == 'A') {
                enemyShip.addCurrentHealth(battler(((int)(Math.random()*6+1)), playerCanonDamage));
            } else {
                //add fleeing stuff
            }
        }
    }
}
