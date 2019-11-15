package main.java.model;

import javax.swing.text.Position;
import java.util.HashMap;
import java.util.Map;

public class Bateau {

    private HashMap<Position, Compartiment> Compartiments;

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
    }
}
