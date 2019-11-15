package main.java.model.fabriqueEpoque;

import java.util.HashMap;

public abstract class Bateau {

    /*
    private HashMap<Position, Compartiment> compartiments;

    public Bateau (int taille, int pv, Arme... armes) {
        compartiments = new HashMap<>();
        for (int i = 0; i < taille; i++) {
            compartiments.put(new Position(0,0), new Compartiment(pv, armes[i]));
        }
    }

    public boolean hasPosition (Position p) {
        return compartiments.containsKey(p);
    }

    boolean isDead () {
        int pv = 0;

        for (Compartiment c : compartiments) {
            pv += c.getPv();
        }

        return pv == 0;
    }*/

}
