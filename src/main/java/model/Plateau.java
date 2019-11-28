package main.java.model;


import java.util.ArrayList;


public class Plateau {

    private ArrayList<Bateau> bateaux;

    public Arme getArme(Position position){

        Arme arme = null;
        for (Bateau bateau : bateaux){

            arme = bateau.getArme(position);
        }

        return arme;
    }

}
