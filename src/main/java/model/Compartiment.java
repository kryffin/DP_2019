package main.java.model;

public class Compartiment {

    private Arme arme;

    private int pv;
    private int munitionActuel;


    public Compartiment(int pv, int munitionActuel, Arme arme){
        this.pv = pv;
        this.munitionActuel = munitionActuel;
        this.arme = arme;
    }
    public Arme getArme(){

        return this.arme;
    }

    public int getPv() {
        return pv;
    }
}
