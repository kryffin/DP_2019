package main.java.model;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Map;

public class Plateau {

    private List<Bateau> bateaux;

    public Plateau () {
        bateaux = new ArrayList<>();
    }

    public void setBateaux(List<Bateau> bateaux) {
        this.bateaux = bateaux;
    }

    public Arme getArme(Position position){
        Arme arme = null;
        for (Bateau bateau : bateaux){
            if(bateau.hasCompartiment(position)){
                arme = bateau.getArme(position);
            }
        }

        return arme;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plateau<\n");

        for (Bateau b : bateaux) {
            sb.append(b + ",\n\t");
        }

        sb.append(">");

        return sb.toString();
    }
}
