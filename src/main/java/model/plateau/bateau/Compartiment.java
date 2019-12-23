package main.java.model.plateau.bateau;

import main.java.model.Position;

public class Compartiment {

    private Arme arme;

    private int pv;
    private int munitionActuel;
    private Position position;


    public Compartiment(int pv, Arme arme, Position position, int munition){
        this.pv = pv;
        this.arme = arme;
        this.position = position;
        this.munitionActuel = munition;
    }

    public Arme getArme(){
        if(munitionActuel > 0){
            return this.arme;

        }
        return null;
    }

    public Position getPosition(){
        return position;
    }

    public void decreaseMunition(){
        this.munitionActuel -= 1;
    }
    public void setMunitionActuel(int munitionActuel) {
        this.munitionActuel = munitionActuel;
    }

    public int getPv() {
        return pv;
    }

    @Override
    public String toString() {
        return "Compartiment<" + pv + "," + munitionActuel + "," + arme + " Position : "+ position + " Ammo : " + munitionActuel + ">\n";
    }


    public void decreaseHP(int degat){
        int newpv = pv - degat;
        if(newpv < 0){
            pv = 0 ;
        }else{
            pv = newpv;
        }
    }

    public void setPosition(Position p) {
        this.position = p;
    }

    public int getMunition() {
        return munitionActuel;
    }
}
