package main.java.model;

import main.java.model.Position;
import main.java.model.etat.Epoque;

import java.util.ArrayList;
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
        int x = 0;
        for (int i = 0; i < taille; i++) {
            compartiments.put(new Position(x, 0), new Compartiment(pv, armes[i]));
            x++;
            //todo recuperer les munitions actuels en fonction de l'arme et demander à l'époque
        }
    }

    public void initArmes (Epoque epoque) {
        for (Position p : compartiments.keySet()) {
            if (compartiments.get(p).getArme() == null) continue;
            compartiments.get(p).setMunitionActuel(epoque.getMunition(compartiments.get(p).getArme()));
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bateau<");
        for (Position p : compartiments.keySet()) {
            sb.append(compartiments.get(p).toString() + ",");
        }
        sb.append(">");
        return sb.toString();
    }
}
