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
        System.out.println("You were " + Math.abs(timeTaken-time) + " seconds off");
        if (timeTaken == x) {
            System.out.println("You did " + (damage*2.5) + " much damage!");
            return damage*2.5;
        } else if(Math.abs(timeTaken-time) <= (time*.05)) {
            System.out.println("You did " + (damage*1.5) + " much damage!");
            return damage*1.5;
        } else {
            System.out.println("You did " + (damage) + " much damage!");
            return damage;
        }
    }

    public void mainBattle(Ship playerShip, Ship enemyShip, Wallet purse) {
        double currentDamage;
        double enemyCanonDamage = (playerShip.getAura()>=10) ? enemyShip.getCannonDamage()*.5 : (playerShip.getAura()>=5) ? enemyShip.getCannonDamage()*.3 : (playerShip.getAura()>=3) ? enemyShip.getCannonDamage()*.2 : enemyShip.getCannonDamage();
        double playerCanonDamage = playerShip.getCannonDamage();
        String x;


        System.out.println("You have entered a battle against " + enemyShip.getName());

        while (playerShip.getCurrentHealth() > 0 && enemyShip.getCurrentHealth() > 0) {
            System.out.println("Enemy ship health:" + enemyShip.getCurrentHealth() + "Enemy ship damage: " + enemyCanonDamage);
            System.out.println("Your ship health:" + playerShip.getCurrentHealth() + "Your ship damage: " + playerCanonDamage);

            System.out.println();
            System.out.print("Type A to attack and type F to flee: ");

            x = input.nextLine();
            while (x.toUpperCase().charAt(0) != 'A' && x.toUpperCase().charAt(0) != 'F') {
                System.out.println("Only enter either A or F");
                x = input.nextLine();
            }
            enemyShip.addCurrentHealth(battler(((int)(Math.random()*7+1)), playerCanonDamage));


        }
    }


}
