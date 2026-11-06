public class Wallet {
    int doubloons = 0;
    public Wallet(){
        this.doubloons = 100;
    }
    public Wallet(int doubloons){
        this.doubloons = doubloons;
    }
    public void addDoubloons(int doubloons){
        this.doubloons += doubloons;
    }
    public int getDoubloons(){
        return this.doubloons;
    }
}