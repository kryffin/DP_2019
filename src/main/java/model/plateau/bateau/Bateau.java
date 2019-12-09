package main.java.model.plateau.bateau;

import main.java.model.Position;
import main.java.model.etat.Epoque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bateau {


    private HashMap<Position, Compartiment> compartiments;

    public Bateau(int taille, int pv, Arme... armes) {
        compartiments = new HashMap<>();
        for (int i = 0; i < taille; i++) {
            compartiments.put(new Position(i, 0), new Compartiment(pv, armes[i]));
            //todo recuperer les munitions actuels en fonction de l'arme et demander à l'époque
        }
    }

    /**
     * Déplacement d'un bateau, on met les compartiments l'un à coté de l'autre vers la droite à partir de x,y
     * @param x coordinée x
     * @param y coordinée y
     */
    public void setPosition (int x, int y) {
        //suppression et sauvegarde des compartiments
        List<Compartiment> comps = new ArrayList<>(compartiments.values());
        int size = compartiments.size();

        compartiments = new HashMap<>();

        //replacement des compartiments dans leurs emplacement
        for (int i = 0; i < size; i++) {
            System.out.println("EEEEEEEEE");
            compartiments.put(new Position(x + i, y), comps.get(i));
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

    public Compartiment getCompartiment(Position position) {
        return compartiments.get(position);
    }

    public boolean isDead() {
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
        sb.append("Bateau" + compartiments.size() + "<");
        for (Position p : compartiments.keySet()) {
            sb.append(p + ":" + compartiments.get(p).toString() + ",");
        }
        sb.append(">");
        return sb.toString();
    }

    public int getNbCompartiement() {
        return compartiments.size();
    }
}
