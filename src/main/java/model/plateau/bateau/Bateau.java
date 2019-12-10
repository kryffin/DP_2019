package main.java.model.plateau.bateau;

import main.java.model.Position;
import main.java.model.etat.Epoque;

import java.util.*;

public class Bateau {

    private List<Compartiment> compartiments;

    public Bateau(int taille, int pv, Arme... armes) {
        compartiments = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            compartiments.add(new Compartiment(pv, armes[i],new Position(i, 0)));
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
        int i = 0;
        for(Compartiment c : compartiments){
            Position previousPos = c.getPosition();
            Position newPos = new Position(x+i, y);
            c.setPosition(newPos);
            i++;
        }
    }

    public void initArmes (Epoque epoque) {
        for (Compartiment c : compartiments) {
            if (c.getArme() == null) continue;
            c.setMunitionActuel(epoque.getMunition(c.getArme()));
        }
    }

    public boolean hasCompartiment(Position p) {
        for (Compartiment c : compartiments){
            if (c.getPosition().equals(p)){
                return true;
            }
        }
        return false;
    }

    public Compartiment getCompartiment(Position position) {
        for (Compartiment c : compartiments){
            if (c.getPosition().equals(position)){
                return c;
            }
        }
        return null;
    }

    public boolean isDead() {
        int pv = 0;

        for (Compartiment c : compartiments) {
            pv += c.getPv();
        }

        return pv == 0;
    }

    public Arme getArme(Position position) {

        Arme arme = null;
        for (Compartiment c : compartiments) {
            if (c.getPosition().equals(position)) {
                arme = c.getArme();
            }
        }

        return arme;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bateau" + compartiments.size() + "<");
        for(Compartiment c : compartiments){
            sb.append(c.toString());
        }
        sb.append(">");
        return sb.toString();
    }

    public String simpleToString () {
        return "Bateau" + compartiments.size();
    }

    public int getNbCompartiement() {
        return compartiments.size();
    }

    public List<Position> getPositions() {
        ArrayList res = new ArrayList();
        for(Compartiment c : compartiments){
            res.add(c.getPosition());
        }
        return res;
    }
}
