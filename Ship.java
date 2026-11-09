public class Ship{
    double maxHealth = 0;
    double currentHealth = 0;
    double cannonDamage = 0;
    int price = 0;
    int aura = 0;
    String name = "";
    

    public Ship(){
        this.maxHealth = 100;
        this.currentHealth = 100;
        this.cannonDamage = 10;
        this.price = 50;
        this.name = "Ship";
    }

    public Ship(String name, double maxHealth, double currentHealth, double cannonDamage, int price){
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.cannonDamage = cannonDamage;
        this.price = price;
        this.name = name;
    }

    // Getters
    public String getName(){
        return name;
    }
    public double getMaxHealth(){
        return maxHealth;
    }

    public double getCurrentHealth(){
        return currentHealth;
    }

    public double getCannonDamage(){
        return cannonDamage;
    }

    public int getPrice(){
        return price;
    }
    public int getAura(){
        return aura;
    }

    // Setters
    public void setName(String name){
        this.name = name;
    }
    public void setMaxHealth(double maxHealth){
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(double currentHealth){
        this.currentHealth = currentHealth;
    }

    public void setCannonDamage(double cannonDamage){
        this.cannonDamage = cannonDamage;
    }

    public void setPrice(int price){
        this.price = price;
    }
    public void setAura(int aura){
        this.aura = aura;
    }

    public void addCurrentHealth(double damage){
        this.currentHealth = damage;
    }
    //Getters and setters were made with AI.
}
