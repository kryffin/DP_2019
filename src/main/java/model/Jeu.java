package main.java.model;

import main.java.model.etat.Epoque;
import main.java.model.fabriqueEpoque.FabriqueEpoque;

import javax.swing.text.Position;

public class Jeu {

    private Plateau plateau1;
    private Plateau plateau2;
    private Joueurs joueur1;
    private Joueurs joueur2;
    private Epoque epoque;
    private FabriqueEpoque fabriqueEpoque;

    private boolean myTurn;
    private boolean finished;

    // myTurn a true : c'est le tour du joueur1 (humain)
    // myTurn a false : c'est le tour du joueur2 (machine)
    public Arme getArme(Position position){

        Arme arme;
        if (myTurn){

            arme = this.plateau1.getArme(position);
        }else{

            arme = this.plateau2.getArme(position);
        }

        return arme;
    }

}
