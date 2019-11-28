package main.java.model.fabriqueEpoque.fabriqueEpoque1;

import main.java.model.Arme;

import main.java.model.Bateau;
import main.java.model.fabriqueEpoque.FabriqueEpoque;

public class FabriqueEpoque1 implements FabriqueEpoque {

    final int munitionInitLeger = 12;
    public Bateau creerBateau1 () {
        return new Bateau(1, 5, Arme.LEGER);
    }

    public Bateau creerBateau2 () {

        return new Bateau(2, 10, Arme.LEGER, Arme.LEGER);
    }

    public Bateau creerBateau2V2 () {
        return new Bateau(2, 10, Arme.MOYENNE, null);
    }

    public Bateau creerBateau3 () {
        return new Bateau(3, 15, Arme.LEGER, Arme.MOYENNE, Arme.LEGER);
    }

    public Bateau creerBateau3V2 () {
        return new Bateau(3, 15, Arme.MOYENNE, Arme.MOYENNE, null);
    }

    public Bateau creerBateau3V3 () {
        return new Bateau(3, 15, null, Arme.LOURDE, null);
    }

    public Bateau creerBateau4 () {
        return new Bateau(4, 20, Arme.ATOMIQUE, null, null, null);
    }

    public Bateau creerBateau4V2 () {
        return new Bateau(4, 20, Arme.LOURDE, Arme.MOYENNE, null, null);
    }

    public Bateau creerBateau4V3 () {
        return new Bateau(4, 20, Arme.LEGER, Arme.MOYENNE, Arme.MOYENNE, Arme.LEGER);
    }


}
