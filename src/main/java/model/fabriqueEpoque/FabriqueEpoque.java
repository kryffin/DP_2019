package main.java.model.fabriqueEpoque;

import main.java.model.plateau.bateau.Bateau;

import java.util.List;

public interface FabriqueEpoque {

    List<Bateau> creerFlotte ();
    Bateau creerBateau1 ();
    Bateau creerBateau2 ();
    Bateau creerBateau2V2 ();
    Bateau creerBateau3 ();
    Bateau creerBateau3V2 ();
    Bateau creerBateau3V3 ();
    Bateau creerBateau4 ();
    Bateau creerBateau4V2 ();
    Bateau creerBateau4V3 ();

}
