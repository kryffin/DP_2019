package main.java.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plateau {

    private ArrayList<Bateau> bateaux;

    public Arme getArme(Position position){

        Arme arme = null;
        for (Bateau bateau : bateaux){
            if(bateau.hasCompartiment(position)){
                arme = bateau.getArme(position);
            }
        }
        return arme;
    }

}
