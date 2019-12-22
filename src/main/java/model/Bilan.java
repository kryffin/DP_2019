package main.java.model;

public class Bilan {

    private Position target;
    private EtatTir[] etats;
    private Position[] pattern;

    public Bilan (Position target) {
        this.target = target;
    }

    public void setEtat (EtatTir etat, Position position) {
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i].equals(position)) {
                etats[i] = etat;
                return;
            }
        }
    }

    public EtatTir[] getEtats() {
        return etats;
    }

    public void setEtats(EtatTir... etats) {
        this.etats = etats;
    }

    public Position[] getPattern() {
        return pattern;
    }

    public void setPattern(Position... pattern) {
        this.pattern = pattern;
        etats = new EtatTir[pattern.length];
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }
}
