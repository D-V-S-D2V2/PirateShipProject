import java.time.*;
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
        
        if (timeTaken == x) {
            return damage*2.5;
        } else if(Math.abs(timeTaken-time) <= (time*.05)) {
            return damage*1.5;
        } else {
            return damage;
        }
    }

    public void mainBattle(Ship playerShip, Ship enemyShip, Wallet purse) {

        double enemyCanonDamage = (playerShip.getAura()>=10) ? enemyShip.getCannonDamage()*.5 : (playerShip.getAura()>=5) ? enemyShip.getCannonDamage()*.3 : (playerShip.getAura()>=3) ? enemyShip.getCannonDamage()*.2 : enemyShip.getCannonDamage();

    }
}