package main.java.model.fabriqueEpoque.fabriqueEpoque2;

import main.java.model.plateau.bateau.Arme;
import main.java.model.plateau.bateau.Bateau;
import main.java.model.fabriqueEpoque.FabriqueEpoque;

import java.util.ArrayList;
import java.util.List;

public class FabriqueEpoque2 implements FabriqueEpoque {

    public List<Bateau> creerFlotte () {
        List<Bateau> bateaux = new ArrayList<>();

        bateaux.add(creerBateau1());
        bateaux.add(creerBateau1());
        bateaux.add(creerBateau1());
        bateaux.add(creerBateau1());

        return bateaux;
    }

    public Bateau creerBateau1 () {
        return new Bateau(1, 15, Arme.LEGER);
    }

    public Bateau creerBateau2 () {
        return new Bateau(2, 30, Arme.LEGER, Arme.LEGER);
    }

    public Bateau creerBateau2V2 () {
        return new Bateau(2, 30, Arme.MOYENNE, null);
    }

    public Bateau creerBateau3 () {
        return new Bateau(3, 45, Arme.LEGER, Arme.MOYENNE, Arme.LEGER);
    }

    public Bateau creerBateau3V2 () {
        return new Bateau(3, 45, Arme.MOYENNE, Arme.MOYENNE, null);
    }

    public Bateau creerBateau3V3 () {
        return new Bateau(3, 45, null, Arme.LOURDE, null);
    }

    public Bateau creerBateau4 () {
        return new Bateau(4, 60, Arme.ATOMIQUE, null, null, null);
    }

    public Bateau creerBateau4V2 () {
        return new Bateau(4, 60, Arme.LOURDE, Arme.MOYENNE, null, null);
    }

    public Bateau creerBateau4V3 () {
        return new Bateau(4, 60,Arme.LEGER, Arme.MOYENNE, Arme.MOYENNE, Arme.LEGER);
    }


}
