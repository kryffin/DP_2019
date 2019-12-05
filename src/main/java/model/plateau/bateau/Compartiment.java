package main.java.model.plateau.bateau;

public class Compartiment {

    private Arme arme;

    private int pv;
    private int munitionActuel;


    public Compartiment(int pv, Arme arme){
        this.pv = pv;
        this.arme = arme;
    }

    public Arme getArme(){
        return this.arme;
    }

    public void setMunitionActuel(int munitionActuel) {
        this.munitionActuel = munitionActuel;
    }

    public int getPv() {
        return pv;
    }

    @Override
    public String toString() {
        return "Compartiment<" + pv + "," + munitionActuel + "," + arme + ">";
    }


    public void decreaseHP(int degat){
        int newpv = pv - degat;
        if(newpv < 0){
            pv = 0 ;
        }else{
            pv = newpv;
        }
    }
}
