public class ManOfWar extends Ship {
    private int heavyMortars = 0;
    private double mortarDamage = 0;
    private double armorPlating = 0;

    // Parameterized constructor
    public ManOfWar(String name, double maxHealth, double currentHealth, double cannonDamage, int price, int heavyMortars, double mortarDamage, double armorPlating) {
        super(name, maxHealth, currentHealth, cannonDamage, price);
        this.heavyMortars = heavyMortars;
        this.mortarDamage = mortarDamage;
        this.armorPlating = armorPlating;
    }
    
    public double getMortarsDamage() {
        return (heavyMortars * mortarDamage);
    }

    // Overrides addCurrentHealth so incoming damage (negative numbers) is reduced by Armor Plating!
    @Override
    public void addCurrentHealth(double damage) {
        if (damage < 0) {
            double reducedDamage = damage * (1.0 - armorPlating);
            super.addCurrentHealth(reducedDamage);
        } else {
            super.addCurrentHealth(damage);
        }
    }

    // Default constructor
    public ManOfWar() {
        super();
        this.heavyMortars = 0;
        this.mortarDamage = 0;
        this.armorPlating = 0;
    }

    // Getters and setters
    public int getHeavyMortars() {
        return heavyMortars;
    }

    public void setHeavyMortars(int heavyMortars) {
        this.heavyMortars = heavyMortars;
    }

    public double getMortarDamage() {
        return mortarDamage;
    }

    public void setMortarDamage(double mortarDamage) {
        this.mortarDamage = mortarDamage;
    }

    public double getArmorPlating() {
        return armorPlating;
    }

    public void setArmorPlating(double armorPlating) {
        this.armorPlating = armorPlating;
    }
}
