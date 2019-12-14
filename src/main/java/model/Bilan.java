package main.java.model;

public class Bilan {

    private EtatTir[] etats;
    private Position[] pattern;

    public Bilan (Position[] pattern) {
        this.pattern = pattern;
    }

    public void setEtat (EtatTir etat, Position position) {
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i].equals(position)) {
                etats[i] = etat;
                return;
            }
        }
    }

}
