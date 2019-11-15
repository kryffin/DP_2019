package main.java.model;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.Map;

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
