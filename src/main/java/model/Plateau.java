package main.java.model;

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
            System.out.println("Placement du bateau " + i);
            System.out.println("Choisissez la coordonnée x :");
            int x = sc.nextInt();
            System.out.println("Choisissez la coordonnée y :");
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
