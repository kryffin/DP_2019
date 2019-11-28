package main.java.model;

import main.java.model.Position;
import java.util.HashMap;
import java.util.Map;


public class Bateau {

    /// CODE V1
    /*private HashMap<Position, Compartiment> Compartiments;

    public Bateau(HashMap<Position, Compartiment> compartiments) {
        Compartiments = compartiments;
    }

    public Arme getArme(Position position){

        Arme arme = null;
        for (Map.Entry entry : this.Compartiments.entrySet()) {

            if (entry.getKey().equals(position)) {

                arme = ((Compartiment) entry.getValue()).getArme();
            }
        }

        return arme;
    }*/

    private HashMap<Position, Compartiment> compartiments;

    public Bateau(int taille, int pv, Arme... armes) {
        compartiments = new HashMap<>();
        for (int i = 0; i < taille; i++) {
            Position p = new Position(0,0);
            compartiments.put(new Position(0, 0), new Compartiment(pv, 10, armes[i]));
            //todo recuperer les munitions actuels en fonction de l'arme et demander à l'époque
        }
    }

    public boolean hasCompartiment(Position p) {
        return compartiments.containsKey(p);
    }

    boolean isDead() {
        int pv = 0;

        for (Compartiment c : compartiments.values()) {
            pv += c.getPv();
        }

        return pv == 0;
    }

    public Arme getArme(Position position) {

        Arme arme = null;
        for (Map.Entry entry : compartiments.entrySet()) {

            if (entry.getKey().equals(position)) {

                arme = ((Compartiment) entry.getValue()).getArme();
            }
        }

        return arme;
    }
}
