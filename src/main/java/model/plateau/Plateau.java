package main.java.model.plateau;

import main.java.model.Position;
import main.java.model.plateau.bateau.Arme;
import main.java.model.plateau.bateau.Bateau;

import java.util.*;
import java.util.List;

public class Plateau {

    private List<Bateau> bateaux;

    public Plateau () {
        bateaux = new ArrayList<>();
    }

    public void placement () {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < bateaux.size(); i++) {
            //System.out.println("Placement du bateau " + i);
            //System.out.println("Choisissez la coordonnée x :");
            int x = sc.nextInt();
            //System.out.println("Choisissez la coordonnée y :");
            int y = sc.nextInt();
            bateaux.get(i).setPosition(x, y);
        }
    }

    public void setBateaux(List<Bateau> bateaux) {
        this.bateaux = bateaux;
    }

    public List<Bateau> getBateaux() {
        return bateaux;
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

    public boolean plateauBienForme () {
        boolean[][] casesUtilisees = new boolean[10][10];

        // parcours des bateaux
        for (Bateau b : bateaux) {
            // parcours des positions occupées par le bateau
            for (Position p : b.getPositions()) {
                // si la position est déjà occupée : retourner faux
                if (casesUtilisees[p.getX()][p.getY()]) return false;
                // sinon on dit que cette case est occupée
                casesUtilisees[p.getX()][p.getY()] = true;
            }
        }

        // à ce moment, tout va bien
        return true;
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

    public Arme getRandomArme() {
        Arme arme = null;
        int size = this.bateaux.size();
        Random rand = new Random();
        while(arme==null){
            int int_r = rand.nextInt(size);
            Bateau b = this.getBateaux().get(int_r);
            int size2 = b.getNbCompartiement();
            int int_r2 = rand.nextInt(size2);
            arme = b.getArme(b.getPositions().get(int_r2));
        }
        return arme;
    }
}
