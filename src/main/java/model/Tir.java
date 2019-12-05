package main.java.model;

import main.java.model.plateau.bateau.Arme;

/**
 * Classe représentant un tir, sera utilisé pour envoyer l'information du choix de notre tir au joueur adverse
 */
public class Tir {

    private Arme arme;
    private Position cible;

    public Tir (Arme arme, Position cible) {
        this.arme = arme;
        this.cible = cible;
    }

    public Arme getArme() {
        return arme;
    }

    public Position getCible() {
        return cible;
    }
}
